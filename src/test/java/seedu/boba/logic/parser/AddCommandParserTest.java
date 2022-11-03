package seedu.boba.logic.parser;

import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.boba.logic.commands.CommandTestUtil.BIRTHDAY_MONTH_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.BIRTHDAY_MONTH_DESC_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.EXCEED_MAX_INTEGER_REWARD_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_BIRTHDAY_MONTH_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_REWARD_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.boba.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.boba.logic.commands.CommandTestUtil.REWARD_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.REWARD_DESC_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.TAG_DESC_GOLD;
import static seedu.boba.logic.commands.CommandTestUtil.TAG_DESC_MEMBER;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_BIRTHDAY_MONTH_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_REWARD_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_GOLD;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_MEMBER;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.boba.testutil.TypicalCustomers.AMY;
import static seedu.boba.testutil.TypicalCustomers.BOB;

import org.junit.jupiter.api.Test;

import seedu.boba.logic.commands.AddCommand;
import seedu.boba.model.customer.BirthdayMonth;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Name;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;
import seedu.boba.model.tag.Tag;
import seedu.boba.testutil.CustomerBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Customer expectedCustomer = new CustomerBuilder(BOB).withTags(VALID_TAG_MEMBER).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB + TAG_DESC_MEMBER, new AddCommand(expectedCustomer));

        // multiple tags - all accepted
        Customer expectedCustomerMultipleTags = new CustomerBuilder(BOB).withTags(VALID_TAG_MEMBER, VALID_TAG_GOLD)
            .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + BIRTHDAY_MONTH_DESC_BOB
            + REWARD_DESC_BOB + TAG_DESC_GOLD + TAG_DESC_MEMBER, new AddCommand(expectedCustomerMultipleTags));
    }

    @Test
    public void parse_multipleUniqueFields_failure() {
        String message = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // multiple names - rejected
        assertParseFailure(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB + TAG_DESC_MEMBER, message);

        // multiple phones - rejected
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB + TAG_DESC_MEMBER, message);

        // multiple emails - rejected
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
            + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB + TAG_DESC_MEMBER, message);

        // multiple birthdayMonths - rejected
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + BIRTHDAY_MONTH_DESC_AMY
            + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB + TAG_DESC_MEMBER, message);

        // multiple rewards - rejected
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REWARD_DESC_AMY
            + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB + TAG_DESC_MEMBER, message);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Customer expectedCustomer = new CustomerBuilder(AMY).withTags().build();
        assertParseSuccess(parser,
            NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + BIRTHDAY_MONTH_DESC_AMY + REWARD_DESC_AMY,
            new AddCommand(expectedCustomer));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
            VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(parser,
            NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB,
            expectedMessage);

        // missing email prefix
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB,
            expectedMessage);

        // missing birthdayMonth prefix
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_BIRTHDAY_MONTH_BOB + VALID_REWARD_BOB,
            expectedMessage);

        // missing reward prefix
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + BIRTHDAY_MONTH_DESC_BOB + VALID_REWARD_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
            VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_BIRTHDAY_MONTH_BOB + VALID_REWARD_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
            INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB
            + TAG_DESC_GOLD + TAG_DESC_MEMBER, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser,
            NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB
            + TAG_DESC_GOLD + TAG_DESC_MEMBER, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB
            + TAG_DESC_GOLD + TAG_DESC_MEMBER, Email.MESSAGE_CONSTRAINTS);

        // invalid birthdayMonth
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_BIRTHDAY_MONTH_DESC + REWARD_DESC_BOB
                + TAG_DESC_GOLD + TAG_DESC_MEMBER, BirthdayMonth.MESSAGE_CONSTRAINTS);

        // invalid reward
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + BIRTHDAY_MONTH_DESC_BOB + INVALID_REWARD_DESC
            + TAG_DESC_GOLD + TAG_DESC_MEMBER, Reward.MESSAGE_CONSTRAINTS);

        // exceed max integer reward
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + BIRTHDAY_MONTH_DESC_BOB
                + EXCEED_MAX_INTEGER_REWARD_DESC + TAG_DESC_GOLD + TAG_DESC_MEMBER, Reward.MESSAGE_MAX_INTEGER);

        // invalid tag
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB
            + INVALID_TAG_DESC + VALID_TAG_MEMBER, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
            INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + BIRTHDAY_MONTH_DESC_BOB + INVALID_REWARD_DESC,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + BIRTHDAY_MONTH_DESC_BOB + REWARD_DESC_BOB + TAG_DESC_GOLD + TAG_DESC_MEMBER,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
