package org.acme.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.Entity.ReportEntity;
import org.acme.Service.ReportService;

import java.util.List;

@Path("/report")
public class ReportController {
    @Inject
    ReportService reportService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/allReports")
    public List<ReportEntity> getAllReports(){
        return reportService.findAll();
    }
}
