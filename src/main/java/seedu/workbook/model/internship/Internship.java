package seedu.workbook.model.internship;

import static seedu.workbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.workbook.model.tag.Tag;

/**
 * Represents an Internship Application in WorkBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Internship {

    // Identity fields
    private final Company company;
    private final Role role;
    private final Email email;
    private final Stage stage;

    // Data fields

    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Internship(Company company, Role role, Email email, Stage stage, Set<Tag> tags) {
        requireAllNonNull(company, role, email, stage, tags);
        this.company = company;
        this.role = role;
        this.email = email;
        this.stage = stage;
        this.tags.addAll(tags);
    }

    public Company getCompany() {
        return company;
    }

    public Role getRole() {
        return role;
    }


    public Email getEmail() {
        return email;
    }
    public Stage getStage() {
        return stage;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both internships have the same company AND role.
     * This defines a weaker notion of equality between two internships.
     */
    public boolean isSameInternship(Internship otherInternship) {
        if (otherInternship == this) {
            return true;
        }

        return otherInternship != null
                && otherInternship.getCompany().equals(getCompany())
                && otherInternship.getRole().equals(getRole());
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
                && otherInternship.getRole().equals(getRole())
                && otherInternship.getEmail().equals(getEmail())
                && otherInternship.getStage().equals(getStage())
                && otherInternship.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(company, role, email, stage, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCompany())
                .append("; Role: ")
                .append(getRole())
                .append("; Email: ")
                .append(getEmail())
                .append("; Stage: ")
                .append(getStage());


        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
