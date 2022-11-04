package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TEAM_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_INDEX_1;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_INDEX_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TaskUnmarkCommand;

public class TaskUnmarkCommandParserTest {
    private final TaskUnmarkCommandParser parser = new TaskUnmarkCommandParser();

    @Test
    public void parse_correctFormat_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TEAM_INDEX_1 + TASK_INDEX_1,
                new TaskUnmarkCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidTeamIndex_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_TEAM_INDEX + TASK_INDEX_1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskUnmarkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTaskIndex_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TEAM_INDEX_1 + INVALID_TASK_INDEX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskUnmarkCommand.MESSAGE_USAGE));
    }
}
