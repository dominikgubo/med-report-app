package org.acme.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.Entity.PromptEntity;
import org.acme.Repository.PromptRepository;

import java.util.List;

@ApplicationScoped
public class PromptService {
    @Inject
    PromptRepository promptRepository;

    public PromptEntity findPromptById(Long id){
        return promptRepository.findById(id);
    }
    public List<PromptEntity> findAll(){
        return promptRepository.findAll().list();
    }
}
