package seedu.rc4hdb.model.venues.booking.fields;

import java.util.List;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;

/**
 * Represents the fields for a booking.
 */
public interface BookingField {

    List<String> FIELDS = List.of(
            Venue.IDENTIFIER, Resident.IDENTIFIER,
            HourPeriod.IDENTIFIER, Day.IDENTIFIER
    );

}
