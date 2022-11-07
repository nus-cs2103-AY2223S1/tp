package nus.climods.logic.parser.parameters;

import nus.climods.logic.parser.ParserUtil;

/**
 * Represents a User Flag parameter
 */

public class UserFlagParameter extends OptionalPositionalParameter<Boolean> {
    public static final int USER_FLAG_INDEX = 1;

    public static final String PARSE_EXCEPTION_MESSAGE = "Invalid user flag: %s";

    /**
     * Creates a user flag parameter with
     * @param argumentString
     */
    public UserFlagParameter(String argumentString) {
        super(USER_FLAG_INDEX, argumentString, ParserUtil::parseUserFlag, PARSE_EXCEPTION_MESSAGE);
    }
}
