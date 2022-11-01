package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.categoryless.HelpCommand;
import taskbook.logic.commands.categoryless.UndoCommand;
import taskbook.logic.commands.tasks.TaskSortCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;

public class HelpCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    public void execute_null_returnsGeneralUsage() {
        CommandResult expected = new CommandResult(HelpCommand.USER_GUIDE_LINK + HelpCommand.MESSAGE_GENERAL_USAGE);
        assertCommandSuccess(new HelpCommand(null), model, expected, expectedModel);
    }

    @Test
    public void execute_taskSort_returnsUsage() {
        CommandResult expected = new CommandResult(TaskSortCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand(HelpCommand.CommandWord.TASK_SORT), model, expected, expectedModel);
    }

    @Test
    public void execute_undo_returnsUsage() {
        CommandResult expected = new CommandResult(UndoCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand(HelpCommand.CommandWord.UNDO), model, expected, expectedModel);
    }

    @Test
    public void equals_nullCommandWord() {
        HelpCommand null1 = new HelpCommand(null);
        HelpCommand null2 = new HelpCommand(null);
        assertEquals(null1, null2);
        assertEquals(null2, null1);
        assertNotEquals(null1, null);

        HelpCommand other = new HelpCommand(HelpCommand.CommandWord.REDO);
        assertNotEquals(null1, other);
        assertNotEquals(other, null1);
        assertNotEquals(other, null);
    }
}
