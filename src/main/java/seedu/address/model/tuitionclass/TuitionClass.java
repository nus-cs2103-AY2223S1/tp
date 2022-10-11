package seedu.address.model.tuitionclass;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.level.Level;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tag.Tag;

/**
 * Represents a TuitionClass in the address book.
 */
public class TuitionClass {

    private final Name name;
    private final Subject subject;
    private final Level level;
    private final Day day;
    private final Time time;
    private final Set<Tag> tags = new HashSet<>();
    private final List<Student> students = new ArrayList<>();
    private final List<Tutor> tutors = new ArrayList<>();


    /**
     * Every field must be present and not null.
     */
    public TuitionClass(Name name, Subject subject, Level level, Day day, Time time, Set<Tag> tags,
                        List<Student> students, List<Tutor> tutors) {
        requireAllNonNull(name, subject, day, time, tags);
        this.name = name;
        this.subject = subject;
        this.level = level;
        this.day = day;
        this.time = time;
        this.tags.addAll(tags);
        this.students.addAll(students);
        this.tutors.addAll(tutors);
    }

    public Name getName() {
        return name;
    }

    public Subject getSubject() {
        return subject;
    }

    public Level getLevel() {
        return level;
    }

    public Day getDay() {
        return day;
    }

    public Time getTime() {
        return time;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both tuition classes have the same name.
     * This defines a weaker notion of equality between two tuition classes.
     */
    public boolean isSameTuitionClass(TuitionClass otherClass) {
        if (otherClass == this) {
            return true;
        }

        return otherClass != null
                && otherClass.getName().equals(getName());
    }

    /**
     * Returns true if both tuition classes have the same data fields.
     * This defines a stronger notion of equality between two tuition classes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TuitionClass)) {
            return false;
        }

        TuitionClass otherClass = (TuitionClass) other;
        return otherClass.getName().equals(getName())
                && otherClass.getSubject().equals(getSubject())
                && otherClass.getLevel().equals(getLevel())
                && otherClass.getDay().equals(getDay())
                && otherClass.getTime().equals(getTime())
                && otherClass.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, subject, level, day, time);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Subject: ")
                .append(getSubject())
                .append("; Level: ")
                .append(getLevel())
                .append("; Day: ")
                .append(getDay())
                .append("; Time: ")
                .append(getTime());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
