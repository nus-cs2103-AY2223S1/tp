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

    /**
     * Every field must be present and not null.
     */
    public ObservableTrip() {
        tripTitle = new SimpleStringProperty();
        tripDescription = new SimpleStringProperty();
        tripIsDone = new SimpleBooleanProperty();
    }

    public void setTrip(Trip trip) {
        tripTitle.set(trip.getTitle().fullTitle);
        tripDescription.set(trip.getDescription().value);
        tripIsDone.set(trip.isDone());
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

    public SimpleBooleanProperty getObservableBoolean() { return tripIsDone; }
}
