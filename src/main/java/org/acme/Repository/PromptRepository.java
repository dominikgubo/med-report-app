package org.acme.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.Entity.PromptEntity;

@ApplicationScoped
public class PromptRepository implements PanacheRepository {
    public PromptEntity findById(Long id){
        return findById(id);
    }
}
