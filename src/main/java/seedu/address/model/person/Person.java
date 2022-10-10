package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Position position;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Remark remark;
    private final ApplicationProcess applicationProcess;
    private final Date date;
    private final Website website;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Remark remark, Position position,
                  ApplicationProcess applicationProcess, Date date, Website website, Set<Tag> tags) {
        requireAllNonNull(name, position, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.remark = remark;
        this.tags.addAll(tags);
        this.position = position;
        this.applicationProcess = applicationProcess;
        this.date = date;
        this.website = website;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Remark getRemark() {
        return remark;
    }

    public Position getPosition() {
        return position;
    }

    public Website getWebsite() {
        return website;
    }

    public Date getDate() {
        return date;
    }

    public ApplicationProcess getApplicationProcess() {
        return applicationProcess;
    }


    /**
     * Compares two person based on the category to be compared by
     * @param o The other person to be compared to
     * @param category The category to be compared by
     * @return a non-negative number if this person is before the other and negative number otherwise
     */
    public int compareTo(Person o, ComparableCategory category) {
        switch (category) {
        case NAME:
            return this.getName().compareTo(o.getName());
        case APPLICATION_PROCESS:
            return this.getApplicationProcess().compareTo(o.getApplicationProcess());
        case POSITION:
            return this.getPosition().compareTo(o.getPosition());
        case DATE:
            return this.getDate().compareTo(o.getDate());
        default:
            return 0;
        }
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name and position.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName()) && otherPerson.getPosition().equals(getPosition());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getRemark().equals(getRemark())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getPosition().equals(getPosition())
                && otherPerson.getWebsite().equals(getWebsite())
                && otherPerson.getDate().equals(getDate())
                && otherPerson.getApplicationProcess().equals(getApplicationProcess());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, remark, tags, position, website, date, applicationProcess);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Position: ")
                .append(getPosition())
                .append("; Application Process: ")
                .append(getApplicationProcess())
                .append("; Date: ")
                .append(getDate())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Website: ")
                .append(getWebsite())
                .append("; Remark: ")
                .append(getRemark());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
