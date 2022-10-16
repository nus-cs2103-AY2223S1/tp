package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.subject.SubjectHandler;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    // New fields
    private final StudentClass studentClass;
    private final Set<Remark> remarksList;
    private final SubjectHandler subjectHandler;

    /**
     * Every field must be present and not null (except attendance, remark and grade).
     */
    public Person(Name name, Phone phone, Email email, Address address, StudentClass studentClass,
                  Set<Remark> remarksList,
                  SubjectHandler subjectsTaken, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, studentClass, remarksList, subjectsTaken, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);

        // Added in v1.2
        this.studentClass = studentClass;
        this.remarksList = remarksList;
        this.subjectHandler = subjectsTaken;
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

    public Address getAddress() {
        return address;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public Set<Remark> getRemarks() {
        return Collections.unmodifiableSet(remarksList);
    }

    public SubjectHandler getSubjectHandler() {
        return subjectHandler;
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

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, studentClass, remarksList, subjectHandler, tags);
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
               && otherPerson.getAddress().equals(getAddress())
               && otherPerson.getTags().equals(getTags())
               && otherPerson.getStudentClass().equals(getStudentClass())
               && otherPerson.getRemarks().equals(getRemarks());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
               .append("; Phone: ")
               .append(getPhone())
               .append("; Email: ")
               .append(getEmail())
               .append("; Address: ")
               .append(getAddress())
               .append("; Class: ")
               .append(getStudentClass())
               .append("; Remarks: ")
               .append(getRemarks())
               .append("; Subject: ")
               .append(getSubjectHandler());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
