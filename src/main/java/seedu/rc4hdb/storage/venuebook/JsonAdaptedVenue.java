package seedu.rc4hdb.storage.venuebook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.rc4hdb.commons.exceptions.IllegalValueException;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.RecurrentBooking;

/**
 * Jackson-friendly version of {@link Venue}.
 */
public class JsonAdaptedVenue {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Venue's %s field is missing!";

    private List<JsonAdaptedRecurrentBooking> bookings = new ArrayList<>();
    private String venueName;

    /**
     * Constructs a {@code JsonAdaptedBooking} with the given {@code bookings}, {@code venueName}.
     */
    @JsonCreator
    public JsonAdaptedVenue(@JsonProperty("bookings") List<JsonAdaptedRecurrentBooking> bookings,
                            @JsonProperty("venueName") String venueName) {
        if (bookings != null) {
            this.bookings = bookings;
        }
        this.venueName = venueName;
    }

    /**
     * Converts a given {@code Venue} into this class for Jackson use.
     */
    public JsonAdaptedVenue(Venue source) {
        // to be updated with logic that builds other types of bookings when other types of bookings are added.
        bookings.addAll(source.getBookings()
                .stream().map(booking -> new JsonAdaptedRecurrentBooking((RecurrentBooking) booking))
                .collect(Collectors.toList()));
        venueName = source.getVenueName().value;
    }

    /**
     * Converts this Jackson-friendly adapted venue object into the model's {@code Venue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted venue.
     */
    public Venue toModelType() throws IllegalValueException {
        final List<Booking> modelBookings = new ArrayList<>();
        for (JsonAdaptedBooking booking : bookings) {
            modelBookings.add(booking.toModelType());
        }

        throwIfNullField(venueName, VenueName.class);
        if (!VenueName.isValidVenueName(venueName)) {
            throw new IllegalValueException(VenueName.MESSAGE_CONSTRAINTS);
        }
        final VenueName modelVenueName = new VenueName(venueName);

        return new Venue(modelBookings, modelVenueName);
    }

    /**
     * Throws an {@code IllegalValueException} if field is null.
     * @param field The field to check if null.
     * @param fieldClass The {@code Class} that field is supposed to be converted into.
     * @throws IllegalValueException if field is null.
     */
    private void throwIfNullField(String field, Class<?> fieldClass) throws IllegalValueException {
        if (field == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, fieldClass.getSimpleName()));
        }
    }

}
