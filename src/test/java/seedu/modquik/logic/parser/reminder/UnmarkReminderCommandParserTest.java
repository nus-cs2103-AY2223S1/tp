package seedu.modquik.logic.parser.reminder;

import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modquik.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modquik.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.modquik.testutil.TypicalIndexes.INDEX_FIRST_REMINDER;

import org.junit.jupiter.api.Test;

import seedu.modquik.logic.commands.reminder.UnmarkReminderCommand;

public class UnmarkReminderCommandParserTest {
    private UnmarkReminderCommandParser parser = new UnmarkReminderCommandParser();

    @Test
    public void parse_validArgs_returnsMarkReminderCommand() {
        assertParseSuccess(parser, "1", new UnmarkReminderCommand(INDEX_FIRST_REMINDER));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnmarkReminderCommand.MESSAGE_USAGE));
    }
}
