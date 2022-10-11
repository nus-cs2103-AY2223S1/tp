package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.EMAIL_DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.GENDER_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.GENDER_DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.HOUSE_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.HOUSE_DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_GENDER_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_HOUSE_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_MATRIC_NUMBER_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_NAME_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_ROOM_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_TAG_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.MATRIC_NUMBER_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.MATRIC_NUMBER_DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.NAME_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.NAME_DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.PHONE_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.PHONE_DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.ROOM_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.ROOM_DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.TAG_DESC_FRIEND;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_GENDER_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_HOUSE_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_MATRIC_NUMBER_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_NAME_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_PHONE_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_ROOM_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;
import static seedu.rc4hdb.testutil.TypicalResidents.AMY;
import static seedu.rc4hdb.testutil.TypicalResidents.BOB;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.modelcommands.AddCommand;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.tag.Tag;
import seedu.rc4hdb.testutil.ResidentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Resident expectedResident = new ResidentBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROOM_DESC_BOB + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedResident));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROOM_DESC_BOB + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedResident));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROOM_DESC_BOB + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedResident));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ROOM_DESC_BOB + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedResident));

        // multiple rooms - last room accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_AMY
                + ROOM_DESC_BOB + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedResident));

        // multiple gender - last gender accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + GENDER_DESC_AMY + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedResident));

        // multiple houses - last house accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + GENDER_DESC_BOB + HOUSE_DESC_AMY + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedResident));

        // multiple matric numbers - last matric number accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_AMY + MATRIC_NUMBER_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedResident));

        // multiple tags - all accepted
        Resident expectedResidentMultipleTags = new ResidentBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                new AddCommand(expectedResidentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Resident expectedResident = new ResidentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ROOM_DESC_AMY
                + GENDER_DESC_AMY + HOUSE_DESC_AMY + MATRIC_NUMBER_DESC_AMY,
                new AddCommand(expectedResident));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                        + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                        + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ROOM_DESC_BOB
                        + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB,
                expectedMessage);

        // missing room prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ROOM_BOB
                        + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB,
                expectedMessage);

        // missing gender prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                        + VALID_GENDER_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB,
                expectedMessage);

        // missing house prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                        + GENDER_DESC_BOB + VALID_HOUSE_BOB + MATRIC_NUMBER_DESC_BOB,
                expectedMessage);

        // missing matric number prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                        + GENDER_DESC_BOB + HOUSE_DESC_BOB + VALID_MATRIC_NUMBER_BOB,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ROOM_BOB
                + VALID_GENDER_BOB + VALID_HOUSE_BOB + VALID_MATRIC_NUMBER_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ROOM_DESC_BOB
                + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid room
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ROOM_DESC
                + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Room.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + INVALID_GENDER_DESC + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Gender.MESSAGE_CONSTRAINTS);

        // invalid house
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + GENDER_DESC_BOB + INVALID_HOUSE_DESC + MATRIC_NUMBER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, House.MESSAGE_CONSTRAINTS);

        // invalid matric number
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + GENDER_DESC_BOB + HOUSE_DESC_BOB + INVALID_MATRIC_NUMBER_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, MatricNumber.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                        + GENDER_DESC_BOB + HOUSE_DESC_BOB + INVALID_MATRIC_NUMBER_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ROOM_DESC_BOB + GENDER_DESC_BOB + HOUSE_DESC_BOB + MATRIC_NUMBER_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
