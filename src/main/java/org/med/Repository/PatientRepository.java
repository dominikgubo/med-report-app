package org.med.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.med.Entity.PatientEntity;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<PatientEntity> {
}
