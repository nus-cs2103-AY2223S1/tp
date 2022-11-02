package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_DO_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_TASK_A;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_CS2040;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DO_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CS2030;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.TASK_A;

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
        Task expectedTask = new TaskBuilder(TASK_A).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_DESC_CS2030 + DESCRIPTION_DESC_TASK_A,
                new AddTaskCommand(expectedTask));

        // multiple modules - last module accepted
        assertParseSuccess(parser, MODULE_DESC_CS2040 + MODULE_DESC_CS2030 + DESCRIPTION_DESC_TASK_A,
                new AddTaskCommand(expectedTask));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, MODULE_DESC_CS2030 + DESCRIPTION_DESC_DO_TUTORIAL + DESCRIPTION_DESC_TASK_A,
                new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing module prefix
        assertParseFailure(parser, VALID_MODULE_CS2030 + DESCRIPTION_DESC_DO_TUTORIAL, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, MODULE_DESC_CS2030 + VALID_DESCRIPTION_DO_TUTORIAL, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_MODULE_CS2030 + VALID_DESCRIPTION_DO_TUTORIAL, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module
        assertParseFailure(parser, INVALID_MODULE_DESC + DESCRIPTION_DESC_DO_TUTORIAL,
                ModuleCode.MODULE_CODE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, MODULE_DESC_CS2030 + INVALID_TASK_DESCRIPTION_DESC,
                TaskDescription.DESCRIPTION_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_MODULE_DESC + INVALID_TASK_DESCRIPTION_DESC,
                ModuleCode.MODULE_CODE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + MODULE_DESC_CS2030 + DESCRIPTION_DESC_DO_TUTORIAL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
