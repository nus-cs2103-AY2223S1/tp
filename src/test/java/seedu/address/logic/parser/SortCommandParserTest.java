package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.sort.SortByAppointment;
import seedu.address.logic.parser.sort.SortByIncome;
import seedu.address.logic.parser.sort.SortByName;
import seedu.address.logic.parser.sort.SortByRiskTag;


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

        SortCommand expectedSortCommandRisk =
                new SortCommand(new SortByRiskTag("asc"), "risk");
        assertParseSuccess(parser, "risk", expectedSortCommandRisk);

        SortCommand expectedSortCommandIncome =
                new SortCommand(new SortByIncome("asc"), "income");
        assertParseSuccess(parser, "income", expectedSortCommandIncome);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n name \n", expectedSortCommandName);
        assertParseSuccess(parser, " \t appt \n", expectedSortCommandAppt);
        assertParseSuccess(parser, " \t risk \n", expectedSortCommandRisk);
        assertParseSuccess(parser, " \t income \n", expectedSortCommandIncome);
    }

    @Test
    public void parse_validArgs_returnsSortCommandDesc() {
        SortCommand expectedSortCommandName =
                new SortCommand(new SortByName("desc"), "name in descending order");
        assertParseSuccess(parser, "name desc", expectedSortCommandName);

        SortCommand expectedSortCommandAppt =
                new SortCommand(new SortByAppointment("desc"), "appt in descending order");
        assertParseSuccess(parser, "appt desc", expectedSortCommandAppt);

        SortCommand expectedSortCommandRisk =
                new SortCommand(new SortByRiskTag("desc"), "risk in descending order");
        assertParseSuccess(parser, "risk desc", expectedSortCommandRisk);

        SortCommand expectedSortCommandIncome =
                new SortCommand(new SortByIncome("desc"), "income in descending order");
        assertParseSuccess(parser, "income desc", expectedSortCommandIncome);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n name desc \n", expectedSortCommandName);
        assertParseSuccess(parser, " \t appt desc \n", expectedSortCommandAppt);
        assertParseSuccess(parser, " \t risk desc \n", expectedSortCommandRisk);
        assertParseSuccess(parser, " \t income desc \n", expectedSortCommandIncome);
    }
}

