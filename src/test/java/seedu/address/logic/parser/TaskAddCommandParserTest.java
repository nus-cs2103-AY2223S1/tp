package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_STUDY;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_STUDY;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_INDEX_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalTasks.STUDY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TaskAddCommand;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class TaskAddCommandParserTest {
    private TaskAddCommandParser parser = new TaskAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(STUDY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TEAM_INDEX_1 + TASK_NAME_DESC_STUDY + TASK_DEADLINE_DESC_STUDY, new TaskAddCommand(INDEX_FIRST_PERSON, expectedTask));

        // multiple names - last name accepted

    }
}
