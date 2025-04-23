package org.med.Service;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.sf.jasperreports.engine.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;

@ApplicationScoped
public class JaspersoftService {
    @Inject
    AgroalDataSource dataSource;
    //TODO; check relative pathing & change folder structure
    private final String REPORT_TEMPLATE_PATH = "reportTemplates/initialBloodReportSQL.jrxml";
    private String REPORT_GENERATION_DIRECTORY_PATH = "generatedJasperReports/";

    //TODO; add mapping layer for reportDataset or investigate another approach
    // TODO; add gitignore for generatedJasperReports
    public void generatePdfReport(HashMap<String, Object> reportDataset) throws JRException, NoSuchFileException, IOException {
        InputStream templateStream = getClass().getClassLoader().getResourceAsStream(REPORT_TEMPLATE_PATH);
        JasperReport report = JasperCompileManager.compileReport(templateStream);
        try(Connection conn = dataSource.getConnection()){
            JasperPrint fillManager = JasperFillManager.fillReport(report, reportDataset, conn);

            Path reportDir = Paths.get(REPORT_GENERATION_DIRECTORY_PATH);
            Long epochMillis = Instant.now().toEpochMilli();
            Path fullReportPath = reportDir.resolve("report_" + epochMillis + ".pdf");

            JasperExportManager.exportReportToPdfFile(fillManager, fullReportPath.toString());
        } catch (SQLException e){
        }
    }
}
