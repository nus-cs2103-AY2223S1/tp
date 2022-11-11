package swift.logic.commands;

import static java.util.Objects.requireNonNull;
import static swift.logic.commands.EditTaskCommand.EditTaskDescriptor;
import static swift.logic.commands.EditTaskCommand.createEditedTask;
import static swift.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static swift.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.ArrayList;
import java.util.List;

import swift.commons.core.Messages;
import swift.commons.core.index.Index;
import swift.logic.commands.exceptions.CommandException;
import swift.logic.parser.Prefix;
import swift.model.Model;
import swift.model.task.Task;

/**
 * Marks an existing task in the address book as incomplete.
 */
public class UnmarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES =
            new ArrayList<>(List.of(PREFIX_TASK_INDEX));

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number "
            + "used in the displayed task list as incomplete.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Marked Task %1$s as incomplete.";
    public static final String MESSAGE_TASK_ALREADY_INCOMPLETE =
            "This task is already marked as incomplete.";

    private final Index index;
    private final EditTaskDescriptor unmarkTaskDescriptor;


    /**
     * @param index of the task in the filtered task list to mark as incomplete
     */
    public UnmarkTaskCommand(Index index) {
        requireNonNull(index);
        this.index = index;
        unmarkTaskDescriptor = new EditTaskDescriptor();
        unmarkTaskDescriptor.setIsDone(false);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, unmarkTaskDescriptor);

        if (!taskToEdit.isDone()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_INCOMPLETE);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, editedTask),
                CommandType.TASKS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkTaskCommand)) {
            return false;
        }

        // state check
        UnmarkTaskCommand e = (UnmarkTaskCommand) other;
        return index.equals(e.index) && unmarkTaskDescriptor.equals(e.unmarkTaskDescriptor);
    }
}
