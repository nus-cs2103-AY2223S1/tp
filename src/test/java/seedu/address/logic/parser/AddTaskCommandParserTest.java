package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2106_ADD_TASK_A;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_MA2001;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASK_A;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASK_B;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASK_C;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_A;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.task.Task;

public class AddTaskCommandParserTest {
    private final AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AddTaskCommand expectedCommand =
                new AddTaskCommand(DESC_CS2106_ADD_TASK_A);

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + MODULE_CODE_DESC_CS2106 + MODULE_TASK_A,
                expectedCommand);

        // multiple task descriptions - last task accepted
        assertParseSuccess(parser,
                MODULE_CODE_DESC_CS2106 + MODULE_TASK_C + MODULE_TASK_B
                        + MODULE_TASK_A, expectedCommand);

        // order of arguments should not matter
        assertParseSuccess(parser, MODULE_TASK_A + MODULE_CODE_DESC_CS2106,
                expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddTaskCommand.MESSAGE_USAGE);

        // missing module code prefix
        assertParseFailure(parser, VALID_CS_MODULE_CODE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, MODULE_TASK_C, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid task prefix
        assertParseFailure(parser, MODULE_CODE_DESC_MA2001 + VALID_ADDRESS_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));

        // invalid task description (null)
        assertParseFailure(parser, MODULE_CODE_DESC_MA2001 + INVALID_TASK_DESC,
                Task.MESSAGE_CONSTRAINTS);

        // both module code and task description are invalid - only
        // invalid module code is reported.
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC + INVALID_TASK_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_TASK_DESC + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + MODULE_CODE_DESC_MA2001 + VALID_TASK_A,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
