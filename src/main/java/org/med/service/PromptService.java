package org.med.service;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.med.entity.MedicalParameterEntity;
import org.med.entity.PromptEntity;
import org.med.entity.ReportEntity;
import org.med.repository.PromptRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.med.constants.PromptConstants.*;

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

    public String generateBloodReportPrompt(List<MedicalParameterEntity> medicalParams, Character sexAbbreviation, String reportType){
        String sex = sexAbbreviation.equals('F') ? "Female" : "Male";

        if(medicalParams.isEmpty()){
            throw new IllegalArgumentException("Report can't be generated if Medical Parameters are empty");
        }
        ReportEntity usedReport = medicalParams.get(0).getReport();

        MedicalParameterEntity sexParam = MedicalParameterEntity.builder()
                .parameterName("Patient sex")
                .parameterValue(sex)
                .report(usedReport)
                .build();
        medicalParams.add(sexParam);
        String medicalParamString = Arrays.toString(medicalParams.toArray());

        return getPromptSpecificToReportType(reportType) + medicalParamString;
    }

    private String getPromptSpecificToReportType(String reportType){
        switch (reportType.toLowerCase()){
            case "blood":
                return BLOOD_REPORT_PROMPT;
            case "hormone":
                return HORMONE_REPORT_PROMPT;
            case "urine":
                return URINE_REPORT_PROMPT;
        }
        return UNDEFINED_REPORT_PROMPT;
    }
}
