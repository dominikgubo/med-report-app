package org.med.Utils;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.med.Service.MedicalParameterService;
import org.med.Service.PatientService;
import org.med.Service.PromptService;
import org.med.Service.ReportService;
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
        reportService.deleteAll();
        medicalParameterService.deleteAll();
        patientService.deleteAll();
        promptService.deleteAll();
    }
}
