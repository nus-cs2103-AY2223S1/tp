package seedu.trackascholar.model.applicant;

import static seedu.trackascholar.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.trackascholar.model.major.Major;

/**
 * Represents an Applicant in TrackAScholar.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Applicant {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final ApplicationStatus applicationStatus;
    private final Scholarship scholarship;
    private final Pin pin;

    private final Set<Major> majors = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Scholarship scholarship,
                     ApplicationStatus applicationStatus, Set<Major> majors, Pin pin) {
        requireAllNonNull(name, phone, email, scholarship, applicationStatus, majors);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.scholarship = scholarship;
        this.applicationStatus = applicationStatus;
        this.pin = pin;
        this.majors.addAll(majors);
    }

    /**
     * Every field must be present and not null.
     */

    public Applicant(Name name, Phone phone, Email email, Scholarship scholarship,
                     ApplicationStatus applicationStatus, Set<Major> majors) {
        requireAllNonNull(name, phone, email, scholarship, applicationStatus, majors);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.scholarship = scholarship;
        this.applicationStatus = applicationStatus;
        this.pin = new Pin(false);
        this.majors.addAll(majors);
    }

    public Name getName() {
        return name;
    }

    public String getFullName() {
        return name.getFullName();
    }

    public Phone getPhone() {
        return phone;
    }

    public String getPhoneNumber() {
        return phone.getPhoneNumber();
    }

    public Email getEmail() {
        return email;
    }

    public String getEmailAddress() {
        return email.getEmailAddress();
    }

    public Scholarship getScholarship() {
        return scholarship;
    }

    public String getScholarshipName() {
        return scholarship.getScholarshipName();
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public String getStatusOfApplication() {
        return applicationStatus.getStatusOfApplication();
    }

    public Pin getPin() {
        return pin;
    }

    public boolean getHasPinned() {
        return pin.getHasPinned();
    }

    /**
     * Returns an immutable major set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Major> getMajors() {
        return Collections.unmodifiableSet(majors);
    }

    /**
     * Returns true if both applicants have the same name.
     * This defines a weaker notion of equality between two applicants.
     */
    public boolean isSameApplicant(Applicant otherApplicant) {
        if (otherApplicant == this) {
            return true;
        }

        return otherApplicant != null
                && otherApplicant.getFullName().equalsIgnoreCase(this.getFullName());
    }

    /**
     * Returns true if this applicant has a matching application status.
     */
    public boolean isMatchingApplicationStatus(ApplicationStatus otherStatus) {
        return otherStatus != null
                && otherStatus.equals(applicationStatus);
    }

    /**
     * Returns a comparator that sorts applicants lexicographically by name.
     * No tiebreaker needed as applicant names are guaranteed to be unique.
     */

    public static Comparator<Applicant> sortByName() {
        return (a1, a2) -> {
            Name n1 = a1.getName();
            Name n2 = a2.getName();
            return n1.compareTo(n2);
        };
    }

    /**
     * Returns a comparator that sorts applicants lexicographically by scholarship.
     * If both applicants have the same scholarship, tiebreak by name.
     */

    public static Comparator<Applicant> sortByScholarship() {
        return (a1, a2) -> {
            Scholarship s1 = a1.getScholarship();
            Scholarship s2 = a2.getScholarship();
            int result = s1.compareTo(s2);
            if (result == 0) {
                Name n1 = a1.getName();
                Name n2 = a2.getName();
                return n1.compareTo(n2);
            }
            return result;
        };
    }

    /**
     * Returns a comparator that sorts applicants by application status. Pending applicants are
     * displayed at the top of the list, followed by accepted applicants, and lastly rejected applicants.
     * If both applicants have the same application status, tiebreak by name.
     */

    public static Comparator<Applicant> sortByStatus() {
        return (a1, a2) -> {
            ApplicationStatus as1 = a1.getApplicationStatus();
            ApplicationStatus as2 = a2.getApplicationStatus();
            int result = as1.compareTo(as2);
            if (result == 0) {
                Name n1 = a1.getName();
                Name n2 = a2.getName();
                return n1.compareTo(n2);
            }
            return result;
        };
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return otherApplicant.getName().equals(getName())
                && otherApplicant.getPhone().equals(getPhone())
                && otherApplicant.getEmail().equals(getEmail())
                && otherApplicant.getScholarship().equals(getScholarship())
                && otherApplicant.getStatusOfApplication().equals(getStatusOfApplication())
                && otherApplicant.getPin().equals(getPin())
                && otherApplicant.getMajors().equals(getMajors());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, scholarship, applicationStatus, majors, pin);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("\n Phone: ")
                .append(getPhone())
                .append("\n Email: ")
                .append(getEmail())
                .append("\n Scholarship: ")
                .append(getScholarship())
                .append("\n Application Status: ")
                .append(getStatusOfApplication());

        Set<Major> majors = getMajors();
        if (!majors.isEmpty()) {
            builder.append("\n Major(s): ");
            majors.forEach(builder::append);
        }

        return builder.toString();
    }

}
