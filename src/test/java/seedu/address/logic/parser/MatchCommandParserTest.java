package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MatchCommand;

public class MatchCommandParserTest {
    private final MatchCommandParser parser = new MatchCommandParser();

    @Test
    public void parse_emptyString_parseFailure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_MISSING_INDEX + MatchCommand.MESSAGE_USAGE);
        String input = "";
        assertParseFailure(parser, input, expected);
    }

    @Test
    public void parse_spaces_parseFailure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_MISSING_INDEX + MatchCommand.MESSAGE_USAGE);
        String input = "          \n";
        assertParseFailure(parser, input, expected);
    }

    @Test
    public void parse_invalidArgs_parseFailure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_MISSING_INDEX + MatchCommand.MESSAGE_USAGE);
        String input = "this is an invalid argument";
        assertParseFailure(parser, input, expected);
    }

    @Test
    public void parse_validIndex_parseSuccess() {
        //small index
        MatchCommand expectedCommand = new MatchCommand(INDEX_FIRST);
        String input = " " + INDEX_FIRST.getOneBased();
        assertParseSuccess(parser, input, expectedCommand);

        //large index
        expectedCommand = new MatchCommand(Index.fromOneBased(1000));
        input = " 1000";
        assertParseSuccess(parser, input, expectedCommand);

        //largest possible index
        expectedCommand = new MatchCommand(Index.fromOneBased(Integer.MAX_VALUE));
        input = " " + Integer.MAX_VALUE;
        assertParseSuccess(parser, input, expectedCommand);
    }
}
