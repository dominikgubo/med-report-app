package org.med.service;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.sf.jasperreports.engine.*;
import org.med.utils.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

@ApplicationScoped
public class JaspersoftService {
    @Inject
    AgroalDataSource dataSource;
    //TODO; check relative pathing & change folder structure
    private final String REPORT_TEMPLATE_PATH = "reportTemplates/medicalReportTemplate.jrxml";

    //TODO; add mapping layer for reportDataset or investigate another approach
    // TODO; add gitignore for generatedJasperReports
    public void generatePdfReport(HashMap<String, Object> reportDataset, Path reportSavePath) throws JRException, NoSuchFileException, IOException {

        InputStream templateStream = FileUtils.getInputStream(REPORT_TEMPLATE_PATH);
        JasperReport report = JasperCompileManager.compileReport(templateStream);
        try(Connection conn = dataSource.getConnection()){
            JasperPrint fillManager = JasperFillManager.fillReport(report, reportDataset, conn);
            JasperExportManager.exportReportToPdfFile(fillManager, reportSavePath.toString());
        } catch (SQLException e){
        }
    }
}
