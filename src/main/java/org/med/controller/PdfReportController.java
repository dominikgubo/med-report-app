package org.med.controller;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;
import org.med.model.dto.PDFReportGenerationRequestDto;
import org.med.service.PdfReportService;

import java.io.IOException;
import java.net.HttpURLConnection;

@ApplicationScoped
@Path("/pdf")
public class PdfReportController {
    @Inject
    PdfReportService pdfReportService;
    @POST
    @Path("/generate")
    public Response generateJaspersoftBloodReportPdf(PDFReportGenerationRequestDto request) throws JRException, IOException {
        pdfReportService.generateReportPdf(request);
        Log.info("PDF Report has been successfully generated for reportId: " + request.getReportId());

        return Response.ok("Report generated").status(HttpURLConnection.HTTP_CREATED).build();
    }
}
