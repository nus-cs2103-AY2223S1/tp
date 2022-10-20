package seedu.address.model.internship;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Internship in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Internship {

    // Identity fields
    private final Company company;
    private final Link link;
    private final Description description;
    private final ApplicationStatus applicationStatus;

    // Data fields
    private final AppliedDate appliedDate;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Internship(Company company, Link link, Description description, ApplicationStatus applicationStatus,
                      AppliedDate appliedDate, Set<Tag> tags) {
        requireAllNonNull(company, link, description, appliedDate, tags);
        this.company = company;
        this.link = link;
        this.description = description;
        this.applicationStatus = applicationStatus;
        this.appliedDate = appliedDate;
        this.tags.addAll(tags);
    }

    public Company getCompany() {
        return company;
    }

    public Link getLink() {
        return link;
    }

    public Description getDescription() {
        return description;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public AppliedDate getAppliedDate() {
        return appliedDate;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both internships have the same company.
     * This defines a weaker notion of equality between two internships.
     */
    public boolean isSameInternship(Internship otherInternship) {
        if (otherInternship == this) {
            return true;
        }

        return otherInternship != null
                && otherInternship.getCompany().equals(getCompany());
    }

    /**
     * Returns true if both internships have the same identity and data fields.
     * This defines a stronger notion of equality between two internships.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Internship)) {
            return false;
        }

        Internship otherInternship = (Internship) other;
        return otherInternship.getCompany().equals(getCompany())
                && otherInternship.getLink().equals(getLink())
                && otherInternship.getDescription().equals(getDescription())
                && otherInternship.getApplicationStatus().equals(getApplicationStatus())
                && otherInternship.getAppliedDate().equals(getAppliedDate())
                && otherInternship.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(company, link, description, applicationStatus, appliedDate, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCompany())
                .append("; Link: ")
                .append(getLink())
                .append("; Description: ")
                .append(getDescription())
                .append("; Application Status: ")
                .append(getApplicationStatus())
                .append("; AppliedDate: ")
                .append(getAppliedDate());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
