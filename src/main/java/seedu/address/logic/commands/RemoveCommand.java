package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.applicant.ApplicationStatus;

/**
 * Removes applicants whose application status matches user specified status from TrackAScholar.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes applicants whose application process"
            + " is completed (accepted/rejected) from the applicant list.\n"
            + "Parameters: accepted/rejected\n"
            + "Example: " + COMMAND_WORD + " accepted";

    public static final String MESSAGE_REMOVE_APPLICANTS_SUCCESS = "Applicants have been removed";

    private final ApplicationStatus targetStatus;

    public RemoveCommand(ApplicationStatus targetStatus) {
        this.targetStatus = targetStatus;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.removeApplicant(targetStatus);
        return new CommandResult(MESSAGE_REMOVE_APPLICANTS_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && targetStatus.equals(((RemoveCommand) other).targetStatus)); // state check
    }
}
