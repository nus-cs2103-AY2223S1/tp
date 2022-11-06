package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_TASKDEADLINE_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_TASKDESCRIPTION_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.hrpro.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.hrpro.logic.commands.CommandTestUtil.TASKDEADLINE_DESC_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.TASKDEADLINE_DESC_BRAVO;
import static seedu.hrpro.logic.commands.CommandTestUtil.TASKDESCRIPTION_DESC_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.TASKDESCRIPTION_DESC_BRAVO;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_BUDGET_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDESCRIPTION_ALPHA;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.hrpro.testutil.TypicalTasks.TASK_ALPHA;

import org.junit.jupiter.api.Test;

import seedu.hrpro.logic.commands.AddTaskCommand;
import seedu.hrpro.model.deadline.Deadline;
import seedu.hrpro.model.task.Task;
import seedu.hrpro.model.task.TaskDescription;
import seedu.hrpro.testutil.TaskBuilder;

/**
 * Contains test cases for AddTaskCommandParser.
 */
public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TASK_ALPHA).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASKDEADLINE_DESC_ALPHA
                + TASKDESCRIPTION_DESC_ALPHA, new AddTaskCommand(expectedTask));

        // Multiple deadline - last one taken
        assertParseSuccess(parser, TASKDEADLINE_DESC_BRAVO + TASKDEADLINE_DESC_ALPHA
                + TASKDESCRIPTION_DESC_ALPHA, new AddTaskCommand(expectedTask));

        // Multiple description - last one taken
        assertParseSuccess(parser, TASKDESCRIPTION_DESC_BRAVO + TASKDEADLINE_DESC_ALPHA
                + TASKDESCRIPTION_DESC_ALPHA, new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // Missing deadline prefix
        assertParseFailure(parser, TASKDESCRIPTION_DESC_ALPHA + VALID_TASKDEADLINE_ALPHA, expectedMessage);

        // Missing description prefix
        assertParseFailure(parser, VALID_TASKDESCRIPTION_ALPHA + TASKDEADLINE_DESC_ALPHA, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TASKDESCRIPTION_ALPHA + VALID_TASKDEADLINE_ALPHA,
                expectedMessage);
    }

    @Test
    public void parse_invalidFields_failure() {
        // Invalid task deadline
        assertParseFailure(parser, TASKDESCRIPTION_DESC_ALPHA
                + INVALID_TASKDEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);

        // Invalid task description
        assertParseFailure(parser, TASKDEADLINE_DESC_BRAVO
                + INVALID_TASKDESCRIPTION_DESC, TaskDescription.MESSAGE_CONSTRAINTS);

        // two invalid values, only the first invalid value reported
        assertParseFailure(parser, INVALID_TASKDESCRIPTION_DESC
                + INVALID_TASKDEADLINE_DESC, TaskDescription.MESSAGE_CONSTRAINTS);

        // Invalid preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TASKDEADLINE_DESC_BRAVO
                        + VALID_TASKDESCRIPTION_ALPHA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
