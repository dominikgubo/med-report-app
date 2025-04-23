package org.med.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.med.Entity.MedicalParameterEntity;
import org.med.Entity.PromptEntity;
import org.med.Repository.PromptRepository;

import java.util.Arrays;
import java.util.List;

import static org.med.Constants.PromptConstants.BLOOD_REPORT_PROMPT;

@ApplicationScoped
@Transactional
public class PromptService {
    @Inject
    PromptRepository promptRepository;

    public PromptEntity findPromptById(Long id){
        return promptRepository.findById(id);
    }
    public List<PromptEntity> findAll(){
        return promptRepository.findAll().list();
    }
    public void deleteAll() { promptRepository.deleteAll(); }

    public String generateBloodReportPrompt(List<MedicalParameterEntity> medicalParams, Character sexAbbreviation){
        String sex = sexAbbreviation.equals('F') ? "Female" : "Male";
        MedicalParameterEntity sexParam = MedicalParameterEntity.builder()
                .parameterName("Patient sex")
                .parameterValue(sex)
                .build();
        medicalParams.add(sexParam);
        //TODO; for time being leave as is - although we can't pass whole patient info only specifics
        //  GDPR/HIPAA compliant. Also bit silly format
        String medicalParamString = Arrays.toString(medicalParams.toArray());
        return BLOOD_REPORT_PROMPT + medicalParamString;
    }
}
