package seedu.travelr.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.travelr.commons.exceptions.IllegalValueException;
import seedu.travelr.model.AddressBook;
import seedu.travelr.model.ReadOnlyAddressBook;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_TRIP = "Trips list contains duplicate trip(s).";

    private final List<JsonAdaptedTrip> trips = new ArrayList<>();
    private final List<JsonAdaptedEvent> bucketList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given trips.
     * TODO: Rename JsonProperty in local data file from persons to trips
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("trips") List<JsonAdaptedTrip> trips,
                                       @JsonProperty("bucketList") List<JsonAdaptedEvent> bucketList) {
        this.trips.addAll(trips);
        this.bucketList.addAll(bucketList);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        trips.addAll(source.getTripList().stream().map(JsonAdaptedTrip::new).collect(Collectors.toList()));
        bucketList.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedTrip jsonAdaptedTrip : trips) {
            Trip trip = jsonAdaptedTrip.toModelType();
            if (addressBook.hasTrip(trip)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRIP);
            }
            addressBook.addTrip(trip);
        }
        for (JsonAdaptedEvent jsonAdaptedEvent : bucketList) {
            Event event = jsonAdaptedEvent.toModelType();
            if (addressBook.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRIP);
            }
            addressBook.addEvent(event);
        }
        return addressBook;
    }

}
