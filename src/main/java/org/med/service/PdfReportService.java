package org.med.service;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.sf.jasperreports.engine.JRException;
import org.med.model.dto.PDFReportGenerationRequestDto;
import org.med.model.entity.MedicalParameterEntity;
import org.med.model.entity.PatientEntity;
import org.med.model.entity.ReportEntity;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PdfReportService {
    @Inject
    ReportService reportService;
    @Inject
    MedicalParameterService medicalParameterService;
    @Inject
    JaspersoftService jaspersoftService;
    @Inject
    OpenAiService openAiService;
    @Inject
    PromptService promptService;
    @Inject
    EmailService emailService;

    private String REPORT_GENERATION_DIRECTORY_PATH = "generatedJasperReports/";

    public void generateReportPdf(PDFReportGenerationRequestDto request) throws JRException, IOException {
        Long startTime = Instant.now().toEpochMilli();
        Log.infov("Started PDF generation of report: {0} ");

        Long reportId = request.getReportId();
        ReportEntity report = reportService.findById(reportId);
        String reportType = fetchReportType(report);
        PatientEntity patient = report.getPatient();
        Character patientSex = patient.getSex();
        List<MedicalParameterEntity> medicalParamValues = medicalParameterService.findByReportId(reportId);

        String prompt = promptService.generateBloodReportPrompt(medicalParamValues, patientSex, reportType);
        String aiResponse = openAiService.generatePromptResponse(prompt);
        Log.infov("PDF Generation AI Delta since execution: {0}s for report: {1}", Instant.now().toEpochMilli() - startTime, reportId);

        HashMap<String, Object> parameterMap = getPopulatedInputParameterMap(reportType, aiResponse, reportId);
        Path reportSavePath = getReportSavePath();
        jaspersoftService.generatePdfReport(parameterMap, reportSavePath);

        if (request.getEmailConfig() != null){
            emailService.sendReportToPatientEmail(patient, reportSavePath.toString(), request.getEmailConfig());
        }

        Log.infov("PDF Generation Jasper Delta since execution: {0}s for report: {1}", Instant.now().toEpochMilli() - startTime, reportId);
    }

    private String fetchReportType(ReportEntity report){
        Optional<String> optionalReportType = report.getReportType();
        return optionalReportType.orElse("Undefined");
    }

    private HashMap<String, Object> getPopulatedInputParameterMap(String reportType, String aiResponse, Long reportId){
        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("reportType", reportType);
        parameterMap.put("promptResponse", aiResponse);
        parameterMap.put("reportId", reportId);

        return parameterMap;
    }

    private Path getReportSavePath(){
        Path reportDir = Paths.get(REPORT_GENERATION_DIRECTORY_PATH);
        Long epochMillis = Instant.now().toEpochMilli();
        Path fullReportPath = reportDir.resolve("report_" + epochMillis + ".pdf");

        return fullReportPath;
    }
}
