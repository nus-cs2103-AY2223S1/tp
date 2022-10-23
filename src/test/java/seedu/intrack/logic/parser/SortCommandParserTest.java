package seedu.intrack.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.intrack.logic.commands.SortCommand;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;


class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_commandWordSpecified_success() {
        // ascending command word present
        String userInputA = " a"; //untrimmed yet
        SortCommand expectedCommandA = new SortCommand("a");
        assertParseSuccess(parser, userInputA, expectedCommandA);

        // descending command word present
        String userInputD = " d"; //untrimmed yet
        SortCommand expectedCommandD = new SortCommand("d");
        assertParseSuccess(parser, userInputD, expectedCommandD);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        // no parameters
        assertParseFailure(parser, SortCommand.COMMAND_WORD, expectedMessage);

    }
}
