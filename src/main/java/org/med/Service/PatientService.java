package org.med.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.med.Entity.PatientEntity;
import org.med.Repository.PatientRepository;

import java.util.List;

@ApplicationScoped
@Transactional
public class PatientService {
    private final PatientRepository patientRepository;
    @Inject
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    public void create(PatientEntity patient){
        patientRepository.persist(patient);
    }
    public List<PatientEntity> getAll(){
        return patientRepository.findAll().list();
    }

    public void deleteAll() {
        patientRepository.deleteAll();
    }
}
