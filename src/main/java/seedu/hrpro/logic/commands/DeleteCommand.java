package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.hrpro.commons.core.Messages;
import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.exceptions.CommandException;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.staff.UniqueStaffList;

/**
 * Deletes a project identified using it's displayed index from HR Pro Max++.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delproj";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the project identified by the index number used in the displayed project list.\n"
            + "Parameters: INDEX (must be a number from 1 to 2147483647)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "Deleted Project: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Project> projectToDelete = model.getProjectWithIndex(targetIndex);
        Project toDelete = projectToDelete.orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX));

        model.deleteProject(toDelete);
        model.setFilteredStaffList(new UniqueStaffList());
        return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
