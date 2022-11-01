package taskbook.logic.parser;

import static taskbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static taskbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.tasks.TaskMarkCommand;
import taskbook.logic.parser.tasks.TaskMarkCommandParser;

public class TaskMarkCommandParserTest {

    private TaskMarkCommandParser parser = new TaskMarkCommandParser();

    @Test
    public void parse_validArgs_returnsMarkCommand() {
        // Note: the space at the start of the userInput is necessary due to ArgumentTokenizer behavior.
        assertParseSuccess(parser, " i/1", new TaskMarkCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskMarkCommand.MESSAGE_USAGE));
    }
}
