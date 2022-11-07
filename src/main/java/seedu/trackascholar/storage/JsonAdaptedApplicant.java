package seedu.trackascholar.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.trackascholar.commons.exceptions.IllegalValueException;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.model.applicant.Email;
import seedu.trackascholar.model.applicant.Name;
import seedu.trackascholar.model.applicant.Phone;
import seedu.trackascholar.model.applicant.Pin;
import seedu.trackascholar.model.applicant.Scholarship;
import seedu.trackascholar.model.major.Major;

/**
 * Jackson-friendly version of {@link Applicant}.
 */
class JsonAdaptedApplicant {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Applicant's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;

    private final String scholarship;
    private final String applicationStatus;

    private final boolean hasPinned;

    private final List<JsonAdaptedMajor> majors = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedApplicant} with the given applicant details.
     */
    @JsonCreator
    public JsonAdaptedApplicant(@JsonProperty("name") String name,
                                @JsonProperty("phone") String phone,
                                @JsonProperty("email") String email,
                                @JsonProperty("scholarship") String scholarship,
                                @JsonProperty("applicationStatus") String applicationStatus,
                                @JsonProperty("majors") List<JsonAdaptedMajor> majors,
                                @JsonProperty("hasPinned") boolean hasPinned) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.scholarship = scholarship;
        this.applicationStatus = applicationStatus;
        this.hasPinned = hasPinned;

        if (majors != null) {
            this.majors.addAll(majors);
        }
    }

    /**
     * Converts a given {@code Applicant} into this class for Jackson use.
     */
    public JsonAdaptedApplicant(Applicant source) {
        name = source.getFullName();
        phone = source.getPhoneNumber();
        email = source.getEmailAddress();
        scholarship = source.getScholarshipName();
        applicationStatus = source.getStatusOfApplication();
        hasPinned = source.getHasPinned();
        majors.addAll(source.getMajors().stream()
                .map(JsonAdaptedMajor::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted applicant object into the model's {@code Applicant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted applicant.
     */
    public Applicant toModelType() throws IllegalValueException {
        final List<Major> applicantMajors = new ArrayList<>();
        for (JsonAdaptedMajor major : majors) {
            applicantMajors.add(major.toModelType());
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

        if (scholarship == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Scholarship.class.getSimpleName()));
        }
        if (!Scholarship.isValidScholarship(scholarship)) {
            throw new IllegalValueException(Scholarship.MESSAGE_CONSTRAINTS);
        }
        final Scholarship modelScholarship = new Scholarship(scholarship);

        if (applicationStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ApplicationStatus.class.getSimpleName()));
        }
        if (!ApplicationStatus.isValidApplicationStatus(applicationStatus)) {
            throw new IllegalValueException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        }
        final ApplicationStatus modelApplicationStatus = new ApplicationStatus(applicationStatus);

        if (applicantMajors.size() > Major.MAXIMUM_NUMBER_OF_MAJORS) {
            throw new IllegalValueException(Major.MESSAGE_CONSTRAINTS);
        }
        final Set<Major> modelMajors = new HashSet<>(applicantMajors);
        final Pin modelPin = new Pin(hasPinned);
        return new Applicant(modelName, modelPhone, modelEmail, modelScholarship,
                modelApplicationStatus, modelMajors, modelPin);

    }

}
