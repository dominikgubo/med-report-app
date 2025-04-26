package org.med.validator;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.med.entity.PatientEntity;
import org.med.service.PatientService;
import org.med.utils.DBCleanUtil;
import org.med.utils.DBTestContainerSetup;

import java.util.List;

@QuarkusTest
@QuarkusTestResource(DBTestContainerSetup.class)
public class ValidBloodTypeValidatorTest {

    @Inject
    private PatientService patientService;
    @Inject
    DBCleanUtil dbCleanUtil;

    @BeforeEach
    public void cleanDB(){
        dbCleanUtil.cleanDB();
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

        patientService.create(patientWithBloodGroupA);
        patientService.create(patientWithBloodGroupAB);
        patientService.create(noBloodGroupPatient);

        List<PatientEntity> patientList = patientService.getAll();
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
            patientService.create(patientWithInvalidBloodGroup1);
        });

        PatientEntity patientWithInvalidBloodGroup2 = PatientEntity.builder()
                .firstName("Bejnamin")
                .lastName("McDonald")
                .bloodType("AB/")
                .build();

        Assertions.assertThrows(ValidationException.class, () -> {
            patientService.create(patientWithInvalidBloodGroup2);
        });

        Assertions.assertEquals(0, patientService.getAll().size());
    }
}