package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.internship.InternshipId;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in InterNUS.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final PersonId personId;
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final InternshipId internshipId;
    private final Company company;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Everything must be present and not purely null.
     * However, optional fields can be an object wrapping null.
     */
    public Person(
            PersonId personId,
            Name name,
            Email email,
            Phone phone,
            InternshipId internshipId,
            Set<Tag> tags,
            Company company) {
        requireAllNonNull(name, tags);
        this.personId = personId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.internshipId = internshipId;
        this.tags.addAll(tags);
        this.company = company;
    }

    public PersonId getPersonId() {
        return personId;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        if (phone == null) {
            return new Phone(null);
        }
        return phone;
    }

    public Email getEmail() {
        if (email == null) {
            return new Email(null);
        }
        return email;
    }

    public InternshipId getInternshipId() {
        return internshipId;
    }

    public Company getCompany() {
        if (company == null) {
            return new Company(null);
        }
        return company;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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
        // solution adapted from
        // https://stackoverflow.com/a/36716166
        return otherPerson.getPersonId().equals(getPersonId())
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && Objects.equals(otherPerson.getPersonId(), getPersonId())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, tags, company);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(("; Email: ")).append(getEmail())
                .append(("; Phone: ")).append(getPhone());
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        builder.append(("; Company: ")).append(getCompany());
        return builder.toString();
    }

}
