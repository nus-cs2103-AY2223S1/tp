package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_MISSING_PREFIXES_ALL;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_REMINDER1;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_REMINDER2;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_REMINDER1;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_REMINDER2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_FORMAT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_REMINDER1;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_REMINDER2;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_REMINDER1;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_REMINDER2;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_REMINDER1;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_REMINDER2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_REMINDER2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_REMINDER1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_REMINDER1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_REMINDER2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_REMINDER1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_REMINDER2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_REMINDER1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_REMINDER2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_REMINDER2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.reminder.AddReminderCommand;
import seedu.address.logic.parser.reminder.AddReminderCommandParser;
import seedu.address.model.datetime.DatetimeCommonUtils;
import seedu.address.model.reminder.Reminder;
import seedu.address.testutil.ReminderBuilder;

/**
 * Test cases for adding reminders
 */
public class AddReminderCommandParserTest {

    private AddReminderCommandParser parser = new AddReminderCommandParser();

    @Test
    public void parse_validArgs_returnsAddReminderCommand() {
        Reminder expectedReminder = new ReminderBuilder().withName(VALID_NAME_REMINDER1)
                .withDeadline(VALID_DEADLINE_REMINDER1).withPriority(VALID_PRIORITY_REMINDER1)
                .withDescription(VALID_DESCRIPTION_REMINDER1).build();

        String userInput = NAME_DESC_REMINDER1 + DATE_DESC_REMINDER1 + TIME_DESC_REMINDER1 + PRIORITY_DESC_REMINDER1
                + DESCRIPTION_DESC_REMINDER1;
        assertParseSuccess(parser, userInput, new AddReminderCommand(expectedReminder));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String userInput;

        // missing name prefix
        userInput = DATE_DESC_REMINDER2 + TIME_DESC_REMINDER2 + PRIORITY_DESC_REMINDER2
                + DESCRIPTION_DESC_REMINDER2;
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_MISSING_PREFIXES_ALL, Arrays.toString(new Prefix[]{CliSyntax.PREFIX_NAME}),
                        AddReminderCommand.MESSAGE_USAGE));

        // missing priority prefix
        userInput = NAME_DESC_REMINDER2 + DATE_DESC_REMINDER2 + TIME_DESC_REMINDER2 + DESCRIPTION_DESC_REMINDER2;
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_MISSING_PREFIXES_ALL, Arrays.toString(new Prefix[]{CliSyntax.PREFIX_PRIORITY}),
                        AddReminderCommand.MESSAGE_USAGE));

        // missing date prefix
        userInput = NAME_DESC_REMINDER1 + TIME_DESC_REMINDER1 + PRIORITY_DESC_REMINDER1 + DESCRIPTION_DESC_REMINDER1;
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_MISSING_PREFIXES_ALL, Arrays.toString(new Prefix[]{CliSyntax.PREFIX_DATE_DAY}),
                        AddReminderCommand.MESSAGE_USAGE));

        // missing time prefix
        userInput = NAME_DESC_REMINDER1 + DATE_DESC_REMINDER1 + PRIORITY_DESC_REMINDER1 + DESCRIPTION_DESC_REMINDER1;
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_MISSING_PREFIXES_ALL, Arrays.toString(new Prefix[]{CliSyntax.PREFIX_TIME}),
                        AddReminderCommand.MESSAGE_USAGE));

        // missing description prefix
        userInput = NAME_DESC_REMINDER1 + TIME_DESC_REMINDER1 + DATE_DESC_REMINDER1 + PRIORITY_DESC_REMINDER1;
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_MISSING_PREFIXES_ALL, Arrays.toString(new Prefix[]{CliSyntax.PREFIX_DESCRIPTION}),
                        AddReminderCommand.MESSAGE_USAGE));


        // all prefixes missing
        userInput = VALID_NAME_REMINDER2 + VALID_DATE_REMINDER2 + VALID_TIME_REMINDER2 + VALID_PRIORITY_REMINDER2
                + VALID_DESCRIPTION_REMINDER2;
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_MISSING_PREFIXES_ALL, Arrays.toString(new Prefix[]{CliSyntax.PREFIX_NAME,
                    CliSyntax.PREFIX_DATE_DAY, CliSyntax.PREFIX_TIME, CliSyntax.PREFIX_DESCRIPTION,
                    CliSyntax.PREFIX_PRIORITY}),
                    AddReminderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDatetimeFormat_throwsParseException() {
        String userInput = NAME_DESC_REMINDER1 + INVALID_DATE_FORMAT_DESC + TIME_DESC_REMINDER1
                + PRIORITY_DESC_REMINDER1 + DESCRIPTION_DESC_REMINDER1;
        assertParseFailure(parser, userInput, DatetimeCommonUtils.DATETIME_MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDatetime_throwsParseException() {
        String userInput;
        userInput = NAME_DESC_REMINDER1 + INVALID_DATE_DESC + TIME_DESC_REMINDER1 + PRIORITY_DESC_REMINDER1
                + DESCRIPTION_DESC_REMINDER1;
        assertParseFailure(parser, userInput, DatetimeCommonUtils.DATETIME_MESSAGE_CONSTRAINTS_UNPARSABLE);

        userInput = NAME_DESC_REMINDER1 + DATE_DESC_REMINDER1 + INVALID_TIME_DESC + PRIORITY_DESC_REMINDER1
                + DESCRIPTION_DESC_REMINDER1;
        assertParseFailure(parser, userInput, DatetimeCommonUtils.DATETIME_MESSAGE_CONSTRAINTS_UNPARSABLE);
    }
}
