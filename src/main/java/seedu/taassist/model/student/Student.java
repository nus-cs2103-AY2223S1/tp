package seedu.taassist.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.uniquelist.Identity;
import seedu.taassist.model.uniquelist.UniqueList;

/**
 * Represents a Student in TA-Assist.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student implements Identity<Student>, Comparable<Student> {

    // Identity fields
    private final Name name;

    // Data fields
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final UniqueList<StudentModuleData> moduleDataList = new UniqueList<>();

    /**
     * Constructor for Student.
     */
    public Student(Name name, Phone phone, Email email, Address address, List<StudentModuleData> moduleDataList) {
        requireAllNonNull(name, phone, email, address, moduleDataList);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.moduleDataList.setElements(moduleDataList);
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
     * Returns an Unmodifiable ObservableList of module data.
     */
    public ObservableList<StudentModuleData> getModuleDataList() {
        return moduleDataList.asUnmodifiableObservableList();
    }

    /**
     * Returns a list of module classes that the student is enrolled in.
     */
    public List<ModuleClass> getModuleClasses() {
        return getModuleDataList().stream()
            .map(StudentModuleData::getModuleClass)
            .collect(Collectors.toList());
    }

    /**
     * Checks if the student is assigned to the provided {@code moduleClass}.
     *
     * @param moduleClass Module class to check if the Student is assigned to it.
     * @return True if the student is assigned to {@code moduleClass}.
     */
    private boolean isInModuleClass(ModuleClass moduleClass) {
        return moduleDataList.contains(new StudentModuleData(moduleClass));
    }

    /**
     * Returns the {@code StudentModuleData} of the student for the given {@code ModuleClass}.
     *
     * @param targetClass Module class of the {@code StudentModuleData} to find.
     * @return {@code StudentModuleData} of the student for the given {@code targetClass}.
     */
    public Optional<StudentModuleData> findStudentModuleData(ModuleClass targetClass) {
        return moduleDataList.findElement(new StudentModuleData(targetClass));
    }

    /**
     * Returns the {@code SessionData} of the student for the given {@code ModuleClass} and {@code Session}.
     * Returns null if ModuleClass or Session doesn't exist in Student.
     *
     * @param targetClass Module class associated with {@code targetSession}.
     * @param targetSession Session of the {@code SessionData} to find.
     * @return {@code SessionData} of the student for the given {@code targetSession} and {@code targetClass}.
     */
    public Optional<SessionData> findSessionData(ModuleClass targetClass, Session targetSession) {
        return findStudentModuleData(targetClass).flatMap(x -> x.findSessionData(targetSession));
    }

    /**
     * Returns a new student by adding the given {@code moduleClass} to the student's module data.
     * If the student is already enrolled in the module class, the same student is returned.
     *
     * @param moduleClass ModuleClass object to add to the student's {@code StudentModuleData}.
     * @return New student with {@code StudentModuleData} containing all existing module classes
     *     plus {@code moduleClass}.
     */
    public Student addModuleClass(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
        if (isInModuleClass(moduleClass)) {
            return this;
        }
        Student newStudent = new Student(name, phone, email, address, moduleDataList.asUnmodifiableObservableList());
        newStudent.moduleDataList.add(new StudentModuleData(moduleClass));
        return newStudent;
    }

    /**
     * Returns a new student by removing the {@code StudentModuleData}
     * of this student for the given {@code ModuleClass}.
     *
     * @param moduleClass ModuleClass object to remove from the student's {@code StudentModuleData}.
     * @return New student with {@code StudentModuleData} containing all existing module classes
     *     except {@code moduleClass}.
     */
    public Student removeModuleClass(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
        List<StudentModuleData> updatedModuleData = moduleDataList.asUnmodifiableObservableList().stream()
                .filter(data -> !data.getModuleClass().isSame(moduleClass))
                .collect(Collectors.toList());
        return new Student(name, phone, email, address, updatedModuleData);
    }

    /**
     * Returns a new student by removing this student's data for the given
     * {@code session} in {@code moduleClass}.
     *
     * @param moduleClass Module class associated with the {@code session}.
     * @param session Session to add.
     * @return New student with the modified {@code StudentModuleData}.
     */
    public Student removeSession(ModuleClass moduleClass, Session session) {
        requireAllNonNull(moduleClass, session);
        List<StudentModuleData> updatedModuleData = moduleDataList.asUnmodifiableObservableList().stream()
            .map(d -> d.getModuleClass().isSame(moduleClass) ? d.removeSession(session) : d)
            .collect(Collectors.toList());
        return new Student(name, phone, email, address, updatedModuleData);
    }

    /**
     * Returns a new student by updating this student's grade for the
     * given {@code session} in {@code moduleClass}.
     * Assumption: The student is assigned to the module class, and the session exists in the module class.
     *
     * @param moduleClass Module class associated with {@code session}.
     * @param session Session to update the grade for.
     * @param grade New grade value.
     * @return New student with the modified {@code StudentModuleData}.
     */
    public Student updateGrade(ModuleClass moduleClass, Session session, Double grade) {
        requireAllNonNull(moduleClass, session, grade);
        List<StudentModuleData> updatedModuleData = moduleDataList.asUnmodifiableObservableList().stream()
                .map(d -> d.getModuleClass().isSame(moduleClass) ? d.updateGrade(session, grade) : d)
                .collect(Collectors.toList());
        return new Student(name, phone, email, address, updatedModuleData);
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    @Override
    public boolean isSame(Student otherStudent) {
        return otherStudent == this
                || (otherStudent != null && otherStudent.getName().equals(getName()));
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
                && otherStudent.getModuleDataList().equals(getModuleDataList());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, moduleDataList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        Phone phone = getPhone();
        Email email = getEmail();
        Address address = getAddress();
        List<StudentModuleData> moduleData = getModuleDataList();

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

    @Override
    public int compareTo(Student other) {
        return name.toString().compareTo(other.name.toString());
    }

}
