package seedu.taassist.model.student;

import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.moduleclass.StudentModuleData;
import seedu.taassist.model.uniquelist.Identity;
import seedu.taassist.model.uniquelist.UniqueList;

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
    private final UniqueList<StudentModuleData> moduleData = new UniqueList<>();

    /**
     * Constructor for Student.
     */
    public Student(Name name, Phone phone, Email email, Address address, List<StudentModuleData> moduleData) {
        requireAllNonNull(name, phone, email, address, moduleData);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.moduleData.setElements(moduleData);
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
     * Returns an immutable moduleData set as an Unmodifiable ObservableList.
     */
    public List<StudentModuleData> getModuleData() {
        return moduleData.asUnmodifiableObservableList();
    }

    /**
     * Returns a list of module classes that the student is enrolled in.
     */
    public List<ModuleClass> getModuleClasses() {
        return getModuleData().stream()
            .map(StudentModuleData::getModuleClass)
            .collect(Collectors.toList());
    }

    /**
     * Returns the {@code StudentModuleData} of the student for the given {@code ModuleClass}.
     */
    public StudentModuleData findStudentModuleData(ModuleClass focusedClass) {
        return moduleData.findElement(new StudentModuleData(focusedClass));
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
                && otherStudent.getModuleData().equals(getModuleData());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, moduleData);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        Phone phone = getPhone();
        Email email = getEmail();
        Address address = getAddress();
        List<StudentModuleData> moduleData = getModuleData();

        if (phone.isPresent()) {
            builder.append("; Phone: ").append(phone);
        }
        if (email.isPresent()) {
            builder.append("; Email: ").append(email);
        }
        if (address.isPresent()) {
            builder.append("; Address: ").append(address);
        }
        if (!moduleData.isEmpty()) {
            builder.append("; Classes: ");
            moduleData.stream().map(StudentModuleData::getModuleClass).forEach(builder::append);
        }
        return builder.toString();
    }

}
