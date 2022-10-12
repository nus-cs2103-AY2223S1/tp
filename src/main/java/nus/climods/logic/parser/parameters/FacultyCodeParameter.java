package nus.climods.logic.parser.parameters;

import nus.climods.logic.parser.ParserUtil;

/**
 * Represents a User Flag parameter
 */

public class FacultyCodeParameter extends OptionalPositionalParameter<String> {
    public static final int FACULTY_CODE_INDX = 0;

    public static final String PARSE_EXCEPTION_MESSAGE = "Invalid faculty code: %s";

    /**
     * Creates a faculty code parameter with
     * @param argumentString
     */
    public FacultyCodeParameter(String argumentString) {
        super(FACULTY_CODE_INDX, argumentString, ParserUtil::parseFacultyCode, PARSE_EXCEPTION_MESSAGE);
    }
}
