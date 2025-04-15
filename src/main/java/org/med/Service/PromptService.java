package org.med.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.med.Entity.PromptEntity;
import org.med.Repository.PromptRepository;

import java.util.List;

@ApplicationScoped
@Transactional
public class PromptService {
    @Inject
    PromptRepository promptRepository;

    public PromptEntity findPromptById(Long id){
        return promptRepository.findById(id);
    }
    public List<PromptEntity> findAll(){
        return promptRepository.findAll().list();
    }

    public void deleteAll() { promptRepository.deleteAll(); }
}
