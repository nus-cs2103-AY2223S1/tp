package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PROFILE = "Profiles list contains duplicate profile(s).";
    public static final String MESSAGE_DUPLICATE_EVENT = "Events list contains duplicate event(s).";

    private final List<JsonAdaptedProfile> profiles = new ArrayList<>();
    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given profiles and events.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("profiles") List<JsonAdaptedProfile> profiles,
                                       @JsonProperty("events") List<JsonAdaptedEvent> events) {
        this.profiles.addAll(profiles);
        this.events.addAll(events);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        profiles.addAll(source.getProfileList().stream().map(JsonAdaptedProfile::new).collect(Collectors.toList()));
        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedProfile jsonAdaptedProfile : profiles) {
            Profile profile = jsonAdaptedProfile.toModelType();
            if (addressBook.hasProfile(profile)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROFILE);
            }
            addressBook.addProfile(profile);
        }
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            if (addressBook.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            addressBook.addEvent(event);
        }
        return addressBook;
    }

}
