package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_MA2001;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePersonFromModuleCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Name;

public class DeletePersonFromModuleCommandParserTest {

    private DeletePersonFromModuleCommandParser parser = new DeletePersonFromModuleCommandParser();

    @Test
    public void parse_validArgs_returnsDeletePersonFromModuleCommand() {
        String userInput = MODULE_CODE_DESC_CS2106 + NAME_DESC_AMY;
        assertParseSuccess(parser,
                userInput,
                new DeletePersonFromModuleCommand(new ModuleCode(VALID_CS2106_MODULE_CODE), new Name(VALID_NAME_AMY)));
    }

    @Test
    public void execute_invalidModuleCode_throwsCommandException() {
        String userInput = INVALID_MODULE_CODE_DESC + NAME_DESC_AMY;
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void execute_invalidName_throwsCommandException() {
        String userInput = MODULE_CODE_DESC_CS2106 + INVALID_NAME_DESC;
        assertParseFailure(parser, userInput, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletePersonFromModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        ModuleCode expectedModuleCode = new ModuleCode(VALID_CS2106_MODULE_CODE);
        Name expectedName = new Name(VALID_NAME_AMY);

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + MODULE_CODE_DESC_CS2106 + NAME_DESC_AMY,
                new DeletePersonFromModuleCommand(expectedModuleCode, expectedName));

        // multiple module codes - last module code accepted
        assertParseSuccess(parser,
                NAME_DESC_AMY + MODULE_CODE_DESC_MA2001 + MODULE_CODE_DESC_CS2106,
                new DeletePersonFromModuleCommand(expectedModuleCode, expectedName));

        // multiple names - last name accepted
        assertParseSuccess(parser,
                NAME_DESC_BOB + NAME_DESC_AMY + MODULE_CODE_DESC_CS2106,
                new DeletePersonFromModuleCommand(expectedModuleCode, expectedName));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletePersonFromModuleCommand.MESSAGE_USAGE);

        // missing module code prefix
        assertParseFailure(parser, NAME_DESC_AMY, expectedMessage);

        // missing name prefix
        assertParseFailure(parser, MODULE_CODE_DESC_CS2106, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_AMY + VALID_CS2106_MODULE_CODE, expectedMessage);
    }
}
