package org.med.Controller;

import io.quarkus.logging.Log;
import io.vertx.ext.web.handler.HttpException;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.med.Entity.ReportEntity;
import org.med.Repository.MedicalParameterRepository;
import org.med.Service.PatientService;
import org.med.Service.ReportService;

import java.util.List;

@Path("/report")
public class ReportController {
    @Inject
    ReportService reportService;
    @Inject
    PatientService patientService;
    @Inject
    MedicalParameterRepository medicalParameterRepository;

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
}
