package seedu.phu.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.phu.commons.core.Messages;
import seedu.phu.commons.core.index.Indexes;
import seedu.phu.commons.exceptions.IllegalIndexException;
import seedu.phu.logic.commands.exceptions.CommandException;
import seedu.phu.model.Model;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.UniqueInternshipList;

/**
 * Deletes all internships identified using their displayed index from the PleaseHireUs
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the internships identified by the index numbers used in the displayed internship list.\n"
            + "Parameters: INDEX... (all indexes must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 3";

    public static final String MESSAGE_DELETE_INTERNSHIP_SUCCESS = "Deleted Internship(s):\n%s";

    private final Indexes targetIndexes;

    public DeleteCommand(Indexes targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();
        UniqueInternshipList internshipsToRemove;

        try {
            internshipsToRemove = targetIndexes.getAllInternshipsFromIndexes(lastShownList);
        } catch (IllegalIndexException error) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        for (Internship internshipToDelete : internshipsToRemove) {
            model.deleteInternship(internshipToDelete);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_INTERNSHIP_SUCCESS, internshipsToRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)); // state check
    }
}
