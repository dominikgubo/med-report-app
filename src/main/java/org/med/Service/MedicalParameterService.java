package org.med.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.med.Entity.MedicalParameterEntity;
import org.med.Repository.MedicalParameterRepository;

import java.util.List;

@ApplicationScoped
@Transactional
public class MedicalParameterService {
    private final MedicalParameterRepository medicalParameterRepository;
    @Inject
    MedicalParameterService(MedicalParameterRepository medicalParameterRepository){this.medicalParameterRepository = medicalParameterRepository;}
    public void deleteAll(){
        medicalParameterRepository.deleteAll();
    }
    public List<MedicalParameterEntity> findByReportId(Long reportId) { return medicalParameterRepository.find("reportId.id", reportId).list(); }
}
