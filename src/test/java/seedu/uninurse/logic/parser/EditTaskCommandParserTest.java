package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_DATETIME_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_DATETIME_RECURRENCE_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_DESCRIPTION_DATETIME_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_DESCRIPTION_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_DESCRIPTION_RECURRENCE_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_RECURRENCE_INSULIN;
import static seedu.uninurse.logic.commands.EditTaskCommand.MESSAGE_FAILURE;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.uninurse.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_ONE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalTasks.TYPICAL_RECURRENCE_DAY;
import static seedu.uninurse.testutil.TypicalTasks.TYPICAL_TASK_INSULIN;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.EditTaskCommand;
import seedu.uninurse.logic.commands.EditTaskCommand.EditTaskDescriptor;

/**
 * Contains unit tests for {@code EditTaskCommandParser}.
 */
class EditTaskCommandParserTest {
    private final EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_patientIndexSpecifiedTaskIndexSpecified_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_TASK_DESCRIPTION + TYPICAL_TASK_INSULIN
                + " | " + DATE_TIME_ONE + "|" + TYPICAL_RECURRENCE_DAY;

        EditTaskCommand expectedCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
                DESC_TASK_INSULIN);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_patientIndexMissingTaskIndexSpecified_failure() {
        String userInput = INDEX_FIRST_ATTRIBUTE.getOneBased() + " " + PREFIX_TASK_DESCRIPTION + TYPICAL_TASK_INSULIN;

        assertParseFailure(parser, userInput, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_patientIndexSpecifiedTaskIndexMissing_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_TASK_DESCRIPTION + TYPICAL_TASK_INSULIN;

        assertParseFailure(parser, userInput, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_emptyDescriptionEmptyDatetimeEmptyRecurrenceEdit_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_TASK_DESCRIPTION;

        assertParseFailure(parser, userInput, MESSAGE_FAILURE);
    }

    @Test
    public void parse_taskPrefixMissing_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " ";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_emptyDescriptionEdit_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_TASK_DESCRIPTION + " | " + DATE_TIME_ONE + "|" + TYPICAL_RECURRENCE_DAY;

        EditTaskCommand expectedCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
                DESC_TASK_DATETIME_RECURRENCE_INSULIN);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyDatetimeEdit_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_TASK_DESCRIPTION + TYPICAL_TASK_INSULIN + " | " + "|" + TYPICAL_RECURRENCE_DAY;

        EditTaskCommand expectedCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
                DESC_TASK_DESCRIPTION_RECURRENCE_INSULIN);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyRecurrenceEdit_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_TASK_DESCRIPTION + TYPICAL_TASK_INSULIN + " | " + DATE_TIME_ONE;

        EditTaskCommand expectedCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
                DESC_TASK_DESCRIPTION_DATETIME_INSULIN);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyDescriptionEmptyDatetimeEdit_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_TASK_DESCRIPTION + " | " + "|" + TYPICAL_RECURRENCE_DAY;

        EditTaskCommand expectedCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
                DESC_TASK_RECURRENCE_INSULIN);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyDescriptionEmptyRecurrenceEdit_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_TASK_DESCRIPTION + " | " + DATE_TIME_ONE;

        EditTaskCommand expectedCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
                DESC_TASK_DATETIME_INSULIN);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyDatetimeEmptyRecurrenceEdit_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_TASK_DESCRIPTION + TYPICAL_TASK_INSULIN;

        EditTaskCommand expectedCommand = new EditTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE,
                DESC_TASK_DESCRIPTION_INSULIN);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_endWithTaskSeparatorEdit_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_TASK_DESCRIPTION + TYPICAL_TASK_INSULIN + " | ";
        assertParseFailure(parser, userInput, EditTaskDescriptor.MESSAGE_CONSTRAINTS);
    }
}
