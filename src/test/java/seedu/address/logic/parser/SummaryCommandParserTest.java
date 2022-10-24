package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SummaryCommand;
import seedu.address.model.entry.Date;

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
                new SummaryCommand(new Date("10-10-2022"));
        assertParseSuccess(parser, " " + PREFIX_DATE + "10-10-2022 ", expectedSummaryCommand);
    }
}
