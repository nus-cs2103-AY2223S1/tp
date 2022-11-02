package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.event.EventCompletedPredicate;
import seedu.travelr.model.list.Itineraries;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.model.trip.TripCompletedPredicate;

/**
 * Displays all completed trips and events in Travelr to the user.
 */
public class ViewCompletedCommand extends Command {

    public static final String COMMAND_WORD = "completed";

    public static final String MESSAGE_SUCCESS = "Listed completed trips and events";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays completed trips and events.\n";

    public ViewCompletedCommand() {
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTripList(new TripCompletedPredicate());
        ObservableList<Trip> trips = model.getFilteredTripList();
        FilteredList<Trip> completedTrips = trips.filtered(new TripCompletedPredicate());
        //Does not change.
        model.updateFilteredEventList(new EventCompletedPredicate(completedTrips));
        model.resetSelectedTrip();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private FilteredList<Event> retrieveItinerariesAsList(Itineraries itinerary) {
        return itinerary.asUnmodifiableObservableList().filtered(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCompletedCommand); // instanceof handles nulls
    }
}
