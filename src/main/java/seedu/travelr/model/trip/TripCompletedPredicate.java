package seedu.travelr.model.trip;

import seedu.travelr.model.event.Event;

import java.util.List;
import java.util.function.Predicate;

public class TripCompletedPredicate implements Predicate<Trip> {


    /**
     * Creates a new EventInItineraryPredicate.
     */
    public TripCompletedPredicate() {
    }


    @Override
    public boolean test(Trip trip) {
        return trip.isDone();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TripCompletedPredicate); // state check
    }
}
