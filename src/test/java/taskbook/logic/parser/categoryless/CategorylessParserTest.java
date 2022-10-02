package taskbook.logic.parser.categoryless;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.categoryless.ExitCommand;

public class CategorylessParserTest {

    @Test
    public void parseCommand_exit() throws Exception {
        String arguments = "";
        Command command = CategorylessParser.parseCommand(ExitCommand.COMMAND_WORD, arguments);
        assertTrue(command instanceof ExitCommand);
    }
}
