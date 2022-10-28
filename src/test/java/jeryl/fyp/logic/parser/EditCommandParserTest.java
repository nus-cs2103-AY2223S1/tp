package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_PROJECT_NAME_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_STUDENT_NAME_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.PROJECT_NAME_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static jeryl.fyp.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_TAG;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.commands.EditCommand;
import jeryl.fyp.logic.commands.EditCommand.EditStudentDescriptor;
import jeryl.fyp.model.student.Email;
import jeryl.fyp.model.student.ProjectName;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.student.StudentName;
import jeryl.fyp.model.tag.Tag;
import jeryl.fyp.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private static final StudentId amyId = new StudentId(VALID_STUDENT_ID_AMY);


    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_STUDENT_NAME_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + STUDENT_NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + STUDENT_NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);

        // invalid student ID
        assertParseFailure(parser, INVALID_STUDENT_ID_DESC + STUDENT_NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, VALID_STUDENT_ID_AMY + INVALID_STUDENT_NAME_DESC,
                StudentName.MESSAGE_CONSTRAINTS); // invalid student name
        assertParseFailure(parser, VALID_STUDENT_ID_AMY + INVALID_EMAIL_DESC,
                Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, VALID_STUDENT_ID_AMY + INVALID_PROJECT_NAME_DESC,
                ProjectName.MESSAGE_CONSTRAINTS); // invalid project name
        assertParseFailure(parser, VALID_STUDENT_ID_AMY + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid student name followed by valid email
        assertParseFailure(parser, VALID_STUDENT_ID_AMY + INVALID_STUDENT_NAME_DESC + EMAIL_DESC_AMY,
                StudentName.MESSAGE_CONSTRAINTS);

        // valid student name followed by invalid student name.
        // The test case for invalid student name followed by valid student name
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, VALID_STUDENT_ID_AMY + STUDENT_NAME_DESC_AMY + INVALID_STUDENT_NAME_DESC,
                StudentName.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Student} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, VALID_STUDENT_ID_AMY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_STUDENT_ID_AMY + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_STUDENT_ID_AMY + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, VALID_STUDENT_ID_AMY + INVALID_STUDENT_NAME_DESC + INVALID_EMAIL_DESC
                + VALID_STUDENT_ID_AMY, StudentName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = amyId + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + STUDENT_NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(amyId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = amyId + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(amyId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // student name
        String userInput = amyId + STUDENT_NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(amyId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = amyId + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(amyId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // project name
        userInput = amyId + PROJECT_NAME_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withProjectName(VALID_PROJECT_NAME_AMY).build();
        expectedCommand = new EditCommand(amyId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = amyId + TAG_DESC_FRIEND;
        descriptor = new EditStudentDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(amyId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {

        String userInput = amyId + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(amyId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = amyId + INVALID_STUDENT_NAME_DESC + STUDENT_NAME_DESC_BOB;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_BOB).build();
        EditCommand expectedCommand = new EditCommand(amyId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = amyId + EMAIL_DESC_BOB + INVALID_STUDENT_NAME_DESC + STUDENT_NAME_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .build();
        expectedCommand = new EditCommand(amyId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        String userInput = amyId + TAG_EMPTY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(amyId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
