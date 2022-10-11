package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Jackson-friendly version of {@link Tutor}.
 */
class JsonAdaptedTutor {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutor's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String qualification;
    private final String institution;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedTuitionClass> tuitionClasses = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTutor} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTutor(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("qualification") String qualification,
                             @JsonProperty("institution") String institution,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("tuitionClasses") List<JsonAdaptedTuitionClass> tuitionClasses) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.qualification = qualification;
        this.institution = institution;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (tuitionClasses != null) {
            this.tuitionClasses.addAll(tuitionClasses);
        }
    }

    /**
     * Converts a given {@code Tutor} into this class for Jackson use.
     */
    public JsonAdaptedTutor(Tutor source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        qualification = source.getQualification().qualification;
        institution = source.getInstitution().institution;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        tuitionClasses.addAll(source.getTuitionClasses().stream()
                .map(JsonAdaptedTuitionClass::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Tutor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Tutor toModelType() throws IllegalValueException {
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

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

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

        if (qualification == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Qualification.class.getSimpleName()));
        }
        if (!Qualification.isValidQualification(qualification)) {
            throw new IllegalValueException(Qualification.MESSAGE_CONSTRAINTS);
        }
        final Qualification modelQualification = new Qualification(qualification);

        if (institution == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Institution.class.getSimpleName()));
        }
        if (!Institution.isValidInstitution(institution)) {
            throw new IllegalValueException(Institution.MESSAGE_CONSTRAINTS);
        }
        final Institution modelInstitution = new Institution(institution);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final List<TuitionClass> modelTuitionClasses = new ArrayList<>();
        for (JsonAdaptedTuitionClass tuitionClass: tuitionClasses) {
            modelTuitionClasses.add(tuitionClass.toModelType());
        }
        return new Tutor(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                modelQualification, modelInstitution, modelTuitionClasses);
    }

}
