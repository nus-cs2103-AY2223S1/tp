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
import seedu.address.github.exceptions.NetworkConnectionException;
import seedu.address.github.exceptions.UserInvalidException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
import seedu.address.model.person.Timezone;
import seedu.address.model.person.contact.Contact;
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.person.github.User;
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
    private final JsonAdaptedGithubUser githubUser;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedContact> contacts = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("contacts") List<JsonAdaptedContact> contacts,
                             @JsonProperty("role") String role, @JsonProperty("timezone") String timezone,
                             @JsonProperty("github") JsonAdaptedGithubUser githubUser) {
        this.name = name;
        this.address = address;
        this.role = role;
        this.timezone = timezone;
        this.githubUser = githubUser;

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

        address = source.getAddress().map(r -> r.value).orElse(null);
        role = source.getRole().map(r -> r.role).orElse(null);
        timezone = source.getTimezone().map(t -> t.timezone).orElse(null);
        githubUser = source.getGithubUser().map(JsonAdaptedGithubUser::new).orElse(null);

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

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Map<ContactType, Contact> modelContacts = new HashMap<>();
        for (JsonAdaptedContact contact : contacts) {
            Contact contactModel = contact.toModelType();
            modelContacts.put(contactModel.getContactType(), contactModel);
        }

        if (address != null && !Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = address != null ? new Address(address) : null;

        if (role != null && !Role.isValidRole(role)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        final Role modelRole = role != null ? new Role(role) : null;

        if (timezone != null && !Timezone.isValidTimezone(timezone)) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Timezone.class.getSimpleName()));
        }
        final Timezone modelTimezone = timezone != null ? new Timezone(timezone) : null;

        User modelGithubUser = null;
        if (githubUser != null) {
            modelGithubUser = githubUser.toModelType();
            try {
                modelGithubUser = ParserUtil.parseGithubUser(modelGithubUser.getUsername());
            } catch (UserInvalidException | ParseException e) {
                throw new IllegalValueException(
                    String.format("GitHub username %s is invalid! Please provide a valid username.",
                        modelGithubUser.getUsername()));
            } catch (NetworkConnectionException ignored) {
                // Ignore if there is a network error, use information stored in cache
            }
        }

        return new Person(modelName, modelAddress, modelTags, modelContacts, modelRole, modelTimezone, modelGithubUser);
    }

}
