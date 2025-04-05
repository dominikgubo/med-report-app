package org.med.ValidatorTest;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.med.Entity.PatientEntity;
import org.med.Service.PatientService;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

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
    public void testValidBloodTypesPatientInsertion() {

        PatientEntity patientWithBloodGroupA = PatientEntity.builder()
                .firstName("Arthur")
                .lastName("Doe")
                .bloodType("A+")
                .build();

        PatientEntity patientWithBloodGroupAB = PatientEntity.builder()
                .firstName("Malcolm")
                .lastName("Woods")
                .bloodType("AB-")
                .build();

        PatientEntity noBloodGroupPatient = PatientEntity.builder()
                .firstName("Sinan")
                .lastName("Sak")
                .build();

        patientService.createPatient(patientWithBloodGroupA);
        patientService.createPatient(patientWithBloodGroupAB);
        patientService.createPatient(noBloodGroupPatient);

        List<PatientEntity> patientList = patientService.getAllPatients();
        Assertions.assertEquals(3, patientList.size());
    }

    @Test
    @Description("Sad path: Invalid patient blood types causing exception")
    public void testInvalidBloodTypePatientInsertion() {
        PatientEntity patientWithInvalidBloodGroup1 = PatientEntity.builder()
                .firstName("John")
                .lastName("Milich")
                .bloodType("C+")
                .build();


        Assertions.assertThrows(ValidationException.class, () -> {
            patientService.createPatient(patientWithInvalidBloodGroup1);
        });

        PatientEntity patientWithInvalidBloodGroup2 = PatientEntity.builder()
                .firstName("Bejnamin")
                .lastName("McDonald")
                .bloodType("AB/")
                .build();

        Assertions.assertThrows(ValidationException.class, () -> {
            patientService.createPatient(patientWithInvalidBloodGroup2);
        });

        Assertions.assertEquals(0, patientService.getAllPatients().size());
    }
}