package seedu.address.model.internship;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.PersonId;

/**
 * Represents an Internship in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Internship {

    // Identity fields
    private final InternshipId internshipId;
    private final CompanyName companyName;
    private final InternshipRole internshipRole;

    // Data fields
    private final InternshipStatus internshipStatus;
    private final PersonId contactPersonId;
    private final InterviewDate interviewDate;

    /**
     * Every field must be present and not null except contact person and interview date.
     */
    public Internship(
            InternshipId internshipId,
            CompanyName companyName,
            InternshipRole internshipRole,
            InternshipStatus internshipStatus,
            PersonId contactPersonId,
            InterviewDate interviewDate) {
        requireAllNonNull(internshipId, companyName, internshipRole);
        this.internshipId = internshipId;
        this.companyName = companyName;
        this.internshipRole = internshipRole;
        this.internshipStatus = internshipStatus;
        this.contactPersonId = contactPersonId;
        this.interviewDate = interviewDate;
    }

    public InternshipId getInternshipId() {
        return internshipId;
    }

    public CompanyName getCompanyName() {
        return companyName;
    }

    public InternshipRole getInternshipRole() {
        return internshipRole;
    }

    public InternshipStatus getInternshipStatus() {
        return internshipStatus;
    }

    public PersonId getContactPersonId() {
        return contactPersonId;
    }

    public InterviewDate getInterviewDate() {
        return interviewDate;
    }

    /**
     * Returns true if both internships have the same company name and internship role.
     * This defines a weaker notion of equality between two internships.
     */
    public boolean isSameInternship(Internship otherInternship) {
        if (otherInternship == this) {
            return true;
        }

        return otherInternship != null
                && otherInternship.getCompanyName().equals(getCompanyName())
                && otherInternship.getInternshipRole().equals(getInternshipRole());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Internship)) {
            return false;
        }

        Internship otherInternship = (Internship) other;
        return otherInternship.getInternshipId().equals(getInternshipId())
                && otherInternship.getCompanyName().equals(getCompanyName())
                && otherInternship.getInternshipRole().equals(getInternshipRole())
                && otherInternship.getInternshipStatus().equals(getInternshipStatus())
                && Objects.equals(otherInternship.getInternshipId(), getInternshipId())
                && otherInternship.getContactPersonId().equals(getContactPersonId())
                && otherInternship.getInterviewDate().equals(getInterviewDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(companyName, internshipRole, internshipStatus, contactPersonId, interviewDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCompanyName())
                .append("; Role: ")
                .append(getInternshipRole())
                .append("; Status: ")
                .append(getInternshipStatus())
                .append("; InterviewDate: ")
                .append(getInterviewDate());

        return builder.toString();
    }
}
