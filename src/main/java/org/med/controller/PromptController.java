package org.med.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.med.entity.PromptEntity;
import org.med.service.OpenAiService;
import org.med.service.PromptService;

import java.util.List;

@Path("/prompt")
public class PromptController {
    @Inject
    PromptService promptService;
    @Inject
    OpenAiService openAiService;
    @GET
    @Path("/all")
    public List<PromptEntity> getAllPrompts(){
        return promptService.findAll();
    }

    @GET
    @Path("/chat")
    public String getPromptResponse(@QueryParam("prompt") String prompt){
        return openAiService.generatePromptResponse(prompt);
    }
}
