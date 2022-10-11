package jeryl.fyp.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jeryl.fyp.commons.exceptions.IllegalValueException;
import jeryl.fyp.model.person.Address;
import jeryl.fyp.model.person.Email;
import jeryl.fyp.model.person.Name;
import jeryl.fyp.model.person.Person;
import jeryl.fyp.model.person.StudentID;
import jeryl.fyp.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String id;
    private final String email;
    private final String address;
    private final String projectName;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("studentID") String id,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("projectName") String projectName, @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.address = address;
        this.projectName = projectName;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        id = source.getStudentID().id;
        email = source.getEmail().value;
        address = source.getAddress().value;
        projectName = source.getProjectName();
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

        if (id == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, StudentID.class.getSimpleName()));
        }
        if (!StudentID.isValidStudentID(id)) {
            throw new IllegalValueException(StudentID.MESSAGE_CONSTRAINTS);
        }
        final StudentID modelStudentID = new StudentID(id);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (projectName == null) {
            // tentatively I put "Project" here -- Yuhao
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Project"));
        }
        /*
        if (!Project.isValidProject(projectName)) {
            throw new IllegalValueException("Projects can take any values, and it should not be blank");
        }
         */
        final String modelProjectName = projectName;

        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Person(modelName, modelStudentID, modelEmail, modelAddress, modelProjectName, modelTags);
    }

}
