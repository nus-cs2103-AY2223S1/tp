package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final Integer personId;
    private final String name;
    private final String phone;
    private final String email;
    private final Integer internshipId;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(
            @JsonProperty("personId") Integer personId,
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("internshipId") Integer internshipId,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.personId = personId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.internshipId = internshipId;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        personId = source.getPersonId().id;
        name = source.getName().fullName;
        phone = source.getPhone() != null ? source.getPhone().value : null;
        email = source.getEmail() != null ? source.getEmail().value : null;
        internshipId = source.getInternshipId() != null ? source.getInternshipId().id : null;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        //personId
        if (personId == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, PersonId.class.getSimpleName()));
        }
        final PersonId modelPersonId = new PersonId(personId);
        //name
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        //phone
        final Phone modelPhone;
        if (phone == null) {
            modelPhone = null;
        } else if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        } else {
            modelPhone = new Phone(phone);
        }
        //email
        final Email modelEmail;
        if (email == null) {
            modelEmail = null;
        } else if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        } else {
            modelEmail = new Email(email);
        }
        //internshipId
        final InternshipId modelInternshipId;
        if (internshipId == null || !InternshipId.isValidId(internshipId)) {
            modelInternshipId = null;
        } else {
            modelInternshipId = new InternshipId(internshipId);
        }
        //tags
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Person(modelPersonId, modelName, modelEmail, modelPhone, modelInternshipId,
                modelTags);
    }
}

