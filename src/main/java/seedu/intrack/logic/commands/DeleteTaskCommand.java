package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;
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
 * Deletes the task identified by the index number used in the displayed task list of the selected internship
 * application.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deltask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the task identified by the index number used "
            + "in the displayed task list of the selected internship application.\n"
            + "Parameters: TASK_INDEX (must be a positive unsigned integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS =
            "Deleted a task from the selected internship application: \n%1$s";

    private final Index index;

    /**
     * @param index Index of the task to be deleted
     */
    public DeleteTaskCommand(Index index) {
        assert index.getOneBased() > 0 : "index should be a positive unsigned integer";
        requireNonNull(index);
        this.index = index;
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
        if (index.getZeroBased() >= copiedTasks.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_INDEX);
        }
        List<Task> editedTasks = new ArrayList<>(copiedTasks);
        editedTasks.remove(index.getZeroBased());

        Internship editedInternship = new Internship(internshipToEdit.getName(), internshipToEdit.getPosition(),
                internshipToEdit.getStatus(), internshipToEdit.getEmail(), internshipToEdit.getWebsite(), editedTasks,
                internshipToEdit.getSalary(), internshipToEdit.getTags(), internshipToEdit.getRemark());

        model.setInternship(internshipToEdit, editedInternship);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, editedInternship));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && index.equals(((DeleteTaskCommand) other).index)); // state check
    }

}
