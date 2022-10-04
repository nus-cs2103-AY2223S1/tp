package seedu.guest.logic.parser;

import static seedu.guest.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.guest.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_NUMBER_OF_GUESTS_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.NUMBER_OF_GUESTS_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.NUMBER_OF_GUESTS_DESC_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.guest.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.guest.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.guest.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NUMBER_OF_GUESTS_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.guest.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.guest.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.guest.testutil.TypicalPersons.AMY;
import static seedu.guest.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.guest.logic.commands.AddCommand;
import seedu.guest.model.guest.Address;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;
import seedu.guest.model.tag.Tag;
import seedu.guest.testutil.GuestBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Guest expectedGuest = new GuestBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedGuest));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedGuest));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedGuest));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedGuest));

        // multiple numbers of guests - last number of guests accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NUMBER_OF_GUESTS_DESC_AMY
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedGuest));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedGuest));

        // multiple tags - all accepted
        Guest expectedGuestMultipleTags = new GuestBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NUMBER_OF_GUESTS_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedGuestMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Guest expectedPerson = new GuestBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NUMBER_OF_GUESTS_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing number of guests prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_NUMBER_OF_GUESTS_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Email.MESSAGE_CONSTRAINTS);

        // invalid number of guests
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_NUMBER_OF_GUESTS_DESC + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                NumberOfGuests.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + ADDRESS_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NUMBER_OF_GUESTS_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
