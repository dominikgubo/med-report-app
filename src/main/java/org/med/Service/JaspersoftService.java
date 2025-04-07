package org.med.Service;

import jakarta.enterprise.context.ApplicationScoped;
import net.sf.jasperreports.engine.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.HashMap;

@ApplicationScoped
public class JaspersoftService {
    //TODO; check relative pathing & change folder structure
    private final String REPORT_TEMPLATE_PATH = "reportTemplates/basicTemplate.jrxml";
    private String REPORT_GENERATION_DIRECTORY_PATH = "generatedJasperReports/";

    //TODO; add mapping layer for reportDataset or investigate another approach
    // TODO; add gitignore for generatedJasperReports
    public void generatePdfReport(HashMap<String, Object> reportDataset) throws JRException, NoSuchFileException, IOException {
        InputStream templateStream = getClass().getClassLoader().getResourceAsStream(REPORT_TEMPLATE_PATH);
        JasperReport report = JasperCompileManager.compileReport(templateStream);
        JasperPrint fillManager = JasperFillManager.fillReport(report, reportDataset, new JREmptyDataSource());

        Path reportDir = Paths.get(REPORT_GENERATION_DIRECTORY_PATH);
        Long epochMillis = Instant.now().toEpochMilli();
        Path fullReportPath = reportDir.resolve("report_" + epochMillis + ".pdf");

        JasperExportManager.exportReportToPdfFile(fillManager, fullReportPath.toString());
    }
}
