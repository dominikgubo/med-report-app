package org.med.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.med.entity.ReportEntity;

import java.util.List;

@ApplicationScoped
public class ReportRepository implements PanacheRepository<ReportEntity> {
    public List<ReportEntity> findByPatientFirstName(String firstName){
        return find("patient.firstName", firstName).list();
    }
}
