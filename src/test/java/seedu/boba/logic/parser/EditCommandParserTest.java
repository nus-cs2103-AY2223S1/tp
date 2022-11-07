package seedu.boba.logic.parser;

import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.boba.logic.commands.CommandTestUtil.BIRTHDAY_MONTH_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.BIRTHDAY_MONTH_DESC_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_BIRTHDAY_MONTH_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_NEGATIVE_REWARD_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_REWARD_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.REWARD_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.REWARD_DESC_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.TAG_DESC_GOLD;
import static seedu.boba.logic.commands.CommandTestUtil.TAG_DESC_MEMBER;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_BIRTHDAY_MONTH_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_REWARD_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_GOLD;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_MEMBER;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.boba.testutil.TypicalEmails.EMAIL_FIRST_PERSON;
import static seedu.boba.testutil.TypicalEmails.EMAIL_SECOND_PERSON;
import static seedu.boba.testutil.TypicalEmails.EMAIL_THIRD_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_FIRST_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_SECOND_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.boba.logic.commands.EditCommand;
import seedu.boba.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.boba.model.customer.BirthdayMonth;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Name;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;
import seedu.boba.model.tag.Tag;
import seedu.boba.testutil.EditCustomerDescriptorBuilder;

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
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON, EditCommand.MESSAGE_NOT_EDITED);

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
    public void parse_phoneInvalidValue_failure() {
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON + INVALID_EMAIL_DESC,
                Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON + INVALID_REWARD_DESC,
                Reward.MESSAGE_MAX_INTEGER); // invalid reward
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON + INVALID_BIRTHDAY_MONTH_DESC,
                BirthdayMonth.MESSAGE_CONSTRAINTS); // invalid birthday month
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON + INVALID_PHONE_DESC + EMAIL_DESC_AMY,
                Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Customer} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON
                + TAG_DESC_MEMBER + TAG_DESC_GOLD + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON
                + TAG_DESC_MEMBER + TAG_EMPTY + TAG_DESC_GOLD, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON
                + TAG_EMPTY + TAG_DESC_MEMBER + TAG_DESC_GOLD, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON
                        + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_REWARD_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emailInvalidValue_failure() {
        assertParseFailure(parser, " " + PREFIX_EMAIL + EMAIL_FIRST_PERSON + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, " " + PREFIX_EMAIL + EMAIL_FIRST_PERSON + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, " " + PREFIX_EMAIL + EMAIL_FIRST_PERSON + INVALID_EMAIL_DESC,
                Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, " " + PREFIX_EMAIL + EMAIL_FIRST_PERSON + INVALID_REWARD_DESC,
                Reward.MESSAGE_MAX_INTEGER); // invalid reward
        assertParseFailure(parser, " " + PREFIX_EMAIL + EMAIL_FIRST_PERSON + INVALID_NEGATIVE_REWARD_DESC,
                Reward.MESSAGE_CONSTRAINTS); // invalid negative reward
        assertParseFailure(parser, " " + PREFIX_EMAIL + EMAIL_FIRST_PERSON + INVALID_BIRTHDAY_MONTH_DESC,
                BirthdayMonth.MESSAGE_CONSTRAINTS); // invalid birthday month
        assertParseFailure(parser, " " + PREFIX_EMAIL + EMAIL_FIRST_PERSON + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, " " + PREFIX_EMAIL + EMAIL_FIRST_PERSON + INVALID_PHONE_DESC + EMAIL_DESC_AMY,
                Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Customer} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, " " + PREFIX_EMAIL + EMAIL_FIRST_PERSON
                + TAG_DESC_MEMBER + TAG_DESC_GOLD + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " " + PREFIX_EMAIL + EMAIL_FIRST_PERSON
                + TAG_DESC_MEMBER + TAG_EMPTY + TAG_DESC_GOLD, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " " + PREFIX_EMAIL + EMAIL_FIRST_PERSON
                + TAG_EMPTY + TAG_DESC_MEMBER + TAG_DESC_GOLD, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, " " + PREFIX_EMAIL + EMAIL_FIRST_PERSON
                        + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_REWARD_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_phoneAllFieldsSpecified_success() {
        Phone targetPhone = PHONE_SECOND_PERSON;
        String userInput = " " + PREFIX_PHONE + targetPhone + PHONE_DESC_BOB + TAG_DESC_GOLD
                + EMAIL_DESC_AMY + REWARD_DESC_AMY + NAME_DESC_AMY + BIRTHDAY_MONTH_DESC_AMY + TAG_DESC_MEMBER;

        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withReward(VALID_REWARD_AMY)
                .withBirthdayMonth(VALID_BIRTHDAY_MONTH_AMY)
                .withTags(VALID_TAG_GOLD, VALID_TAG_MEMBER).build();
        EditCommand expectedCommand = new EditCommand(targetPhone, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emailAllFieldsSpecified_success() {
        Email targetEmail = EMAIL_SECOND_PERSON;
        String userInput = " " + PREFIX_EMAIL + targetEmail + PHONE_DESC_BOB + TAG_DESC_GOLD
                + EMAIL_DESC_AMY + REWARD_DESC_AMY + NAME_DESC_AMY + TAG_DESC_MEMBER;

        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withReward(VALID_REWARD_AMY)
                .withTags(VALID_TAG_GOLD, VALID_TAG_MEMBER).build();
        EditCommand expectedCommand = new EditCommand(targetEmail, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_phoneSomeFieldsSpecified_success() {
        Phone targetPhone = PHONE_FIRST_PERSON;
        String userInput = " " + PREFIX_PHONE + targetPhone + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(PHONE_FIRST_PERSON, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emailSomeFieldsSpecified_success() {
        Email targetEmail = EMAIL_FIRST_PERSON;
        String userInput = " " + PREFIX_EMAIL + targetEmail + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(EMAIL_FIRST_PERSON, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_phoneOneFieldSpecified_success() {
        // name
        Phone targetPhone = PHONE_THIRD_PERSON;
        String userInput = " " + PREFIX_PHONE + targetPhone + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder()
                .withPhone(targetPhone.value).withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetPhone, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = " " + PREFIX_PHONE + targetPhone + PHONE_DESC_AMY;
        descriptor = new EditCustomerDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetPhone, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = " " + PREFIX_PHONE + targetPhone + EMAIL_DESC_AMY;
        descriptor = new EditCustomerDescriptorBuilder()
                .withPhone(targetPhone.value).withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetPhone, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // reward
        userInput = " " + PREFIX_PHONE + targetPhone + REWARD_DESC_AMY;
        descriptor = new EditCustomerDescriptorBuilder()
                .withPhone(targetPhone.value).withReward(VALID_REWARD_AMY).build();
        expectedCommand = new EditCommand(targetPhone, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = " " + PREFIX_PHONE + targetPhone + TAG_DESC_MEMBER;
        descriptor = new EditCustomerDescriptorBuilder()
                .withPhone(targetPhone.value).withTags(VALID_TAG_MEMBER).build();
        expectedCommand = new EditCommand(targetPhone, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emailOneFieldSpecified_success() {
        // name
        Email targetEmail = EMAIL_THIRD_PERSON;
        String userInput = " " + PREFIX_EMAIL + targetEmail + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder()
                .withEmail(targetEmail.value).withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetEmail, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = " " + PREFIX_EMAIL + targetEmail + PHONE_DESC_AMY;
        descriptor = new EditCustomerDescriptorBuilder()
                .withEmail(targetEmail.value).withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetEmail, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = " " + PREFIX_EMAIL + targetEmail + EMAIL_DESC_AMY;
        descriptor = new EditCustomerDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetEmail, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // reward
        userInput = " " + PREFIX_EMAIL + targetEmail + REWARD_DESC_AMY;
        descriptor = new EditCustomerDescriptorBuilder()
                .withEmail(targetEmail.value).withReward(VALID_REWARD_AMY).build();
        expectedCommand = new EditCommand(targetEmail, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = " " + PREFIX_EMAIL + targetEmail + TAG_DESC_MEMBER;
        descriptor = new EditCustomerDescriptorBuilder()
                .withEmail(targetEmail.value).withTags(VALID_TAG_MEMBER).build();
        expectedCommand = new EditCommand(targetEmail, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_phoneAllFieldSpecified_success() {
        Phone targetPhone = PHONE_FIRST_PERSON;
        String userInput = " " + PREFIX_PHONE + targetPhone + PHONE_DESC_AMY + REWARD_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_MEMBER + TAG_DESC_GOLD;

        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder()
                .withPhone(VALID_PHONE_AMY)
                .withReward(VALID_REWARD_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_MEMBER, VALID_TAG_GOLD)
                .build();
        EditCommand expectedCommand = new EditCommand(targetPhone, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emailAllFieldSpecified_success() {
        Email targetEmail = EMAIL_FIRST_PERSON;
        String userInput = " " + PREFIX_EMAIL + targetEmail + PHONE_DESC_AMY + REWARD_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_MEMBER + TAG_DESC_GOLD;

        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder()
                .withPhone(VALID_PHONE_AMY)
                .withReward(VALID_REWARD_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_MEMBER, VALID_TAG_GOLD)
                .build();
        EditCommand expectedCommand = new EditCommand(targetEmail, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_phoneResetTags_success() {
        Phone targetPhone = PHONE_THIRD_PERSON;
        String userInput = " " + PREFIX_PHONE + targetPhone + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder()
                .withPhone(targetPhone.value).withTags().build();
        EditCommand expectedCommand = new EditCommand(targetPhone, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emailResetTags_success() {
        Email targetEmail = EMAIL_THIRD_PERSON;
        String userInput = " " + PREFIX_EMAIL + targetEmail + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder()
                .withEmail(targetEmail.value).withTags().build();
        EditCommand expectedCommand = new EditCommand(targetEmail, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_moreThanTwoPhone_failure() {
        String userInput = " " + PHONE_DESC_AMY + PHONE_DESC_BOB + PHONE_DESC_AMY;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_moreThanTwoEmail_failure() {
        String userInput = " " + EMAIL_DESC_AMY + EMAIL_DESC_BOB + EMAIL_DESC_AMY;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_moreThanOneName_failure() {
        String userInput = " " + PHONE_DESC_AMY + NAME_DESC_AMY + NAME_DESC_BOB;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_moreThanOneReward_failure() {
        String userInput = " " + PHONE_DESC_AMY + REWARD_DESC_AMY + REWARD_DESC_BOB;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_moreThanOneBirthdayMonth_failure() {
        String userInput = " " + PHONE_DESC_AMY + BIRTHDAY_MONTH_DESC_AMY + BIRTHDAY_MONTH_DESC_BOB;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }
}
