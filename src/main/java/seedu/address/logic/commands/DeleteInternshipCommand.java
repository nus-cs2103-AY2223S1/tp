package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.internship.Internship;

/**
 * Deletes an internship identified using it's displayed index from the internship list.
 */
public class DeleteInternshipCommand extends Command {

    public static final String COMMAND_WORD = "delete -i";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the internship identified by the index number used in the displayed internship list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INTERNSHIP_SUCCESS = "Deleted Internship: %1$s";

    private final Index targetIndex;

    public DeleteInternshipCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownInternshipList = model.getFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownInternshipList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToDelete = lastShownInternshipList.get(targetIndex.getZeroBased());
        model.deleteInternship(internshipToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INTERNSHIP_SUCCESS, internshipToDelete));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteInternshipCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteInternshipCommand) other).targetIndex)); // state check
    }
}
