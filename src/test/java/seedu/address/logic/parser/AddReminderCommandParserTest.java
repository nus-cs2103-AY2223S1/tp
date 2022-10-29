package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.reminder.AddReminderCommand;
import seedu.address.model.datetime.DatetimeCommonUtils;
import seedu.address.model.reminder.Reminder;
import seedu.address.testutil.ReminderBuilder;

/**
 * Test cases for adding reminders
 */
public class AddReminderCommandParserTest {

    private AddressBookParser parser = new AddressBookParser();

    @Test
    public void parse_validArgs_returnsAddReminderCommand() {
        Reminder reminder = new ReminderBuilder()
                .withName("yay").withDeadline("2022-01-01 08:00")
                .withDescription("urgent").withPriority("HIGH")
                .build();
        String userInput = "add reminder n/yay D/2022-01-01 T/08:00 d/urgent p/HIGH";
        assertParseSuccess(parser, userInput, new AddReminderCommand(reminder));
    }


    @Test
    public void parse_invalidDatetimeFormat_throwsParseException() {
        String userInput;
        userInput = "add reminder n/yay D/2022-01- T/08:00 d/urgent p/HIGH";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.DATETIME_MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDatetime_throwsParseException() {
        String userInput;
        userInput = "add reminder n/yay D/2022-01-01 T/08:70 d/urgent p/HIGH";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.DATETIME_MESSAGE_CONSTRAINTS_UNPARSABLE);
        userInput = "add reminder n/yay D/2022-88-01 T/08:00 d/urgent p/HIGH";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.DATETIME_MESSAGE_CONSTRAINTS_UNPARSABLE);
    }
}
