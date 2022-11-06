package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Task;

/**
 * Adds a new task to the selected internship application.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a new task to the selected internship application.\n"
            + "Parameters: TASK_NAME /at TASK_TIME (must be in the format dd-MM-yyyy HH:mm)\n"
            + "Example: " + COMMAND_WORD + " Technical Interview /at 04-11-2022 17:00";

    public static final String MESSAGE_ADD_TASK_SUCCESS =
            "Added a new task to the selected internship application: \n%1$s";

    private final Task task;

    /**
     * @param task Task to be added
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        assert task != null : "task should not be null";

        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Internship> lastShownList = model.getSelectedInternship();
        if (lastShownList.size() != 1) {
            throw new CommandException(Messages.MESSAGE_NO_INTERNSHIP_SELECTED);
        }
        Internship internshipToEdit = lastShownList.get(0);

        List<Task> copiedTasks = internshipToEdit.getTasks();
        List<Task> editedTasks = new ArrayList<>(copiedTasks);
        editedTasks.add(task);
        editedTasks.sort(Comparator.comparing(task -> task.taskTime));

        Internship editedInternship = new Internship(internshipToEdit.getName(), internshipToEdit.getPosition(),
                internshipToEdit.getStatus(), internshipToEdit.getEmail(), internshipToEdit.getWebsite(),
                editedTasks, internshipToEdit.getSalary(), internshipToEdit.getTags(), internshipToEdit.getRemark());

        model.setInternship(internshipToEdit, editedInternship);

        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, editedInternship));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && task.equals(((AddTaskCommand) other).task)); // state check
    }

}
