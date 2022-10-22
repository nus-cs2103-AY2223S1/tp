package seedu.intrack.logic.commands;

import static seedu.intrack.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.intrack.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.ArrayList;
import java.util.List;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Task;

/**
 * Updates the current task of an Internship.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the task of the internship identified by "
            + "the index number used in the displayed list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + " DESCRIPTION /at TIME\n"
            + "Example: " + COMMAND_WORD + " 1 Technical Interview /at 04-11-2022 17:00";

    public static final String TASK_COMMAND_CONSTRAINTS = "TASK must be in the format: \n"
            + COMMAND_WORD + " INDEX DESCRIPTION /at TIME\n"
            + "TIME must be in the format dd-MM-yyyy HH:mm";

    public static final String MESSAGE_UPDATE_TASK_SUCCESS = "Updated task of internship: %1$s";

    private final Index index;

    private final Task task;

    /**
     * @param index of the internship in the internship list to update the task of
     * @param task of the internship application
     */
    public AddTaskCommand(Index index, Task task) {
        requireAllNonNull(index, task);

        this.index = index;
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToEdit = lastShownList.get(index.getZeroBased());
        List<Task> copyTasks = internshipToEdit.getTasks();
        List<Task> editedTasks = new ArrayList<>(copyTasks);
        editedTasks.add(task);

        Internship editedInternship = new Internship(internshipToEdit.getName(), internshipToEdit.getPosition(),
                internshipToEdit.getStatus(), internshipToEdit.getPhone(), internshipToEdit.getEmail(),
                internshipToEdit.getAddress(), editedTasks, internshipToEdit.getTags(), internshipToEdit.getRemark());

        model.setInternship(internshipToEdit, editedInternship);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        return new CommandResult(String.format(MESSAGE_UPDATE_TASK_SUCCESS, editedInternship));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddTaskCommand)) {
            return false;
        }

        AddTaskCommand e = (AddTaskCommand) other;

        return index.equals(e.index)
                && task.equals(e.task);
    }

}
