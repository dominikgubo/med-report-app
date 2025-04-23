package org.med.Controller;

import io.quarkus.logging.Log;
import io.vertx.ext.web.handler.HttpException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import net.sf.jasperreports.engine.JRException;
import org.med.Entity.MedicalParameterEntity;
import org.med.Entity.ReportEntity;
import org.med.Service.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Path("/report")
public class ReportController {
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public List<ReportEntity> getAllReports() {
        return reportService.findAll();
    }

    @POST
    @Path("/create")
    public void createReport(ReportEntity report) {
        try {
            reportService.create(report);
        } catch (HttpException e) {
            Log.errorv("Report creation not successful; {0}", e);
        }
    }

    @POST
    @Path("/blood/generate")
    public void generateJaspersoftBloodReportPdf(@QueryParam("reportId") Long id) throws JRException, IOException {
        String reportType = "Blood analysis";
        ReportEntity report = reportService.findById(id);
        Character patientSex = report.getPatient().getSex();
        List<MedicalParameterEntity> medicalParamValues = medicalParameterService.findByReportId(id);

        String prompt = promptService.generateBloodReportPrompt(medicalParamValues, patientSex);
        String aiResponse = openAiService.generatePromptResponse(prompt);

        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("reportType", reportType);
        parameterMap.put("promptResponse", aiResponse);
        jaspersoftService.generatePdfReport(parameterMap);
    }
}
