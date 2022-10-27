package taskbook.logic.parser;

import static taskbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.tasks.TaskSortAddedChronologicalCommand;
import taskbook.logic.commands.tasks.TaskSortCommand;
import taskbook.logic.commands.tasks.TaskSortDateChronologicalCommand;
import taskbook.logic.commands.tasks.TaskSortDateReverseChronologicalCommand;
import taskbook.logic.commands.tasks.TaskSortDescriptionAlphabeticalCommand;
import taskbook.logic.commands.tasks.TaskSortDescriptionReverseAlphabeticalCommand;
import taskbook.logic.parser.tasks.TaskSortCommandParser;

public class TaskSortCommandParserTest {
    private TaskSortCommandParser parser = new TaskSortCommandParser();

    @Test
    public void parse_validArgs_returnsTaskSortDescriptionAlphabeticalCommand() {
        // Note: the space at the start of the userInput is necessary due to ArgumentTokenizer behavior.
        assertParseSuccess(parser, " s/a", new TaskSortDescriptionAlphabeticalCommand());
    }

    @Test
    public void parse_validArgs_returnsTaskSortDescriptionReverseAlphabeticalCommand() {
        // Note: the space at the start of the userInput is necessary due to ArgumentTokenizer behavior.
        assertParseSuccess(parser, " s/ra", new TaskSortDescriptionReverseAlphabeticalCommand());
    }

    @Test
    public void parse_validArgs_returnsTaskSortAddedChronologicalCommand() {
        // Note: the space at the start of the userInput is necessary due to ArgumentTokenizer behavior.
        assertParseSuccess(parser, " s/ca", new TaskSortAddedChronologicalCommand());
    }

    @Test
    public void parse_validArgs_returnsTaskSortDateChronologicalCommand() {
        // Note: the space at the start of the userInput is necessary due to ArgumentTokenizer behavior.
        assertParseSuccess(parser, " s/cd", new TaskSortDateChronologicalCommand());
    }

    @Test
    public void parse_validArgs_returnsTaskSortDateReverseChronologicalCommand() {
        // Note: the space at the start of the userInput is necessary due to ArgumentTokenizer behavior.
        assertParseSuccess(parser, " s/rcd", new TaskSortDateReverseChronologicalCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskSortCommand.MESSAGE_USAGE));
    }
}
