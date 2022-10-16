package taskbook.logic.commands.tasks;

import java.util.function.Predicate;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;
import taskbook.model.task.Task;

/**
 * Finds all tasks matching a keyword.
 * Currently unimplemented
 */
public class TaskFindCommand extends Command {
    private Predicate<Task> predicate = new Predicate<Task>() {
        @Override
        public boolean test(Task task) {
            return true;
        }
    };
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.changeFilteredTaskList(predicate);
        return new CommandResult("SUCCESS");
    }
}
