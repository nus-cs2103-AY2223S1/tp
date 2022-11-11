//@@author kangqiao322
package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.intrack.logic.commands.SortCommand;
import seedu.intrack.logic.commands.SortSalaryCommand;
import seedu.intrack.logic.commands.SortTimeCommand;


class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_commandWordTimeSpecified_success() {
        // ascending command word present
        String userInputA = " time a"; // untrimmed yet
        SortTimeCommand expectedCommandA = new SortTimeCommand("a");
        assertParseSuccess(parser, userInputA, expectedCommandA);

        // descending command word present
        String userInputD = " time d"; // untrimmed yet
        SortTimeCommand expectedCommandD = new SortTimeCommand("d");
        assertParseSuccess(parser, userInputD, expectedCommandD);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        // no parameters
        assertParseFailure(parser, SortCommand.COMMAND_WORD, expectedMessage);

    }

    //@@author johnrhimawan
    @Test
    public void parse_commandWordSalarySpecified_success() {
        // ascending command word present
        String userInputA = " salary a"; // untrimmed yet
        SortSalaryCommand expectedCommandA = new SortSalaryCommand("a");
        assertParseSuccess(parser, userInputA, expectedCommandA);

        // descending command word present
        String userInputD = " salary d"; // untrimmed yet
        SortSalaryCommand expectedCommandD = new SortSalaryCommand("d");
        assertParseSuccess(parser, userInputD, expectedCommandD);
    }

    @Test
    public void parse_invalidCommandFormat_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        String invalidUserInput = " test a";
        assertParseFailure(parser, invalidUserInput, expectedMessage);
    }
}
