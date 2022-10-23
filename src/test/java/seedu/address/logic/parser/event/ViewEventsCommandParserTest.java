package seedu.address.logic.parser.event;

import static seedu.address.logic.commands.event.ViewEventsCommand.MESSAGE_FAILURE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.ViewEventsCommand;

public class ViewEventsCommandParserTest {
    private ViewEventsCommandParser parser = new ViewEventsCommandParser();

    @Test
    public void parse_noTrailingCharacters_success() {
        assertParseSuccess(parser, "", new ViewEventsCommand());
    }

    @Test
    public void parse_trailingWhitespace_success() {
        assertParseSuccess(parser, "       ", new ViewEventsCommand());
    }

    @Test
    public void parse_trailingCharacters_failure() {
        assertParseFailure(parser, "   gds ", MESSAGE_FAILURE);
        assertParseFailure(parser, "5", MESSAGE_FAILURE);
    }
}
