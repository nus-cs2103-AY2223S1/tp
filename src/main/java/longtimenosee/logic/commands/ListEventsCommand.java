package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;
import static longtimenosee.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import longtimenosee.model.Model;

/**
 * Lists all Events in the LTNS to the user.
 */
public class ListEventsCommand extends Command {

    public static final String COMMAND_WORD = "allEvents";

    public static final String MESSAGE_SUCCESS = "Listed all events in LTNS";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}

