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
import seedu.address.model.profile.Profile;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_SIMILAR_NAME = "Profiles list contains similar name(s).";
    public static final String MESSAGE_SIMILAR_EMAIL = "Profiles list contains similar email(s).";

    private final List<JsonAdaptedProfile> profiles = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given profiles.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("profiles") List<JsonAdaptedProfile> profiles) {
        this.profiles.addAll(profiles);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        profiles.addAll(source.getProfileList().stream().map(JsonAdaptedProfile::new).collect(Collectors.toList()));
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
            if (addressBook.hasName(profile)) {
                throw new IllegalValueException(MESSAGE_SIMILAR_NAME);
            }
            if (addressBook.hasEmail(profile)) {
                throw new IllegalValueException(MESSAGE_SIMILAR_EMAIL);
            }
            addressBook.addProfile(profile);
        }
        return addressBook;
    }

}
