package seedu.classify.logic.parser;

import static seedu.classify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.classify.logic.commands.CommandTestUtil.CLASS_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.CLASS_DESC_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_CLASS_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_EXAM_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_PARENT_NAME_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_STUDENT_NAME_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.PARENT_NAME_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.PARENT_NAME_DESC_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.classify.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.classify.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.TAG_DESC_EXAM_1;
import static seedu.classify.logic.commands.CommandTestUtil.TAG_DESC_EXAM_2;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_CLASS_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EXAM_1;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EXAM_2;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static seedu.classify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.classify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.classify.testutil.TypicalStudents.AMY;
import static seedu.classify.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.classify.logic.commands.AddStudentCommand;
import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.Email;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.Phone;
import seedu.classify.model.student.Student;
import seedu.classify.testutil.StudentBuilder;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedPerson = new StudentBuilder(BOB).withExams(VALID_EXAM_2).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STUDENT_NAME_DESC_BOB + ID_DESC_BOB + CLASS_DESC_BOB
                + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_EXAM_2, new AddStudentCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_AMY + STUDENT_NAME_DESC_BOB + ID_DESC_BOB + CLASS_DESC_BOB
                + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_EXAM_2, new AddStudentCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + CLASS_DESC_BOB
                + PARENT_NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + TAG_DESC_EXAM_2, new AddStudentCommand(expectedPerson));

        // multiple ids - last id accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + ID_DESC_AMY + ID_DESC_BOB + CLASS_DESC_BOB
                + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_EXAM_2, new AddStudentCommand(expectedPerson));

        // multiple class - last class accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + CLASS_DESC_AMY + CLASS_DESC_BOB
                + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_EXAM_2, new AddStudentCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + CLASS_DESC_BOB
                + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_AMY + EMAIL_DESC_BOB + TAG_DESC_EXAM_2, new AddStudentCommand(expectedPerson));

        // multiple tags - all accepted
        Student expectedPersonMultipleTags = new StudentBuilder(BOB).withExams(VALID_EXAM_2, VALID_EXAM_1)
                .build();
        assertParseSuccess(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + CLASS_DESC_BOB
                        + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + TAG_DESC_EXAM_2 + TAG_DESC_EXAM_1, new AddStudentCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedPerson = new StudentBuilder(AMY).withExams().build();
        assertParseSuccess(parser, STUDENT_NAME_DESC_AMY + ID_DESC_AMY + CLASS_DESC_AMY
                + PARENT_NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY, new AddStudentCommand(expectedPerson));
        // missing parent name
        Student expectedPersonWithoutParentName = new StudentBuilder(AMY).withParentName("").build();
        assertParseSuccess(parser, STUDENT_NAME_DESC_AMY + ID_DESC_AMY + CLASS_DESC_AMY
                + PHONE_DESC_AMY + EMAIL_DESC_AMY, new AddStudentCommand(expectedPersonWithoutParentName));
        // missing phone
        Student expectedPersonWithoutPhone = new StudentBuilder(AMY).withPhone("").build();
        assertParseSuccess(parser, STUDENT_NAME_DESC_AMY + ID_DESC_AMY + CLASS_DESC_AMY
                + PARENT_NAME_DESC_AMY + EMAIL_DESC_AMY, new AddStudentCommand(expectedPersonWithoutPhone));
        // missing email
        Student expectedPersonWithoutEmail = new StudentBuilder(AMY).withEmail("").build();
        assertParseSuccess(parser, STUDENT_NAME_DESC_AMY + ID_DESC_AMY + CLASS_DESC_AMY
                + PARENT_NAME_DESC_AMY + PHONE_DESC_AMY, new AddStudentCommand(expectedPersonWithoutEmail));
        // missing parent name, phone, email and zero tags
        Student expectedPersonWithoutOptional = new StudentBuilder(AMY)
                .withParentName("")
                .withEmail("")
                .withPhone("")
                .withExams().build();
        assertParseSuccess(parser, STUDENT_NAME_DESC_AMY + ID_DESC_AMY + CLASS_DESC_AMY,
                new AddStudentCommand(expectedPersonWithoutOptional));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_STUDENT_NAME_BOB + CLASS_DESC_BOB + PHONE_DESC_BOB
                        + ID_DESC_BOB + EMAIL_DESC_BOB, expectedMessage);

        // missing id prefix
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + CLASS_DESC_BOB + PHONE_DESC_BOB
                        + VALID_ID_BOB + EMAIL_DESC_BOB, expectedMessage);

        // missing class prefix
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + VALID_CLASS_BOB + PHONE_DESC_BOB
                + ID_DESC_BOB + EMAIL_DESC_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_STUDENT_NAME_BOB + CLASS_DESC_BOB + VALID_PHONE_BOB
                        + VALID_ID_BOB + VALID_EMAIL_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_STUDENT_NAME_DESC + ID_DESC_BOB + CLASS_DESC_BOB
                + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_EXAM_2, Name.MESSAGE_CONSTRAINTS);

        // invalid class
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + INVALID_CLASS_DESC
                + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_EXAM_2, Class.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + CLASS_DESC_BOB + PARENT_NAME_DESC_BOB
                + INVALID_PHONE_DESC + EMAIL_DESC_BOB + TAG_DESC_EXAM_2, Phone.MESSAGE_CONSTRAINTS);

        // invalid parent name
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + CLASS_DESC_BOB + INVALID_PARENT_NAME_DESC
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_EXAM_2, Name.MESSAGE_CONSTRAINTS);

        // invalid id
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + INVALID_ID_DESC + CLASS_DESC_BOB
                + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_EXAM_2, Id.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + CLASS_DESC_BOB + PARENT_NAME_DESC_BOB
                + PHONE_DESC_BOB + INVALID_EMAIL_DESC + TAG_DESC_EXAM_2, Email.MESSAGE_CONSTRAINTS);

        // invalid exam
        assertParseFailure(parser, STUDENT_NAME_DESC_BOB + ID_DESC_BOB + CLASS_DESC_BOB
                + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_EXAM_DESC
                + TAG_DESC_EXAM_2, Exam.MESSAGE_SCORE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_STUDENT_NAME_DESC + ID_DESC_BOB + CLASS_DESC_BOB
                        + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + TAG_DESC_EXAM_2,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + STUDENT_NAME_DESC_BOB + ID_DESC_BOB + CLASS_DESC_BOB
                        + PARENT_NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + TAG_DESC_EXAM_2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));

    }
}
