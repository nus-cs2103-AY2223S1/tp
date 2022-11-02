package seedu.travelr.model;

import java.util.HashSet;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.travelr.model.component.Location;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.event.EventCompletedPredicate;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.model.trip.TripCompletedPredicate;

/**
 * Represents the summary variables.
 */
public class SummaryVariables {

    private final SimpleStringProperty tripsProgress;
    private final SimpleStringProperty eventsProgress;
    private final SimpleStringProperty totalTripsCompleted;
    private final SimpleStringProperty totalEventsCompleted;
    private final SimpleDoubleProperty tripProgressPercent;
    private final SimpleDoubleProperty eventProgressPercent;
    private final SimpleIntegerProperty totalUniqueLocations;

    /**
     * Constructs a SummaryVariables.
     */
    public SummaryVariables() {
        // initialise the observable boolean properties
        tripsProgress = new SimpleStringProperty();
        eventsProgress = new SimpleStringProperty();
        totalTripsCompleted = new SimpleStringProperty();
        totalEventsCompleted = new SimpleStringProperty();
        tripProgressPercent = new SimpleDoubleProperty(0.0);
        eventProgressPercent = new SimpleDoubleProperty(0.0);
        totalUniqueLocations = new SimpleIntegerProperty(0);
    }

    /**
     * Refreshes the SummaryVariables.
     *
     * @param allTripList An observable list of all trips.
     * @param allEventList An observable list of all events.
     */
    public void refresh(ObservableList<Trip> allTripList, ObservableList<Event> allEventList) {
        FilteredList<Trip> completedTripsList = allTripList.filtered(new TripCompletedPredicate());
        FilteredList<Event> completedEventsList =
                allEventList.filtered(new EventCompletedPredicate(completedTripsList));

        int totalTripsCompleted = completedTripsList.size();
        int totalEventsCompleted = completedEventsList.size();
        int totalTrips = allTripList.size();
        int totalEvents = allEventList.size();
        double tripProgressPercent = totalTrips == 0 ? 0 :(double) totalTripsCompleted / totalTrips;
        double eventProgressPercent = totalEvents == 0 ? 0 : (double) totalEventsCompleted / totalEvents;

        HashSet<String> uniqueLocations = new HashSet<>();
        for (Trip trip : completedTripsList) {
            uniqueLocations.add(trip.getLocation().locationName.toUpperCase());
        }

        this.totalUniqueLocations.set(uniqueLocations.size());
        this.totalTripsCompleted.set(String.format("%d", totalTripsCompleted));
        this.totalEventsCompleted.set(String.format("%d", totalEventsCompleted));
        this.tripsProgress.set(String.format("%.1f%s of trips completed",
                tripProgressPercent * 100,
                "%"));
        this.eventsProgress.set(String.format("%.1f%s of events completed",
                eventProgressPercent * 100,
                "%"));
        this.tripProgressPercent.set(tripProgressPercent);
        this.eventProgressPercent.set(eventProgressPercent);
    }

    public SimpleStringProperty getTripsProgress() {
        return tripsProgress;
    }

    public SimpleStringProperty getEventsProgress() {
        return eventsProgress;
    }

    public SimpleStringProperty getTotalTripsCompleted() {
        return totalTripsCompleted;
    }

    public SimpleStringProperty getTotalEventsCompleted() {
        return totalEventsCompleted;
    }

    public SimpleDoubleProperty getTripProgressPercent() {
        return tripProgressPercent;
    }

    public SimpleDoubleProperty getEventProgressPercent() {
        return eventProgressPercent;
    }

    public SimpleIntegerProperty getTotalUniqueLocations() {
        return totalUniqueLocations;
    }

}
