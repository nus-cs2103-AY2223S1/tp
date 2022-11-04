package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GradeProgressCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.MarkPersonDescriptor;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.testutil.MarkPersonDescriptorBuilder;
import seedu.address.testutil.RemovePersonDescriptorBuilder;

public class MarkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE);

    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_noFieldSpecified_failure() {
        String userInput = MarkCommand.COMMAND_WORD;

        String expectedText = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedText);
    }


    @Test
    public void parse_invalidField_failure() {
        String userInput = GradeProgressCommand.COMMAND_WORD;

        String expectedText = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedText);
    }

    @Test
    public void parse_oneFieldsSpecified_success() {
        // For homework field
        String userInput = HOMEWORK_DESC_AMY_REM;
        MarkPersonDescriptor hwDescriptor = new MarkPersonDescriptorBuilder().withHomeworkIndex(VALID_INDEX).build();
        MarkCommand hwExpectedCommand = new MarkCommand(hwDescriptor);
        assertParseSuccess(parser, userInput, hwExpectedCommand);

        // For attendance field
        userInput = ATTENDANCE_DESC_AMY_REM;
        MarkPersonDescriptor attDescriptor = new MarkPersonDescriptorBuilder().withAttendanceIndex(VALID_INDEX).build();
        MarkCommand attExpectedCommand = new MarkCommand(attDescriptor);
        assertParseSuccess(parser, userInput, attExpectedCommand);
    }

}
