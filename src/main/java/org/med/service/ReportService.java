package org.med.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.med.entity.ReportEntity;
import org.med.repository.ReportRepository;

import java.util.List;

@ApplicationScoped
@Transactional
public class ReportService {
    @Inject
    ReportRepository reportRepository;

    public List<ReportEntity> findByFirstName(String firstName) {
        return reportRepository.findByPatientFirstName(firstName);
    }

    public ReportEntity findById(Long id) {
        return reportRepository.findById(id);
    }

    public List<ReportEntity> findAll() {
        return reportRepository.findAll().list();
    }

    public void create(ReportEntity entity) {
        reportRepository.persist(entity);
    }

    public void deleteAll() {
        reportRepository.deleteAll();
    }

}
