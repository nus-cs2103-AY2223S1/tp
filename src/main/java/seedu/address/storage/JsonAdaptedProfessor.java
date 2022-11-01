package seedu.address.storage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Location;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.OfficeHour;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Specialisation;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Professor}.
 */
class JsonAdaptedProfessor extends JsonAdaptedPerson {

    private final String rating;
    private final String specialisation;
    private final String officeHour;
    private final String moduleCode;

    /**
     * Constructs a {@code JsonAdaptedProfessor} with the given professor's details.
     */
    @JsonCreator
    public JsonAdaptedProfessor(@JsonProperty("type") String type, @JsonProperty("name") String name,
                                @JsonProperty("moduleCode") String moduleCode, @JsonProperty("phone") String phone,
                                @JsonProperty("email") String email, @JsonProperty("gender") String gender,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                @JsonProperty("location") String location,
                                @JsonProperty("username") String username, @JsonProperty("rating") String rating,
                                @JsonProperty("specialisation") String specialisation,
                                @JsonProperty("officeHour") String officeHour) {
        super(type, name, phone, email, gender, tagged, location, username);
        this.rating = rating;
        this.specialisation = specialisation;
        this.officeHour = officeHour;
        this.moduleCode = moduleCode;
    }

    /**
     * Converts a given {@code Professor} into this class for Jackson use.
     */
    public JsonAdaptedProfessor(Professor source) {
        super(source);
        this.officeHour = source.getOfficeHour().value;
        this.rating = source.getRating().value;
        this.moduleCode = source.getModuleCode().value;
        this.specialisation = source.getSpecialisation().value;
    }

    public String getRating() {
        return this.rating;
    }
    public String getModuleCode() {
        return this.moduleCode;
    }
    public String getSpecialisation() {
        return this.specialisation;
    }
    public String getOfficeHour() {
        return this.officeHour;
    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Professor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Professor.
     */
    public Person toModelType() throws IllegalValueException {
        final Name modelName = getModelName();
        final Phone modelPhone = getModelPhone();
        final Email modelEmail = getModelEmail();
        final Gender modelGender = getModelGender();
        final Set<Tag> modelTags = new HashSet<>(getPersonTags());
        final Location modelLocation = getModelLocation();
        final GithubUsername modelUsername = getModelUsername();
        final Rating modelRating = getModelRating();
        final ModuleCode modelModuleCode = getModelModuleCode();
        final Specialisation modelSpecialisation = getModelSpecialisation();
        final OfficeHour modelOfficeHour = getModelOfficeHour();

        return new Professor(modelName, modelModuleCode, modelPhone, modelEmail, modelGender, modelTags,
                    modelLocation, modelUsername, modelRating, modelSpecialisation, modelOfficeHour);
    }
    private ModuleCode getModelModuleCode() throws IllegalValueException {
        if (getModuleCode() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(getModuleCode())) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(getModuleCode());
    }

    private OfficeHour getModelOfficeHour() throws IllegalValueException {
        final OfficeHour modelOfficeHour;

        if (getOfficeHour() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        if (getOfficeHour().equals(OfficeHour.EMPTY_OFFICE_HOUR)) {
            modelOfficeHour = new OfficeHour(getOfficeHour(), false);
        } else {
            if (!OfficeHour.isValidOfficeHour(getOfficeHour())) {
                throw new IllegalValueException(OfficeHour.MESSAGE_CONSTRAINTS);
            }
            modelOfficeHour = new OfficeHour(getOfficeHour(), true);
        }
        return modelOfficeHour;
    }

    private Rating getModelRating() throws IllegalValueException {
        if (getRating() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Rating.class.getSimpleName()));
        }

        Rating modelRating;

        if (getRating().equals(Rating.EMPTY_RATING)) {
            modelRating = new Rating(getRating(), false);
        } else {
            if (!Rating.isValidRating(getRating())) {
                throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
            }
            modelRating = new Rating(getRating(), true);
        }
        return modelRating;
    }

    private Specialisation getModelSpecialisation() throws IllegalValueException {
        final Specialisation modelSpecialisation;

        if (getSpecialisation() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Specialisation.class.getSimpleName()));
        }

        if (getSpecialisation().equals(Specialisation.EMPTY_SPECIALISATION)) {
            modelSpecialisation = new Specialisation(getSpecialisation(), false);
        } else {
            if (!Specialisation.isValidSpecialisation(getSpecialisation())) {
                throw new IllegalValueException(Specialisation.MESSAGE_CONSTRAINTS);
            }
            modelSpecialisation = new Specialisation(getSpecialisation(), true);
        }
        return modelSpecialisation;
    }
}
