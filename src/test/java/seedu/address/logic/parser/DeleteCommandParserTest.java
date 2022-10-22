package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteClientCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteRemarkCommand;
import seedu.address.logic.commands.DeleteTransactionCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validIndexAndClientMode_returnsDeleteClientCommand() {
        assertParseSuccess(parser, "1 m/client", new DeleteClientCommand(INDEX_FIRST_CLIENT));
    }

    @Test
    public void parse_validIndexAndTransactionMode_returnsDeleteTransactionCommand() {
        assertParseSuccess(parser, "1 m/transaction", new DeleteTransactionCommand(INDEX_FIRST_CLIENT));
    }

    @Test
    public void parse_validIndexAndRemarkMode_returnsDeleteRemarkCommand() {
        assertParseSuccess(parser, "1 m/company", new DeleteRemarkCommand(INDEX_FIRST_CLIENT));
    }

    @Test
    public void parse_validIndexAndInvalidMode_throwsParseException() {
        assertParseFailure(parser, "1 m/invalidMode",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndexWithValidMode_throwsParseException() {
        assertParseFailure(parser, "b m/client",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndexWithoutMode_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validIndexWithoutMode_throwsParseException() {
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
