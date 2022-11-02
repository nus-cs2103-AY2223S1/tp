package seedu.classify.logic.parser;

import static seedu.classify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.classify.logic.commands.CommandTestUtil.CLASS_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_CLASS_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_EXAM_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.INVALID_STUDENT_NAME_DESC;
import static seedu.classify.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.TAG_DESC_EXAM_1;
import static seedu.classify.logic.commands.CommandTestUtil.TAG_DESC_EXAM_2;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_CLASS_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EXAM_1;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EXAM_2;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_AMY;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.classify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.classify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.classify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.classify.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.classify.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.classify.commons.core.index.Index;
import seedu.classify.logic.commands.EditCommand;
import seedu.classify.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.Email;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.Phone;
import seedu.classify.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String EXAM_EMPTY = " " + PREFIX_EXAM;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_STUDENT_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
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
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_STUDENT_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_ID_DESC, Id.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_CLASS_DESC, Class.MESSAGE_CONSTRAINTS); // invalid class
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_EXAM_DESC, Exam.MESSAGE_SCORE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + ID_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_EXAM_2 + TAG_DESC_EXAM_1 + EXAM_EMPTY, Exam.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_EXAM_2 + EXAM_EMPTY + TAG_DESC_EXAM_1, Exam.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + EXAM_EMPTY + TAG_DESC_EXAM_2 + TAG_DESC_EXAM_1, Exam.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_STUDENT_NAME_DESC + INVALID_ID_DESC
                        + VALID_EMAIL_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_EXAM_1
                + ID_DESC_AMY + EMAIL_DESC_AMY + STUDENT_NAME_DESC_AMY + CLASS_DESC_AMY + TAG_DESC_EXAM_2;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentName(VALID_STUDENT_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withId(VALID_ID_AMY).withClassName(VALID_CLASS_AMY)
                .withEmail(VALID_EMAIL_AMY).withExams(VALID_EXAM_1, VALID_EXAM_2).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + ID_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withId(VALID_ID_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + STUDENT_NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // class
        userInput = targetIndex.getOneBased() + CLASS_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withClassName(VALID_CLASS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // id
        userInput = targetIndex.getOneBased() + ID_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withId(VALID_ID_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_EXAM_2;
        descriptor = new EditStudentDescriptorBuilder().withExams(VALID_EXAM_2).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + EMAIL_DESC_AMY + ID_DESC_AMY
                + TAG_DESC_EXAM_2 + PHONE_DESC_AMY + EMAIL_DESC_AMY + ID_DESC_AMY + TAG_DESC_EXAM_2
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ID_DESC_BOB + TAG_DESC_EXAM_1;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB).withExams(VALID_EXAM_2, VALID_EXAM_1)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + ID_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).withId(VALID_ID_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
