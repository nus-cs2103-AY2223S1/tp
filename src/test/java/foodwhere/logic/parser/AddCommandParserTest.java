package foodwhere.logic.parser;

import static foodwhere.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static foodwhere.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static foodwhere.logic.commands.CommandTestUtil.DETAIL_DESC_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.DETAIL_DESC_HUSBAND;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_DETAIL_DESC;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static foodwhere.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static foodwhere.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static foodwhere.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static foodwhere.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static foodwhere.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static foodwhere.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static foodwhere.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_DETAIL_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_DETAIL_HUSBAND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.logic.commands.AddCommand;
import foodwhere.model.detail.Detail;
import foodwhere.model.person.Address;
import foodwhere.model.person.Name;
import foodwhere.model.person.Person;
import foodwhere.model.person.Phone;
import foodwhere.testutil.PersonBuilder;
import foodwhere.testutil.TypicalPersons;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(TypicalPersons.BOB).withDetails(VALID_DETAIL_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + DETAIL_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + DETAIL_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + DETAIL_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB  + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + DETAIL_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple details - all accepted
        Person expectedPersonMultipleDetails =
                new PersonBuilder(TypicalPersons.BOB).withDetails(VALID_DETAIL_FRIEND, VALID_DETAIL_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + DETAIL_DESC_HUSBAND + DETAIL_DESC_FRIEND, new AddCommand(expectedPersonMultipleDetails));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero details
        Person expectedPerson = new PersonBuilder(TypicalPersons.AMY).withDetails().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + DETAIL_DESC_HUSBAND + DETAIL_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + DETAIL_DESC_HUSBAND + DETAIL_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_ADDRESS_DESC
                + DETAIL_DESC_HUSBAND + DETAIL_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid detail
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_DETAIL_DESC + VALID_DETAIL_FRIEND, Detail.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB+ INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + DETAIL_DESC_HUSBAND + DETAIL_DESC_FRIEND,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
