package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.model.Model.PREDICATE_SHOW_ALL_TRIPS;

import seedu.travelr.model.Model;

/**
 * Lists all trips in Travelr to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all trips";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTripList(PREDICATE_SHOW_ALL_TRIPS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
