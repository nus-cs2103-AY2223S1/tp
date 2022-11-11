package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.BIRTHDAY_DESC_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.BIRTHDAY_DESC_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.INCOME_DESC_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.INCOME_DESC_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static longtimenosee.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static longtimenosee.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static longtimenosee.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static longtimenosee.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static longtimenosee.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static longtimenosee.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static longtimenosee.logic.commands.CommandTestUtil.RISK_APPETITE_DESC_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.RISK_APPETITE_DESC_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static longtimenosee.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseFailure;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static longtimenosee.testutil.TypicalPersons.AMY;
import static longtimenosee.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import longtimenosee.logic.commands.AddCommand;
import longtimenosee.model.person.Address;
import longtimenosee.model.person.Email;
import longtimenosee.model.person.Name;
import longtimenosee.model.person.Person;
import longtimenosee.model.person.Phone;
import longtimenosee.model.tag.Tag;
import longtimenosee.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB
                + RISK_APPETITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB
                + RISK_APPETITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB
                + RISK_APPETITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB
                + RISK_APPETITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB
                + RISK_APPETITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB
                + RISK_APPETITE_DESC_BOB, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                       + BIRTHDAY_DESC_AMY + INCOME_DESC_AMY + RISK_APPETITE_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB + RISK_APPETITE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB + RISK_APPETITE_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB + RISK_APPETITE_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB + RISK_APPETITE_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB + RISK_APPETITE_DESC_BOB,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                        + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB + RISK_APPETITE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + BIRTHDAY_DESC_BOB + INCOME_DESC_BOB
                        + RISK_APPETITE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
