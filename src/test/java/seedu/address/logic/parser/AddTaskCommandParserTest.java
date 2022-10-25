package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASKDEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASKDESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TASKDEADLINE_DESC_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.TASKDEADLINE_DESC_BRAVO;
import static seedu.address.logic.commands.CommandTestUtil.TASKDESCRIPTION_DESC_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.TASKDESCRIPTION_DESC_BRAVO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASKDESCRIPTION_ALPHA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.TASK_ALPHA;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.deadline.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TASK_ALPHA).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASKDEADLINE_DESC_ALPHA
                + TASKDESCRIPTION_DESC_ALPHA, new AddTaskCommand(expectedTask));

        // Multiple deadline - last one taken
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASKDEADLINE_DESC_BRAVO + TASKDEADLINE_DESC_ALPHA
                + TASKDESCRIPTION_DESC_ALPHA, new AddTaskCommand(expectedTask));

        // Multiple description - last one taken
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASKDESCRIPTION_DESC_BRAVO + TASKDEADLINE_DESC_ALPHA
                + TASKDESCRIPTION_DESC_ALPHA, new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_missingCompulsoryFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // Missing task deadline
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASKDESCRIPTION_DESC_ALPHA, expectedMessage);

        // Missing task description
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASKDEADLINE_DESC_BRAVO, expectedMessage);
    }

    @Test
    public void parse_invalidFields_failure() {
        // Invalid task deadline
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASKDESCRIPTION_DESC_ALPHA
                + INVALID_TASKDEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);

        // Invalid task description
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASKDEADLINE_DESC_BRAVO
                + INVALID_TASKDESCRIPTION_DESC, TaskDescription.MESSAGE_CONSTRAINTS);

        // Invalid preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TASKDEADLINE_DESC_BRAVO
                        + VALID_TASKDESCRIPTION_ALPHA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingCompulsoryPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // Missing task deadline prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASKDESCRIPTION_DESC_ALPHA
                + VALID_TASKDEADLINE_ALPHA, expectedMessage);

        // Missing task description prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASKDEADLINE_DESC_BRAVO
                + VALID_TASKDESCRIPTION_ALPHA, expectedMessage);

    }
}
