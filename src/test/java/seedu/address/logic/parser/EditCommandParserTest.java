package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DATESLOT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATESLOT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.UID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.UID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATESLOT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATESLOT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UID_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String DATE_AND_SLOT_EMPTY = " " + PREFIX_DATE_AND_SLOT;
    private static final String DATE_AND_SLOT_INDEXES_EMPTY = " " + PREFIX_DATE_AND_SLOT_INDEX;

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditCommand.MESSAGE_USAGE);

    private static final String OTHER_DATES_AND_SLOTS = "2022-12-17,3";
    private static final String OTHER_DATES_AND_SLOTS_TWO = "2022-06-18,4";
    private static final String OTHER_DATES_AND_SLOTS_DESC = " " + PREFIX_DATE_AND_SLOT + OTHER_DATES_AND_SLOTS;
    private static final String OTHER_DATES_AND_SLOTS_DESC_TWO = " " + PREFIX_DATE_AND_SLOT
            + OTHER_DATES_AND_SLOTS_TWO;
    private static final String OTHER_DATES_AND_SLOTS_INDEX = "1";
    private static final String OTHER_DATES_AND_SLOTS_INDEX_TWO = "2";
    private static final String OTHER_DATES_AND_SLOTS_INDEX_DESC = " " + PREFIX_DATE_AND_SLOT_INDEX
            + OTHER_DATES_AND_SLOTS_INDEX;
    private static final String OTHER_DATES_AND_SLOTS_INDEX_DESC_TWO = " " + PREFIX_DATE_AND_SLOT_INDEX
            + OTHER_DATES_AND_SLOTS_INDEX_TWO;
    private static final String OTHER_TAG = "heartDisease";
    private static final String OTHER_TAG_TWO = "children";
    private static final String OTHER_TAG_DESC = " " + PREFIX_TAG + OTHER_TAG;
    private static final String OTHER_TAG_DESC_TWO = " " + PREFIX_TAG + OTHER_TAG_TWO;

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, UID_DESC_AMY, EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, " " + PREFIX_UID + "-5" + NAME_DESC_AMY, Uid.MESSAGE_CONSTRAINTS);

        // zero index
        // assertParseFailure(parser, " " + PREFIX_UID + "0" + NAME_DESC_AMY,
        // Uid.MESSAGE_CONSTRAINTS);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, " " + PREFIX_UID + "1 some random string", Uid.MESSAGE_CONSTRAINTS);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, " " + PREFIX_UID + "1 i/ string", Uid.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " " + PREFIX_UID + "1" + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, " " + PREFIX_UID + "1" + INVALID_GENDER_DESC,
                Gender.MESSAGE_CONSTRAINTS); // invalid gender
        assertParseFailure(parser, " " + PREFIX_UID + "1" + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, " " + PREFIX_UID + "1" + INVALID_EMAIL_DESC,
                Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, " " + PREFIX_UID + "1" + INVALID_ADDRESS_DESC,
                Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, " " + PREFIX_UID + "1" + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, " " + PREFIX_UID + "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY,
                Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone
        // followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, " " + PREFIX_UID + "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code
        // Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, " " + PREFIX_UID + "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " " + PREFIX_UID + "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " " + PREFIX_UID + "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, " " + PREFIX_UID + "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                + VALID_ADDRESS_AMY + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = UID_DESC_AMY + GENDER_DESC_AMY + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withGender(VALID_GENDER_AMY).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = UID_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = UID_DESC_BOB + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(new Uid(VALID_UID_BOB), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // gender
        userInput = UID_DESC_BOB + GENDER_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withGender(VALID_GENDER_AMY).build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_BOB), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = UID_DESC_BOB + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_BOB), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = UID_DESC_BOB + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_BOB), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = UID_DESC_BOB + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_BOB), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = UID_DESC_BOB + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_BOB), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {

        String userInput = UID_DESC_AMY + PHONE_DESC_AMY + GENDER_DESC_AMY + ADDRESS_DESC_AMY
                + EMAIL_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withGender(VALID_GENDER_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsAll() {
        // tags with different inputs
        String userInput = UID_DESC_AMY + TAG_DESC_FRIEND
                + TAG_DESC_HUSBAND + OTHER_TAG_DESC + OTHER_TAG_DESC_TWO;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND,
                VALID_TAG_HUSBAND, OTHER_TAG, OTHER_TAG_TWO).build();
        EditCommand expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = UID_DESC_AMY + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = UID_DESC_AMY + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        String userInput = UID_DESC_AMY + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allDatesTimesCases_success() {
        // empty dateTimes, empty dateTimeIndexes
        String userInput = UID_DESC_AMY + DATE_AND_SLOT_EMPTY + DATE_AND_SLOT_INDEXES_EMPTY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withDatesSlots()
                .withDateSlotIndexes().build();
        EditCommand expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // empty dateTimeIndexes only
        userInput = UID_DESC_AMY + DATE_AND_SLOT_INDEXES_EMPTY;
        descriptor = new EditPersonDescriptorBuilder().withDateSlotIndexes().build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // non-empty dateTimes only
        userInput = UID_DESC_AMY + DATESLOT_DESC_BOB + DATESLOT_DESC_AMY + OTHER_DATES_AND_SLOTS_DESC;
        descriptor = new EditPersonDescriptorBuilder().withDatesSlots(VALID_DATESLOT_BOB,
                VALID_DATESLOT_AMY, OTHER_DATES_AND_SLOTS).build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // non-empty dateTimes, non-empty dateTimeIndexes
        userInput = UID_DESC_AMY + OTHER_DATES_AND_SLOTS_DESC_TWO + OTHER_DATES_AND_SLOTS_INDEX_DESC;
        descriptor = new EditPersonDescriptorBuilder().withDateSlotIndexes(OTHER_DATES_AND_SLOTS_INDEX)
                .withDatesSlots(OTHER_DATES_AND_SLOTS_TWO).build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // non-empty dateTimeIndexes only
        userInput = UID_DESC_AMY + OTHER_DATES_AND_SLOTS_INDEX_DESC + OTHER_DATES_AND_SLOTS_INDEX_DESC_TWO;
        descriptor = new EditPersonDescriptorBuilder().withDateSlotIndexes(OTHER_DATES_AND_SLOTS_INDEX,
                OTHER_DATES_AND_SLOTS_INDEX_TWO).build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // non-empty dateTimes and empty dateTimeIndexes
        userInput = UID_DESC_AMY + OTHER_DATES_AND_SLOTS_DESC_TWO + DATE_AND_SLOT_INDEXES_EMPTY;
        descriptor = new EditPersonDescriptorBuilder().withDatesSlots(OTHER_DATES_AND_SLOTS_TWO)
                .withDateSlotIndexes().build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // non-empty dateTimeIndexes and empty dateTimeIndexes
        userInput = UID_DESC_AMY + OTHER_DATES_AND_SLOTS_INDEX_DESC + DATE_AND_SLOT_EMPTY;
        descriptor = new EditPersonDescriptorBuilder().withDateSlotIndexes(OTHER_DATES_AND_SLOTS_INDEX)
                .withDatesSlots().build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // empty dateTimes only
        userInput = UID_DESC_AMY + DATE_AND_SLOT_EMPTY;
        descriptor = new EditPersonDescriptorBuilder().withDatesSlots().build();
        expectedCommand = new EditCommand(new Uid(VALID_UID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }
}
