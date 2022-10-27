package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Clears the task panel.
 */
public class SortTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "sort";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL
            + ": Sort the tasks by their deadlines.\n"
            + "Parameters: \n"
            + "Example: " + COMMAND_WORD_FULL;

    public static final String MESSAGE_SUCCESS = "Task Panel has been sorted by deadlines.";

    private static final Comparator<Task> DeadlineComparator = Comparator.comparing(Task::getDeadline);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<Task> taskList = new ArrayList<>(model.getFilteredTaskList());
        taskList.sort(DeadlineComparator);
        model.setFilteredTaskList(FXCollections.observableList(taskList));

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
