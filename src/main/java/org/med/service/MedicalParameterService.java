package org.med.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.med.entity.MedicalParameterEntity;
import org.med.repository.MedicalParameterRepository;

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
    public List<MedicalParameterEntity> findByReportId(Long reportId) { return medicalParameterRepository.find("report.id", reportId).list(); }
}
