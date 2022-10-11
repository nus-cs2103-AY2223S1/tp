package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_UID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.UID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.UID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UID_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.category.Category;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withCategory(VALID_CATEGORY_BOB).withTags(VALID_TAG_FRIEND)
                .build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB
                + GENDER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple categories - last category accepted
        assertParseSuccess(parser,  UID_DESC_BOB + CATEGORY_DESC_AMY + CATEGORY_DESC_BOB + NAME_DESC_BOB
                + GENDER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_AMY + NAME_DESC_BOB
                + GENDER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple genders - last gender accepted
        assertParseSuccess(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB
                + GENDER_DESC_AMY + GENDER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB
                + GENDER_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple dateTimes - all accepted
        Person expectedPersonMultipleDatesTimes = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND)
                .withDatesTimes(VALID_DATETIME_BOB, VALID_DATETIME_AMY).build();
        assertParseSuccess(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DATETIME_DESC_AMY + DATETIME_DESC_BOB
                        + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleDatesTimes));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND
                        + TAG_DESC_HUSBAND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero dateTimes
        Person expectedPersonNoDatesTimes = new PersonBuilder(AMY).withDatesTimes().build();
        assertParseSuccess(parser, UID_DESC_AMY + CATEGORY_DESC_AMY + NAME_DESC_AMY + GENDER_DESC_AMY
                + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedPersonNoDatesTimes));

        // zero tags
        Person expectedPersonNoTag = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, UID_DESC_AMY + CATEGORY_DESC_AMY + NAME_DESC_AMY + GENDER_DESC_AMY
                + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + DATETIME_DESC_AMY,
                new AddCommand(expectedPersonNoTag));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing uid prefix
        assertParseFailure(parser, VALID_UID_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB, expectedMessage);
        // missing category prefix
        assertParseFailure(parser, UID_DESC_BOB + VALID_CATEGORY_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB, expectedMessage);

        // missing name prefix
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + VALID_NAME_BOB + GENDER_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB, expectedMessage);

        // missing gender prefix
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + VALID_GENDER_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_UID_BOB + VALID_CATEGORY_BOB + VALID_NAME_BOB + VALID_GENDER_BOB
                + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid id
        assertParseFailure(parser, INVALID_UID_DESC + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DATETIME_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Uid.MESSAGE_CONSTRAINTS);
        // invalid category
        assertParseFailure(parser, UID_DESC_BOB + INVALID_CATEGORY_DESC + NAME_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND, Category.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + INVALID_NAME_DESC + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + INVALID_GENDER_DESC
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND, Gender.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                        + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + DATETIME_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid datesTimes
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + INVALID_DATETIME_DESC + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND, DateTime.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DATETIME_DESC_BOB + INVALID_TAG_DESC
                        + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, UID_DESC_BOB + CATEGORY_DESC_BOB + INVALID_NAME_DESC + GENDER_DESC_BOB
                 + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + DATETIME_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + UID_DESC_BOB + CATEGORY_DESC_BOB + NAME_DESC_BOB
                        + GENDER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + DATETIME_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,

                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
