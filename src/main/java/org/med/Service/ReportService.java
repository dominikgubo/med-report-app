package org.med.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.med.Entity.ReportEntity;
import org.med.Repository.ReportRepository;

import java.util.List;

@ApplicationScoped
public class ReportService {
    @Inject
    ReportRepository reportRepository;

    public ReportEntity findByFirstName(String firstName){
        return reportRepository.findByFirstName(firstName);
    }
    public List<ReportEntity> findAll(){
        return reportRepository.findAll().list();
    }

}
