package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Student;
import seedu.address.model.person.TeachingAssistant;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
@JsonDeserialize(using = JsonAdaptedPersonDeserializer.class)
abstract class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String type;
    private final String name;
    private final String phone;
    private final String email;
    private final String gender;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String location;
    private final String username;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("type") String type, @JsonProperty("name") String name,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("gender") String gender,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("location") String location, @JsonProperty("username") String username) {
        this.type = type;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.location = location;
        this.username = username;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        if (source instanceof Student) {
            type = "s";
        } else if (source instanceof Professor) {
            type = "p";
        } else if (source instanceof TeachingAssistant) {
            type = "t";
        } else {
            type = "invalid";
        }
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        gender = source.getGender().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        location = source.getLocation().value;
        username = source.getUsername().value;
    }

    public String getType() {
        return this.type;
    }
    public String getName() {
        return this.name;
    }
    public String getPhone() {
        return this.phone;
    }
    public String getEmail() {
        return this.email;
    }
    public String getGender() {
        return this.gender;
    }
    public List<JsonAdaptedTag> getTagged() {
        return this.tagged;
    }
    public String getLocation() {
        return location;
    }
    public String getUsername() {
        return username;
    }


    public abstract Person toModelType() throws IllegalValueException;

    public Name getModelName() throws IllegalValueException {
        if (getName() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(getName())) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(getName());
    }

    public Phone getModelPhone() throws IllegalValueException {
        if (getPhone() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(getPhone())) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(getPhone());
    }

    public Email getModelEmail() throws IllegalValueException {
        if (getEmail() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(getEmail())) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(getEmail());
    }

    public Gender getModelGender() throws IllegalValueException {
        if (getGender() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(getGender())) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(getGender());
    }

    public Location getModelLocation() throws IllegalValueException {
        if (getLocation() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }

        if (!Location.isValidLocation(getLocation())) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }

        return new Location(getLocation());
    }

    public GithubUsername getModelUsername() throws IllegalValueException {
        if (getUsername() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GithubUsername.class.getSimpleName()));
        }

        final GithubUsername modelUsername;

        if (getUsername().equals(GithubUsername.DEFAULT_USERNAME)) {
            modelUsername = new GithubUsername(getUsername(), false);
        } else {
            if (!GithubUsername.isValidUsername(getUsername())) {
                throw new IllegalValueException(GithubUsername.MESSAGE_CONSTRAINTS);
            }
            modelUsername = new GithubUsername(getUsername(), true);
        }
        return modelUsername;
    }

    public List<Tag> getPersonTags() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : getTagged()) {
            personTags.add(tag.toModelType());
        }
        return personTags;
    }


}
