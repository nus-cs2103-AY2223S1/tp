package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.ReminderCommand;
import seedu.address.model.person.Reminder;

public class ReminderCommandParserTest {
    private ReminderCommandParser parser = new ReminderCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Reminder expectedReminderBob = new Reminder(CommandTestUtil.VALID_REMINDER_BOB,
                CommandTestUtil.VALID_REMINDER_DATE_BOB);
        Reminder expectedReminderAmy = new Reminder(CommandTestUtil.VALID_REMINDER_AMY,
                CommandTestUtil.VALID_REMINDER_DATE_AMY);

        assertParseSuccess(parser, "1" + CommandTestUtil.REMINDER_DESC_BOB,
                new ReminderCommand(INDEX_FIRST_PERSON, expectedReminderBob));

        assertParseSuccess(parser, "2" + CommandTestUtil.REMINDER_DESC_AMY,
                new ReminderCommand(INDEX_SECOND_PERSON, expectedReminderAmy));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE);

        // missing task prefix
        assertParseFailure(parser, "1 " + CommandTestUtil.VALID_REMINDER_BOB + " " + PREFIX_DATE
                + CommandTestUtil.VALID_REMINDER_DATE_BOB, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, "1 " + PREFIX_REMINDER + CommandTestUtil.VALID_REMINDER_BOB + " "
                + CommandTestUtil.VALID_REMINDER_DATE_BOB, expectedMessage);

        // all prefix missing
        assertParseFailure(parser, "1 " + CommandTestUtil.VALID_REMINDER_BOB + " "
                + CommandTestUtil.VALID_REMINDER_DATE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid index
        assertParseFailure(parser, "a " + CommandTestUtil.REMINDER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));

        // invalid date format
        assertParseFailure(parser, "1 " + PREFIX_REMINDER + CommandTestUtil.VALID_REMINDER_BOB
                        + CommandTestUtil.INVALID_DATE_FORMAT_DESC, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReminderCommand.MESSAGE_DATE_CONSTRAINTS));

        // invalid date
        assertParseFailure(parser, "1 " + PREFIX_REMINDER + CommandTestUtil.VALID_REMINDER_BOB
                        + CommandTestUtil.INVALID_DATE_DESC, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReminderCommand.MESSAGE_INVALID_DATE));

        // empty reminder
        assertParseFailure(parser, "1 " + PREFIX_REMINDER + " " + PREFIX_DATE
                        + CommandTestUtil.VALID_REMINDER_DATE_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReminderCommand.MESSAGE_EMPTY_REMINDER));

        // empty date
        assertParseFailure(parser, "1 " + PREFIX_REMINDER + CommandTestUtil.VALID_REMINDER_BOB + " "
                        + PREFIX_DATE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReminderCommand.MESSAGE_EMPTY_DATE));
    }
}
