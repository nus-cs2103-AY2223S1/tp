package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.intrack.logic.commands.AddTaskCommand;
import seedu.intrack.model.internship.Task;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_inputSpecified_success() {
        // task present
        String validTask = "Technical Interview /at 21-10-2022 10:00";
        String userInput = " " + validTask;
        AddTaskCommand expectedCommand = new AddTaskCommand(
                new Task("Technical Interview", "21-10-2022 10:00"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AddTaskCommand.COMMAND_WORD, expectedMessage);
    }
}
