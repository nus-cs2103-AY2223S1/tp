package seedu.travelr.model.trip;

import javafx.beans.property.SimpleStringProperty;

public class ObservableTrip {
    private final SimpleStringProperty tripTitle;
    private final SimpleStringProperty tripDescription;

    public ObservableTrip() {
        tripTitle = new SimpleStringProperty();
        tripDescription = new SimpleStringProperty();
    }

    public void setTrip(Trip trip) {
        tripTitle.set(trip.getTitle().fullTitle);
        tripDescription.set(trip.getDescription().value);
    }

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
}
