package org.med.constants;

public class PromptConstants {
    //TODO; need to differentiate prompts between different reportType prompts, for time being copy-pasted
    private static final String PROMPT_COMMON_INSTRUCTIONS = "summarize whether the results are okay for patient as a whole. " +
            "Comment out the specific individual values if they are out of bounds. Don't go in the extremes. " +
            "If any values are out of the bounds suggest how this could be changed, and suggest whether this could cause some problems. ";

    private static final String PROMPT_REFERENCE_RANGE_INSTRUCTION = "If the reference range isn't provided in the medical parameters," +
            " fetch the reference range yourself as reliably as possible. ";

    public static final String BLOOD_REPORT_PROMPT = "With given blood examination data, " +
            PROMPT_COMMON_INSTRUCTIONS + "Your response should always start with sentence " +
            "'Based on the provided blood analysis data for the patient, " +
            "here is a summary of the results:'. " + PROMPT_REFERENCE_RANGE_INSTRUCTION +
            "Measured data:";

    public static final String URINE_REPORT_PROMPT = "With given urine examination data, " +
            PROMPT_COMMON_INSTRUCTIONS + "Your response should always start with sentence " +
            "'Based on the provided urine analysis data for the patient, " +
            "here is a summary of the results:'. " + PROMPT_REFERENCE_RANGE_INSTRUCTION +
            "Measured data:";

    public static final String HORMONE_REPORT_PROMPT = "With given hormone examination data, " +
            PROMPT_COMMON_INSTRUCTIONS + "Your response should always start with sentence " +
            "'Based on the provided hormone analysis data for the patient, " +
            "here is a summary of the results:'. " + PROMPT_REFERENCE_RANGE_INSTRUCTION +
            "Measured data:";

    public static final String UNDEFINED_REPORT_PROMPT = "With not specifically defined examination data, " +
            PROMPT_COMMON_INSTRUCTIONS + "Your response should always start with sentence " +
            "'Based on the provided data for the patient, " +
            "here is a summary of the results:'. " + PROMPT_REFERENCE_RANGE_INSTRUCTION +
            "Measured data:";
}
