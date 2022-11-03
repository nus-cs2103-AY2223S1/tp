package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DEADLINE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DEADLINE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TEAM_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_COOK;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_STUDY;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_COOK;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_REVIEW;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_STUDY;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_INDEX_1;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_INDEX_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_STUDY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAM;
import static seedu.address.testutil.TypicalTasks.REVIEW;
import static seedu.address.testutil.TypicalTasks.STUDY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TaskAddCommand;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class TaskAddCommandParserTest {
    private TaskAddCommandParser parser = new TaskAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(STUDY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TEAM_INDEX_1 + TASK_NAME_DESC_STUDY
                + TASK_DEADLINE_DESC_STUDY, new TaskAddCommand(INDEX_FIRST_TEAM, expectedTask));

        // multiple team index - last team index accepted
        assertParseSuccess(parser, TEAM_INDEX_1 + TEAM_INDEX_2 + TASK_NAME_DESC_STUDY
                + TASK_DEADLINE_DESC_STUDY, new TaskAddCommand(INDEX_SECOND_TEAM, expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, TEAM_INDEX_1 + TASK_NAME_DESC_COOK + TASK_NAME_DESC_STUDY
                + TASK_DEADLINE_DESC_STUDY, new TaskAddCommand(INDEX_FIRST_TEAM, expectedTask));

        // multiple deadline - last deadline accepted
        assertParseSuccess(parser, TEAM_INDEX_1 + TASK_NAME_DESC_STUDY + TASK_DEADLINE_DESC_COOK
                + TASK_DEADLINE_DESC_STUDY, new TaskAddCommand(INDEX_FIRST_TEAM, expectedTask));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no deadline given
        Task expectedTask = new TaskBuilder(REVIEW).build();
        assertParseSuccess(parser, TEAM_INDEX_1 + TASK_NAME_DESC_REVIEW,
                new TaskAddCommand(INDEX_FIRST_TEAM, expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE);

        // missing team index prefix
        assertParseFailure(parser, "1" + VALID_TASK_NAME_STUDY, expectedMessage);

        // missing name prefix
        assertParseFailure(parser, TEAM_INDEX_1 + VALID_TASK_NAME_STUDY, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid team index
        assertParseFailure(parser, INVALID_TEAM_INDEX + TASK_NAME_DESC_STUDY + TASK_DEADLINE_DESC_STUDY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));

        // invalid name
        assertParseFailure(parser, TEAM_INDEX_1 + INVALID_TASK_NAME_DESC + TASK_DEADLINE_DESC_STUDY,
                Name.MESSAGE_CONSTRAINTS);

        // invalid deadline (wrong format)
        assertParseFailure(parser, TEAM_INDEX_1 + TASK_NAME_DESC_STUDY + INVALID_TASK_DEADLINE_DESC_1,
                Task.MESSAGE_INVALID_DATE_FORMAT);

        // invalid deadline (non-existent date)
        assertParseFailure(parser, TEAM_INDEX_1 + TASK_NAME_DESC_STUDY + INVALID_TASK_DEADLINE_DESC_2,
                Task.MESSAGE_INVALID_DATE_FORMAT);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TEAM_INDEX_1 + TASK_NAME_DESC_STUDY + TASK_DEADLINE_DESC_STUDY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));
    }
}
