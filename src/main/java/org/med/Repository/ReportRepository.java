package org.med.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.med.Entity.ReportEntity;
@ApplicationScoped
public class ReportRepository implements PanacheRepository<ReportEntity> {
    public ReportEntity findByPatientFirstName(String firstName){
        return find("patient.firstName", firstName).firstResult();
    }
}
