package org.med.Service;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.med.Entity.MedicalParameterEntity;
import org.med.Entity.PatientEntity;
import org.med.Entity.ReportEntity;
import org.med.Repository.MedicalParameterRepository;
import org.med.Utils.DBCleanUtil;
import org.med.Utils.DBTestContainerSetup;

import java.util.Collections;
@QuarkusTest
@QuarkusTestResource(DBTestContainerSetup.class)
public class ReportServiceTest {
    @Inject
    ReportService reportService;
    @Inject
    MedicalParameterRepository medicalParameterRepository;
    @Inject
    PatientService patientService;
    @Inject
    DBCleanUtil dbCleanUtil;

    @BeforeEach
    public void cleanDB(){
        dbCleanUtil.cleanDB();
    }

    @Test
    @Description("Happy path: test dummy report creation")
    public void testCreateReport(){
        PatientEntity patientEntity = PatientEntity.builder()
                .firstName("Slavko")
                .lastName("Kockica")
                .bloodType("A+")
                .build();

        patientService.create(patientEntity);


        MedicalParameterEntity parameter = MedicalParameterEntity.builder()
                .parameterName("Blood pressure")
                .parameterValue("120/80")
                .build();

        medicalParameterRepository.persist(parameter);

        ReportEntity reportEntity = ReportEntity.builder()
                .patient(patientEntity)
                .medicalParameterList(Collections.singletonList(parameter))
                .build();

        reportService.create(reportEntity);
        Assertions.assertEquals(1, reportService.findAll().size());

        ReportEntity report = reportService.findByFirstName("Slavko").get(0);
        Assertions.assertNotNull(report);

        String bloodPressureValue = report.getMedicalParameterList().get(0).getParameterValue();
        Assertions.assertEquals("120/80", bloodPressureValue);
    }
}
