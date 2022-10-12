package seedu.address.logic.commands.task;


import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.Task;


/**
 * Lists all persons in the address book to the user.
 */
public class TaskListCommand extends Command {

    public static final String COMMAND_WORD = "task list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        List<Task> tasks = model.getFilteredTaskList();
        String display = "";

        for (int i = 0; i < tasks.size(); i++) {
            display += tasks.get(i).toString() + "\n";
        }

        return new CommandResult(display + MESSAGE_SUCCESS);
    }
}
