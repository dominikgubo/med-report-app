package org.med.utils;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.med.service.MedicalParameterService;
import org.med.service.PatientService;
import org.med.service.PromptService;
import org.med.service.ReportService;
@Singleton
public class DBCleanUtil {
    @Inject
    private PatientService patientService;
    @Inject
    private ReportService reportService;
    @Inject
    private MedicalParameterService medicalParameterService;
    @Inject
    private PromptService promptService;

    public void cleanDB() {
        medicalParameterService.deleteAll();
        reportService.deleteAll();
        patientService.deleteAll();
        promptService.deleteAll();
    }
}
