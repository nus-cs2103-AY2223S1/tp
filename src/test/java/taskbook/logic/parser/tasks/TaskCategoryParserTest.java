package taskbook.logic.parser.tasks;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.tasks.TaskDeadlineCommand;
import taskbook.logic.commands.tasks.TaskDeleteCommand;
import taskbook.logic.commands.tasks.TaskEventCommand;
import taskbook.logic.commands.tasks.TaskListCommand;
import taskbook.logic.commands.tasks.TaskTodoCommand;
import taskbook.logic.parser.exceptions.ParseException;

public class TaskCategoryParserTest {

    @Test
    public void parseCommand_todo() throws Exception {
        // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
        String arguments = " o/Sam d/Finish the assignment";
        Command command = TaskCategoryParser.parseCommand(TaskTodoCommand.COMMAND_WORD, arguments);
        assertTrue(command instanceof TaskTodoCommand);
    }

    @Test
    public void parseCommand_deadline() throws Exception {
        // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
        String arguments = " o/Sam d/Finish the assignment t/2022-03-11";
        Command command = TaskCategoryParser.parseCommand(TaskDeadlineCommand.COMMAND_WORD, arguments);
        assertTrue(command instanceof TaskDeadlineCommand);
    }

    @Test
    public void parseCommand_event() throws Exception {
        // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
        String arguments = " o/Sam d/Finish the assignment t/2005-10-27";
        Command command = TaskCategoryParser.parseCommand(TaskEventCommand.COMMAND_WORD, arguments);
        assertTrue(command instanceof TaskEventCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
        String arguments = " i/1";
        Command command = TaskCategoryParser.parseCommand(TaskDeleteCommand.COMMAND_WORD, arguments);
        assertTrue(command instanceof TaskDeleteCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        String arguments = "";
        Command command = TaskCategoryParser.parseCommand(TaskListCommand.COMMAND_WORD, arguments);
        assertTrue(command instanceof TaskListCommand);
    }

    @Test
    public void parseCommand_invalid() {
        // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
        String arguments = " o/Sam d/Finish the assignment";
        String commandWord = "gibberish";
        assertThrows(ParseException.class, () -> TaskCategoryParser.parseCommand(commandWord, arguments));
    }
}
