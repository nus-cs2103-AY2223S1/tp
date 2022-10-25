package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SLACK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMEZONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SLACK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TIMEZONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SLACK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMEZONE_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetCommand;
import seedu.address.logic.commands.SetCommand.SetPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Role;
import seedu.address.model.person.Timezone;
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.person.contact.Email;
import seedu.address.model.person.contact.Phone;
import seedu.address.model.person.contact.Slack;
import seedu.address.model.person.contact.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.SetPersonDescriptorBuilder;

public class SetCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetCommand.MESSAGE_USAGE);

    private final SetCommandParser parser = new SetCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // nothing specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, INVALID_TIMEZONE_DESC, Timezone.MESSAGE_CONSTRAINTS); // invalid time
        assertParseFailure(parser, INVALID_TELEGRAM_DESC, Telegram.MESSAGE_CONSTRAINTS); // invalid tele
        assertParseFailure(parser, INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, INVALID_SLACK_DESC, Slack.MESSAGE_CONSTRAINTS); // invalid slack
        assertParseFailure(parser, INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS); // invalid role

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_ADDRESS_DESC + VALID_TAG_FRIEND,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = TAG_DESC_HUSBAND
                + ADDRESS_DESC_AMY
                + NAME_DESC_AMY
                + ROLE_DESC_AMY
                + TIMEZONE_DESC_AMY
                + TELEGRAM_DESC_AMY
                + EMAIL_DESC_AMY
                + PHONE_DESC_AMY
                + SLACK_DESC_AMY
                + TAG_DESC_FRIEND;

        SetPersonDescriptor descriptor =
                new SetPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                        .withAddress(VALID_ADDRESS_AMY)
                        .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                        .withRole(VALID_ROLE_AMY)
                        .withTimezone(VALID_TIMEZONE_AMY)
                        .withContact(ContactType.SLACK, VALID_SLACK_AMY)
                        .withContact(ContactType.EMAIL, VALID_EMAIL_AMY)
                        .withContact(ContactType.TELEGRAM, VALID_TELEGRAM_AMY)
                        .withContact(ContactType.PHONE, VALID_PHONE_AMY)
                        .build();
        SetCommand expectedCommand = new SetCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = NAME_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND;

        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withAddress(VALID_ADDRESS_BOB)
                .withName(VALID_NAME_BOB)
                .withTags(VALID_TAG_FRIEND)
                .build();
        SetCommand expectedCommand = new SetCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyNameSpecified_success() {
        String userInput = NAME_DESC_AMY;
        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .build();
        SetCommand expectedCommand = new SetCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyAddressSpecified_success() {
        String userInput = ADDRESS_DESC_AMY;
        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withAddress(VALID_ADDRESS_AMY)
                .build();
        SetCommand expectedCommand = new SetCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyRoleSpecified_success() {
        String userInput = ROLE_DESC_AMY;
        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withRole(VALID_ROLE_AMY)
                .build();
        SetCommand expectedCommand = new SetCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyTimezoneSpecified_success() {
        String userInput = TIMEZONE_DESC_AMY;
        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withTimezone(VALID_TIMEZONE_AMY)
                .build();
        SetCommand expectedCommand = new SetCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyTagsSpecified_success() {
        String userInput = TAG_DESC_FRIEND;
        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withTags(VALID_TAG_FRIEND)
                .build();
        SetCommand expectedCommand = new SetCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyTelegramSpecified_success() {
        String userInput = TELEGRAM_DESC_AMY;
        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withContact(ContactType.TELEGRAM, VALID_TELEGRAM_AMY)
                .build();
        SetCommand expectedCommand = new SetCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyEmailSpecified_success() {
        String userInput = EMAIL_DESC_AMY;
        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withContact(ContactType.EMAIL, VALID_EMAIL_AMY)
                .build();
        SetCommand expectedCommand = new SetCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyPhoneSpecified_success() {
        String userInput = PHONE_DESC_AMY;
        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withContact(ContactType.PHONE, VALID_PHONE_AMY)
                .build();
        SetCommand expectedCommand = new SetCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlySlackSpecified_success() {
        String userInput = SLACK_DESC_AMY;
        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withContact(ContactType.SLACK, VALID_SLACK_AMY)
                .build();
        SetCommand expectedCommand = new SetCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = ADDRESS_DESC_AMY
                + TAG_DESC_FRIEND
                + ADDRESS_DESC_AMY
                + TAG_DESC_FRIEND
                + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND;

        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();

        SetCommand expectedCommand = new SetCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = INVALID_ADDRESS_DESC + ADDRESS_DESC_BOB;
        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withAddress(VALID_ADDRESS_BOB)
                .build();
        SetCommand expectedCommand = new SetCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        String userInput = TAG_EMPTY;

        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withTags()
                .build();
        SetCommand expectedCommand = new SetCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
