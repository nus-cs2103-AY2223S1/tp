package seedu.travelr.logic.commands;

import seedu.travelr.model.Model;
import seedu.travelr.model.event.Event;

import java.util.Comparator;

import static seedu.travelr.logic.parser.CliSyntax.PREFIX_REVERSE_ORDER;

public class SortEventsCommand extends Command {
    public static final String COMMAND_WORD = "sort-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts events within BucketList.\n"
            + "Parameter (Optional): "
            + PREFIX_REVERSE_ORDER + "\n"
            + "This command will sort events by titles alphabetically.\n"
            + PREFIX_REVERSE_ORDER + " will make the events be sorted in reverse order.\n"
            + "Example: " + COMMAND_WORD + PREFIX_REVERSE_ORDER;

    public static final String SORT_SUCCESS = "Bucket list has been sorted.";

    private final Comparator<Event> comparator;

    public SortEventsCommand(Comparator<Event> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        model.sortBucketList(comparator);
        return new CommandResult(SORT_SUCCESS);
    }
}
