package org.med.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.med.Entity.MedicalParameterEntity;

@ApplicationScoped
@Transactional
public class MedicalParameterRepository implements PanacheRepository<MedicalParameterEntity> {
}
