package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CLIENTTAG_DESC_CURRENT;
import static seedu.address.logic.commands.CommandTestUtil.CLIENTTAG_DESC_POTENTIAL;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INCOME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INCOME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLIENTTAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INCOME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MONTHLY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLANTAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RISKTAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MONTHLY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MONTHLY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PLANTAG_DESC_SAVINGS;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RISKTAG_DESC_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.RISKTAG_DESC_LOW;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENTTAG_POTENTIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INCOME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MONTHLY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLANTAG_SAVINGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISKTAG_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.IncomeLevel;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.ClientTag;
import seedu.address.model.tag.PlanTag;
import seedu.address.model.tag.RiskTag;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INCOME_DESC_BOB + MONTHLY_DESC_BOB
                + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS + CLIENTTAG_DESC_POTENTIAL
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INCOME_DESC_BOB + MONTHLY_DESC_BOB
                + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS + CLIENTTAG_DESC_POTENTIAL
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INCOME_DESC_BOB + MONTHLY_DESC_BOB
                + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS + CLIENTTAG_DESC_POTENTIAL
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INCOME_DESC_BOB + MONTHLY_DESC_BOB
                + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS + CLIENTTAG_DESC_POTENTIAL
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + INCOME_DESC_BOB + MONTHLY_DESC_BOB
                + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS + CLIENTTAG_DESC_POTENTIAL
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple income - last income accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MONTHLY_DESC_BOB + INCOME_DESC_AMY + INCOME_DESC_BOB
                + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS + CLIENTTAG_DESC_POTENTIAL
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        //multiple riskTags - last riskTag accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + INCOME_DESC_BOB + MONTHLY_DESC_BOB
                + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS + RISKTAG_DESC_HIGH
                + CLIENTTAG_DESC_POTENTIAL + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        //multiple planTags - last planTag accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + INCOME_DESC_BOB + MONTHLY_DESC_BOB
                + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS + PLANTAG_DESC_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        //multiple clientTags - last planTag accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + INCOME_DESC_BOB + MONTHLY_DESC_BOB
                + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS + CLIENTTAG_DESC_CURRENT
                + CLIENTTAG_DESC_POTENTIAL + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS + TAG_DESC_HUSBAND
                + CLIENTTAG_DESC_POTENTIAL + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + INCOME_DESC_AMY + MONTHLY_DESC_AMY + RISKTAG_DESC_LOW
                        + PLANTAG_DESC_SAVINGS + CLIENTTAG_DESC_CURRENT,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH
                + PLANTAG_DESC_SAVINGS + CLIENTTAG_DESC_POTENTIAL, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH
                + PLANTAG_DESC_SAVINGS + CLIENTTAG_DESC_POTENTIAL, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                        + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH
                + PLANTAG_DESC_SAVINGS + CLIENTTAG_DESC_POTENTIAL, expectedMessage);

        // missing income prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + VALID_INCOME_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL, expectedMessage);

        // missing monthly prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INCOME_DESC_BOB + VALID_MONTHLY_BOB + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS
                        + CLIENTTAG_DESC_POTENTIAL,
                expectedMessage);
        // missing riskTag prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB + VALID_RISKTAG_HIGH + PLANTAG_DESC_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL, expectedMessage);

        // missing planTag prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH + VALID_PLANTAG_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL, expectedMessage);
        // missing clientTag prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS
                + VALID_CLIENTTAG_POTENTIAL, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                + VALID_INCOME_BOB + VALID_MONTHLY_BOB + VALID_RISKTAG_HIGH + VALID_PLANTAG_SAVINGS
                + VALID_CLIENTTAG_POTENTIAL, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);
        // invalid income
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_INCOME_DESC + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, IncomeLevel.MESSAGE_CONSTRAINTS);
        // invalid monthly
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + INCOME_DESC_BOB + INVALID_MONTHLY_DESC + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid riskTag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB + PLANTAG_DESC_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL + INVALID_RISKTAG_DESC + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, RiskTag.MESSAGE_CONSTRAINTS);

        // invalid planTag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB
                + RISKTAG_DESC_HIGH + INVALID_PLANTAG_DESC + TAG_DESC_HUSBAND
                + CLIENTTAG_DESC_POTENTIAL + TAG_DESC_FRIEND, PlanTag.MESSAGE_CONSTRAINTS);
        // invalid clientTag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB
                + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS + TAG_DESC_HUSBAND
                + INVALID_CLIENTTAG_DESC + TAG_DESC_FRIEND, ClientTag.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS
                + CLIENTTAG_DESC_POTENTIAL + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                        + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH
                + PLANTAG_DESC_SAVINGS + CLIENTTAG_DESC_POTENTIAL , Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INCOME_DESC_BOB + MONTHLY_DESC_BOB + RISKTAG_DESC_HIGH + PLANTAG_DESC_SAVINGS
                        + CLIENTTAG_DESC_POTENTIAL + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
