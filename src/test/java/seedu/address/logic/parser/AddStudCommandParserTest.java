package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PARENT_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PARENT_NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudCommand;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Id;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.StudentBuilder;

public class AddStudCommandParserTest {
    private AddStudCommandParser parser = new AddStudCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedPerson = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STUDENT_NAME_DESC_BOB + ID_DESC_BOB + PARENT_NAME_DESC_BOB
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddStudCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_AMY + STUDENT_NAME_DESC_BOB + ID_DESC_BOB + PARENT_NAME_DESC_BOB
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddStudCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + PARENT_NAME_DESC_BOB + PHONE_DESC_AMY
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddStudCommand(expectedPerson));

        // multiple ids - last id accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + ID_DESC_AMY + ID_DESC_BOB + PARENT_NAME_DESC_BOB
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddStudCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddStudCommand(expectedPerson));

        // multiple tags - all accepted
        Student expectedPersonMultipleTags = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, new AddStudCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedPerson = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, STUDENT_NAME_DESC_AMY + ID_DESC_AMY + PARENT_NAME_DESC_AMY
                        + PHONE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND,
                new AddStudCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_STUDENT_NAME_BOB + PHONE_DESC_BOB + ID_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + VALID_PHONE_BOB + ID_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing id prefix
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + PHONE_DESC_BOB + VALID_ID_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + PHONE_DESC_BOB + ID_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_STUDENT_NAME_BOB + VALID_PHONE_BOB + VALID_ID_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_STUDENT_NAME_DESC + ID_DESC_BOB + PARENT_NAME_DESC_BOB
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + PARENT_NAME_DESC_BOB
                + INVALID_PHONE_DESC + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid id
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + INVALID_ID_DESC + PARENT_NAME_DESC_BOB
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, Id.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + PARENT_NAME_DESC_BOB
                + PHONE_DESC_BOB + INVALID_ADDRESS_DESC + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + PARENT_NAME_DESC_BOB
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + INVALID_TAG_DESC + TAG_DESC_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_STUDENT_NAME_DESC + ID_DESC_BOB + PARENT_NAME_DESC_BOB
                        + PHONE_DESC_BOB + INVALID_ADDRESS_DESC + TAG_DESC_FRIEND,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + STUDENT_NAME_DESC_BOB + ID_DESC_BOB + PARENT_NAME_DESC_BOB
                        + PHONE_DESC_BOB + INVALID_ADDRESS_DESC + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudCommand.MESSAGE_USAGE));

    }
}
