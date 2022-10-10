package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.List.ListAllCommand;
import seedu.address.logic.commands.List.ListModuleCommand;
import seedu.address.logic.commands.List.ListUnmarkedCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListCommandParserTest {

    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parseCommand_listAll() throws Exception {
        assertTrue(parser.parse(ListAllCommand.COMMAND_WORD) instanceof ListAllCommand);
        assertTrue(parser.parse(ListAllCommand.COMMAND_WORD + " 3") instanceof ListAllCommand);
    }

    @Test
    public void parseCommand_listModule() throws Exception {
        assertTrue(parser.parse(ListModuleCommand.COMMAND_WORD) instanceof ListModuleCommand);
        assertTrue(parser.parse(ListModuleCommand.COMMAND_WORD + " 3") instanceof ListModuleCommand);
    }

    @Test
    public void parseCommand_listUnmarked() throws Exception {
        assertTrue(parser.parse(ListUnmarkedCommand.COMMAND_WORD) instanceof ListUnmarkedCommand);
        assertTrue(parser.parse(ListUnmarkedCommand.COMMAND_WORD + " 3") instanceof ListUnmarkedCommand);
    }

}
