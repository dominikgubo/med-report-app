package org.med.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.sf.jasperreports.engine.JRException;
import org.med.entity.MedicalParameterEntity;
import org.med.entity.ReportEntity;

import java.io.IOException;
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

    public void generateReportPdf(Long reportId) throws JRException, IOException {
        ReportEntity report = reportService.findById(reportId);
        String reportType = fetchReportType(report);
        Character patientSex = report.getPatient().getSex();
        List<MedicalParameterEntity> medicalParamValues = medicalParameterService.findByReportId(reportId);

        String prompt = promptService.generateBloodReportPrompt(medicalParamValues, patientSex, reportType);
        String aiResponse = openAiService.generatePromptResponse(prompt);

        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("reportType", reportType);
        parameterMap.put("promptResponse", aiResponse);
        jaspersoftService.generatePdfReport(parameterMap);
    }

    private String fetchReportType(ReportEntity report){
        Optional<String> optionalReportType = report.getReportType();
        return optionalReportType.orElse("Undefined");
    }
}
