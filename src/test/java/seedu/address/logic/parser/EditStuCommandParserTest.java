package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ATTENDANCE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStuCommand;
import seedu.address.logic.commands.EditStuCommand.EditStudentDescriptor;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.StuEmail;
import seedu.address.model.student.StuName;
import seedu.address.model.student.Telegram;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditStuCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStuCommand.MESSAGE_USAGE);

    private EditStuCommandParser parser = new EditStuCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditStuCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, StuName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_TELEGRAM_DESC, Telegram.MESSAGE_CONSTRAINTS); // invalid telegram
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, StuEmail.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ATTENDANCE_DESC, Attendance.MESSAGE_CONSTRAINTS); //invalid attendance

        // invalid telegram followed by valid email
        assertParseFailure(parser, "1" + INVALID_TELEGRAM_DESC + EMAIL_DESC_AMY, Telegram.MESSAGE_CONSTRAINTS);

        // valid telegram followed by invalid telegram. The test case for invalid telegram followed by valid telegram
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + TELEGRAM_DESC_BOB + INVALID_TELEGRAM_DESC, Telegram.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_TELEGRAM_AMY
                        + VALID_ATTENDANCE_AMY, StuName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + ATTENDANCE_DESC_AMY
                + EMAIL_DESC_AMY + TELEGRAM_DESC_AMY + NAME_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withTelegram(VALID_TELEGRAM_AMY).withEmail(VALID_EMAIL_AMY).withAttendance(VALID_ATTENDANCE_AMY)
                .build();
        EditStuCommand expectedCommand = new EditStuCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + TELEGRAM_DESC_BOB + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditStuCommand expectedCommand = new EditStuCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditStuCommand expectedCommand = new EditStuCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // telegram
        userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_AMY).build();
        expectedCommand = new EditStuCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditStuCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // attendance
        userInput = targetIndex.getOneBased() + ATTENDANCE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withAttendance(VALID_ATTENDANCE_AMY).build();
        expectedCommand = new EditStuCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY + ATTENDANCE_DESC_AMY + EMAIL_DESC_AMY
                + TELEGRAM_DESC_AMY + ATTENDANCE_DESC_AMY + EMAIL_DESC_AMY
                + TELEGRAM_DESC_BOB + ATTENDANCE_DESC_BOB + EMAIL_DESC_BOB;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB)
                .withEmail(VALID_EMAIL_BOB).withAttendance(VALID_ATTENDANCE_BOB).build();
        EditStuCommand expectedCommand = new EditStuCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_TELEGRAM_DESC + TELEGRAM_DESC_BOB;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB).build();
        EditStuCommand expectedCommand = new EditStuCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_TELEGRAM_DESC + ATTENDANCE_DESC_BOB
                + TELEGRAM_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB).withEmail(VALID_EMAIL_BOB)
                .withAttendance(VALID_ATTENDANCE_BOB).build();
        expectedCommand = new EditStuCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}

