package seedu.address.model.person.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 */
public class Student extends Person {

    private final School school;
    private final Level level;
    private final NextOfKin nextOfKin;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, School school, Level level,
                   NextOfKin nextOfKin) {
        super(name, phone, email, address, tags);
        requireAllNonNull(school, level, nextOfKin);
        this.school = school;
        this.level = level;
        this.nextOfKin = nextOfKin;
    }

    public School getSchool() {
        return school;
    }

    public Level getLevel() {
        return level;
    }

    public NextOfKin getNextOfKin() {
        return nextOfKin;
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getTags().equals(getTags())
                && otherStudent.getSchool().equals(getSchool())
                && otherStudent.getLevel().equals(getLevel())
                && otherStudent.getNextOfKin().equals(getNextOfKin());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(), this.getTags(),
                school, level, nextOfKin);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(super.toString())
                .append("; School: ")
                .append(getSchool())
                .append("; Level: ")
                .append(getLevel())
                .append("; Next of kin: ")
                .append(getNextOfKin());

        return builder.toString();
    }
}
