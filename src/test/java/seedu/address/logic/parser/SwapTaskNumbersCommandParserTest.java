package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_NUMBER_OF_TASK_NUMBERS_TO_SWAP;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_NUMBER;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_NUMBERS_TO_SWAP;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2106_SWAP_TASKS_ONE_AND_TWO;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_STRING;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NUMBERS_DESC_NEGATIVE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NUMBERS_DESC_NON_NUMERIC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NUMBERS_DESC_ONLY_ONE_NUMBER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NUMBERS_DESC_SAME_NUMBER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NUMBERS_DESC_THREE_NUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NUMBERS_DESC_ZERO;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_MA2001;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASKLIST_DESC_NUMBER_THREE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASKLIST_DESC_SWAP_ONE_AND_TWO;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASKLIST_DESC_SWAP_TWO_AND_THREE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_CS2103T;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NUMBERS_TO_SWAP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SwapTaskNumbersCommand;
import seedu.address.model.module.ModuleCode;

/**
 * Tests parser for {@code SwapTaskNumbersCommand}.
 */
public class SwapTaskNumbersCommandParserTest {

    private final SwapTaskNumbersCommandParser parser = new SwapTaskNumbersCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        SwapTaskNumbersCommand expectedCommand =
                new SwapTaskNumbersCommand(DESC_CS2106_SWAP_TASKS_ONE_AND_TWO);
        assertParseSuccess(parser,
                MODULE_CODE_DESC_CS2106 + MODULE_TASKLIST_DESC_SWAP_ONE_AND_TWO,
                expectedCommand);

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + MODULE_CODE_DESC_CS2106 + MODULE_TASKLIST_DESC_SWAP_ONE_AND_TWO,
                expectedCommand);

        // multiple module codes - last module code used
        assertParseSuccess(parser,
                MODULE_CODE_DESC_MA2001 + MODULE_CODE_DESC_CS2106
                        + MODULE_TASKLIST_DESC_SWAP_ONE_AND_TWO, expectedCommand);

        // multiple task number pairs given - last task number pair accepted
        assertParseSuccess(parser,
                MODULE_CODE_DESC_CS2106 + MODULE_TASKLIST_DESC_SWAP_TWO_AND_THREE
                        + MODULE_TASKLIST_DESC_SWAP_ONE_AND_TWO, expectedCommand);

        // order of arguments should not matter
        assertParseSuccess(parser, MODULE_TASKLIST_DESC_SWAP_ONE_AND_TWO + MODULE_CODE_DESC_CS2106,
                expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SwapTaskNumbersCommand.MESSAGE_USAGE);

        // missing task number prefix
        assertParseFailure(parser, MODULE_CODE_DESC_CS2106, expectedMessage);

        // missing module code prefix
        assertParseFailure(parser, MODULE_TASKLIST_DESC_SWAP_TWO_AND_THREE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, EMPTY_STRING, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // incorrect prefix
        assertParseFailure(parser,
                MODULE_CODE_DESC_MA2001 + VALID_MODULE_LINK_CS2103T,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwapTaskNumbersCommand.MESSAGE_USAGE));

        // invalid module code
        assertParseFailure(parser,
                MODULE_TASKLIST_DESC_SWAP_TWO_AND_THREE + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid task number - non-numeric
        assertParseFailure(parser,
                MODULE_CODE_DESC_MA2001 + INVALID_TASK_NUMBERS_DESC_NON_NUMERIC,
                MESSAGE_INVALID_TASK_NUMBER);

        // invalid task number - negative
        assertParseFailure(parser,
                MODULE_CODE_DESC_MA2001 + INVALID_TASK_NUMBERS_DESC_NEGATIVE,
                MESSAGE_INVALID_TASK_NUMBER);

        // invalid task number - zero
        assertParseFailure(parser,
                MODULE_CODE_DESC_MA2001 + INVALID_TASK_NUMBERS_DESC_ZERO,
                MESSAGE_INVALID_TASK_NUMBER);

        // invalid task number - same number given
        assertParseFailure(parser,
                MODULE_CODE_DESC_MA2001 + INVALID_TASK_NUMBERS_DESC_SAME_NUMBER,
                MESSAGE_INVALID_TASK_NUMBERS_TO_SWAP);

        // invalid number of task numbers - no numbers given
        String inputWithNoTaskNumbers =
                MODULE_CODE_DESC_MA2001 + " " + PREFIX_TASK_NUMBERS_TO_SWAP;
        assertParseFailure(parser, inputWithNoTaskNumbers,
                MESSAGE_INVALID_NUMBER_OF_TASK_NUMBERS_TO_SWAP);

        // invalid number of task numbers - only one number given
        assertParseFailure(parser,
                MODULE_CODE_DESC_MA2001 + INVALID_TASK_NUMBERS_DESC_ONLY_ONE_NUMBER,
                MESSAGE_INVALID_NUMBER_OF_TASK_NUMBERS_TO_SWAP);

        // invalid number of task numbers - three numbers given
        assertParseFailure(parser,
                MODULE_CODE_DESC_MA2001 + INVALID_TASK_NUMBERS_DESC_THREE_NUMBERS,
                MESSAGE_INVALID_NUMBER_OF_TASK_NUMBERS_TO_SWAP);


        // both module code and task numbers are invalid - only
        // invalid module code is reported.
        assertParseFailure(parser,
                INVALID_TASK_NUMBERS_DESC_SAME_NUMBER + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                INVALID_MODULE_CODE_DESC + INVALID_TASK_NUMBERS_DESC_SAME_NUMBER,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + MODULE_CODE_DESC_MA2001 + MODULE_TASKLIST_DESC_NUMBER_THREE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwapTaskNumbersCommand.MESSAGE_USAGE));
    }
}
