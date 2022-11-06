package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.model.Model.PREDICATE_SHOW_ALL_ITINERARIES;

import seedu.waddle.model.Model;

/**
 * Lists all itineraries in Waddle to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all itineraries";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItineraryList(PREDICATE_SHOW_ALL_ITINERARIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
