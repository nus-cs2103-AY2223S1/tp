package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECORD_DATA_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECORD_DATA_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECORD_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATE_2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.record.Record.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.address.testutil.TypicalIndexes.THIRD_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditRecordCommand;
import seedu.address.logic.commands.EditRecordCommand.EditRecordDescriptor;
import seedu.address.testutil.EditRecordDescriptorBuilder;

public class EditRecordCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRecordCommand.MESSAGE_USAGE);

    private EditRecordCommandParser parser = new EditRecordCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_RECORD_DATA, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditRecordCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid record date
        assertParseFailure(parser, "1" + INVALID_RECORD_DATE_DESC, MESSAGE_INVALID_DATE_FORMAT);
        // invalid record data
        assertParseFailure(parser, "1" + INVALID_RECORD_DATA_DESC, MESSAGE_INVALID_RECORD_DATA_FORMAT);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_RECORD_DATE_DESC + INVALID_RECORD_DATA_DESC,
                MESSAGE_INVALID_DATE_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = SECOND_INDEX;
        String userInput = targetIndex.getOneBased() + " d/" + VALID_RECORD_DATE + " r/" + VALID_RECORD_DATA;

        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder().withDate(VALID_RECORD_DATE)
                .withData(VALID_RECORD_DATA).build();
        EditRecordCommand expectedCommand = new EditRecordCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldsSpecified_success() {
        // record data
        Index targetIndex = FIRST_INDEX;
        String userInput = targetIndex.getOneBased() + " r/" + VALID_RECORD_DATA;
        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder().withData(VALID_RECORD_DATA).build();
        EditRecordCommand expectedCommand = new EditRecordCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // record date
        userInput = targetIndex.getOneBased() + " d/" + VALID_RECORD_DATE;
        descriptor = new EditRecordDescriptorBuilder().withDate(VALID_RECORD_DATE).build();
        expectedCommand = new EditRecordCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = FIRST_INDEX;
        String userInput = targetIndex.getOneBased() + " d/" + VALID_RECORD_DATE + " r/" + VALID_RECORD_DATA
                + " d/" + VALID_RECORD_DATE_2;

        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder().withDate(VALID_RECORD_DATE_2)
                .withData(VALID_RECORD_DATA).build();
        EditRecordCommand expectedCommand = new EditRecordCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = FIRST_INDEX;
        String userInput = targetIndex.getOneBased() + " r/" + INVALID_RECORD_DATA_DESC + " r/" + VALID_RECORD_DATA;
        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder().withData(VALID_RECORD_DATA).build();
        EditRecordCommand expectedCommand = new EditRecordCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addingMedications_success() {
        Index targetIndex = THIRD_INDEX;
        String userInput = targetIndex.getOneBased() + " m/Paracetamol";

        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder().withMedications("Paracetamol").build();
        EditRecordCommand expectedCommand = new EditRecordCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
