package seedu.travelr.model.trip;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Represents an observable Trip.
 */
public class ObservableTrip {
    private final SimpleStringProperty tripTitle;
    private final SimpleStringProperty tripDescription;
    private final SimpleBooleanProperty tripIsDone;
    private final SimpleStringProperty tripLocation;
    private final SimpleStringProperty tripDate;

    /**
     * Every field must be present and not null.
     */
    public ObservableTrip() {
        tripTitle = new SimpleStringProperty();
        tripDescription = new SimpleStringProperty();
        tripIsDone = new SimpleBooleanProperty();
        tripLocation = new SimpleStringProperty();
        tripDate = new SimpleStringProperty();
    }

    public void setTrip(Trip trip) {
        tripIsDone.set(trip.isDone());
        tripLocation.set(trip.getLocation().locationName);
        tripDate.set(trip.getDateField().toString());
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

    public SimpleStringProperty getObservableDate() {
        return tripDate;
    }
    public SimpleStringProperty getObservableLocation() {
        return tripLocation;
    }

    public boolean isEqual(Trip trip) {
        return trip.getTitle().fullTitle.equals(tripTitle.get());
    }

    public SimpleBooleanProperty getObservableBoolean() {
        return tripIsDone;
    }
}
