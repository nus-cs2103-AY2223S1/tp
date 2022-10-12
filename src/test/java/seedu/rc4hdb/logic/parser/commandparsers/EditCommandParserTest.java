package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.EMAIL_DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.GENDER_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.GENDER_DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.HOUSE_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_NAME_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_ROOM_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.INVALID_TAG_DESC;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.MATRIC_NUMBER_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.NAME_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.PHONE_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.PHONE_DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.ROOM_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.ROOM_DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.TAG_DESC_FRIEND;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_EMAIL_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_GENDER_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_GENDER_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_HOUSE_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_MATRIC_NUMBER_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_NAME_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_PHONE_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_PHONE_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_ROOM_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_ROOM_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_FIRST_RESIDENT;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_SECOND_RESIDENT;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_THIRD_RESIDENT;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.logic.commands.modelcommands.EditCommand;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.tag.Tag;
import seedu.rc4hdb.testutil.ResidentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

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
        assertParseFailure(parser, "1" + INVALID_ROOM_DESC, Room.MESSAGE_CONSTRAINTS); // invalid room
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Resident} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ROOM_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_RESIDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ROOM_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND
                + GENDER_DESC_BOB + HOUSE_DESC_AMY + MATRIC_NUMBER_DESC_AMY;

        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withRoom(VALID_ROOM_AMY)
                .withGender(VALID_GENDER_BOB).withHouse(VALID_HOUSE_AMY).withMatricNumber(VALID_MATRIC_NUMBER_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_RESIDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_RESIDENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new ResidentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new ResidentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // room
        userInput = targetIndex.getOneBased() + ROOM_DESC_AMY;
        descriptor = new ResidentDescriptorBuilder().withRoom(VALID_ROOM_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // gender
        userInput = targetIndex.getOneBased() + GENDER_DESC_AMY;
        descriptor = new ResidentDescriptorBuilder().withGender(VALID_GENDER_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // house
        userInput = targetIndex.getOneBased() + HOUSE_DESC_AMY;
        descriptor = new ResidentDescriptorBuilder().withHouse(VALID_HOUSE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // matric number
        userInput = targetIndex.getOneBased() + MATRIC_NUMBER_DESC_AMY;
        descriptor = new ResidentDescriptorBuilder().withMatricNumber(VALID_MATRIC_NUMBER_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new ResidentDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_RESIDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ROOM_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ROOM_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + ROOM_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withRoom(VALID_ROOM_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_RESIDENT;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ROOM_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new ResidentDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withRoom(VALID_ROOM_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_RESIDENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
