package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SummaryCommand;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.EntryInYearMonthPredicate;



public class SummaryCommandParserTest {
    private SummaryCommandParser parser = new SummaryCommandParser();

    @Test
    public void parse_validArgs_returnsSummaryCommand() {
        // no leading and trailing whitespaces
        SummaryCommand expectedSummaryCommand =
                new SummaryCommand();
        assertParseSuccess(parser, " ", expectedSummaryCommand);
    }

    @Test
    public void parse_validArgs_returnsSummaryCommandWithDate() {
        // no leading and trailing whitespaces
        SummaryCommand expectedSummaryCommand =
                new SummaryCommand(new EntryInYearMonthPredicate(
                        YearMonth.parse("2022-09",
                                DateTimeFormatter.ofPattern(Date.YEAR_MONTH_PATTERN))));
        assertParseSuccess(parser, " " + PREFIX_MONTH + "2022-09 ", expectedSummaryCommand);
    }
}
