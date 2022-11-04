package seedu.waddle.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.model.ReadOnlyWaddle;
import seedu.waddle.model.Waddle;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * An Immutable Waddle that is serializable to JSON format.
 */
@JsonRootName(value = "waddle")
class JsonSerializableWaddle {

    public static final String MESSAGE_DUPLICATE_ITINERARY = "Itinerary list contains duplicate itinerary(ies).";

    private final List<JsonAdaptedItinerary> itineraries = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given itineraries.
     */
    @JsonCreator
    public JsonSerializableWaddle(@JsonProperty("itineraries") List<JsonAdaptedItinerary> itineraries) {
        this.itineraries.addAll(itineraries);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableWaddle(ReadOnlyWaddle source) {
        itineraries.addAll(source.getItineraryList().stream().map(JsonAdaptedItinerary::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Waddle toModelType() throws IllegalValueException {
        Waddle waddle = new Waddle();
        for (JsonAdaptedItinerary jsonAdaptedItinerary : itineraries) {
            Itinerary itinerary = jsonAdaptedItinerary.toModelType();
            if (waddle.hasItinerary(itinerary)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ITINERARY);
            }
            waddle.addItinerary(itinerary);
        }
        return waddle;
    }

}
