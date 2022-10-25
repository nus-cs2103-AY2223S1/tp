package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
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
        String userInput = MODULE_CODE_DESC_CS2106 + " " + NAME_DESC_AMY;
        assertParseSuccess(parser,
                userInput,
                new DeletePersonFromModuleCommand(new ModuleCode(VALID_CS2106_MODULE_CODE), new Name(VALID_NAME_AMY)));
    }

    @Test
    public void execute_invalidModuleCode_throwsCommandException() {
        String userInput = INVALID_MODULE_CODE_DESC + " " + NAME_DESC_AMY;
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void execute_invalidName_throwsCommandException() {
        String userInput = MODULE_CODE_DESC_CS2106 + " " + INVALID_NAME_DESC;
        assertParseFailure(parser, userInput, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletePersonFromModuleCommand.MESSAGE_USAGE));
    }
}
