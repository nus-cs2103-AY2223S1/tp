package seedu.address.model.internship;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Objects;

import seedu.address.model.person.PersonId;

/**
 * Represents an Internship in InterNUS.
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

    public String getDisplayName() {
        return companyName.toString() + " " + internshipRole.toString();
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
        if (interviewDate == null) {
            return new InterviewDate(null);
        }
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

    /**
     * Returns a Comparator that compares Internships by companyName.
     *
     * @return the Comparator.
     */
    public static Comparator<Internship> compareByCompanyName() {
        return Comparator.comparing(i -> i.getCompanyName().toString().toLowerCase());
    }

    /**
     * Returns a Comparator that compares Internships by interviewDate.
     * Earlier dates are smaller (and appear higher in the sorted list).
     * Internships with no interview dates are greater (and appear lower in the sorted list).
     *
     * @return the Comparator.
     */
    // solution adapted from
    // https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
    // #comparing-java.util.function.Function-java.util.Comparator-
    public static Comparator<Internship> compareByInterviewDate() {
        return Comparator.comparing(
                i -> i.getInterviewDate().datetime, Comparator.nullsLast(Comparator.naturalOrder()));
    }

    /**
     * Returns a Comparator that compares Internships by internshipStatus.
     * The ordering is defined in {@code InternshipStatus.State}.
     *
     * @return the Comparator.
     */
    public static Comparator<Internship> compareByInternshipStatus() {
        return Comparator.comparingInt(i -> i.getInternshipStatus().currentState.getLevel());
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
                .append("; Interview Date: ")
                .append(getInterviewDate());

        return builder.toString();
    }
}
