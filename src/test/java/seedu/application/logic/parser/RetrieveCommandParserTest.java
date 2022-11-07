package seedu.application.logic.parser;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.commands.CommandTestUtil.INVALID_FUTURE_DATE;
import static seedu.application.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.application.logic.commands.CommandTestUtil.UNKNOWN_PREFIX;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.RetrieveCommand;

public class RetrieveCommandParserTest {

    private RetrieveCommandParser parser = new RetrieveCommandParser();

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, INDEX_FIRST_APPLICATION.getOneBased() + PREAMBLE_WHITESPACE,
                new RetrieveCommand(INDEX_FIRST_APPLICATION));
    }

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(parser, INVALID_FUTURE_DATE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RetrieveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_unexpectedPrefix_failure() {

        // unexpected prefix behind valid input
        assertParseFailure(parser, INDEX_FIRST_APPLICATION.getOneBased() + UNKNOWN_PREFIX,
                Parser.MESSAGE_UNKNOWN_PREFIX_FOUND + RetrieveCommand.MESSAGE_USAGE);

        // unexpected prefix after valid input
        assertParseFailure(parser, UNKNOWN_PREFIX + INDEX_FIRST_APPLICATION.getOneBased(),
                Parser.MESSAGE_UNKNOWN_PREFIX_FOUND + RetrieveCommand.MESSAGE_USAGE);
    }
}
