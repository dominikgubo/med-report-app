package org.med.ValidatorTest;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.med.Entity.PatientEntity;
import org.med.Service.PatientService;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ValidBloodTypeValidatorTest {

    @Inject
    private PatientService patientService;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withPassword("root")
            .withUsername("root");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    @Description("Happy path: Valid patient blood types")
    public void testPatientServiceInjection() {

        PatientEntity patientWithBloodGroup = PatientEntity.builder()
                .firstName("Arthur")
                .lastName("Doe")
                .bloodType("A+")
                .build();

        PatientEntity noBloodGroupPatient = PatientEntity.builder()
                .firstName("Sinan")
                .lastName("Sak")
                .build();

        patientService.createPatient(patientWithBloodGroup);
        patientService.createPatient(noBloodGroupPatient);

        List<PatientEntity> patientList = patientService.getAllPatients();
        Assertions.assertEquals(2, patientList.size());
    }
}