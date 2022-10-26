package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.model.Model.PREDICATE_SHOW_ALL_ITINERARIES;

import javafx.stage.StageStyle;
import seedu.waddle.logic.StageManager;
import seedu.waddle.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class FreeCommand extends Command {

    public static final String COMMAND_WORD = "free";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(StageManager.getInstance().getSelectedItinerary().getVacantSlots());
    }
}