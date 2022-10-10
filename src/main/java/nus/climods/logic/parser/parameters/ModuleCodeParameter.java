package nus.climods.logic.parser.parameters;

import nus.climods.logic.parser.ParserUtil;

/**
 * Represents a positional ModuleCode parameter
 */
public class ModuleCodeParameter extends PositionalParameter<String> {
    public static final String INVALID_INPUT_MESSAGE = "Module code should not be empty!";
    private static final int MODULE_CODE_INDEX = 0;


    public ModuleCodeParameter(String argumentsString, String parseExceptionMessage) {
        super(MODULE_CODE_INDEX, argumentsString, ParserUtil::parseModuleCode, parseExceptionMessage);
        this.setEmptyInputMessage(INVALID_INPUT_MESSAGE);
    }

}
