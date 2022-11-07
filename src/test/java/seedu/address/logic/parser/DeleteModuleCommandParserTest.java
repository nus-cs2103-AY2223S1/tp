package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteModuleCommand;

/**
 * Contains unit tests for {@code DeleteModuleCommandParser}.
 */
public class DeleteModuleCommandParserTest {

    private DeleteModuleCommandParser parser = new DeleteModuleCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteModuleCommand() {
        assertParseSuccess(parser, "1", new DeleteModuleCommand(INDEX_FIRST_MODULE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // non-integer
        assertParseFailure(parser, "a b", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // empty string
        assertParseFailure(parser, "", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_MODULE_INDEX);

        // negative index
        assertParseFailure(parser, "-2", MESSAGE_INVALID_MODULE_INDEX);

        // positive signed index
        assertParseFailure(parser, "+3", MESSAGE_INVALID_MODULE_INDEX);

        // valid index followed by string
        assertParseFailure(parser, "1 i/ string", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));

        // number bigger than max integer
        assertParseFailure(parser, "9999999999999999999999", MESSAGE_INVALID_MODULE_INDEX);

    }

}
