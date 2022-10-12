package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import seedu.travelr.model.Model;

/**
 * Represents the EventListCommand
 */
public class EventListCommand extends Command {

    public static final String COMMAND_WORD = "list-e";

    public static final String MESSAGE_SUCCESS = "Listed all events";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
