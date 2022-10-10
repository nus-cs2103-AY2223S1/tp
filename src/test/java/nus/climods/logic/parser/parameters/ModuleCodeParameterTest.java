package nus.climods.logic.parser.parameters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import nus.climods.commons.core.module.ModuleCode;
import nus.climods.logic.parser.exceptions.ParseException;


/**
 * Tests for ModuleCodeParameter class
 */
public class ModuleCodeParameterTest {
    private static List<String> validCodes = List.of("CS2103", "CS2106", "CS2102");
    private static final String INVALID_CODE_MESSAGE = "Invalid code: %s";
    private static final String EMPTY_INPUT_MESSAGE = "Empty module code is not valid";
    private static Optional<ModuleCode> convertModuleCode(String code) {
        if (!validCodes.contains(code)) {
            return Optional.empty();
        }
        return Optional.of(new ModuleCode(code));
    }

    private ModuleCodeParameter createModuleCodeParameter(String argsString) {
        return new ModuleCodeParameter(argsString, ModuleCodeParameterTest::convertModuleCode,
                INVALID_CODE_MESSAGE);
    }

    @Test
    public void parseModuleCode_validInput_returnsModuleCode() {
        String argsString = " CS2102";
        ModuleCodeParameter mcp = createModuleCodeParameter(argsString);

        try {
            ModuleCode mc = mcp.getArgValue();
            assertEquals("CS2102", mc.getModuleCode());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseModuleCode_validInputWithoutSpace_returnsModuleCode() {
        String argsString = "CS2103";
        ModuleCodeParameter mcp = createModuleCodeParameter(argsString);


        try {
            ModuleCode mc = mcp.getArgValue();
            assertEquals("CS2103", mc.getModuleCode());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseModuleCode_validInputWithOtherArguments_returnsModuleCode() {
        String argsString = "CS2106 xxx";
        ModuleCodeParameter mcp = createModuleCodeParameter(argsString);

        try {
            ModuleCode mc = mcp.getArgValue();
            assertEquals("CS2106", mc.getModuleCode());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseModuleCode_invalidInput_throwsParseException() {
        String argsString = "notmc";
        ModuleCodeParameter mcp = createModuleCodeParameter(argsString);

        try {
            ModuleCode mc = mcp.getArgValue();
        } catch (ParseException e) {
            assertEquals(String.format(INVALID_CODE_MESSAGE, argsString), e.getMessage());
        }
    }

    @Test
    public void parseModuleCode_invalidInputEmptyString_throwsParseException() {
        String argsString = "  ";
        ModuleCodeParameter mcp = createModuleCodeParameter(argsString);

        try {
            mcp.setEmptyInputMessage(EMPTY_INPUT_MESSAGE);
            ModuleCode mc = mcp.getArgValue();
        } catch (ParseException e) {
            assertEquals(EMPTY_INPUT_MESSAGE, e.getMessage());
        }
    }
}
