package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
import seedu.address.model.person.Timezone;
import seedu.address.model.person.contact.Contact;
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String address;
    private final String role;
    private final String timezone;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedContact> contacts = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("contacts") List<JsonAdaptedContact> contacts,
                             @JsonProperty("role") String role, @JsonProperty("timezone") String timezone) {
        this.name = name;
        this.address = address;
        this.role = role;
        this.timezone = timezone;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }

        if (contacts != null) {
            this.contacts.addAll(contacts);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        address = source.getAddress().value;

        if (source.getRole() != null) {
            role = source.getRole().role;
        } else {
            role = null;
        }

        if (source.getTimezone() != null) {
            timezone = source.getTimezone().timezone;
        } else {
            timezone = null;
        }

        tagged.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
        contacts.addAll(source.getContacts().values().stream()
                        .map(JsonAdaptedContact::new)
                        .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Map<ContactType, Contact> modelContacts = new HashMap<>();
        for (JsonAdaptedContact contact: contacts) {
            Contact contactModel = contact.toModelType();
            modelContacts.put(contactModel.getContactType(), contactModel);
        }

        if (role != null && !Role.isValidRole(role)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }

        final Role modelRole = role != null ? new Role(role) : null;

        if (timezone != null && !Timezone.isValidTimezone(timezone)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Timezone.class.getSimpleName()));
        }

        final Timezone modelTimezone = timezone != null ? new Timezone(timezone) : null;

        return new Person(modelName, modelAddress, modelTags, modelContacts, modelRole, modelTimezone);
    }

}
