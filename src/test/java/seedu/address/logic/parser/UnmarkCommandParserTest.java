package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_DESC_AMY_REM;
import static seedu.address.logic.commands.CommandTestUtil.HOMEWORK_DESC_AMY_REM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GradeProgressCommand;
import seedu.address.logic.commands.MarkPersonDescriptor;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.testutil.MarkPersonDescriptorBuilder;

public class UnmarkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE);

    private UnmarkCommandParser parser = new UnmarkCommandParser();

    @Test
    public void parse_noFieldSpecified_failure() {
        String userInput = UnmarkCommand.COMMAND_WORD;

        String expectedText = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedText);
    }


    @Test
    public void parse_invalidField_failure() {
        String userInput = GradeProgressCommand.COMMAND_WORD;

        String expectedText = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedText);
    }

    @Test
    public void parse_oneFieldsSpecified_success() {
        // For homework field
        String userInput = HOMEWORK_DESC_AMY_REM;
        MarkPersonDescriptor hwDescriptor = new MarkPersonDescriptorBuilder().withHomeworkIndex(VALID_INDEX).build();
        UnmarkCommand hwExpectedCommand = new UnmarkCommand(hwDescriptor);
        assertParseSuccess(parser, userInput, hwExpectedCommand);

        // For attendance field
        userInput = ATTENDANCE_DESC_AMY_REM;
        MarkPersonDescriptor attDescriptor = new MarkPersonDescriptorBuilder().withAttendanceIndex(VALID_INDEX).build();
        UnmarkCommand attExpectedCommand = new UnmarkCommand(attDescriptor);
        assertParseSuccess(parser, userInput, attExpectedCommand);
    }
}
