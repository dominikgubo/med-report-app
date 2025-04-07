package org.med.Controller;

import io.quarkus.logging.Log;
import io.vertx.ext.web.handler.HttpException;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import net.sf.jasperreports.engine.JRException;
import org.med.Entity.ReportEntity;
import org.med.Service.JaspersoftService;
import org.med.Service.ReportService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Path("/report")
public class ReportController {
    @Inject
    ReportService reportService;

    @Inject
    JaspersoftService jaspersoftService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public List<ReportEntity> getAllReports(){
        return reportService.findAll();
    }

    @POST
    @Path("/create")
    public void createReport(ReportEntity report){
        try {
            reportService.createReport(report);
        } catch (HttpException e){
            Log.errorv("Report creation not successful; {0}", e);
        }
    }

    @POST
    @Path("/generatePdf")
    public void generateJaspersoftReportPdf() throws JRException, IOException {
        // TODO; shift logic arrange project structure
        HashMap<String, Object> testHashMap = new HashMap<>();
        testHashMap.put("reportType", "Blood examination");
        jaspersoftService.generatePdfReport(testHashMap);
    }
}
