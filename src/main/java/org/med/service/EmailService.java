package org.med.service;

import io.quarkus.logging.Log;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.med.model.dto.EmailConfigurationDto;
import org.med.model.entity.PatientEntity;
import org.med.utils.FileUtils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@ApplicationScoped
public class EmailService {
    @Inject
    Mailer mailer;

    private static final String MAIL_SUBJECT = "Medical analysis - ";

    public void sendReportToPatientEmail(PatientEntity patient, String reportFilePathing, EmailConfigurationDto emailConfig){
        Predicate<EmailConfigurationDto> isEmailConfigInvalid = cfg -> cfg.isSendToPatientsStoredEmailAddress() == false && cfg.getReceivingAddresses().isEmpty();
        if(isEmailConfigInvalid.test(emailConfig)){
            throw new IllegalArgumentException("If email configuration is enabled - receiving address is needed");
        }
        if(emailConfig.isSendToPatientsStoredEmailAddress() && !checkIfPatientHasStoredAddress(patient)){
            throw new IllegalArgumentException("If report is being sent to the stored patient email - patient email can't be null");
        }

        try {
            String patientFullName = patient.getFirstName().concat(" ").concat(patient.getLastName());
            sendEmailWithPdf(emailConfig, patientFullName, reportFilePathing, patient);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Log.info("PDF report has been sent to the patients email address");
    }

    private void sendEmailWithPdf(EmailConfigurationDto emailConfig, String name, String pdfPathing, PatientEntity patient) throws IOException {
        String mailBody = MessageFormat.format("Dear {0}, you can find your medical report analysis in the attachments section of this email.", name);
        mailBody = emailConfig.getAdditionalNotes() != null ? mailBody.concat("\n\n").concat(emailConfig.getAdditionalNotes()) : mailBody;
        byte[] pdfReport = FileUtils.readFile(pdfPathing);

        List<String> receiverAddresses = emailConfig.getReceivingAddresses() == null ? new ArrayList<>() : emailConfig.getReceivingAddresses();

        if(emailConfig.isSendToPatientsStoredEmailAddress()){
            receiverAddresses.add(patient.getEmail());
        }

        mailer.send(
                Mail.withText(patient.getEmail(), getEmailSubject(emailConfig, name), mailBody)
                        .addAttachment(MAIL_SUBJECT + name, pdfReport, "application/pdf")
                        .setFrom("placeholder-clinic@gmail.com")
                        .setTo(receiverAddresses)
                        .setCc(emailConfig.getCarbonCopyAddresses() != null && !emailConfig.getCarbonCopyAddresses().isEmpty() ? emailConfig.getCarbonCopyAddresses() : Collections.EMPTY_LIST)
        );
    }

    private String getEmailSubject(EmailConfigurationDto cfg, String patientFullName){
        if(cfg.getSubject() != null && !cfg.getSubject().isEmpty()) { return cfg.getSubject(); }
        return MAIL_SUBJECT + patientFullName;
    }

    private Boolean checkIfPatientHasStoredAddress(PatientEntity patient){
        return patient.getEmail() != null && !patient.getEmail().isEmpty();
    }


}
