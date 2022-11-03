package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_DESC_AMY_REM;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_PROGRESS_DESC_AMY_REM;
import static seedu.address.logic.commands.CommandTestUtil.HOMEWORK_DESC_AMY_REM;
import static seedu.address.logic.commands.CommandTestUtil.SESSION_DESC_AMY_REM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.RemoveCommand.RemovePersonDescriptor;
import seedu.address.testutil.RemovePersonDescriptorBuilder;

public class RemoveCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE);

    private RemoveCommandParser parser = new RemoveCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative integer
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero integer
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = HOMEWORK_DESC_AMY_REM + SESSION_DESC_AMY_REM + GRADE_PROGRESS_DESC_AMY_REM
                + ATTENDANCE_DESC_AMY_REM;

        RemovePersonDescriptor descriptor = new RemovePersonDescriptorBuilder()
                .withHomeworkIndex(VALID_INDEX).withAttendanceIndex(VALID_INDEX)
                .withGradeProgressIndex(VALID_INDEX).withSessionIndex(VALID_INDEX).build();
        RemoveCommand expectedCommand = new RemoveCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldsSpecified_success() {
        // homework
        String userInput = HOMEWORK_DESC_AMY_REM;
        RemovePersonDescriptor descriptor = new RemovePersonDescriptorBuilder().withHomeworkIndex(VALID_INDEX).build();
        RemoveCommand expectedCommand = new RemoveCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //session
        userInput = SESSION_DESC_AMY_REM;
        descriptor = new RemovePersonDescriptorBuilder().withSessionIndex(VALID_INDEX).build();
        expectedCommand = new RemoveCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //attendance
        userInput = ATTENDANCE_DESC_AMY_REM;
        descriptor = new RemovePersonDescriptorBuilder().withAttendanceIndex(VALID_INDEX).build();
        expectedCommand = new RemoveCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //grade progress
        userInput = GRADE_PROGRESS_DESC_AMY_REM;
        descriptor = new RemovePersonDescriptorBuilder().withGradeProgressIndex(VALID_INDEX).build();
        expectedCommand = new RemoveCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }
}
