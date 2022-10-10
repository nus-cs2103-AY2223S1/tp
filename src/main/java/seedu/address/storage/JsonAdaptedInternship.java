package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.internship.InternshipRole;
import seedu.address.model.internship.InternshipStatus;
import seedu.address.model.internship.InterviewDate;
import seedu.address.model.person.PersonId;

/**
 * Jackson-friendly version of {@link Internship}.
 */
class JsonAdaptedInternship {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship's %s field is missing!";

    private final Integer internshipId;
    private final String companyName;
    private final String internshipRole;
    private final String internshipStatus;
    private final Integer contactPersonId;
    private final String interviewDate;

    /**
     * Constructs a {@code JsonAdaptedInternship} with the given internship details.
     */
    @JsonCreator
    public JsonAdaptedInternship(
            @JsonProperty("internshipId") Integer internshipId,
            @JsonProperty("companyName") String companyName,
            @JsonProperty("internshipRole") String internshipRole,
            @JsonProperty("internshipStatus") String internshipStatus,
            @JsonProperty("contactPersonId") Integer contactPersonId,
            @JsonProperty("interviewDate") String interviewDate) {
        this.internshipId = internshipId;
        this.companyName = companyName;
        this.internshipRole = internshipRole;
        this.internshipStatus = internshipStatus;
        this.contactPersonId = contactPersonId;
        this.interviewDate = interviewDate;
    }

    /**
     * Converts a given {@code Internship} into this class for Jackson use.
     */
    public JsonAdaptedInternship(Internship source) {

        internshipId = source.getInternshipId().id;
        companyName = source.getCompanyName().fullName;
        internshipRole = source.getInternshipRole().roleName;
        internshipStatus = source.getInternshipStatus().currentState.name();
        contactPersonId = source.getContactPersonId() != null ? source.getContactPersonId().id : null;
        interviewDate = source.getInterviewDate() != null ? source.getInterviewDate().toString() : null;
    }

    /**
     * Converts this Jackson-friendly adapted internship object into the model's {@code Internship} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted internship.
     */
    public Internship toModelType() throws IllegalValueException {
        // InternshipId
        if (internshipId == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, InternshipId.class.getSimpleName()));
        }
        if (!InternshipId.isValidId(internshipId)) {
            throw new IllegalValueException(InternshipId.MESSAGE_CONSTRAINTS);
        }
        final InternshipId modelInternshipId = new InternshipId(internshipId);

        // CompanyName
        if (companyName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidName(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelCompanyName = new CompanyName(companyName);

        // InternshipRole
        if (internshipRole == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, InternshipRole.class.getSimpleName()));
        }
        if (!InternshipRole.isValidName(internshipRole)) {
            throw new IllegalValueException(InternshipRole.MESSAGE_CONSTRAINTS);
        }
        final InternshipRole modelInternshipRole = new InternshipRole(internshipRole);

        // InternshipStatus
        if (internshipStatus == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, InternshipStatus.class.getSimpleName()));
        }
        if (!InternshipStatus.isValidStatus(internshipStatus)) {
            throw new IllegalValueException(InternshipStatus.MESSAGE_CONSTRAINTS);
        }
        final InternshipStatus modelInternshipStatus =
                new InternshipStatus(InternshipStatus.State.valueOf(internshipStatus));

        // ContactPersonId
        final PersonId modelContactPersonId;
        if (contactPersonId == null || !InternshipId.isValidId(contactPersonId)) {
            modelContactPersonId = null;
        } else {
            modelContactPersonId = new PersonId(contactPersonId);
        }

        // InterviewDate
        InterviewDate modelInterviewDate;
        if (interviewDate == null || !InterviewDate.isValidDatetimeStr(interviewDate)) {
            modelInterviewDate = null;
        } else {
            modelInterviewDate = new InterviewDate(interviewDate);
        }

        return new Internship(
                modelInternshipId,
                modelCompanyName,
                modelInternshipRole,
                modelInternshipStatus,
                modelContactPersonId,
                modelInterviewDate);
    }

}
