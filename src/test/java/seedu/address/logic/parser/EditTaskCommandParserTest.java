package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.model.task.Task;

/**
 * Contains unit tests for EditTaskCommandParser.
 */
class EditTaskCommandParserTest {

    private final EditTaskCommandParser parser = new EditTaskCommandParser();
    private final String nonEmptyTask = "Some task";

    @Test
    public void parse_patientIndexSpecifiedTaskIndexSpecified_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_TASK.getOneBased() + " "
                + PREFIX_TASK_DESCRIPTION + nonEmptyTask;

        EditTaskCommand expectedCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK,
                new Task(nonEmptyTask));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_patientIndexMissingTaskIndexSpecified_failure() {
        String userInput = INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_TASK_DESCRIPTION + nonEmptyTask;

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_patientIndexSpecifiedTaskIndexMissing_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_TASK_DESCRIPTION + nonEmptyTask;

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_emptyTaskEdit_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_TASK.getOneBased() + " "
                + PREFIX_TASK_DESCRIPTION;

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_taskPrefixMissing_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_TASK.getOneBased() + " ";

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_NOT_EDITED);

        assertParseFailure(parser, userInput, expectedMessage);
    }
}
