package seedu.rc4hdb.logic.parser;

import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.GENDER_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.HOUSE_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_GENDER_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_HOUSE_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_MATRIC_NUMBER_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_NAME_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_ROOM_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_TAG_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.MATRIC_NUMBER_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.NAME_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.PHONE_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.PHONE_DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.ROOM_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.TAG_DESC_FRIEND;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_EMAIL_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_GENDER_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_HOUSE_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_MATRIC_NUMBER_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_NAME_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_PHONE_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_ROOM_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.modelcommands.FilterCommand;
import seedu.rc4hdb.logic.parser.commandparsers.FilterCommandParser;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.tag.Tag;
import seedu.rc4hdb.testutil.ResidentDescriptorBuilder;

public class FilterCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE);

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "filter", FilterCommand.MESSAGE_NOT_FILTERED);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "something else", FilterCommand.MESSAGE_NOT_FILTERED);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "l/ string", FilterCommand.MESSAGE_NOT_FILTERED);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, INVALID_MATRIC_NUMBER_DESC,
                MatricNumber.MESSAGE_CONSTRAINTS); // invalid matric number
        assertParseFailure(parser, INVALID_HOUSE_DESC, House.MESSAGE_CONSTRAINTS); // invalid house
        assertParseFailure(parser, INVALID_GENDER_DESC, Gender.MESSAGE_CONSTRAINTS); // invalid gender
        assertParseFailure(parser, INVALID_ROOM_DESC, Room.MESSAGE_CONSTRAINTS); // invalid room

        // invalid phone followed by valid email
        assertParseFailure(parser, INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Resident} being Filtered,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + HOUSE_DESC_AMY
                + ROOM_DESC_AMY + GENDER_DESC_AMY + MATRIC_NUMBER_DESC_AMY + TAG_DESC_FRIEND;

        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withRoom(VALID_ROOM_AMY)
                .withGender(VALID_GENDER_AMY).withHouse(VALID_HOUSE_AMY).withTags(VALID_TAG_FRIEND)
                .withMatricNumber(VALID_MATRIC_NUMBER_AMY).build();
        FilterCommand expectedCommand = new FilterCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = PHONE_DESC_AMY + EMAIL_DESC_AMY;

        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).build();
        FilterCommand expectedCommand = new FilterCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
