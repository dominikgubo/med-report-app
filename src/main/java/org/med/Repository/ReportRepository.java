package org.med.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.med.Entity.ReportEntity;

import java.util.List;

@ApplicationScoped
public class ReportRepository implements PanacheRepository<ReportEntity> {
    public List<ReportEntity> findByPatientFirstName(String firstName){
        return find("patient.firstName", firstName).list();
    }
}
