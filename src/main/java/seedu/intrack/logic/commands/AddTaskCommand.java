package seedu.intrack.logic.commands;

import static seedu.intrack.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.intrack.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Task;

/**
 * Adds a new Task to the selected Internship.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the selected internship application.\n"
            + "Parameters: DESCRIPTION /at TIME (must be in the format dd-MM-yyyy HH:mm)\n"
            + "Example: " + COMMAND_WORD + " Technical Interview /at 04-11-2022 17:00";

    public static final String MESSAGE_UPDATE_TASK_SUCCESS = "Added task: %1$s";

    private final Task task;

    /**
     * @param task Task to be added
     */
    public AddTaskCommand(Task task) {
        requireAllNonNull(task);

        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
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
                internshipToEdit.getStatus(), internshipToEdit.getPhone(), internshipToEdit.getEmail(),
                internshipToEdit.getWebsite(), editedTasks, internshipToEdit.getTags(), internshipToEdit.getRemark());

        model.setInternship(internshipToEdit, editedInternship);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        return new CommandResult(String.format(MESSAGE_UPDATE_TASK_SUCCESS, editedInternship));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && task.equals(((AddTaskCommand) other).task)); // state check
    }

}
