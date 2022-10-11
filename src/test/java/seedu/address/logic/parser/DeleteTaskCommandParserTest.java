//@@author cheeheng-reused
//Reused from existing AB3 code with minor modifications
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2106_DELETE_TASK_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NUMBER_DESC_NEGATIVE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NUMBER_DESC_NON_NUMERIC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NUMBER_DESC_ZERO;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_MA2001;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASKLIST_DESC_NUMBER_ONE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASKLIST_DESC_NUMBER_THREE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASKLIST_DESC_NUMBER_TWO;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASK_DESC_C;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS_MODULE_CODE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.DeleteTaskCommandParser.MESSAGE_INDEX_IS_NOT_A_POSITIVE_INTEGER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.model.module.ModuleCode;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteModuleCommand code. For example, inputs "m/CS2103T" and "m/CS2103T abc" take
 * the same path through the DeleteModuleCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteTaskCommandParserTest {

    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        DeleteTaskCommand expectedCommand =
                new DeleteTaskCommand(DESC_CS2106_DELETE_TASK_ONE);

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + MODULE_CODE_DESC_CS2106 + MODULE_TASKLIST_DESC_NUMBER_ONE,
                expectedCommand);

        // multiple task descriptions - last task accepted
        assertParseSuccess(parser,
                MODULE_CODE_DESC_CS2106 + MODULE_TASKLIST_DESC_NUMBER_TWO
                        + MODULE_TASKLIST_DESC_NUMBER_ONE, expectedCommand);

        // order of arguments should not matter
        assertParseSuccess(parser, MODULE_TASKLIST_DESC_NUMBER_ONE + MODULE_CODE_DESC_CS2106,
                expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteTaskCommand.MESSAGE_USAGE);

        // missing module code prefix
        assertParseFailure(parser, VALID_CS_MODULE_CODE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, MODULE_TASK_DESC_C, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid task prefix
        assertParseFailure(parser, MODULE_CODE_DESC_MA2001 + "a/ task",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
        // invalid module code
        assertParseFailure(parser, MODULE_TASKLIST_DESC_NUMBER_ONE + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);
        // invalid task number - non-numeric
        assertParseFailure(parser, MODULE_CODE_DESC_MA2001 + INVALID_TASK_NUMBER_DESC_NON_NUMERIC,
                MESSAGE_INDEX_IS_NOT_A_POSITIVE_INTEGER);
        // invalid task number - negative
        assertParseFailure(parser,
                MODULE_CODE_DESC_MA2001 + INVALID_TASK_NUMBER_DESC_NEGATIVE,
                MESSAGE_INDEX_IS_NOT_A_POSITIVE_INTEGER);
        // invalid task number - ZERO
        assertParseFailure(parser,
                MODULE_CODE_DESC_MA2001 + INVALID_TASK_NUMBER_DESC_ZERO,
                MESSAGE_INDEX_IS_NOT_A_POSITIVE_INTEGER);
        // both module code and task description are invalid - only
        // invalid module code is reported.
        assertParseFailure(parser, INVALID_TASK_NUMBER_DESC_NON_NUMERIC + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC + INVALID_TASK_NUMBER_DESC_NON_NUMERIC,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + MODULE_CODE_DESC_MA2001 + MODULE_TASKLIST_DESC_NUMBER_THREE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
    }
}
