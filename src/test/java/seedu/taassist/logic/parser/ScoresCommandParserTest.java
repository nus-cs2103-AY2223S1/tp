package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.taassist.logic.commands.CommandTestUtil.SESSION_DESC_LAB1;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_SESSION_LAB1;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.ScoresCommand;
import seedu.taassist.model.session.Session;

public class ScoresCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScoresCommand.COMMAND_WORD, ScoresCommand.MESSAGE_USAGE);

    private final ScoresCommandParser parser = new ScoresCommandParser();

    @Test
    public void parse_emptyUserInput_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingPrefix_failure() {
        assertParseFailure(parser, VALID_SESSION_LAB1, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + SESSION_DESC_LAB1, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_prefixWithEmptySession_failure() {
        assertParseFailure(parser, PREFIX_SESSION + PREAMBLE_WHITESPACE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validArgs_success() {
        // no leading and trailing whitespaces
        ScoresCommand expectedScoresCommand =
                new ScoresCommand(new Session(VALID_SESSION_LAB1));
        assertParseSuccess(parser, SESSION_DESC_LAB1, expectedScoresCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \t " + SESSION_DESC_LAB1 + " \t \n", expectedScoresCommand);
    }
}
