package org.med.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.med.Repository.MedicalParameterRepository;

@ApplicationScoped
@Transactional
public class MedicalParameterService {
    @Inject
    MedicalParameterRepository medicalParameterRepository;

    public void deleteAll(){
        medicalParameterRepository.deleteAll();
    }
}
