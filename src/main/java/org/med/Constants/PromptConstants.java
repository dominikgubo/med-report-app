package org.med.Constants;

public class PromptConstants {
    public static String BLOOD_REPORT_PROMPT = "With given blood examination data, summarize whether the results are okay for" +
            " patient as a whole. Comment out the specific individual values if they are out of bounds. Don't go in the extremes," +
            "if any values are out of the bounds suggest how this could be changed, and suggest whether this could cause some problems" +
            ". Your response should always start with sentence 'Based on the provided blood analysis data for the patient, " +
            "here is a summary of the results:'. If the reference range isn't provided" +
            "fetch the reference range yourself as reliably as possible. Measured data:";
}
