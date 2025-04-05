package org.acme.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.Entity.ReportEntity;
@ApplicationScoped
public class ReportRepository implements PanacheRepository<ReportEntity> {
    public ReportEntity findByFirstName(String firstName){
        return find("firstName", firstName).firstResult();
    }
}
