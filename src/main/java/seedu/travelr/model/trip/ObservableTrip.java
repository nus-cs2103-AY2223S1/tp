package seedu.travelr.model.trip;

import javafx.beans.property.SimpleStringProperty;

/**
 * Represents an observable Trip.
 */
public class ObservableTrip {
    private final SimpleStringProperty tripTitle;
    private final SimpleStringProperty tripDescription;

    /**
     * Every field must be present and not null.
     */
    public ObservableTrip() {
        tripTitle = new SimpleStringProperty();
        tripDescription = new SimpleStringProperty();
    }

    public void setTrip(Trip trip) {
        tripTitle.set(trip.getTitle().fullTitle);
        tripDescription.set(trip.getDescription().value);
    }

    /**
     * Resets the observable trip.
     */
    public void resetTrip() {
        tripTitle.set(null);
        tripDescription.set(null);
    }

    public SimpleStringProperty getObservableTitle() {
        return tripTitle;
    }

    public SimpleStringProperty getObservableDescription() {
        return tripDescription;
    }

    public boolean isEqual(Trip trip) {
        return trip.getTitle().fullTitle.equals(tripTitle.get());
    }
}
