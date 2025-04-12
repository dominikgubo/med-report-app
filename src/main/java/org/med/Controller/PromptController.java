package org.med.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.med.Entity.PromptEntity;
import org.med.Service.PromptService;

import java.util.List;

@Path("/prompt")
public class PromptController {
    @Inject
    PromptService promptService;
    @GET
    @Path("/all")
    public List<PromptEntity> getAllPrompts(){
        return promptService.findAll();
    }
}
