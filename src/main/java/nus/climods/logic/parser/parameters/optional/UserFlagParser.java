package nus.climods.logic.parser.parameters.optional;

import nus.climods.logic.parser.exceptions.ParseException;

public class UserFlagParser extends OptionalArgumentsParser {
    public UserFlagParser(String argumentsString) throws ParseException {
        super(argumentsString, new UserFlag());
    }
}
