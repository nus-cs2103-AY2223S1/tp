package seedu.address.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Student which is-a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {
    private final Set<ModuleCode> moduleCodes = new HashSet<>();

    private final Year year;

    /**
     * Every field must be present and not null.
     */

    public Student(Name name, Phone phone, Email email, Gender gender, Set<Tag> tags,
                   Location location, GithubUsername username, Set<ModuleCode> moduleCodes, Year year) {
        super(name, phone, email, gender, tags, location, username);
        this.moduleCodes.addAll(moduleCodes);
        this.year = year;
    }

    public Set<ModuleCode> getModuleCodes() {
        return Collections.unmodifiableSet(this.moduleCodes);
    }

    public Year getYear() {
        return this.year;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getGender(), getTags(), getLocation(),
                getUsername(), moduleCodes, year);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Name: ")
                .append(getName());

        if (!this.moduleCodes.isEmpty()) {
            builder.append("; Module Code: ");
            this.moduleCodes.forEach(builder:: append);
        }

        if (!year.value.equals(Year.EMPTY_YEAR)) {
            builder.append("; Year: ")
                    .append(year.value);
        }

        builder.append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Gender: ")
                .append(getGender());

        if (!getUsername().value.equals(GithubUsername.DEFAULT_USERNAME)) {
            builder.append("; Github Username: ")
                    .append(getUsername());
        }

        builder.append("; Location: ")
                .append(getLocation());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public int compareModuleCode(Person person) {
        if (person instanceof Student) {
            return compareName(person);
        }
        return -1;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }
        Student otherPerson = (Student) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getLocation().equals(getLocation())
                && otherPerson.getUsername().equals(getUsername())
                && otherPerson.getModuleCodes().equals(getModuleCodes())
                && otherPerson.getYear().equals(getYear());
    }
}
