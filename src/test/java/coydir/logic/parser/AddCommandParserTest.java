package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static coydir.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static coydir.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static coydir.logic.commands.CommandTestUtil.DEPARTMENT_DESC_AMY;
import static coydir.logic.commands.CommandTestUtil.DEPARTMENT_DESC_BOB;
import static coydir.logic.commands.CommandTestUtil.DEPARTMENT_DESC_PRITTAM;
import static coydir.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static coydir.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static coydir.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static coydir.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_DESC;
import static coydir.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static coydir.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static coydir.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static coydir.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static coydir.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static coydir.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static coydir.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static coydir.logic.commands.CommandTestUtil.NAME_DESC_PRITTAM;
import static coydir.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static coydir.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static coydir.logic.commands.CommandTestUtil.POSITION_DESC_AMY;
import static coydir.logic.commands.CommandTestUtil.POSITION_DESC_BOB;
import static coydir.logic.commands.CommandTestUtil.POSITION_DESC_PRITTAM;
import static coydir.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static coydir.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static coydir.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static coydir.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static coydir.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_DEPARTMENT_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static coydir.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static coydir.logic.parser.CommandParserTestUtil.assertParseFailure;
import static coydir.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static coydir.testutil.TypicalPersons.AMY;
import static coydir.testutil.TypicalPersons.BOB;
import static coydir.testutil.TypicalPersons.PRITTAM;

import org.junit.jupiter.api.Test;

import coydir.logic.commands.AddCommand;
import coydir.model.person.Address;
import coydir.model.person.Department;
import coydir.model.person.Email;
import coydir.model.person.EmployeeId;
import coydir.model.person.Name;
import coydir.model.person.Person;
import coydir.model.person.Phone;
import coydir.model.person.Position;
import coydir.model.tag.Tag;
import coydir.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        String newId = String.valueOf(EmployeeId.getCount());
        Person expectedPerson = new PersonBuilder(BOB).withEmployeeId(newId).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + POSITION_DESC_BOB + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple names - last name accepted
        newId = String.valueOf(EmployeeId.getCount());
        expectedPerson = new PersonBuilder(BOB).withEmployeeId(newId).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + POSITION_DESC_BOB + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        newId = String.valueOf(EmployeeId.getCount());
        expectedPerson = new PersonBuilder(BOB).withEmployeeId(newId).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + POSITION_DESC_BOB + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        newId = String.valueOf(EmployeeId.getCount());
        expectedPerson = new PersonBuilder(BOB).withEmployeeId(newId).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + POSITION_DESC_BOB + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple positions - last position accepted
        newId = String.valueOf(EmployeeId.getCount());
        expectedPerson = new PersonBuilder(BOB).withEmployeeId(newId).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + POSITION_DESC_AMY
                + POSITION_DESC_BOB + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple departments - last department accepted
        newId = String.valueOf(EmployeeId.getCount());
        expectedPerson = new PersonBuilder(BOB).withEmployeeId(newId).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + POSITION_DESC_BOB
                + DEPARTMENT_DESC_AMY + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        newId = String.valueOf(EmployeeId.getCount());
        expectedPerson = new PersonBuilder(BOB).withEmployeeId(newId).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + POSITION_DESC_BOB
                + DEPARTMENT_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple tags - all accepted
        newId = String.valueOf(EmployeeId.getCount());
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withEmployeeId(newId)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + POSITION_DESC_BOB
                + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // missing tags, leave
        String newId = String.valueOf(EmployeeId.getCount());
        Person expectedPerson = new PersonBuilder(AMY).withEmployeeId(newId).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + POSITION_DESC_AMY + DEPARTMENT_DESC_AMY + ADDRESS_DESC_AMY, new AddCommand(expectedPerson));

        // only compulsory fields
        newId = String.valueOf(EmployeeId.getCount());
        Person expectedPersonMissingOptional = new PersonBuilder(PRITTAM).withEmployeeId(newId).withTags().build();
        assertParseSuccess(parser, NAME_DESC_PRITTAM + POSITION_DESC_PRITTAM + DEPARTMENT_DESC_PRITTAM,
                new AddCommand(expectedPersonMissingOptional));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + POSITION_DESC_BOB + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB, expectedMessage);

        // missing position prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_POSITION_BOB + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB, expectedMessage);

        // missing department prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + POSITION_DESC_BOB + VALID_DEPARTMENT_BOB + ADDRESS_DESC_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_POSITION_BOB + VALID_DEPARTMENT_BOB + VALID_ADDRESS_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + POSITION_DESC_BOB
                + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + POSITION_DESC_BOB
                + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + POSITION_DESC_BOB
                + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Email.MESSAGE_CONSTRAINTS);

        // invalid position
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_POSITION_DESC
                + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Position.MESSAGE_CONSTRAINTS);

        // invalid department
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + POSITION_DESC_BOB
                + INVALID_DEPARTMENT_DESC + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Department.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + POSITION_DESC_BOB
                + DEPARTMENT_DESC_BOB + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + POSITION_DESC_BOB
                + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + POSITION_DESC_BOB
                + DEPARTMENT_DESC_BOB + INVALID_ADDRESS_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + POSITION_DESC_BOB + DEPARTMENT_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
