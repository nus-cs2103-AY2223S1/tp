package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Internship;

/**
 * Selects and shows the details of the internship application specified by the given index.
 */
public class SelectCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects and shows the details of the internship application identified by the index number"
            + " used in the displayed list"
            + "Parameters: INDEX (must be a positive unsigned integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_INTERNSHIP_SUCCESS = "Selected internship application: \n%1$s";

    private final Index targetIndex;

    /**
     * Creates a SelectCommand to select the Internship at the specified Index.
     * @param targetIndex Index of the Internship to be selected.
     */
    public SelectCommand(Index targetIndex) {
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

        Internship internshipToView = lastShownList.get(targetIndex.getZeroBased());
        model.updateSelectedInternship(a -> a.isSameInternship(internshipToView));

        return new CommandResult(String.format(MESSAGE_SELECT_INTERNSHIP_SUCCESS, internshipToView));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectCommand // instanceof handles nulls
                && targetIndex.equals(((SelectCommand) other).targetIndex)); // state check
    }

}
