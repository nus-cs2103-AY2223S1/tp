package seedu.travelr.storage;

import org.junit.jupiter.api.Test;
import seedu.travelr.commons.exceptions.IllegalValueException;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Title;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.travelr.storage.JsonAdaptedTrip.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.testutil.TypicalTrips.MALAYSIA;

public class JsonAdaptedTripTest {
    private static final String INVALID_TITLE = "R@chel";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_EVENT = "#friend";

    private static final String VALID_TITLE = MALAYSIA.getTitle().toString();
    private static final String VALID_DESCRIPTION = MALAYSIA.getDescription().toString();
    private static final List<JsonAdaptedEvent> VALID_EVENTS = MALAYSIA.getEvents().stream()
            .map(JsonAdaptedEvent::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTripDetails_returnsTrip() throws Exception {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(MALAYSIA);
        assertEquals(MALAYSIA, trip.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(INVALID_TITLE, VALID_DESCRIPTION, VALID_EVENTS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(null, VALID_DESCRIPTION, VALID_EVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTrip trip = new JsonAdaptedTrip(VALID_TITLE, null,  VALID_EVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }
}
