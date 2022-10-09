package nus.climods.logic.parser.parameters;

import nus.climods.commons.core.module.ModuleCode;
import nus.climods.logic.parser.ParserUtil;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ListModuleCodeParameter extends ModuleCodeParameter {
    private static Function<String, Optional<ModuleCode>> CONVERSION_FUNCTION = ParserUtil::parseListModuleCode;
    private static String MESSAGE_PARSE_EXCEPTION = "Module code supplied not in list of modules.";
    public ListModuleCodeParameter(List<String> arguments) {
        super(arguments, CONVERSION_FUNCTION, MESSAGE_PARSE_EXCEPTION);
    }
}
