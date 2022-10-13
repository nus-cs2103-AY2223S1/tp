package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECORD_DATA_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECORD_DATA_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECORD_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.RECORD_DATA_DESC;
import static seedu.address.logic.commands.CommandTestUtil.RECORD_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddRecordCommand;
import seedu.address.model.person.Record;

public class AddRecordCommandParserTest {

    private AddRecordCommandParser parser = new AddRecordCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddRecordCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_success() {
        Record expectedRecord = new Record(VALID_RECORD_DATE, VALID_RECORD_DATA);

        assertParseSuccess(parser, RECORD_DATE_DESC + RECORD_DATA_DESC, new AddRecordCommand(expectedRecord));
    }

    @Test
    public void parse_invalidArgs_parseExceptionThrown() {
        String expectedMessageInvalidDate = MESSAGE_INVALID_DATE_FORMAT;
        String expectedMessageInvalidRecordData = MESSAGE_INVALID_RECORD_DATA_FORMAT;

        // Invalid data
        assertParseFailure(parser, RECORD_DATE_DESC + INVALID_RECORD_DATA_DESC,
                expectedMessageInvalidRecordData);

        // Invalid date
        assertParseFailure(parser, INVALID_RECORD_DATE_DESC + RECORD_DATA_DESC, expectedMessageInvalidDate);
    }


}
