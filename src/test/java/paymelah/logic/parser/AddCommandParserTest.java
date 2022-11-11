package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static paymelah.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static paymelah.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static paymelah.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static paymelah.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static paymelah.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static paymelah.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static paymelah.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static paymelah.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static paymelah.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static paymelah.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static paymelah.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static paymelah.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static paymelah.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static paymelah.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static paymelah.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static paymelah.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static paymelah.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static paymelah.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static paymelah.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static paymelah.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static paymelah.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static paymelah.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseFailure;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static paymelah.testutil.TypicalPersons.AMY;
import static paymelah.testutil.TypicalPersons.BOB;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import paymelah.logic.commands.AddCommand;
import paymelah.model.debt.DebtList;
import paymelah.model.person.Address;
import paymelah.model.person.Name;
import paymelah.model.person.Person;
import paymelah.model.person.Phone;
import paymelah.model.person.Telegram;
import paymelah.model.tag.Tag;
import paymelah.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).withDebts().build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + TELEGRAM_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple telegram handles - last telegram handle accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_DESC_AMY + TELEGRAM_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .withDebts().build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().withDebts().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + TELEGRAM_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));

        // all fields missing except name
        expectedPerson = new Person(new Name(VALID_NAME_BOB), Phone.EMPTY_PHONE, Telegram.EMPTY_TELEGRAM,
                Address.EMPTY_ADDRESS, Collections.emptySet(), new DebtList());
        assertParseSuccess(parser, NAME_DESC_BOB, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + TELEGRAM_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_TELEGRAM_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + TELEGRAM_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + TELEGRAM_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid telegram handle
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_TELEGRAM_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Telegram.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + TELEGRAM_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
