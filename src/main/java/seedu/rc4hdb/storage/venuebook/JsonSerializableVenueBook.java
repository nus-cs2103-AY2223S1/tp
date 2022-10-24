package seedu.rc4hdb.storage.venuebook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.rc4hdb.commons.exceptions.IllegalValueException;
import seedu.rc4hdb.model.ReadOnlyVenueBook;
import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.model.venues.Venue;

/**
 * An Immutable ResidentBook that is serializable to JSON format.
 */
public class JsonSerializableVenueBook {

    public static final String MESSAGE_DUPLICATE_VENUE = "Venues list contains duplicate venue(s).";

    private final List<JsonAdaptedVenue> venues = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableVenueBook} with the given venues.
     */
    @JsonCreator
    public JsonSerializableVenueBook(@JsonProperty("venues") List<JsonAdaptedVenue> venues) {
        this.venues.addAll(venues);
    }

    /**
     * Converts a given {@code ReadOnlyVenueBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableVenueBook}.
     */
    public JsonSerializableVenueBook(ReadOnlyVenueBook source) {
        venues.addAll(source.getVenueList()
                .stream().map(JsonAdaptedVenue::new).collect(Collectors.toList()));
    }

    /**
     * Converts this venue book into the model's {@code VenueBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public VenueBook toModelType() throws IllegalValueException {
        VenueBook venueBook = new VenueBook();
        for (JsonAdaptedVenue jsonAdaptedVenue : venues) {
            Venue venue = jsonAdaptedVenue.toModelType();
            if (venueBook.hasVenue(venue)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_VENUE);
            }
            venueBook.addVenue(venue);
        }
        return venueBook;
    }

}
