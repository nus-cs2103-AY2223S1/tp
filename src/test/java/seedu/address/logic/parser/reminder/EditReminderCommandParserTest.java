package seedu.address.logic.parser.reminder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_REMINDER1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_FORMAT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_REMINDER1;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_REMINDER1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_REMINDER1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.reminder.EditReminderCommand;
import seedu.address.model.datetime.DatetimeCommonUtils;

public class EditReminderCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditReminderCommand.MESSAGE_USAGE);

    private EditReminderCommandParser parser = new EditReminderCommandParser();

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
