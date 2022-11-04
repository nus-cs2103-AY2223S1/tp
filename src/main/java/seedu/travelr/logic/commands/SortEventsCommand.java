package seedu.travelr.logic.commands;

import static seedu.travelr.logic.parser.CliSyntax.PREFIX_REVERSE_ORDER;

import java.util.Comparator;

import seedu.travelr.model.Model;
import seedu.travelr.model.event.Event;


/**
 * Sorts the Events within Bucket List.
 */
public class SortEventsCommand extends Command {
    public static final String COMMAND_WORD = "sort-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all events within Travelr.\n"
            + "Parameter (Optional): "
            + PREFIX_REVERSE_ORDER + "\n"
            + "This command will sort events by titles alphabetically.\n"
            + PREFIX_REVERSE_ORDER + " will make the events be sorted in reverse order.\n"
            + "Example: " + COMMAND_WORD + PREFIX_REVERSE_ORDER;

    public static final String SORT_SUCCESS = "All events have been sorted.";

    private final Comparator<Event> comparator;

    public SortEventsCommand(Comparator<Event> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        model.sortEvents(comparator);
        return new CommandResult(SORT_SUCCESS);
    }
}
