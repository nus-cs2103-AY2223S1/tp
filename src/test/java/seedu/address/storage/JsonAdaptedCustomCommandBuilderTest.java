package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomCommandBuilders.DEFAULT_INFO_COMMAND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.CustomCommandBuilder;

public class JsonAdaptedCustomCommandBuilderTest {

    private static final String INVALID_MACRO_NAME = "@$%!";
    private static final String INVALID_MACRO_REPLACE = "";

    private static final String VALID_MACRO_NAME = DEFAULT_INFO_COMMAND.getRepr();
    private static final String VALID_MACRO_REPLACE = DEFAULT_INFO_COMMAND.getCommandData();

    @Test
    public void toModelType_validCustomCommandBuilderDetails_returnsCustomCommandBuilder() throws Exception {
        JsonAdaptedCustomCommandBuilder customCommandBuilder = new JsonAdaptedCustomCommandBuilder(VALID_MACRO_NAME,
                VALID_MACRO_REPLACE);
        assertEquals(DEFAULT_INFO_COMMAND, customCommandBuilder.toModelType());
    }

    @Test
    public void toModelType_invalidMacroName_throwsIllegalArgumentException() {
        JsonAdaptedCustomCommandBuilder customCommandBuilder = new JsonAdaptedCustomCommandBuilder(INVALID_MACRO_NAME,
                VALID_MACRO_REPLACE);
        String expectedMessage = CustomCommandBuilder.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customCommandBuilder::toModelType);
    }

    @Test
    public void toModelType_nullMacroName_throwsIllegalArgumentException() {
        JsonAdaptedCustomCommandBuilder customCommandBuilder = new JsonAdaptedCustomCommandBuilder(null,
                VALID_MACRO_REPLACE);
        String expectedMessage = CustomCommandBuilder.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customCommandBuilder::toModelType);
    }

    @Test
    public void toModelType_invalidMacroReplace_throwsIllegalArgumentException() throws Exception {
        JsonAdaptedCustomCommandBuilder customCommandBuilder = new JsonAdaptedCustomCommandBuilder(VALID_MACRO_NAME,
                INVALID_MACRO_REPLACE);
        String expectedMessage = CustomCommandBuilder.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customCommandBuilder::toModelType);
    }

    @Test
    public void toModelType_nullMacroReplace_throwsIllegalArgumentException() {
        JsonAdaptedCustomCommandBuilder customCommandBuilder = new JsonAdaptedCustomCommandBuilder(
            VALID_MACRO_NAME, null);
        String expectedMessage = CustomCommandBuilder.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customCommandBuilder::toModelType);
    }
}
