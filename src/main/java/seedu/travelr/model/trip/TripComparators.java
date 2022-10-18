package seedu.travelr.model.trip;

import java.util.Comparator;

public class TripComparators {
    public static Comparator<Trip> compareByTitle = (x,y) -> x.compareTitle(y);
}
