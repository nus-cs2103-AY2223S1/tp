package seedu.travelr.logic.commands;

import seedu.travelr.model.Model;
import seedu.travelr.model.trip.Trip;

import java.util.Comparator;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts trips within Travelr.";

    public static final String SORT_SUCCESS = "Trips have been sorted.";

    public static final Comparator<Trip> comp = (x, y) -> x.compareTitle(y);

    @Override
    public CommandResult execute(Model model) {
        model.sortTripsByComparator(comp);
        return new CommandResult(SORT_SUCCESS);
    }
}
