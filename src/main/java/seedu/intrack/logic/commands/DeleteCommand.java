package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Internship;

/**
 * Deletes the internship application identified by the index number used in the displayed list from InTrack.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the internship application identified by the index number used in the displayed list "
            + "from InTrack.\n"
            + "Parameters: INDEX (must be a positive unsigned integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INTERNSHIP_SUCCESS = "Deleted internship application: \n%1$s";

    private final Index targetIndex;

    /**
     * @param targetIndex Index of the internship application to be deleted.
     */
    public DeleteCommand(Index targetIndex) {
        assert targetIndex.getOneBased() > 0 : "index should be a positive unsigned integer";
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Internship> lastShownList = model.getFilteredInternshipList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteInternship(internshipToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_INTERNSHIP_SUCCESS, internshipToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }

}
