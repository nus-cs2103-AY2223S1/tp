package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.hrpro.commons.core.Messages;
import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.exceptions.CommandException;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.project.Project;

/**
 * Displays all staff members for a given project in HR Pro Max++ to the user.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Listed all Staff members from: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the staff list of the project identified by the index number used.\n"
            + "Parameters: INDEX (must be a number from 1 to 2147483647)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Creates a view command to put the specified {@code UniqueStaffList} on display
     */
    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Project> projectToView = model.getProjectWithIndex(targetIndex);
        Project toView = projectToView.orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX));

        model.setFilteredStaffList(toView.getStaffList());
        model.updateFilteredStaffList(Model.PREDICATE_SHOW_ALL_STAFF);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toView));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }
}
