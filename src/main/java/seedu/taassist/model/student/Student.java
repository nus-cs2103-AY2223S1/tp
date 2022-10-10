package seedu.taassist.model.student;

import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.uniquelist.Identity;

/**
 * Represents a Student in TA-Assist.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student implements Identity<Student> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<ModuleClass> moduleClasses = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<ModuleClass> moduleClasses) {
        requireAllNonNull(name, phone, email, address, moduleClasses);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.moduleClasses.addAll(moduleClasses);
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

    /**
     * Returns an immutable moduleClass set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleClass> getModuleClasses() {
        return Collections.unmodifiableSet(moduleClasses);
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    @Override
    public boolean isSame(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
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
                && otherStudent.getModuleClasses().equals(getModuleClasses());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, moduleClasses);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        Phone phone = getPhone();
        Email email = getEmail();
        Address address = getAddress();
        Set<ModuleClass> moduleClasses = getModuleClasses();

        if (phone.isPresent()) {
            builder.append("; Phone: ").append(phone);
        }
        if (email.isPresent()) {
            builder.append("; Email: ").append(email);
        }
        if (address.isPresent()) {
            builder.append("; Address: ").append(address);
        }
        if (!moduleClasses.isEmpty()) {
            builder.append("; Classes: ");
            moduleClasses.forEach(builder::append);
        }
        return builder.toString();
    }

}
