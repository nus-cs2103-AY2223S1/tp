package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListMeetingCommand;

public class ListMeetingCommandParserTest {

    private ListMeetingCommandParser parser = new ListMeetingCommandParser();

    @Test
    public void parse_validArguments_success() {
        // Without any arguments
        assertParseSuccess(parser, "", new ListMeetingCommand(DateKeyword.ALL_TIME));

        // With tomorrow date keyword
        assertParseSuccess(parser, " d/tomorrow", new ListMeetingCommand(DateKeyword.TOMORROW));

        // With week date keyword
        assertParseSuccess(parser, " d/week", new ListMeetingCommand(DateKeyword.THIS_WEEK));

        // With month date keyword
        assertParseSuccess(parser, " d/month", new ListMeetingCommand(DateKeyword.THIS_MONTH));
    }

    @Test
    public void parse_invalidDateKeyword_failure() {
        // Invalid date keyword
        assertParseFailure(parser, " d/thismonth", ParserUtil.MESSAGE_INVALID_DATE_KEYWORD);
    }

}
