package org.med.service;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.sf.jasperreports.engine.JRException;
import org.med.entity.MedicalParameterEntity;
import org.med.entity.ReportEntity;

import java.io.IOException;
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

    public void generateReportPdf(Long reportId) throws JRException, IOException {
        Long startTime = Instant.now().toEpochMilli();
        Log.infov("Started PDF generation of report: {0} ");

        ReportEntity report = reportService.findById(reportId);
        String reportType = fetchReportType(report);
        Character patientSex = report.getPatient().getSex();
        List<MedicalParameterEntity> medicalParamValues = medicalParameterService.findByReportId(reportId);

        String prompt = promptService.generateBloodReportPrompt(medicalParamValues, patientSex, reportType);
        String aiResponse = openAiService.generatePromptResponse(prompt);
        Log.infov("PDF Generation AI Delta since execution: {0}s for report: {1}", Instant.now().getEpochSecond()-startTime, reportId);

        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("reportType", reportType);
        parameterMap.put("promptResponse", aiResponse);
        parameterMap.put("reportId", reportId);
        jaspersoftService.generatePdfReport(parameterMap);

        Log.infov("PDF Generation Jasper Delta since execution: {0}s for report: {1}", Instant.now().toEpochMilli()-startTime, reportId);
    }

    private String fetchReportType(ReportEntity report){
        Optional<String> optionalReportType = report.getReportType();
        return optionalReportType.orElse("Undefined");
    }
}
