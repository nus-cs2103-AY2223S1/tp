package nus.climods.logic.parser.parameters;

import nus.climods.logic.parser.ParserUtil;

/**
 * Represents a Faculty Code parameter
 */

public class FacultyCodeParameter extends OptionalPositionalParameter<String> {
    public static final int FACULTY_CODE_INDEX = 0;

    public static final String PARSE_EXCEPTION_MESSAGE = "Invalid faculty code: %s";

    /**
     * Creates a faculty code parameter with
     * @param argumentString
     */
    public FacultyCodeParameter(String argumentString) {
        super(FACULTY_CODE_INDEX, argumentString, ParserUtil::parseFacultyCode, PARSE_EXCEPTION_MESSAGE);
    }
}
