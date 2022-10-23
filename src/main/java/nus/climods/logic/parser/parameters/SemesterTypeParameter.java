package nus.climods.logic.parser.parameters;

import org.openapitools.client.model.SemestersEnum;

import nus.climods.logic.parser.ParserUtil;

/**
 * Represents a Semester Type parameter
 */
public class SemesterTypeParameter extends PositionalParameter<SemestersEnum> {
    public static final String INVALID_INPUT_MESSAGE = "Semester should not be empty!";
    public static final String PARSE_EXCEPTION_MESSAGE = "Invalid Semester: %s";
    private static final int LESSON_TYPE_INDEX = 1;

    /**
     * Creates a SemesterTypeParameter with argumentsString
     *
     * @param argumentsString Arguments string to parse lesson type from
     */
    public SemesterTypeParameter(String argumentsString) {
        super(LESSON_TYPE_INDEX, argumentsString, ParserUtil::parseSemesterType, PARSE_EXCEPTION_MESSAGE);
        this.setEmptyInputMessage(INVALID_INPUT_MESSAGE);
    }
}
