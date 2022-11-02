package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.travelr.model.Model;
import seedu.travelr.model.Travelr;

/**
 * Clears Travelr.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Travelr has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTravelr(new Travelr());
        model.resetSelectedTrip();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
