package seedu.rc4hdb.storage.venuebook;

import seedu.rc4hdb.commons.exceptions.IllegalValueException;
import seedu.rc4hdb.model.venues.booking.Booking;

/**
 * Interface for building Jackson-friendly version of {@link Booking}.
 */
public interface JsonAdaptedBooking {

    /**
     * Converts this Jackson-friendly adapted booking object into the model's {@code Booking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted booking.
     */
    Booking toModelType() throws IllegalValueException;

}
