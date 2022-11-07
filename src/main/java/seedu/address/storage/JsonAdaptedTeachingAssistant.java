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
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rating;
import seedu.address.model.person.TeachingAssistant;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link TeachingAssistant}.
 */
class JsonAdaptedTeachingAssistant extends JsonAdaptedPerson {

    private final String moduleCode;

    private final String rating;

    /**
     * Constructs a {@code JsonAdaptedTeachingAssistant} with the given Teaching Assistant's details.
     */
    @JsonCreator
    public JsonAdaptedTeachingAssistant(@JsonProperty("type") String type, @JsonProperty("name") String name,
                                        @JsonProperty("moduleCode") String moduleCode,
                                        @JsonProperty("phone") String phone,
                                        @JsonProperty("email") String email, @JsonProperty("gender") String gender,
                                        @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                        @JsonProperty("location") String location,
                                        @JsonProperty("username") String username,
                                        @JsonProperty("rating") String rating) {
        super(type, name, phone, email, gender, tagged, location, username);
        this.moduleCode = moduleCode;
        this.rating = rating;
    }

    /**
     * Converts a given {@code TeachingAssistant} into this class for Jackson use.
     */
    public JsonAdaptedTeachingAssistant(TeachingAssistant source) {
        super(source);
        this.rating = source.getRating().value;
        this.moduleCode = source.getModuleCode().value;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public String getRating() {
        return this.rating;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code TeachingAssistant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted TeachingAssistant.
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

        return new TeachingAssistant(modelName, modelModuleCode, modelPhone, modelEmail, modelGender,
            modelTags, modelLocation, modelUsername, modelRating);
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

}
