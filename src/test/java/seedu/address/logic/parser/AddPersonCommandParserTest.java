package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.PersonCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.PersonCommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.PersonCommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.PersonCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.PersonCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.PersonCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.PersonCommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.PersonCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.PersonCommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.PersonCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.PersonCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.PersonCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.PersonCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.PersonCommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.PersonCommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddPersonCommandParserTest {
    private AddPersonCommandParser parser = new AddPersonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddPersonCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPersonTags = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY,
                new AddPersonCommand(expectedPersonTags));
        // zero phone
        Person expectedPersonPhone = new PersonBuilder(AMY).withPhone(null).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY,
                new AddPersonCommand(expectedPersonPhone));

        // zero email
        Person expectedPersonEmail = new PersonBuilder(AMY).withEmail(null).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY,
                new AddPersonCommand(expectedPersonEmail));

        // zero email and phone
        Person expectedPersonEmailAndPhone = new PersonBuilder(AMY).withEmail(null).withPhone(null).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY,
                new AddPersonCommand(expectedPersonEmailAndPhone));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
    }
}
