package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.level.Level;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.Day;
import seedu.address.model.tuitionclass.Name;
import seedu.address.model.tuitionclass.Subject;
import seedu.address.model.tuitionclass.Time;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building TuitionClass objects.
 */
public class TuitionClassBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_SUBJECT = "MATHEMATICS";
    public static final String DEFAULT_LEVEL = "SECONDARY4";
    public static final String DEFAULT_DAY = "MONDAY";
    public static final String DEFAULT_START_TIME = "10:00";
    public static final String DEFAULT_END_TIME = "12:00";

    private Name name;
    private Subject subject;
    private Level level;
    private Day day;
    private Time time;
    private Set<Tag> tags;
    private List<Student> students;
    private List<Tutor> tutors;

    /**
     * Creates a {@code TuitionClassBuilder} with the default details.
     */
    public TuitionClassBuilder() {
        name = new Name(DEFAULT_NAME);
        subject = Subject.createSubject(DEFAULT_SUBJECT);
        level = Level.createLevel(DEFAULT_LEVEL);
        day = Day.createDay(DEFAULT_DAY);
        time = new Time(DEFAULT_START_TIME, DEFAULT_END_TIME);
        tags = new HashSet<>();
        students = new ArrayList<>();
        tutors = new ArrayList<>();
    }

    /**
     * Initializes the TuitionClassBuilder with the data of {@code tuitionClassToCopy}.
     */
    public TuitionClassBuilder(TuitionClass tuitionClassToCopy) {
        name = tuitionClassToCopy.getName();
        subject = tuitionClassToCopy.getSubject();
        level = tuitionClassToCopy.getLevel();
        day = tuitionClassToCopy.getDay();
        time = tuitionClassToCopy.getTime();
        tags = new HashSet<>(tuitionClassToCopy.getTags());
        students = tuitionClassToCopy.getStudents();
        tutors = tuitionClassToCopy.getTutors();
    }

    /**
     * Sets the {@code Name} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Subject} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withSubject(String subject) {
        this.subject = Subject.createSubject(subject);
        return this;
    }

    /**
     * Sets the {@code Level} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withLevel(String level) {
        this.level = Level.createLevel(level);
        return this;
    }

    /**
     * Sets the {@code Day} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withDay(String day) {
        this.day = Day.createDay(day);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withTime(String startTime, String endTime) {
        this.time = new Time(startTime, endTime);
        return this;
    }

    /**
     * Sets the {@code Students} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withStudents(Student ... stu) {
        this.students = new ArrayList<>(Arrays.asList(stu));
        return this;
    }

    /**
     * Sets the {@code Tutors} of the {@code TuitionClass} that we are building.
     */
    public TuitionClassBuilder withTutors(Tutor ... tut) {
        this.tutors = new ArrayList<>(Arrays.asList(tut));
        return this;
    }

    public TuitionClass build() {
        return new TuitionClass(name, subject, level, day, time, tags, students, tutors);
    }
}
