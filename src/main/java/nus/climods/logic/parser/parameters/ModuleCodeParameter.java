package nus.climods.logic.parser.parameters;

import nus.climods.logic.parser.ParserUtil;

/**
 * Represents a positional ModuleCode parameter
 */
public class ModuleCodeParameter extends PositionalParameter<String> {

    public static final String INVALID_INPUT_MESSAGE = "Module code should not be empty!";
    public static final String PARSE_EXCEPTION_MESSAGE = "'%s' not in current NUS curriculum";
    private static final int MODULE_CODE_INDEX = 0;

    /**
     * Creates a ModuleCodeParameter with argumentsString
     *
     * @param argumentsString Arguments string to parse module code from
     */
    public ModuleCodeParameter(String argumentsString) {
        super(MODULE_CODE_INDEX, argumentsString, ParserUtil::parseModuleCode, PARSE_EXCEPTION_MESSAGE);
        this.setEmptyInputMessage(INVALID_INPUT_MESSAGE);
    }

}
