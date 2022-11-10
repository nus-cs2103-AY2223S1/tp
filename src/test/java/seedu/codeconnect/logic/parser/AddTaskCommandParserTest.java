package seedu.codeconnect.logic.parser;

import static seedu.codeconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codeconnect.logic.commands.CommandTestUtil.DEADLINE_DESC_BUY_GROCERIES;
import static seedu.codeconnect.logic.commands.CommandTestUtil.DEADLINE_DESC_FINISH_TP;
import static seedu.codeconnect.logic.commands.CommandTestUtil.DEADLINE_DESC_LAB_2;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.MODULE_DESC_FINISH_TP;
import static seedu.codeconnect.logic.commands.CommandTestUtil.MODULE_DESC_LAB_2;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_DEADLINE_FINISH_TP;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_MODULE_FINISH_TP;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_TASK_NAME_BUY_GROCERIES;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_TASK_NAME_FINISH_TP;
import static seedu.codeconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.codeconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.codeconnect.testutil.TypicalTasks.BUY_GROCERIES;
import static seedu.codeconnect.testutil.TypicalTasks.FINISH_TP;

import org.junit.jupiter.api.Test;

import seedu.codeconnect.logic.commands.AddTaskCommand;
import seedu.codeconnect.model.module.Module;
import seedu.codeconnect.model.task.Deadline;
import seedu.codeconnect.model.task.Task;
import seedu.codeconnect.model.task.TaskName;

public class AddTaskCommandParserTest {
    private final AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = FINISH_TP;

        // Typical usage example
        assertParseSuccess(parser, VALID_TASK_NAME_FINISH_TP + DEADLINE_DESC_FINISH_TP + MODULE_DESC_FINISH_TP,
                new AddTaskCommand(expectedTask));

        // multiple modules - last module accepted
        assertParseSuccess(parser,
                VALID_TASK_NAME_FINISH_TP + MODULE_DESC_LAB_2 + DEADLINE_DESC_FINISH_TP
                        + MODULE_DESC_FINISH_TP, new AddTaskCommand(expectedTask));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser,
                VALID_TASK_NAME_FINISH_TP + DEADLINE_DESC_LAB_2 + DEADLINE_DESC_FINISH_TP
                        + MODULE_DESC_FINISH_TP, new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_optionalFieldMissing_success() {
        // optional modules
        assertParseSuccess(parser, VALID_TASK_NAME_BUY_GROCERIES + DEADLINE_DESC_BUY_GROCERIES,
                new AddTaskCommand(BUY_GROCERIES));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing name
        assertParseFailure(parser, "" + DEADLINE_DESC_FINISH_TP + MODULE_DESC_FINISH_TP,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, VALID_TASK_NAME_FINISH_TP + VALID_DEADLINE_FINISH_TP + MODULE_DESC_FINISH_TP,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TASK_NAME_FINISH_TP + VALID_DEADLINE_FINISH_TP + VALID_MODULE_FINISH_TP,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + DEADLINE_DESC_FINISH_TP + MODULE_DESC_FINISH_TP,
                TaskName.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, VALID_TASK_NAME_FINISH_TP + INVALID_DEADLINE_DESC + MODULE_DESC_FINISH_TP,
                Deadline.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, VALID_TASK_NAME_FINISH_TP + DEADLINE_DESC_FINISH_TP + INVALID_MODULE_DESC,
                Module.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + DEADLINE_DESC_FINISH_TP + INVALID_MODULE_DESC,
                TaskName.MESSAGE_CONSTRAINTS);
    }
}
