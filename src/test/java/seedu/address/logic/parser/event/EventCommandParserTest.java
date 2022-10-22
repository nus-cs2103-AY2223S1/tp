package seedu.address.logic.parser.event;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.event.EventCommand;

public class EventCommandParserTest {

    private EventCommandParser parser = new EventCommandParser();

    @Test
    public void parse_missingOptionFlag_failure() {
        assertParseFailure(parser, " HELLO WORLD",
                String.format(Messages.MESSAGE_FLAG_NOT_SPECIFIED,
                        EventCommand.EVENT_FORMAT + EventCommand.VALID_FLAGS));

        assertParseFailure(parser, " TEXT -e", EventCommand.OPTION_WRONG_ORDER);

        assertParseFailure(parser, " TEXT -e -e", EventCommand.OPTION_WRONG_ORDER_NO_MULTIPLE);
    }

    @Test
    public void parse_multipleOptionFlags_failure() {
        assertParseFailure(parser, " -e -e", EventCommand.OPTION_NO_MULTIPLE);
        assertParseFailure(parser, " -e Hello -e", EventCommand.OPTION_NO_MULTIPLE);
        assertParseFailure(parser, " -hello -world -cs2103t", EventCommand.OPTION_NO_MULTIPLE);
    }

    @Test
    public void parse_eventOption_failure() {
        assertParseFailure(parser, " -unknowncommand",
                EventCommand.OPTION_UNKNOWN + EventCommand.VALID_FLAGS);
    }

}
