package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Location;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.Year;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent extends JsonAdaptedPerson {
    private final List<JsonAdaptedModuleCode> moduleCodes = new ArrayList<>();

    private final String year;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student's details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("type") String type, @JsonProperty("name") String name,
                              @JsonProperty("moduleCodes") List<JsonAdaptedModuleCode> moduleCodes,
                              @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                              @JsonProperty("gender") String gender,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("location") String location,
                              @JsonProperty("username") String username, @JsonProperty("year") String year) {
        super(type, name, phone, email, gender, tagged, location, username);
        this.year = year;
        if (moduleCodes != null) {
            this.moduleCodes.addAll(moduleCodes);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        super(source);
        this.year = source.getYear().value;
        this.moduleCodes.addAll(source.getModuleCodes().stream()
                .map(JsonAdaptedModuleCode::new)
                .collect(Collectors.toList()));
    }

    public String getYear() {
        return this.year;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Person toModelType() throws IllegalValueException {
        final Name modelName = getModelName();
        final Phone modelPhone = getModelPhone();
        final Email modelEmail = getModelEmail();
        final Gender modelGender = getModelGender();
        final Set<Tag> modelTags = new HashSet<>(getPersonTags());
        final Location modelLocation = getModelLocation();
        final GithubUsername modelUsername = getModelUsername();
        final Set<ModuleCode> modelModuleCodes = getModelModuleCodes();
        final Year modelYear = getModelYear();

        return new Student(modelName, modelPhone, modelEmail, modelGender, modelTags, modelLocation, modelUsername,
                modelModuleCodes, modelYear);
    }

    private Year getModelYear() throws IllegalValueException {
        if (getYear() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Year.class.getSimpleName()));
        }

        final Year modelYear;

        if (getYear().equals(Year.EMPTY_YEAR)) {
            modelYear = new Year(Year.EMPTY_YEAR, false);
        } else {
            if (!Year.isValidYear(getYear())) {
                throw new IllegalValueException(Year.MESSAGE_CONSTRAINTS);
            }
            modelYear = new Year(getYear(), true);
        }
        return modelYear;
    }

    private Set<ModuleCode> getModelModuleCodes() throws IllegalValueException {
        final List<ModuleCode> moduleCodes = new ArrayList<>();
        for (JsonAdaptedModuleCode moduleCode : this.moduleCodes) {
            moduleCodes.add(moduleCode.toModelType());
        }

        return new HashSet<>(moduleCodes);
    }


}
