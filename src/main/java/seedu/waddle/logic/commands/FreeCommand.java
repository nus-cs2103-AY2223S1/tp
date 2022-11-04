package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.waddle.logic.StageManager;
import seedu.waddle.model.Model;

/**
 * Lists all free timeslots in the itinerary to the user.
 */
public class FreeCommand extends Command {

    public static final String COMMAND_WORD = "free";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(StageManager.getInstance().getSelectedItinerary().getVacantSlots());
    }
}
