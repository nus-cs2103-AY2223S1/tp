package seedu.travelr.model.trip;

import org.junit.jupiter.api.Test;
import seedu.travelr.testutil.TripBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_EVENT_SIGHTSEEING;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_TITLE_ANTARCTICA;
import static seedu.travelr.testutil.TypicalTrips.ANTARCTICA;
import static seedu.travelr.testutil.TypicalTrips.GERMANY;

public class TripTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Trip trip = new TripBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> trip.getEvents().remove(0));
    }

    @Test
    public void isSameTrip() {
        // same object -> returns true
        assertTrue(GERMANY.isSameTrip(GERMANY));

        // null -> returns false
        assertFalse(GERMANY.isSameTrip(null));

        // same title, all other attributes different -> returns true
        Trip editedGermany = new TripBuilder(GERMANY)
                .withDescription(VALID_DESCRIPTION_ANTARCTICA).withEvents(VALID_EVENT_SIGHTSEEING).build();
        assertTrue(GERMANY.isSameTrip(editedGermany));

        // different title, all other attributes same -> returns false
        editedGermany = new TripBuilder(GERMANY).withTitle(VALID_TITLE_ANTARCTICA).build();
        assertFalse(GERMANY.isSameTrip(editedGermany));

        // title differs in case, all other attributes same -> returns false
        Trip editedBob = new TripBuilder(ANTARCTICA).withTitle(VALID_TITLE_ANTARCTICA.toLowerCase()).build();
        assertFalse(ANTARCTICA.isSameTrip(editedBob));

        // title has trailing spaces, all other attributes same -> returns false
        String titleWithTrailingSpaces = VALID_TITLE_ANTARCTICA + " ";
        editedBob = new TripBuilder(ANTARCTICA).withTitle(titleWithTrailingSpaces).build();
        assertFalse(ANTARCTICA.isSameTrip(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Trip aliceCopy = new TripBuilder(GERMANY).build();
        assertTrue(GERMANY.equals(aliceCopy));

        // same object -> returns true
        assertTrue(GERMANY.equals(GERMANY));

        // null -> returns false
        assertFalse(GERMANY.equals(null));

        // different type -> returns false
        assertFalse(GERMANY.equals(5));

        // different trip -> returns false
        assertFalse(GERMANY.equals(ANTARCTICA));

        // different title -> returns false
        Trip editedGermany = new TripBuilder(GERMANY).withTitle(VALID_TITLE_ANTARCTICA).build();
        assertFalse(GERMANY.equals(editedGermany));

        // different description -> returns false
        editedGermany = new TripBuilder(GERMANY).withDescription(VALID_DESCRIPTION_ANTARCTICA).build();
        assertFalse(GERMANY.equals(editedGermany));

        // different events -> returns false
        editedGermany = new TripBuilder(GERMANY).withEvents(VALID_EVENT_SIGHTSEEING).build();
        assertFalse(GERMANY.equals(editedGermany));
    }
}
