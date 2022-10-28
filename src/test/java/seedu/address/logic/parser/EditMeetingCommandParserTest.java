package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_MEETING_1;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_MEETING_2;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MEETING_1;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MEETING_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_MEETING_1;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_MEETING_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MEETING_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MEETING_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MEETING_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MEETING_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MEETING_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MEETING_2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CreateMeetingCommandParser.DATE_TIME_VALIDATOR;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class EditMeetingCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE);

    private EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_MEETING_1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditMeetingCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DESCRIPTION_DESC_MEETING_1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DESCRIPTION_DESC_MEETING_1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() throws ParseException, java.text.ParseException {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + DATE_DESC_MEETING_1 + LOCATION_DESC_MEETING_1
                + DESCRIPTION_DESC_MEETING_1;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withDescription(VALID_NAME_MEETING_1)
                .withLocation(VALID_LOCATION_MEETING_1)
            .withDate(DATE_TIME_VALIDATOR.processDateTime(VALID_DATE_MEETING_1)).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_MEETING_1 + LOCATION_DESC_MEETING_1;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withDescription(VALID_NAME_MEETING_1)
                .withLocation(VALID_LOCATION_MEETING_1).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() throws ParseException, java.text.ParseException {
        // description
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_MEETING_1;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withDescription(VALID_NAME_MEETING_1).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_MEETING_1;
        descriptor = new EditMeetingDescriptorBuilder().withLocation(VALID_LOCATION_MEETING_1).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_MEETING_1;
        descriptor = new EditMeetingDescriptorBuilder()
            .withDate(DATE_TIME_VALIDATOR.processDateTime(VALID_DATE_MEETING_1)).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        System.out.println(descriptor.getDate());
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() throws ParseException, java.text.ParseException {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_MEETING_1 + LOCATION_DESC_MEETING_1
               + DATE_DESC_MEETING_1 + DESCRIPTION_DESC_MEETING_2 + LOCATION_DESC_MEETING_2 + DATE_DESC_MEETING_2;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withDescription(VALID_NAME_MEETING_2)
                .withLocation(VALID_LOCATION_MEETING_2)
            .withDate(DATE_TIME_VALIDATOR.processDateTime(VALID_DATE_MEETING_2)).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() throws ParseException, java.text.ParseException {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_DATE_DESC + DATE_DESC_MEETING_1;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
            .withDate(DATE_TIME_VALIDATOR.processDateTime(VALID_DATE_MEETING_1)).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
