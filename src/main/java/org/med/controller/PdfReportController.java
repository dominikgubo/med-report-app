package org.med.controller;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import net.sf.jasperreports.engine.JRException;
import org.med.service.PdfReportService;

import java.io.IOException;
@ApplicationScoped
@Path("/pdf")
public class PdfReportController {
    @Inject
    PdfReportService pdfReportService;
    @POST
    @Path("/generate")
    public void generateJaspersoftBloodReportPdf(@QueryParam("reportId") Long id) throws JRException, IOException {
        // TODO; add query param flags which would indicate the complexity level of AI response
        // TODO; add a query param flag for patient measurements to be included
        pdfReportService.generateReportPdf(id);
        Log.info("PDF Report has been successfully generated for reportId: " + id);
    }
}
