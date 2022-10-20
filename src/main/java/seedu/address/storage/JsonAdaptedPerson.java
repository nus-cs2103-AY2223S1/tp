package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.*;

/**
 * Jackson-friendly version of {@link Person}.
 */
@JsonDeserialize(using = JsonAdaptedPersonDeserializer.class)
abstract class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String type;
    private final String moduleCode;
    private final String name;
    private final String phone;
    private final String email;
    private final String gender;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String location;
    private final String username;
    private final String rating;
    private final String year;
    private final String specialisation;
    private final String officeHour;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("type") String type, @JsonProperty("name") String name,
                             @JsonProperty("moduleCode") String moduleCode, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("gender") String gender,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("location") String location, @JsonProperty("username") String username,
                             @JsonProperty("rating") String rating, @JsonProperty("year") String year,
                             @JsonProperty("specialisation") String specialisation,
                             @JsonProperty("officeHour") String officeHour) {
        this.type = type;
        this.name = name;
        this.moduleCode = moduleCode;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.location = location;
        this.username = username;
        this.rating = rating;
        this.year = year;
        this.specialisation = specialisation;
        this.officeHour = officeHour;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        if (source instanceof Student) {
            Student student = (Student) source;
            type = "s";
            moduleCode = "";
            rating = "";
            year = student.getYear().value;
            specialisation = "";
            officeHour = "";
        } else if (source instanceof Professor) {
            type = "p";
            Professor prof = (Professor) source;
            moduleCode = prof.getModuleCode().value;
            rating = prof.getRating().value;
            year = "";
            specialisation = prof.getSpecialisation().value;
            officeHour = prof.getOfficeHour().value;
        } else if (source instanceof TeachingAssistant) {
            type = "t";
            TeachingAssistant ta = (TeachingAssistant) source;
            moduleCode = ta.getModuleCode().value;
            rating = ta.getRating().value;
            year = "";
            specialisation = "";
            officeHour = "";
        } else {
            type = "invalid";
            moduleCode = "invalid";
            rating = "invalid";
            year = "invalid";
            specialisation = "invalid";
            officeHour = "invalid";
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
    public String getModuleCode() {
        return this.moduleCode;
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
    public String getRating() {
        return rating;
    }
    public String getYear() {
        return year;
    }
    public String getSpecialisation() {
        return specialisation;
    }
    public String getOfficeHour() {
        return officeHour;
    }

    public abstract Person toModelType() throws IllegalValueException;

}
