package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Status;

/**
 * Updates the status of an Internship with upper and lowercase "p", "r" and "o" after
 * the s/ prefix.
 */
public class StatusCommand extends Command {

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, status: %2$s";
    public static final String COMMAND_WORD = "status";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Quickly updates the progress of the your Internship status "
            + "by the index number used in the last Internship listing. "
            + "Existing Status will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + " [STATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + " p";
    public static final String STATUS_COMMAND_CONSTRAINTS =
              "You can only use the command word p or P to set this Internship as Progressing, \n"
            + "the command word o or O to set this Internship as Offered\n"
            + "and the command word r or R to set this Internship as Rejected\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + " r\n"
            + "to set this Internship's status to Rejected";

    public static final String MESSAGE_UPDATE_STATUS_SUCCESS = "Updated status of Internship: %1$s";
    public static final String MESSAGE_DELETE_STATUS_SUCCESS = "Removed status from Internship: %1$s";

    private final Index index;

    private final Status status;

    /**
     * @param index of the internship in the internship list to update the status of
     * @param status of the internship application
     */
    public StatusCommand(Index index, Status status) {
        requireAllNonNull(index, status);

        this.index = index;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToedit = lastShownList.get(index.getZeroBased());
        Internship editedInternship = new Internship(internshipToedit.getName(),
                internshipToedit.getPosition(), internshipToedit.getPhone(),
                internshipToedit.getEmail(), status, internshipToedit.getAddress(),
                internshipToedit.getTags());

        model.setInternship(internshipToedit, editedInternship);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        return new CommandResult(generateSuccessMessage(editedInternship));
    }

    private String generateSuccessMessage(Internship internshipToedit) {
        String message = !status.value.isEmpty() ? MESSAGE_UPDATE_STATUS_SUCCESS : MESSAGE_DELETE_STATUS_SUCCESS;
        return String.format(message, internshipToedit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StatusCommand)) {
            return false;
        }
        StatusCommand e = (StatusCommand) other;
        return index.equals(e.index)
                && status.equals(e.status);
    }


}
