package seedu.rc4hdb.storage.venue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.rc4hdb.storage.venuebook.JsonAdaptedVenue.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_5_TO_6PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_6_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_TUESDAY_6_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.commons.exceptions.IllegalValueException;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.storage.venuebook.JsonAdaptedRecurrentBooking;
import seedu.rc4hdb.storage.venuebook.JsonAdaptedVenue;

public class JsonAdaptedVenueTest {

    private static final List<JsonAdaptedRecurrentBooking> VALID_BOOKINGS = List.of(MR_ALICE_MONDAY_5_TO_6PM,
            MR_ALICE_MONDAY_6_TO_7PM, MR_ALICE_TUESDAY_6_TO_7PM).stream().map(JsonAdaptedRecurrentBooking::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validVenue_returnsVenue() throws IllegalValueException {
        JsonAdaptedVenue venue = new JsonAdaptedVenue(MEETING_ROOM);
        assertEquals(MEETING_ROOM, venue.toModelType());
    }

    @Test
    public void toModelType_nullVenueName_throwsIllegalValueException() {
        JsonAdaptedVenue venue = new JsonAdaptedVenue(VALID_BOOKINGS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, VenueName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, venue::toModelType);
    }
}
