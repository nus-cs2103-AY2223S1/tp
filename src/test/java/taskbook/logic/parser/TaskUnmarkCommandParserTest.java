package taskbook.logic.parser;

import static taskbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static taskbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.tasks.TaskUnmarkCommand;
import taskbook.logic.parser.tasks.TaskUnmarkCommandParser;

public class TaskUnmarkCommandParserTest {

    private TaskUnmarkCommandParser parser = new TaskUnmarkCommandParser();

    @Test
    public void parse_validArgs_returnsUnmarkCommand() {
        // Note: the space at the start of the userInput is necessary due to ArgumentTokenizer behavior.
        assertParseSuccess(parser, " i/1", new TaskUnmarkCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskUnmarkCommand.MESSAGE_USAGE));
    }
}
