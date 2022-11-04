package seedu.trackascholar.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.TrackAScholar;
import seedu.trackascholar.ui.AlertWindow;

/**
 * Clears all applicants from TrackAScholar.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_CLEAR_SUCCESS = "TrackAScholar has been cleared!";
    public static final String MESSAGE_CLEAR_TERMINATION = "Clearing of all data is cancelled!";
    public static final String MESSAGE_CLEAR_CONFIRMATION =
            "Are you sure that you want to terminate all data from TrackAScholar?";

    private boolean isConfirmed;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        promptUserConfirmation(MESSAGE_CLEAR_CONFIRMATION);
        if (isConfirmed) {
            return confirmClear(model);
        } else {
            return cancelClear();
        }
    }

    /**
     * After confirmation from user, all data from TrackAScholar is purged.
     */
    public CommandResult confirmClear(Model model) {
        model.setTrackAScholar(new TrackAScholar());
        return new CommandResult(MESSAGE_CLEAR_SUCCESS);
    }

    /**
     * After cancellation from user, all data from TrackAScholar remains intact.
     */
    public CommandResult cancelClear() {
        return new CommandResult(MESSAGE_CLEAR_TERMINATION);
    }

    /**
     * Prompts user for confirmation before proceeding with purging of data
     * @param  message prompted to user
     */
    public void promptUserConfirmation(String message) {
        AlertWindow window = new AlertWindow();
        this.isConfirmed = window.display(message);
    }
}
