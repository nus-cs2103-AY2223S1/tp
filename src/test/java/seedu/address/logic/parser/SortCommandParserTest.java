package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.sort.SortByAppointment;
import seedu.address.logic.parser.sort.SortByClientTag;
import seedu.address.logic.parser.sort.SortByIncome;
import seedu.address.logic.parser.sort.SortByMonthly;
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
        assertParseFailure(parser, "na", String.format(SortCommand.MESSAGE_INCORRECT_KEYWORD, SortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "app", String.format(SortCommand.MESSAGE_INCORRECT_KEYWORD, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgsAscending_success() {
        SortCommand expectedSortCommandName =
                new SortCommand(new SortByName("asc"), "name");
        assertParseSuccess(parser, "name", expectedSortCommandName);
        assertParseSuccess(parser, " \n name \n", expectedSortCommandName);
    }

    @Test
    public void parse_validAppointmentArgsAscending_success() {
        SortCommand expectedSortCommandAppt =
                new SortCommand(new SortByAppointment("asc"), "appt");
        assertParseSuccess(parser, "appt", expectedSortCommandAppt);
        assertParseSuccess(parser, " \t appt \n", expectedSortCommandAppt);
    }

    @Test
    public void parse_validRiskTagArgsAscending_success() {
        SortCommand expectedSortCommandRisk =
                new SortCommand(new SortByRiskTag("asc"), "risk");
        assertParseSuccess(parser, "risk", expectedSortCommandRisk);
        assertParseSuccess(parser, " \t risk \n", expectedSortCommandRisk);
    }
    @Test
    public void parse_validIncomeArgsAscending_success() {
        SortCommand expectedSortCommandIncome =
                new SortCommand(new SortByIncome("asc"), "income");
        assertParseSuccess(parser, "income", expectedSortCommandIncome);
        assertParseSuccess(parser, " \t income \n", expectedSortCommandIncome);
    }
    @Test
    public void parse_validMonthlyArgsAscending_success() {
        SortCommand expectedSortCommandMonthly =
                new SortCommand(new SortByMonthly("asc"), "monthly");
        assertParseSuccess(parser, "monthly", expectedSortCommandMonthly);
        assertParseSuccess(parser, " \t monthly \n", expectedSortCommandMonthly);
    }

    @Test
    public void parse_validClientTagArgsAscending_success() {
        SortCommand expectedSortCommandClientTag =
                new SortCommand(new SortByClientTag("asc"), "client");
        assertParseSuccess(parser, "client", expectedSortCommandClientTag);
        assertParseSuccess(parser, " \t client\n", expectedSortCommandClientTag);
    }

    @Test
    public void parse_validNameArgsDescending_success() {
        SortCommand expectedSortCommandName =
                new SortCommand(new SortByName("desc"), "name in descending order");
        assertParseSuccess(parser, "name desc", expectedSortCommandName);
        assertParseSuccess(parser, " \n name desc \n", expectedSortCommandName);
    }

    @Test
    public void parse_validAppointmentArgsDescending_success() {
        SortCommand expectedSortCommandAppt =
                new SortCommand(new SortByAppointment("desc"), "appt in descending order");
        assertParseSuccess(parser, "appt desc", expectedSortCommandAppt);
        assertParseSuccess(parser, " \t appt desc \n", expectedSortCommandAppt);
    }

    @Test
    public void parse_validRiskTagArgsDescending_success() {
        SortCommand expectedSortCommandRisk =
                new SortCommand(new SortByRiskTag("desc"), "risk in descending order");
        assertParseSuccess(parser, "risk desc", expectedSortCommandRisk);
        assertParseSuccess(parser, " \t risk desc \n", expectedSortCommandRisk);
    }
    @Test
    public void parse_validIncomeArgsDescending_success() {
        SortCommand expectedSortCommandIncome =
                new SortCommand(new SortByIncome("desc"), "income in descending order");
        assertParseSuccess(parser, "income desc", expectedSortCommandIncome);
        assertParseSuccess(parser, " \t income desc \n", expectedSortCommandIncome);
    }
    @Test
    public void parse_validMonthlyArgsDescending_success() {
        SortCommand expectedSortCommandMonthly =
                new SortCommand(new SortByMonthly("desc"), "monthly in descending order");
        assertParseSuccess(parser, "monthly desc", expectedSortCommandMonthly);
        assertParseSuccess(parser, " \t monthly desc \n", expectedSortCommandMonthly);
    }

    @Test
    public void parse_validClientTagArgsDescending_success() {
        SortCommand expectedSortCommandClientTag =
                new SortCommand(new SortByClientTag("desc"), "client in descending order");
        assertParseSuccess(parser, "client desc", expectedSortCommandClientTag);
        assertParseSuccess(parser, " \t client desc \n", expectedSortCommandClientTag);
    }
}

