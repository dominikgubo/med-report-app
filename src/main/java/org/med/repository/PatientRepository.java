package org.med.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.med.entity.PatientEntity;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<PatientEntity> {
}
