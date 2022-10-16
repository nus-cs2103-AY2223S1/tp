package seedu.address.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Student which is-a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {
    private final Set<ModuleCode> moduleCodes = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email,
                   Gender gender, Set<Tag> tags, Location location, Set<ModuleCode> moduleCodes) {
        super(name, phone, email, gender, tags, location);
        this.moduleCodes.addAll(moduleCodes);
    }

    public Set<ModuleCode> getModuleCodes() {
        return Collections.unmodifiableSet(moduleCodes);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Name: ")
                .append(getName());

        if (!moduleCodes.isEmpty()) {
            builder.append("; Module Code: ");
            moduleCodes.forEach(builder:: append);
        }

        builder.append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Gender: ")
                .append(getGender())
                .append("; Location: ")
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
}
