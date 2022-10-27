package seedu.travelr.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.event.EventCompletedPredicate;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.model.trip.TripCompletedPredicate;

public class SummaryVariables {

    private final SimpleStringProperty tripsProgress;
    private final SimpleStringProperty eventsProgress;
    private final SimpleStringProperty totalTripsCompleted;
    private final SimpleStringProperty totalEventsCompleted;

    public SummaryVariables() {
        // initialise the observable boolean properties
        tripsProgress = new SimpleStringProperty();
        eventsProgress = new SimpleStringProperty();
        totalTripsCompleted = new SimpleStringProperty();
        totalEventsCompleted = new SimpleStringProperty();
    }

    public void refresh(ObservableList<Trip> allTripList, ObservableList<Event> allEventList) {
        FilteredList<Trip> completedTripsList = allTripList.filtered(new TripCompletedPredicate());
        FilteredList<Event> completedEventsList =
                allEventList.filtered(new EventCompletedPredicate(completedTripsList));

        int totalTrips = allTripList.size();
        int totalEvents = allEventList.size();
        int totalTripsCompleted = completedTripsList.size();
        int totalEventsCompleted = completedEventsList.size();

        this.totalTripsCompleted.set(String.format("%d Trips completed!", totalTripsCompleted));
        this.totalEventsCompleted.set(String.format("%d Events completed!", totalEventsCompleted));
        this.tripsProgress.set(String.format("%.1f%s of trips completed",
                (double) totalTripsCompleted / totalTrips * 100,
                "%"));
        this.eventsProgress.set(String.format("%.1f%s of events completed",
                (double) totalEventsCompleted / totalEvents * 100,
                "%"));
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

}
