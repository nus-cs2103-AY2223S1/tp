package nus.climods.logic.parser.parameters;

import java.util.Optional;
import java.util.function.Function;

import nus.climods.commons.core.module.ModuleCode;
import nus.climods.logic.parser.ParserUtil;


/**
 * Represents a module code from the full list of modules.
 */
public class ListModuleCodeParameter extends ModuleCodeParameter {
    private static final Function<String, Optional<ModuleCode>> CONVERSION_FUNCTION = ParserUtil::parseListModuleCode;
    private static final String MESSAGE_PARSE_EXCEPTION = "Module code supplied not in list of modules!";
    public ListModuleCodeParameter(String argumentsString) {
        super(argumentsString, CONVERSION_FUNCTION, MESSAGE_PARSE_EXCEPTION);
    }
}
