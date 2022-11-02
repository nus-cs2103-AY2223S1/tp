package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_1;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_2;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESCRIPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESCRIPTION_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.TASK1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TASK1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_1 + TASK_DESCRIPTION_1,
                new AddTaskCommand(expectedTask));

        // multiple modules - last module accepted
        assertParseSuccess(parser, MODULE_2 + MODULE_1 + TASK_DESCRIPTION_1, new AddTaskCommand(expectedTask));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, MODULE_1 + TASK_DESCRIPTION_2 + TASK_DESCRIPTION_1,
                new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing module prefix
        assertParseFailure(parser, VALID_MODULE_1 + TASK_DESCRIPTION_1, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, MODULE_1 + VALID_TASK_DESCRIPTION_1, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_MODULE_1 + VALID_TASK_DESCRIPTION_1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module
        assertParseFailure(parser, INVALID_MODULE + TASK_DESCRIPTION_1, ModuleCode.MODULE_CODE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, MODULE_1 + INVALID_TASK_DESCRIPTION,
                TaskDescription.DESCRIPTION_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_MODULE + INVALID_TASK_DESCRIPTION,
                ModuleCode.MODULE_CODE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + MODULE_1 + TASK_DESCRIPTION_1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
