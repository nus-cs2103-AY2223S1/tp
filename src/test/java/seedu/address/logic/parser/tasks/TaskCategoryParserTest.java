package seedu.address.logic.parser.tasks;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.tasks.TaskAddCommand;
import seedu.address.logic.commands.tasks.TaskDeleteCommand;
import seedu.address.logic.commands.tasks.TaskListCommand;

public class TaskCategoryParserTest {

    @Test
    public void parseCommand_add() throws Exception {
        String arguments = "o/Sam d/Finish the assignment";
        Command command = TaskCategoryParser.parseCommand(TaskAddCommand.COMMAND_WORD, arguments);
        assertTrue(command instanceof TaskAddCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        String arguments = "1";
        Command command = TaskCategoryParser.parseCommand(TaskDeleteCommand.COMMAND_WORD, arguments);
        assertTrue(command instanceof TaskDeleteCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        String arguments = "";
        Command command = TaskCategoryParser.parseCommand(TaskListCommand.COMMAND_WORD, arguments);
        assertTrue(command instanceof TaskListCommand);
    }
}
