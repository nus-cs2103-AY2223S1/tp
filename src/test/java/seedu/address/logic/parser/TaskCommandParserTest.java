package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TASK_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TaskCommand;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;


public class TaskCommandParserTest {

    private TaskCommandParser parser = new TaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder()
                .withTitle(VALID_TASK_TITLE)
                .withDescription(VALID_TASK_DESCRIPTION)
                .build();

        assertParseSuccess(parser, TASK_TITLE_DESC + TASK_DESCRIPTION_DESC, new TaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TASK_TITLE  + TASK_DESCRIPTION_DESC, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, TASK_TITLE_DESC + VALID_TASK_DESCRIPTION, expectedMessage);

        // missing all prefix
        assertParseFailure(parser, VALID_TASK_TITLE + VALID_TASK_DESCRIPTION, expectedMessage);
    }
}
