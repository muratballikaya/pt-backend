package com.softtech.personalitytest.ptbackend.helper;

public class QuestionHelper {
    public static String generateInternalId(int sequence, int conditionalSequence ){
        return new StringBuilder().append(sequence).append("-").append(conditionalSequence).toString();
    }
}
