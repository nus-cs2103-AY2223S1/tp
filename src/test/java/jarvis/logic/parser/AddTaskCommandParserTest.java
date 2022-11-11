package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.commands.CommandTestUtil.INVALID_TASK_DEADLINE;
import static jarvis.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static jarvis.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static jarvis.logic.commands.CommandTestUtil.TASK_DEADLINE_MISSION1;
import static jarvis.logic.commands.CommandTestUtil.TASK_DESC_MISSION1;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import jarvis.logic.commands.AddTaskCommand;
import jarvis.model.Task;
import jarvis.model.TaskDeadline;
import jarvis.testutil.TaskBuilder;
import jarvis.testutil.TypicalTasks;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TypicalTasks.MISSION1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASK_DESC_MISSION1 + TASK_DEADLINE_MISSION1,
                new AddTaskCommand(expectedTask));

        assertParseSuccess(parser, TASK_DESC_MISSION1 + TASK_DEADLINE_MISSION1,
                new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing task description prefix
        assertParseFailure(parser, TASK_DEADLINE_MISSION1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid deadline
        assertParseFailure(parser, TASK_DESC_MISSION1 + INVALID_TASK_DEADLINE , TaskDeadline.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TASK_DESC_MISSION1 + TASK_DEADLINE_MISSION1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
