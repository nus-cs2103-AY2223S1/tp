package nus.climods.logic.parser.parameters;

import java.util.Optional;
import java.util.function.Function;

import nus.climods.commons.core.module.ModuleCode;
import nus.climods.logic.parser.ParserUtil;


/**
 * Represents a module code from the user's list of modules.
 */
public class UserModuleCodeParameter extends ModuleCodeParameter {
    private static Function<String, Optional<ModuleCode>> CONVERSION_FUNCTION = ParserUtil::parseUserModuleCode;
    private static String MESSAGE_PARSE_EXCEPTION = "Module code supplied not in your module list!";
    public UserModuleCodeParameter(String argumentsString) {
        super(argumentsString, CONVERSION_FUNCTION, MESSAGE_PARSE_EXCEPTION);
    }
}
