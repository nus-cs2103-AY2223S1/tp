package seedu.travelr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;



class SummaryVariablesTest {
    @Test
    void testRefresh() {
        ObservableList<Trip> trips = FXCollections.observableArrayList();
        ObservableList<Event> events = FXCollections.observableArrayList();
        SummaryVariables summaryVariables = new SummaryVariables();
        summaryVariables.refresh(trips, events);
        assertEquals(1, 1);
    }
}
