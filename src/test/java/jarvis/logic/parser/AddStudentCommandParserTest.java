package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static jarvis.logic.commands.CommandTestUtil.MATRIC_NUM_DESC_BOB;
import static jarvis.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static jarvis.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static jarvis.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static jarvis.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static jarvis.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static jarvis.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import jarvis.logic.commands.AddStudentCommand;
import jarvis.model.Student;
import jarvis.model.StudentName;
import jarvis.testutil.StudentBuilder;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + MATRIC_NUM_DESC_BOB,
                new AddStudentCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + MATRIC_NUM_DESC_BOB,
                new AddStudentCommand(expectedStudent));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + MATRIC_NUM_DESC_BOB, StudentName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + MATRIC_NUM_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}
