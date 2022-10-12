package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_PROJECT_NAME_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static jeryl.fyp.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static jeryl.fyp.logic.commands.CommandTestUtil.PROJECT_NAME_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.PROJECT_NAME_DESC_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static jeryl.fyp.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static jeryl.fyp.testutil.TypicalStudents.AMY;
import static jeryl.fyp.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.commands.AddCommand;
import jeryl.fyp.model.student.Email;
import jeryl.fyp.model.student.Name;
import jeryl.fyp.model.student.ProjectName;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.tag.Tag;
import jeryl.fyp.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + PROJECT_NAME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + PROJECT_NAME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple student IDs - last student ID accepted
        assertParseSuccess(parser, NAME_DESC_BOB + STUDENT_ID_DESC_AMY + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + PROJECT_NAME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + PROJECT_NAME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple project names - last project name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + PROJECT_NAME_DESC_AMY
                + PROJECT_NAME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + PROJECT_NAME_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + STUDENT_ID_DESC_AMY + EMAIL_DESC_AMY
                + PROJECT_NAME_DESC_AMY, new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + PROJECT_NAME_DESC_BOB,
                expectedMessage);

        // missing student ID prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_STUDENT_ID_BOB + EMAIL_DESC_BOB + PROJECT_NAME_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + VALID_EMAIL_BOB + PROJECT_NAME_DESC_BOB,
                expectedMessage);

        // missing project name prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + VALID_PROJECT_NAME_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_STUDENT_ID_BOB + VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + PROJECT_NAME_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid student ID
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_STUDENT_ID_DESC + EMAIL_DESC_BOB
                + PROJECT_NAME_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, StudentId.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + INVALID_EMAIL_DESC
                + PROJECT_NAME_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid project name
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_PROJECT_NAME_DESC + INVALID_TAG_DESC + VALID_TAG_FRIEND, ProjectName.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + PROJECT_NAME_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                        + PROJECT_NAME_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                        + PROJECT_NAME_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
