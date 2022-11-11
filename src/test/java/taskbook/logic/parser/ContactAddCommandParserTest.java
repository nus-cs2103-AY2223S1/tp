package taskbook.logic.parser;

import static taskbook.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static taskbook.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static taskbook.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static taskbook.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static taskbook.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static taskbook.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static taskbook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static taskbook.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static taskbook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static taskbook.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static taskbook.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static taskbook.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static taskbook.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static taskbook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static taskbook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static taskbook.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static taskbook.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static taskbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static taskbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static taskbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static taskbook.testutil.TypicalTaskBook.AMY;
import static taskbook.testutil.TypicalTaskBook.BOB;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.contacts.ContactAddCommand;
import taskbook.logic.parser.contacts.ContactAddCommandParser;
import taskbook.model.person.Address;
import taskbook.model.person.Email;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.person.Phone;
import taskbook.model.tag.Tag;
import taskbook.testutil.PersonBuilder;

public class ContactAddCommandParserTest {

    private ContactAddCommandParser parser = new ContactAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new ContactAddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new ContactAddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new ContactAddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new ContactAddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new ContactAddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new ContactAddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalTagsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new ContactAddCommand(expectedPerson));
    }

    @Test
    public void parse_allOptionalFieldsMissing_success() {
        Person expectedPerson = new PersonBuilder(AMY)
                .withAddress(Address.NO_ADDRESS_PROVIDED)
                .withPhone(Phone.NO_PHONE_PROVIDED)
                .withEmail(Email.NO_EMAIL_PROVIDED)
                .withTags()
                .build();
        assertParseSuccess(parser, NAME_DESC_AMY,
                new ContactAddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ContactAddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ContactAddCommand.MESSAGE_USAGE));
    }
}
