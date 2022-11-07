package seedu.rc4hdb.storage.venue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.rc4hdb.storage.venuebook.JsonAdaptedRecurrentBooking.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalBookings.HP_5_TO_6PM_STRING;
import static seedu.rc4hdb.testutil.TypicalBookings.MONDAY_STRING;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_5_TO_6PM;
import static seedu.rc4hdb.testutil.TypicalResidents.BENSON;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM_STRING;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.rc4hdb.commons.exceptions.IllegalValueException;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;
import seedu.rc4hdb.storage.residentbook.JsonAdaptedResident;
import seedu.rc4hdb.storage.residentbook.JsonAdaptedTag;
import seedu.rc4hdb.storage.venuebook.JsonAdaptedRecurrentBooking;

public class JsonAdaptedRecurrentBookingTest {
    private static final String INVALID_VENUE_NAME = "";
    private static final String INVALID_HOUR_PERIOD = "0-24";
    private static final String INVALID_DAY_OF_WEEK = "MONDAY";

    private static final String VALID_VENUE_NAME = MEETING_ROOM_STRING;
    private static final String VALID_HOUR_PERIOD = HP_5_TO_6PM_STRING;
    private static final String VALID_DAY_OF_WEEK = MONDAY_STRING;

    private static final JsonAdaptedResident VALID_RESIDENT = new JsonAdaptedResident(BENSON);
    private static final JsonAdaptedResidentStub INVALID_RESIDENT = new JsonAdaptedResidentStub("B@NSON Meier",
            "98765432", "benson@example.com", "12-01", "M", "U", "A0728394U",
            null);

    @Test
    public void toModelType_validRecurrentBookingDetails_returnsRecurrentBooking() throws IllegalValueException {
        JsonAdaptedRecurrentBooking booking = new JsonAdaptedRecurrentBooking(MR_ALICE_MONDAY_5_TO_6PM);
        assertEquals(MR_ALICE_MONDAY_5_TO_6PM, booking.toModelType());
    }

    @Test
    public void toModelType_invalidVenueName_throwsIllegalValueException() {
        JsonAdaptedRecurrentBooking booking =
                new JsonAdaptedRecurrentBooking(INVALID_VENUE_NAME, VALID_RESIDENT,
                        VALID_HOUR_PERIOD, VALID_DAY_OF_WEEK);
        String expectedMessage = VenueName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_nullVenueName_throwsIllegalValueException() {
        JsonAdaptedRecurrentBooking booking =
                new JsonAdaptedRecurrentBooking(null, VALID_RESIDENT,
                        VALID_HOUR_PERIOD, VALID_DAY_OF_WEEK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, VenueName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_invalidResident_throwsIllegalValueException() {
        JsonAdaptedRecurrentBooking booking =
                new JsonAdaptedRecurrentBooking(VALID_VENUE_NAME, INVALID_RESIDENT,
                        VALID_HOUR_PERIOD, VALID_DAY_OF_WEEK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_invalidHourPeriod_throwsIllegalValueException() {
        JsonAdaptedRecurrentBooking booking =
                new JsonAdaptedRecurrentBooking(VALID_VENUE_NAME, VALID_RESIDENT,
                        INVALID_HOUR_PERIOD, VALID_DAY_OF_WEEK);
        String expectedMessage = HourPeriod.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_nullHourPeriod_throwsIllegalValueException() {
        JsonAdaptedRecurrentBooking booking =
                new JsonAdaptedRecurrentBooking(VALID_VENUE_NAME, VALID_RESIDENT, null, VALID_DAY_OF_WEEK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, HourPeriod.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    @Test
    public void toModelType_invalidDayOfWeek_throwsIllegalValueException() {
        JsonAdaptedRecurrentBooking booking =
                new JsonAdaptedRecurrentBooking(VALID_VENUE_NAME, VALID_RESIDENT,
                        VALID_HOUR_PERIOD, INVALID_DAY_OF_WEEK);
        String expectedMessage = Day.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }


    @Test
    public void toModelType_nullDayOfWeek_throwsIllegalValueException() {
        JsonAdaptedRecurrentBooking booking =
                new JsonAdaptedRecurrentBooking(VALID_VENUE_NAME, VALID_RESIDENT, VALID_HOUR_PERIOD, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, booking::toModelType);
    }

    private static class JsonAdaptedResidentStub extends JsonAdaptedResident {

        public static final IllegalValueException DUMMY_ILLEGAL_VALUE_EXCEPTION =
                new IllegalValueException("Names should only contain alphanumeric characters and spaces, "
                        +
                        "and it should not be blank");

        public JsonAdaptedResidentStub(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                       @JsonProperty("email") String email, @JsonProperty("room") String room,
                                       @JsonProperty("gender") String gender, @JsonProperty("house") String house,
                                       @JsonProperty("matricNumber") String matricNumber,
                                       @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
            super(name, phone, email, room, gender, house, matricNumber, tagged);
        }

        @Override
        public Resident toModelType() throws IllegalValueException {
            throw DUMMY_ILLEGAL_VALUE_EXCEPTION;
        }
    }


}
