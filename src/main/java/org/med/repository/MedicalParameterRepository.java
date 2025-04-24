package org.med.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.med.entity.MedicalParameterEntity;

@ApplicationScoped
@Transactional
public class MedicalParameterRepository implements PanacheRepository<MedicalParameterEntity> {
}
