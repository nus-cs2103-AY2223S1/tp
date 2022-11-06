package seedu.modquik.logic.parser.reminder;

import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modquik.logic.commands.CommandTestUtil.DATE_DESC_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.DESCRIPTION_DESC_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.DESCRIPTION_DESC_REMINDER2;
import static seedu.modquik.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.modquik.logic.commands.CommandTestUtil.INVALID_DATE_FORMAT_DESC;
import static seedu.modquik.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.modquik.logic.commands.CommandTestUtil.NAME_DESC_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.NAME_DESC_REMINDER2;
import static seedu.modquik.logic.commands.CommandTestUtil.PRIORITY_DESC_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.TIME_DESC_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_DEADLINE_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_DESCRIPTION_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_DESCRIPTION_REMINDER2;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_REMINDER2;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_PRIORITY_REMINDER1;
import static seedu.modquik.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modquik.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.modquik.testutil.TypicalIndexes.INDEX_FIRST_REMINDER;
import static seedu.modquik.testutil.TypicalIndexes.INDEX_SECOND_REMINDER;
import static seedu.modquik.testutil.TypicalIndexes.INDEX_THIRD_REMINDER;

import org.junit.jupiter.api.Test;

import seedu.modquik.commons.core.index.Index;
import seedu.modquik.logic.commands.reminder.EditReminderCommand;
import seedu.modquik.logic.commands.reminder.EditReminderCommand.EditReminderDescriptor;
import seedu.modquik.model.datetime.DatetimeCommonUtils;
import seedu.modquik.testutil.EditReminderDescriptorBuilder;

public class EditReminderCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditReminderCommand.MESSAGE_USAGE);

    private EditReminderCommandParser parser = new EditReminderCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_REMINDER;
        String userInput = targetIndex.getOneBased() + NAME_DESC_REMINDER1 + DATE_DESC_REMINDER1 + TIME_DESC_REMINDER1
                + PRIORITY_DESC_REMINDER1 + DESCRIPTION_DESC_REMINDER1;

        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder().withName(VALID_NAME_REMINDER1)
                .withDeadline(VALID_DEADLINE_REMINDER1).withPriority(VALID_PRIORITY_REMINDER1)
                .withDescription(VALID_DESCRIPTION_REMINDER1).build();

        EditReminderCommand expectedCommand = new EditReminderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_REMINDER;
        String userInput = targetIndex.getOneBased() + NAME_DESC_REMINDER2 + DESCRIPTION_DESC_REMINDER2;

        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder().withName(VALID_NAME_REMINDER2)
                .withDescription(VALID_DESCRIPTION_REMINDER2).build();

        EditReminderCommand expectedCommand = new EditReminderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_REMINDER;
        String userInput = targetIndex.getOneBased() + NAME_DESC_REMINDER1;
        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder().withName(VALID_NAME_REMINDER1).build();
        EditReminderCommand expectedCommand = new EditReminderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date and time
        userInput = targetIndex.getOneBased() + DATE_DESC_REMINDER1 + TIME_DESC_REMINDER1;
        descriptor = new EditReminderDescriptorBuilder().withDeadline(VALID_DEADLINE_REMINDER1).build();
        expectedCommand = new EditReminderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = targetIndex.getOneBased() + PRIORITY_DESC_REMINDER1;
        descriptor = new EditReminderDescriptorBuilder().withPriority(VALID_PRIORITY_REMINDER1).build();
        expectedCommand = new EditReminderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_REMINDER2;
        descriptor = new EditReminderDescriptorBuilder().withDescription(VALID_DESCRIPTION_REMINDER2).build();
        expectedCommand = new EditReminderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_REMINDER1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditReminderCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_REMINDER1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_REMINDER1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 z/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DATE_DESC + TIME_DESC_REMINDER1,
                DatetimeCommonUtils.DATETIME_MESSAGE_CONSTRAINTS_UNPARSABLE); // invalid date
        assertParseFailure(parser, "1" + INVALID_TIME_DESC + DATE_DESC_REMINDER1,
                DatetimeCommonUtils.DATETIME_MESSAGE_CONSTRAINTS_UNPARSABLE); // invalid time
        assertParseFailure(parser, "1" + INVALID_DATE_FORMAT_DESC + TIME_DESC_REMINDER1,
                DatetimeCommonUtils.DATETIME_MESSAGE_CONSTRAINTS); // invalid date format

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_DATE_FORMAT_DESC + INVALID_TIME_DESC + NAME_DESC_REMINDER1,
                DatetimeCommonUtils.DATETIME_MESSAGE_CONSTRAINTS);
    }
}
