package seedu.address.logic.parser.reminder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_REMINDER;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.reminder.MarkReminderCommand;

public class MarkReminderCommandParserTest {
    private MarkReminderCommandParser parser = new MarkReminderCommandParser();

    @Test
    public void parse_validArgs_returnsMarkReminderCommand() {
        assertParseSuccess(parser, "1", new MarkReminderCommand(INDEX_FIRST_REMINDER));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkReminderCommand.MESSAGE_USAGE));
    }
}
