package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_FINISH_TP;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_LAB_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_FINISH_TP;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_LAB_2;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_FINISH_TP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_FINISH_TP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_FINISH_TP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_FINISH_TP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.FINISH_TP;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.module.Module;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;

public class AddTaskCommandParserTest {
    private final AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = FINISH_TP;

        // Typical usage example
        assertParseSuccess(parser, TASK_NAME_DESC_FINISH_TP + DEADLINE_DESC_FINISH_TP + MODULE_DESC_FINISH_TP,
                new AddTaskCommand(expectedTask));

        // multiple modules - last module accepted
        assertParseSuccess(parser,
                TASK_NAME_DESC_FINISH_TP + MODULE_DESC_LAB_2 + DEADLINE_DESC_FINISH_TP + MODULE_DESC_FINISH_TP,
                new AddTaskCommand(expectedTask));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser,
                TASK_NAME_DESC_FINISH_TP + DEADLINE_DESC_LAB_2 + DEADLINE_DESC_FINISH_TP + MODULE_DESC_FINISH_TP,
                new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing name
        assertParseFailure(parser, "" + DEADLINE_DESC_FINISH_TP + MODULE_DESC_FINISH_TP,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, TASK_NAME_DESC_FINISH_TP + VALID_DEADLINE_FINISH_TP + MODULE_DESC_FINISH_TP,
                expectedMessage);

        // missing module prefix
        assertParseFailure(parser, TASK_NAME_DESC_FINISH_TP + DEADLINE_DESC_FINISH_TP + VALID_MODULE_FINISH_TP,
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
        assertParseFailure(parser, TASK_NAME_DESC_FINISH_TP + INVALID_DEADLINE_DESC + MODULE_DESC_FINISH_TP,
                Deadline.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, TASK_NAME_DESC_FINISH_TP + DEADLINE_DESC_FINISH_TP + INVALID_MODULE_DESC,
                Module.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + DEADLINE_DESC_FINISH_TP + INVALID_MODULE_DESC,
                TaskName.MESSAGE_CONSTRAINTS);
    }
}
