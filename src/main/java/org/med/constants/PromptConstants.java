package org.med.constants;

public class PromptConstants {
    //TODO; need to differentiate prompts between different reportType prompts, for time being copy-pasted
    private static final String PROMPT_COMMON_INSTRUCTIONS = "summarize whether the results are good for patient as a whole," +
            "if not go more in detail. Comment out the individual medical parameter values only if they are " +
            "out of bounds or concerning. If the individual value is not out of bounds or concerning skip it. " +
            "Don't go to the extremes. If any values are out of the bounds suggest how they could be " +
            "changed (e.g. food, exercise, drugs and similar), suggestion which is well known for that parameter, " +
            "essentially something real Doctor would suggest, but don't go to the extremes. " +
            "Your output response needs to be plain-text without any unnecessary symbols, such as '*'. ";

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

    public static final String SUGAR_REPORT_PROMPT = "With given sugar examination data, " +
            PROMPT_COMMON_INSTRUCTIONS + "Your response should always start with sentence " +
            "'Based on the provided sugar analysis data for the patient, " +
            "here is a summary of the results:'. " + PROMPT_REFERENCE_RANGE_INSTRUCTION +
            "Measured data:";

    public static final String BLOOD_PRESSURE_REPORT_PROMPT = "With given sugar examination data, " +
            PROMPT_COMMON_INSTRUCTIONS + "Your response should always start with sentence " +
            "'Based on the provided sugar analysis data for the patient, " +
            "here is a summary of the results:'. " + PROMPT_REFERENCE_RANGE_INSTRUCTION +
            "Measured data:";

    public static final String UNDEFINED_REPORT_PROMPT = "With not specifically defined examination data, " +
            PROMPT_COMMON_INSTRUCTIONS + "Your response should always start with sentence " +
            "'Based on the provided data for the patient, " +
            "here is a summary of the results:'. " + PROMPT_REFERENCE_RANGE_INSTRUCTION +
            "Measured data:";
}
