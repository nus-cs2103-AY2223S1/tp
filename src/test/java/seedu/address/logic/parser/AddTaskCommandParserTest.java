package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_OTHERS;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_LOW;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_MEDIUM;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_LOW;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskDeadline;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TypicalTasks;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TypicalTasks.TASK_AMY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC_A + DESCRIPTION_DESC_A + CATEGORY_DESC_FRONTEND
                + DEADLINE_DESC_A + PRIORITY_DESC_LOW, new AddTaskCommand(expectedTask, null));

        // multiple task names - last name accepted
        assertParseSuccess(parser, TASK_NAME_DESC_B + TASK_NAME_DESC_A + DESCRIPTION_DESC_A + CATEGORY_DESC_FRONTEND
                + DEADLINE_DESC_A + PRIORITY_DESC_LOW, new AddTaskCommand(expectedTask, null));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, TASK_NAME_DESC_A + DESCRIPTION_DESC_B + DESCRIPTION_DESC_A + CATEGORY_DESC_FRONTEND
                + DEADLINE_DESC_A + PRIORITY_DESC_LOW, new AddTaskCommand(expectedTask, null));

        // multiple categories - last category accepted
        assertParseSuccess(parser, TASK_NAME_DESC_A + DESCRIPTION_DESC_A + CATEGORY_DESC_OTHERS + CATEGORY_DESC_FRONTEND
                + DEADLINE_DESC_A + PRIORITY_DESC_LOW, new AddTaskCommand(expectedTask, null));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, TASK_NAME_DESC_A + DESCRIPTION_DESC_A + CATEGORY_DESC_FRONTEND + DEADLINE_DESC_B
                + DEADLINE_DESC_A + PRIORITY_DESC_LOW, new AddTaskCommand(expectedTask, null));

        // multiple priorities - last priority accepted
        assertParseSuccess(parser, TASK_NAME_DESC_A + DESCRIPTION_DESC_A + CATEGORY_DESC_FRONTEND + DEADLINE_DESC_A
                + PRIORITY_DESC_MEDIUM + PRIORITY_DESC_LOW, new AddTaskCommand(expectedTask, null));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no person email
        Task expectedTask = new TaskBuilder(TypicalTasks.TASK_AMY).withPerson(null).build();
        assertParseSuccess(parser, TASK_NAME_DESC_A + DESCRIPTION_DESC_A + CATEGORY_DESC_FRONTEND
                + DEADLINE_DESC_A + PRIORITY_DESC_LOW, new AddTaskCommand(expectedTask, null));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_AMY + DESCRIPTION_DESC_A + CATEGORY_DESC_FRONTEND + DEADLINE_DESC_A
                        + PRIORITY_DESC_LOW,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, NAME_DESC_AMY + VALID_DESCRIPTION_A + CATEGORY_DESC_FRONTEND + DEADLINE_DESC_A
                        + PRIORITY_DESC_LOW,
                expectedMessage);

        // missing category prefix
        assertParseFailure(parser, NAME_DESC_AMY + DESCRIPTION_DESC_A + VALID_CATEGORY_FRONTEND + DEADLINE_DESC_A
                        + PRIORITY_DESC_LOW,
                expectedMessage);

        // missing priority prefix
        assertParseFailure(parser, NAME_DESC_AMY + DESCRIPTION_DESC_A + CATEGORY_DESC_FRONTEND + DEADLINE_DESC_A
                        + VALID_PRIORITY_LOW,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_AMY + DESCRIPTION_DESC_A + CATEGORY_DESC_FRONTEND + VALID_DEADLINE_A
                        + PRIORITY_DESC_LOW,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                VALID_NAME_AMY + VALID_DESCRIPTION_A + VALID_CATEGORY_FRONTEND + VALID_DEADLINE_A + VALID_PRIORITY_LOW,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid priority
        assertParseFailure(parser, NAME_DESC_AMY + DESCRIPTION_DESC_A + CATEGORY_DESC_FRONTEND + DEADLINE_DESC_A
                + INVALID_PRIORITY_DESC, String.format(Priority.MESSAGE_CONSTRAINTS, INVALID_PRIORITY));

        // invalid category
        assertParseFailure(parser, NAME_DESC_AMY + DESCRIPTION_DESC_A + INVALID_CATEGORY_DESC + DEADLINE_DESC_A
                + PRIORITY_DESC_LOW, String.format(TaskCategory.MESSAGE_CONSTRAINTS, INVALID_CATEGORY));

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_AMY + DESCRIPTION_DESC_A + CATEGORY_DESC_FRONTEND + INVALID_DEADLINE_DESC
                + PRIORITY_DESC_LOW, String.format(TaskDeadline.MESSAGE_CONSTRAINTS, INVALID_DEADLINE));

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + NAME_DESC_AMY + DESCRIPTION_DESC_A + CATEGORY_DESC_FRONTEND + DEADLINE_DESC_A
                        + PRIORITY_DESC_LOW,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
