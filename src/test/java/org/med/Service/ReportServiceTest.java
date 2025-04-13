package org.med.Service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.med.Entity.MedicalParameterEntity;
import org.med.Entity.PatientEntity;
import org.med.Entity.ReportEntity;
import org.med.Repository.MedicalParameterRepository;

import java.util.Collections;

import static org.med.Utils.TestContainerSetup.startTestContainer;
import static org.med.Utils.TestContainerSetup.stopTestContainer;

@QuarkusTest
public class ReportServiceTest {
    @Inject
    ReportService reportService;
    @Inject
    MedicalParameterRepository medicalParameterRepository;
    @Inject
    PatientService patientService;

    @BeforeAll
    public static void beforeAll(){
        startTestContainer();
    }
    @AfterAll
    public static void afterAll(){
        stopTestContainer();
    }

    @Test
    @Description("Happy path: test dummy report creation")
    public void testCreateReport(){
        PatientEntity patientEntity = PatientEntity.builder()
                .firstName("Slavko")
                .lastName("Kockica")
                .bloodType("A+")
                .sex('M')
                .weight(70)
                .age(48)
                .build();

        patientService.createPatient(patientEntity);


        MedicalParameterEntity parameter = MedicalParameterEntity.builder()
                .parameterName("Blood pressure")
                .parameterValue("120/80")
                .build();

        medicalParameterRepository.persist(parameter);

        ReportEntity reportEntity = ReportEntity.builder()
                .patient(patientEntity)
                .medicalParameterList(Collections.singletonList(parameter))
                .build();

        reportService.createReport(reportEntity);
        Assertions.assertEquals(1, reportService.findAll().size());

        ReportEntity report = reportService.findByFirstName("Slavko");
        Assertions.assertNotNull(report);

        String bloodPressureValue = report.getMedicalParameterList().get(0).getParameterValue();
        Assertions.assertEquals("120/80", bloodPressureValue);
    }
}
