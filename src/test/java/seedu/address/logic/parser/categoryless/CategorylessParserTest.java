package seedu.address.logic.parser.categoryless;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.categoryless.ExitCommand;

public class CategorylessParserTest {

    @Test
    public void parseCommand_exit() throws Exception {
        String arguments = "";
        Command command = CategorylessParser.parseCommand(ExitCommand.COMMAND_WORD, arguments);
        assertTrue(command instanceof ExitCommand);
    }
}
