package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PARAMETER_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDITIONAL_NOTE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.APPENDED_ADDITIONAL_NOTE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NOK_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MONEY_OWED_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.MONEY_PAID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NOK_PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NOK_PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RATES_PER_CLASS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BEGINNER;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_INTERMEDIATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOK_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BEGINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTERMEDIATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withTags(VALID_TAG_BEGINNER).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_BEGINNER, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_BEGINNER, new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_BEGINNER, new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_BEGINNER, new AddCommand(expectedStudent));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_BEGINNER, new AddCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags =
                new StudentBuilder(BOB).withTags(VALID_TAG_BEGINNER, VALID_TAG_INTERMEDIATE).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_INTERMEDIATE + TAG_DESC_BEGINNER,
                new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + NOK_PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY, new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, expectedMessage);

        // missing next of kin phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_NOK_PHONE_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + VALID_EMAIL_BOB
                + ADDRESS_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_ADDRESS_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_NOK_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_ADDRESS_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_INTERMEDIATE + TAG_DESC_BEGINNER, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_INTERMEDIATE + TAG_DESC_BEGINNER, Phone.MESSAGE_CONSTRAINTS);

        // invalid next of kin phone
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_NOK_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_INTERMEDIATE + TAG_DESC_BEGINNER, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + TAG_DESC_INTERMEDIATE + TAG_DESC_BEGINNER, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + TAG_DESC_INTERMEDIATE + TAG_DESC_BEGINNER, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_BEGINNER, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_INTERMEDIATE + TAG_DESC_BEGINNER,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidParameters_failure() {
        // include rate parameter
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_INTERMEDIATE + TAG_DESC_BEGINNER + RATES_PER_CLASS_DESC_BOB,
                String.format(MESSAGE_INVALID_PARAMETER_FORMAT, AddCommand.RELEVANT_PARAMETERS));

        // include owed parameter
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_INTERMEDIATE + TAG_DESC_BEGINNER + MONEY_OWED_DESC_BOB,
                String.format(MESSAGE_INVALID_PARAMETER_FORMAT, AddCommand.RELEVANT_PARAMETERS));

        // include paid parameter
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_INTERMEDIATE + TAG_DESC_BEGINNER + MONEY_PAID_DESC_BOB,
                String.format(MESSAGE_INVALID_PARAMETER_FORMAT, AddCommand.RELEVANT_PARAMETERS));

        // include dt parameter
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_INTERMEDIATE + TAG_DESC_BEGINNER + CLASS_DESC_BOB,
                String.format(MESSAGE_INVALID_PARAMETER_FORMAT, AddCommand.RELEVANT_PARAMETERS));

        // include nt parameter
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_INTERMEDIATE + TAG_DESC_BEGINNER + ADDITIONAL_NOTE_DESC_BOB,
                String.format(MESSAGE_INVALID_PARAMETER_FORMAT, AddCommand.RELEVANT_PARAMETERS));

        // include nt-a parameter
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + APPENDED_ADDITIONAL_NOTE_DESC_BOB + TAG_DESC_INTERMEDIATE
                        + TAG_DESC_BEGINNER,
                String.format(MESSAGE_INVALID_PARAMETER_FORMAT, AddCommand.RELEVANT_PARAMETERS));
    }

}
