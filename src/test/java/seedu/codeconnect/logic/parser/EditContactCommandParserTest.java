package seedu.codeconnect.logic.parser;

import static seedu.codeconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codeconnect.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.GITHUB_DESC_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_MODS_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.MODULE_DESC_COMBINED;
import static seedu.codeconnect.logic.commands.CommandTestUtil.MODULE_DESC_CS1101;
import static seedu.codeconnect.logic.commands.CommandTestUtil.MODULE_DESC_CS2030S;
import static seedu.codeconnect.logic.commands.CommandTestUtil.MODULE_DESC_FINISH_TP;
import static seedu.codeconnect.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.codeconnect.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.codeconnect.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_MODULE_CS1101;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_MODULE_CS2030S;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_MODULE_FINISH_TP;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.codeconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.codeconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.codeconnect.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.codeconnect.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.codeconnect.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.codeconnect.commons.core.index.Index;
import seedu.codeconnect.logic.commands.EditContactCommand;
import seedu.codeconnect.logic.commands.EditContactCommand.EditPersonDescriptor;
import seedu.codeconnect.model.module.Module;
import seedu.codeconnect.model.person.Address;
import seedu.codeconnect.model.person.Email;
import seedu.codeconnect.model.person.Github;
import seedu.codeconnect.model.person.Name;
import seedu.codeconnect.model.person.Phone;
import seedu.codeconnect.model.person.Telegram;
import seedu.codeconnect.model.tag.Tag;
import seedu.codeconnect.testutil.EditPersonDescriptorBuilder;

public class EditContactCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditContactCommand.MESSAGE_USAGE);

    private EditContactCommandParser parser = new EditContactCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditContactCommand.MESSAGE_NOT_EDITED);

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
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_GITHUB_DESC, Github.MESSAGE_CONSTRAINTS); // invalid github
        assertParseFailure(parser, "1" + INVALID_TELEGRAM_DESC, Telegram.MESSAGE_CONSTRAINTS); // invalid tele
        assertParseFailure(parser, "1" + INVALID_MODS_DESC, Module.MESSAGE_CONSTRAINTS); // invalid mods

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY
                        + VALID_PHONE_AMY + VALID_MODULE_CS1101 + VALID_GITHUB_AMY + INVALID_TELEGRAM_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND + MODULE_DESC_CS1101
                + GITHUB_DESC_AMY + TELEGRAM_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withModules(VALID_MODULE_CS1101)
                .withGithub(VALID_GITHUB_AMY).withTelegram(VALID_TELEGRAM_AMY).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // mods
        userInput = targetIndex.getOneBased() + MODULE_DESC_CS1101;
        descriptor = new EditPersonDescriptorBuilder().withModules(VALID_MODULE_CS1101).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // github
        userInput = targetIndex.getOneBased() + GITHUB_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withGithub(VALID_GITHUB_AMY).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // telegram
        userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withTelegram(VALID_TELEGRAM_AMY).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + MODULE_DESC_CS1101 + ADDRESS_DESC_AMY
                + EMAIL_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_CS2030S
                + TAG_DESC_HUSBAND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .withModules(VALID_MODULE_CS2030S, VALID_MODULE_CS1101).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /** m/A B m/C -> m == [A,B,C] */
    @Test
    public void parse_spaceSeparatedAndMultipleModules_acceptsAll() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + MODULE_DESC_COMBINED + ADDRESS_DESC_AMY
                + EMAIL_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_FINISH_TP
                + TAG_DESC_HUSBAND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .withModules(VALID_MODULE_CS2030S, VALID_MODULE_CS1101, VALID_MODULE_FINISH_TP).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB + MODULE_DESC_CS2030S;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withModules(VALID_MODULE_CS2030S).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
