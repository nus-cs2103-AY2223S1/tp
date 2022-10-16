package seedu.guest.logic.parser;

import static seedu.guest.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.guest.logic.commands.CommandTestUtil.DATE_RANGE_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.DATE_RANGE_DESC_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_DATE_RANGE_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_IS_ROOM_CLEAN_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_NUMBER_OF_GUESTS_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.IS_ROOM_CLEAN_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.IS_ROOM_CLEAN_DESC_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.NUMBER_OF_GUESTS_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.NUMBER_OF_GUESTS_DESC_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_DATE_RANGE_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_DATE_RANGE_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_IS_ROOM_CLEAN_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_IS_ROOM_CLEAN_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NUMBER_OF_GUESTS_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NUMBER_OF_GUESTS_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.guest.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.guest.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.guest.testutil.TypicalIndexes.INDEX_FIRST_GUEST;
import static seedu.guest.testutil.TypicalIndexes.INDEX_SECOND_GUEST;
import static seedu.guest.testutil.TypicalIndexes.INDEX_THIRD_GUEST;

import org.junit.jupiter.api.Test;

import seedu.guest.commons.core.index.Index;
import seedu.guest.logic.commands.EditCommand;
import seedu.guest.logic.commands.EditCommand.EditGuestDescriptor;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.IsRoomClean;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;
import seedu.guest.testutil.EditGuestDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_DATE_RANGE_DESC,
                DateRange.MESSAGE_CONSTRAINTS); // invalid date range
        assertParseFailure(parser, "1" + INVALID_NUMBER_OF_GUESTS_DESC,
                NumberOfGuests.MESSAGE_CONSTRAINTS); // invalid number of guests
        assertParseFailure(parser, "1" + INVALID_IS_ROOM_CLEAN_DESC,
                IsRoomClean.MESSAGE_CONSTRAINTS); // invalid is room clean

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_DATE_RANGE_AMY
                        + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_GUEST;

        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + DATE_RANGE_DESC_AMY + NUMBER_OF_GUESTS_DESC_AMY + NAME_DESC_AMY + IS_ROOM_CLEAN_DESC_AMY;

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withDateRange(VALID_DATE_RANGE_AMY)
                .withNumberOfGuests(VALID_NUMBER_OF_GUESTS_AMY).withIsRoomClean(VALID_IS_ROOM_CLEAN_AMY)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_GUEST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_GUEST;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditCommand.EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditGuestDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditGuestDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date range
        userInput = targetIndex.getOneBased() + DATE_RANGE_DESC_AMY;
        descriptor = new EditGuestDescriptorBuilder().withDateRange(VALID_DATE_RANGE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // number of guests
        userInput = targetIndex.getOneBased() + NUMBER_OF_GUESTS_DESC_AMY;
        descriptor = new EditGuestDescriptorBuilder().withNumberOfGuests(VALID_NUMBER_OF_GUESTS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // is room clean
        userInput = targetIndex.getOneBased() + IS_ROOM_CLEAN_DESC_AMY;
        descriptor = new EditGuestDescriptorBuilder().withIsRoomClean(VALID_IS_ROOM_CLEAN_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_GUEST;

        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + DATE_RANGE_DESC_AMY + NUMBER_OF_GUESTS_DESC_AMY + IS_ROOM_CLEAN_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + DATE_RANGE_DESC_AMY + NUMBER_OF_GUESTS_DESC_AMY + IS_ROOM_CLEAN_DESC_AMY
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + DATE_RANGE_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + IS_ROOM_CLEAN_DESC_BOB;

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withDateRange(VALID_DATE_RANGE_BOB)
                .withNumberOfGuests(VALID_NUMBER_OF_GUESTS_BOB).withIsRoomClean(VALID_IS_ROOM_CLEAN_BOB)
                .build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_GUEST;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + DATE_RANGE_DESC_BOB
                 + PHONE_DESC_BOB;
        descriptor = new EditGuestDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withDateRange(VALID_DATE_RANGE_BOB).build();

        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
