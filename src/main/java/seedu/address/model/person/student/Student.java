package seedu.address.model.person.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.nextofkin.NextOfKin;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.model.tuitionclass.exceptions.DuplicateTuitionClassException;
import seedu.address.model.tuitionclass.exceptions.TuitionClassNotAssignedException;

/**
 * Represents a Student in the address book.
 */
public class Student extends Person {

    private static int id = 0;
    private final School school;
    private final Level level;
    private NextOfKin nextOfKin;
    private final List<TuitionClass> tuitionClasses = new ArrayList<>();
    private final String uniqueId;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, School school, Level level,
                   NextOfKin nextOfKin) {
        super(name, phone, email, address, tags);
        requireAllNonNull(school, level);
        id++;
        this.school = school;
        this.level = level;
        this.nextOfKin = nextOfKin;

        LocalTime timeAdded = LocalTime.now();
        this.uniqueId = timeAdded + "student";
    }


    /**
     * Every field must be present and not null.
     * Should not be used in AddCommand as tuition classes
     * are not added in AddCommand.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, School school, Level level,
                   NextOfKin nextOfKin, List<TuitionClass> tuitionClasses) {
        super(name, phone, email, address, tags);
        requireAllNonNull(school, level);
        id++;
        this.school = school;
        this.level = level;
        this.nextOfKin = nextOfKin;
        this.tuitionClasses.addAll(tuitionClasses);

        LocalTime timeAdded = LocalTime.now();
        this.uniqueId = timeAdded + "student";
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

    public List<TuitionClass> getTuitionClasses() {
        return tuitionClasses;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void minusId() {
        id--;
    }

    /**
     * Add {@code tuitionClass} to the list of classes that Student is currently enrolled to.
     */
    public void assignClassToStudent(TuitionClass tuitionClass) {
        if (tuitionClasses.stream().anyMatch(tuitionClass::isSameTuitionClass)) {
            throw new DuplicateTuitionClassException();
        }
        tuitionClasses.add(tuitionClass);
    }

    /**
     * Remove {@code tuitionClass} from the list of classes that Student is currently enrolled to.
     */
    public void unassignClassFromStudent(TuitionClass tuitionClass) {
        if (tuitionClasses.stream().noneMatch(tuitionClass::isSameTuitionClass)) {
            throw new TuitionClassNotAssignedException();
        }
        tuitionClasses.remove(tuitionClass);
    }

    /**
     * Add {@code nextOfKin} to student.
     */
    public void addNextOfKin(NextOfKin nextOfKin) throws CommandException {
        requireAllNonNull(nextOfKin);
        if (this.nextOfKin != null && this.nextOfKin.equals(nextOfKin)) {
            throw new CommandException("trying to assign same nextOfKin");
        }
        this.nextOfKin = nextOfKin;
    }

    public void removeNextOfKin() {
        this.nextOfKin = null;
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
                && otherStudent.getTuitionClasses().equals(getTuitionClasses());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(), this.getTags(),
                school, level, nextOfKin, tuitionClasses);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(super.toString())
                .append("; School: ")
                .append(getSchool())
                .append("; Level: ")
                .append(getLevel());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
