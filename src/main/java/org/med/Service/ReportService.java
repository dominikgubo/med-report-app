package org.med.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.med.Entity.ReportEntity;
import org.med.Repository.ReportRepository;

import java.util.List;

@ApplicationScoped
@Transactional
public class ReportService {
    @Inject
    ReportRepository reportRepository;

    public ReportEntity findByFirstName(String firstName) {
        return reportRepository.findByPatientFirstName(firstName);
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
