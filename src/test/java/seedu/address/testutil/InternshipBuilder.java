package seedu.address.testutil;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.internship.InternshipRole;
import seedu.address.model.internship.InternshipStatus;
import seedu.address.model.internship.InterviewDate;
import seedu.address.model.person.PersonId;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {

    public static final Integer DEFAULT_INTERNSHIP_ID = 0;
    public static final String DEFAULT_COMPANY_NAME = "ABCDE Pte Ltd";
    public static final String DEFAULT_ROLE = "Workman";
    public static final String DEFAULT_NAME = DEFAULT_COMPANY_NAME + " " + DEFAULT_ROLE;
    public static final InternshipStatus.State DEFAULT_STATUS = InternshipStatus.State.ACCEPTED;
    public static final String DEFAULT_INTERVIEW_DATE = "2022-11-11 11:11";

    private InternshipId internshipId;
    private CompanyName name;
    private InternshipRole role;
    private InternshipStatus status;
    private InterviewDate interviewDate;
    private PersonId contactPersonId;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        internshipId = new InternshipId(DEFAULT_INTERNSHIP_ID);
        name = new CompanyName(DEFAULT_COMPANY_NAME);
        role = new InternshipRole(DEFAULT_ROLE);
        status = new InternshipStatus(DEFAULT_STATUS);
        interviewDate = new InterviewDate(DEFAULT_INTERVIEW_DATE);
        contactPersonId = null;
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        contactPersonId = internshipToCopy.getContactPersonId();
        name = internshipToCopy.getCompanyName();
        role = internshipToCopy.getInternshipRole();
        status = internshipToCopy.getInternshipStatus();
        internshipId = internshipToCopy.getInternshipId();
        interviewDate = internshipToCopy.getInterviewDate();
    }

    /**
     * Sets the {@code PersonId} of the {@code Person} that we are building.
     * This refers to the contact person linked to the internship.
     */
    public InternshipBuilder withPersonId(Integer id) {
        this.contactPersonId = new PersonId(id);
        return this;
    }

    /**
     * Sets the {@code Company Name} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withName(String name) {
        this.name = new CompanyName(name);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withRole(String role) {
        this.role = new InternshipRole(role);
        return this;
    }


    /**
     * Sets the {@code InternshipId} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withInternshipId(Integer id) {
        this.internshipId = new InternshipId(id);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withStatus(String status) {
        try {
            this.status = ParserUtil.parseInternshipStatus(status);
            return this;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Sets the {@code Interview Date} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withInterviewDate(String date) {
        if (date == null || date.isBlank()) {
            this.interviewDate = new InterviewDate(null);
        } else {
            this.interviewDate = new InterviewDate(date);
        }
        return this;
    }

    public Internship build() {
        return new Internship(internshipId, name, role, status, contactPersonId, interviewDate);
    }

}
