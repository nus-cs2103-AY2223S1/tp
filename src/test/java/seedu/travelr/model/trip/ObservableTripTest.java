package seedu.travelr.model.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.travelr.model.component.DateField;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Location;
import seedu.travelr.model.component.Title;


class ObservableTripTest {
    private static ObservableTrip observableTrip;
    private static Trip testTrip;
    private static Title testTitle;
    private static Description testDescription;
    private static Location testLocation;
    private static DateField testDateField;

    @BeforeAll
    static void setup() {
        testTitle = new Title("a");
        testDescription = new Description("b");
        testLocation = new Location("c");
        testDateField = new DateField("01-01-2000");
        testTrip = new Trip(testTitle, testDescription, testLocation, testDateField);
        observableTrip = new ObservableTrip();
        observableTrip.setTrip(testTrip);
    }

    @Test
    void setTripTest() {
        observableTrip.setTrip(testTrip);
        assertTrue(true);
    }

    @Test
    void resetTripTest() {
        observableTrip.resetTrip();
        observableTrip.setTrip(testTrip);
        assertTrue(true);
    }

    @Test
    void getObservableTitleTest() {
        String test = observableTrip.getObservableTitle().get();
        assertEquals(test, testTitle.fullTitle);
    }

    @Test
    void getObservableDescriptionTest() {
        String test = observableTrip.getObservableDescription().get();
        assertEquals(test, testDescription.value);
    }

    @Test
    void getObservableDateTest() {
        String test = observableTrip.getObservableDate().get();
        assertEquals(test, testDateField.toString());
    }

    @Test
    void getObservableLocationTest() {
        String test = observableTrip.getObservableLocation().get();
        assertEquals(test, testLocation.locationName);
    }

    @Test
    void isEqualTest() {
        assertTrue(observableTrip.isEqual(testTrip));
    }

    @Test
    void getObservableBooleanTest() {
        testTrip.markNotDone();
        assertFalse(observableTrip.getObservableBoolean().get());
    }
}