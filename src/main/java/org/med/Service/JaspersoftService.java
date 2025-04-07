package org.med.Service;

import net.sf.jasperreports.engine.*;

import java.util.HashMap;

public class JaspersoftService {
    //TODO; check relative pathing
    private String REPORT_GENERATION_DIRECTORY_PATH = "\\MedicalApp\\generatedReports";
    //TODO; add mapping layer for reportDataset or investigate another approach
    public void generatePdfReport(HashMap<String, Object> reportDataset) throws JRException {
        JasperReport report = JasperCompileManager.compileReport(REPORT_GENERATION_DIRECTORY_PATH);
        JasperPrint writeManager = JasperFillManager.fillReport(report, reportDataset, new JREmptyDataSource());
    }
}
