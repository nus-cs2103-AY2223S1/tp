package nus.climods.logic.parser.parameters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import nus.climods.logic.parser.exceptions.ParseException;


/**
 * Tests for ModuleCodeParameter class
 */
public class ModuleCodeParameterTest {

    private static final String EMPTY_INPUT_MESSAGE = "Empty module code is not valid";

    private ModuleCodeParameter createModuleCodeParameter(String argsString) {
        return new ModuleCodeParameter(argsString);
    }

    @Test
    public void parseModuleCode_validInput_returnsModuleCode() {
        String argsString = " CS2102";
        ModuleCodeParameter mcp = createModuleCodeParameter(argsString);

        try {
            String mc = mcp.getArgValue();
            assertEquals("CS2102", mc);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseModuleCode_validInputWithoutSpace_returnsModuleCode() {
        String argsString = "CS2103";
        ModuleCodeParameter mcp = createModuleCodeParameter(argsString);

        try {
            String mc = mcp.getArgValue();
            assertEquals("CS2103", mc);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseModuleCode_validInputLowerCasereturnsModuleCode() {
        String argsString = "cs2103";
        ModuleCodeParameter mcp = createModuleCodeParameter(argsString);

        try {
            String mc = mcp.getArgValue();
            assertEquals(argsString, mc);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseModuleCode_validInputWithOtherArguments_returnsModuleCode() {
        String argsString = "CS2106 xxx";
        ModuleCodeParameter mcp = createModuleCodeParameter(argsString);

        try {
            String mc = mcp.getArgValue();
            assertEquals("CS2106", mc);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseModuleCode_invalidInput_throwsParseException() {
        String argsString = "notmc";
        ModuleCodeParameter mcp = createModuleCodeParameter(argsString);

        try {
            String mc = mcp.getArgValue();
        } catch (ParseException e) {
            assertEquals(String.format(ModuleCodeParameter.PARSE_EXCEPTION_MESSAGE, argsString), e.getMessage());
        }
    }

    @Test
    public void parseModuleCode_invalidInputEmptyStringDefaultMessage_throwsParseException() {
        String argsString = "  ";
        ModuleCodeParameter mcp = createModuleCodeParameter(argsString);

        try {
            String mc = mcp.getArgValue();
        } catch (ParseException e) {
            assertEquals(ModuleCodeParameter.INVALID_INPUT_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void parseModuleCode_invalidInputEmptyStringCustomMessage_throwsParseException() {
        String argsString = "  ";
        ModuleCodeParameter mcp = createModuleCodeParameter(argsString);

        try {
            mcp.setEmptyInputMessage(EMPTY_INPUT_MESSAGE);
            String mc = mcp.getArgValue();
        } catch (ParseException e) {
            assertEquals(EMPTY_INPUT_MESSAGE, e.getMessage());
        }
    }
}
