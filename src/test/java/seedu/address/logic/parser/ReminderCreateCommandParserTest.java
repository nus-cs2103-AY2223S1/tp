package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.DateTimeParser.MESSAGE_INVALID_DATE_TIME_FORMAT;
import static seedu.address.testutil.TypicalReminders.REMINDER_MEETING_DATETIME;
import static seedu.address.testutil.TypicalReminders.REMINDER_MEETING_DESCRIPTION;
import static seedu.address.testutil.TypicalReminders.REMINDER_PROMOTION_DATETIME_STRING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ReminderCreateCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class ReminderCreateCommandParserTest {
    private ReminderCreateCommandParser parser = new ReminderCreateCommandParser();

    @Test
    public void parse_missingPrefixes_parseException() throws ParseException {
        assertParseFailure(parser, REMINDER_MEETING_DESCRIPTION, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ReminderCreateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTimeFormat_parseException() throws ParseException {
        assertParseFailure(parser, "1 d=" + REMINDER_MEETING_DESCRIPTION
                        + " dt=" + REMINDER_PROMOTION_DATETIME_STRING, MESSAGE_INVALID_DATE_TIME_FORMAT);
    }

    @Test
    public void parse_exceedDescriptionLength_parseException() throws ParseException {
        String stringWith257Characters = "qXQMxfVRPCuLwU4E6GgbhHyGvEBCwwvWV5rmYGBLQC2YkxaBQtNj5xLNhUWBLkjXPGfkqbHS"
                + "cikKHiXLzcJhpWVHqvZfa42tLgWfngdkeC8RzeJUk6WvtbXzWz4V7eMqu8UznQ3ui5GFbjyNzGgEiPAKEvRjYfdEZcJd65wpgx4"
                + "zcAgteC3WyB3GCFTwwhyRDLm8jiFYFZeD8rdMi6rFz6aUVejr5bMMx8UwBE2VGuMGEFu9Gp2wTZk5xXzBBNqAy";
        assertParseFailure(parser, "1 d=" + stringWith257Characters
                + " dt=" + REMINDER_PROMOTION_DATETIME_STRING,
                ReminderCreateCommandParser.MESSAGE_MAX_DESCRIPTION_LENGTH_EXCEEDED);
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        assertParseSuccess(parser, "1 d=" + REMINDER_MEETING_DESCRIPTION + " dt=2022-12-12 11:11",
                new ReminderCreateCommand(Index.fromOneBased(1), REMINDER_MEETING_DESCRIPTION,
                        REMINDER_MEETING_DATETIME));
    }
}
