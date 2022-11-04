package seedu.trackascholar.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.ui.AlertWindow;

/**
 * Removes applicants whose application status matches user specified status from TrackAScholar.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes applicants whose application process "
            + "is completed, i.e. accepted/rejected (case-insensitive), from the applicant list.\n"
            + "Parameters: accepted/rejected\n"
            + "Example: " + COMMAND_WORD + " accepted";

    public static final String MESSAGE_REMOVE_APPLICANTS_SUCCESS = "Applicants have been removed";
    public static final String MESSAGE_REMOVE_APPLICANTS_TERMINATION = "Removal of selected applicants is cancelled!";
    public static final String MESSAGE_REMOVE_APPLICANTS_CONFIRMATION =
            "Are you sure that you want to remove selected applicants from TrackAScholar?";

    private final ApplicationStatus targetStatus;

    private boolean isConfirmed;

    public RemoveCommand(ApplicationStatus targetStatus) {
        this.targetStatus = targetStatus;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        promptUserConfirmation(MESSAGE_REMOVE_APPLICANTS_CONFIRMATION);
        if (isConfirmed) {
            return confirmRemove(model);
        } else {
            return cancelRemove();
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && targetStatus.equals(((RemoveCommand) other).targetStatus)); // state check
    }

    /**
     * After confirmation from user, all data from TrackAScholar is purged.
     */
    public CommandResult confirmRemove(Model model) {
        model.removeApplicant(targetStatus);
        return new CommandResult(MESSAGE_REMOVE_APPLICANTS_SUCCESS);
    }

    /**
     * After cancellation from user, all data from TrackAScholar remains intact.
     */
    public CommandResult cancelRemove() {
        return new CommandResult(MESSAGE_REMOVE_APPLICANTS_TERMINATION);
    }

    /**
     * Prompts user for confirmation before proceeding with removing of data
     * @param  message prompted to user
     */
    public void promptUserConfirmation(String message) {
        AlertWindow window = new AlertWindow();
        this.isConfirmed = window.display(message);
    }
}
