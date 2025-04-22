package org.med.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.med.Entity.PromptEntity;
import org.med.Service.OpenAiService;
import org.med.Service.PromptService;

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
