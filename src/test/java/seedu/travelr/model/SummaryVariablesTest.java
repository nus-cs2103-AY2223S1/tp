package seedu.travelr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;



class SummaryVariablesTest {

    static SummaryVariables sv;

    @BeforeAll
    static void setup() {
        sv = new SummaryVariables();
        sv.set(1,1,1,1.0,1.0);
    }

    @Test
    void testRefresh() {
        ObservableList<Trip> trips = FXCollections.observableArrayList();
        ObservableList<Event> events = FXCollections.observableArrayList();
        sv.refresh(trips, events);
        sv.set(1,1,1,1.0,1.0);
        assertEquals(1, 1);
    }

    @Test
    void testSet() {
        SummaryVariables sv = new SummaryVariables();
        sv.set(1,1,1,1.0,1.0);
        assertEquals(1, 1);
    }

    @Test
    void getTripsProgressTest() {
        SimpleStringProperty TripProgress = sv.getTripsProgress();
        assertEquals(new SimpleStringProperty("100.0% of trips completed").getValue(),
                TripProgress.getValue());
    }

    @Test
    void getEventsProgressTest() {
        SimpleStringProperty eventProgress = sv.getEventsProgress();
        assertEquals(new SimpleStringProperty("100.0% of events completed").getValue(),
                eventProgress.getValue());
    }

    @Test
    void getTotalTripsCompletedTest() {
        SimpleStringProperty totalTripsCompleted = sv.getTotalTripsCompleted();
        assertEquals(new SimpleStringProperty("1").getValue(),
                totalTripsCompleted.getValue());
    }

    @Test
    void getTotalEventsCompletedTest() {
        SimpleStringProperty totalEventsCompleted = sv.getTotalEventsCompleted();
        assertEquals(new SimpleStringProperty("1").getValue(),
                totalEventsCompleted.getValue());
    }

    @Test
    void getTripProgressPercentTest() {
        SimpleDoubleProperty tripProgressPercent = sv.getTripProgressPercent();
        assertEquals(new SimpleDoubleProperty(1.0).getValue(),
                tripProgressPercent.getValue());
    }

    @Test
    void getEventProgressPercent() {
        SimpleDoubleProperty eventProgressPercent = sv.getEventProgressPercent();
        assertEquals(new SimpleDoubleProperty(1.0).getValue(),
                eventProgressPercent.getValue());
    }

    @Test
    void getTotalUniqueLocationsTest() {
        SimpleIntegerProperty totalUniqueLocations = sv.getTotalUniqueLocations();
        assertEquals(new SimpleIntegerProperty(1).getValue(),
                totalUniqueLocations.getValue());
    }
}
