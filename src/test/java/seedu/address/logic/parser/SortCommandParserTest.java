package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.assertj.core.internal.bytebuddy.TypeCache;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.sort.SortByAppointment;
import seedu.address.logic.parser.sort.SortByName;


public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    // No empty arguments
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    // Invalid arguments
    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "na", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "app", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    // No Trailing or leading whitespaces
    @Test
    public void parse_validArgs_returnsSortCommandAsc() {
        SortCommand expectedSortCommandName =
                new SortCommand(new SortByName("asc"), "name");
        assertParseSuccess(parser, "name", expectedSortCommandName);

        SortCommand expectedSortCommandAppt =
                new SortCommand(new SortByAppointment("asc"), "appt");
        assertParseSuccess(parser, "appt", expectedSortCommandAppt);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n name \n", expectedSortCommandName);
        assertParseSuccess(parser, " \t appt \n", expectedSortCommandAppt);
    }

    @Test
    public void parse_validArgs_returnsSortCommandDesc() {
        SortCommand expectedSortCommandName =
                new SortCommand(new SortByName("desc"), "name");
        assertParseSuccess(parser, "name desc", expectedSortCommandName);

        SortCommand expectedSortCommandAppt =
                new SortCommand(new SortByAppointment("desc"), "appt");
        assertParseSuccess(parser, "appt desc", expectedSortCommandAppt);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n name desc \n", expectedSortCommandName);
        assertParseSuccess(parser, " \t appt desc \n", expectedSortCommandAppt);
    }
}

