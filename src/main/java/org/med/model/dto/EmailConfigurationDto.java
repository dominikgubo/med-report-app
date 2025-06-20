package org.med.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.smallrye.common.constraint.NotNull;

import java.util.List;

public class EmailConfigurationDto {
    @JsonProperty("toPatientsExistingAddress")
    @NotNull
    private boolean sendToPatientsStoredEmailAddress;
    private List<String> receivingAddresses;
    private List<String> carbonCopyAddresses;
    private String subject;
    private String additionalNotes;

    public boolean isSendToPatientsStoredEmailAddress() {
        return sendToPatientsStoredEmailAddress;
    }

    public List<String> getReceivingAddresses() {
        return receivingAddresses;
    }

    public List<String> getCarbonCopyAddresses() {
        return carbonCopyAddresses;
    }

    public String getSubject() {
        return subject;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }
}

