package seedu.travelr.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.travelr.commons.exceptions.IllegalValueException;
import seedu.travelr.model.ReadOnlyTravelr;
import seedu.travelr.model.Travelr;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableTravelr {

    public static final String MESSAGE_DUPLICATE_TRIP = "Trips list contains duplicate trip(s).";
    public static final String MESSAGE_DUPLICATE_EVENT = "Events list contains duplicate event(s).";

    private final List<JsonAdaptedTrip> trips = new ArrayList<>();
    private final List<JsonAdaptedEvent> bucketList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTravelr} with the given trips.
     * TODO: Rename JsonProperty in local data file from persons to trips
     */
    @JsonCreator
    public JsonSerializableTravelr(@JsonProperty("bucketList") List<JsonAdaptedEvent> bucketList,
                                   @JsonProperty("trips") List<JsonAdaptedTrip> trips) {
        this.trips.addAll(trips);
        this.bucketList.addAll(bucketList);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTravelr}.
     */
    public JsonSerializableTravelr(ReadOnlyTravelr source) {
        trips.addAll(source.getTripList().stream().map(JsonAdaptedTrip::new).collect(Collectors.toList()));
        bucketList.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Travelr toModelType() throws IllegalValueException {
        Travelr travelr = new Travelr();
        for (JsonAdaptedTrip jsonAdaptedTrip : trips) {
            Trip trip = jsonAdaptedTrip.toModelType();
            if (travelr.hasTrip(trip)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRIP);
            }
            travelr.addTrip(trip);
            for (Event event : trip.getEvents()) {
                if (travelr.hasEvent(event)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
                }
                travelr.addEventToAllEventsList(event);
            }
        }
        for (JsonAdaptedEvent jsonAdaptedEvent : bucketList) {
            Event event = jsonAdaptedEvent.toModelType();
            if (travelr.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            travelr.addEventToBucketListAndAllEventsList(event);
        }
        return travelr;
    }

}
