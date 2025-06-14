package org.med.model.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PDFReportGenerationRequestDto {
    @NotNull
    @NotEmpty
    private Long reportId;
    private EmailConfigurationDto emailConfig;

    // TODO; implement functionality on this, currently not prio
    // 1 - brief, 2 - normal and default, 3 - complex
    @Min(1) @Max(3)
    private Long analysisComplexityLevel;

    // TODO; add functionality of analysis through other patient records
    private Boolean useExternalPatientContext;

    public EmailConfigurationDto getEmailConfig() {
        return emailConfig;
    }

    public Long getAnalysisComplexityLevel() {
        return analysisComplexityLevel;
    }

    public Boolean getUseExternalPatientContext() {
        return useExternalPatientContext;
    }

    public Long getReportId() {
        return reportId;
    }
}
