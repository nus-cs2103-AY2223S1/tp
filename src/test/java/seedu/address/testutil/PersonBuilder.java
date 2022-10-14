package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.AttendanceList;
import seedu.address.model.person.DurationList;
import seedu.address.model.person.GradeProgressList;
import seedu.address.model.person.HomeworkList;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_LESSON_PLAN = "Data structures";

    private Name name;
    private Phone phone;
    private LessonPlan lessonPlan;
    private HomeworkList homeworkList;
    private AttendanceList attendanceList;
    private DurationList durationList;
    private GradeProgressList gradeProgressList;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        lessonPlan = new LessonPlan(DEFAULT_LESSON_PLAN);
        homeworkList = new HomeworkList();
        attendanceList = new AttendanceList();
        durationList = new DurationList();
        gradeProgressList = new GradeProgressList();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        lessonPlan = personToCopy.getLessonPlan();
        homeworkList = new HomeworkList(new ArrayList<>(personToCopy.getHomeworkList().homeworkList));
        attendanceList = new AttendanceList(new ArrayList<>(personToCopy.getAttendanceList().attendanceList));
        durationList = new DurationList(new ArrayList<>(personToCopy.getDurationList().durationList));
        gradeProgressList = new GradeProgressList(new ArrayList<>(personToCopy.getGradeProgressList()
                .gradeProgressList));
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code homeworkList} into a {@code List<Homework>} and set it to the {@code Person}
     * that we are building.
     */
    public PersonBuilder withHomework(String... homeworkList) {
        this.homeworkList = new HomeworkList(SampleDataUtil.getHomeworkList(homeworkList));
        return this;
    }

    /**
     * Parses the {@code AttendanceList} into a {@code List<Attendance} and set it to the {@code Person}
     * that we are building.
     */
    public PersonBuilder withAttendance(String... attendanceList) {
        this.attendanceList = new AttendanceList(SampleDataUtil.getAttendanceList(attendanceList));
        return this;
    }

    public PersonBuilder withDuration(String... durationList) {
        this.durationList = new DurationList(SampleDataUtil.getDurationList(durationList));
        return this;
    }

    /**
     * Parses the {@code homeworkList} into a {@code List<Homework>} and set it to the {@code Person}
     * that we are building.
     */
    public PersonBuilder withGradeProgress(String... gradeProgress) {
        this.gradeProgressList = new GradeProgressList(SampleDataUtil.getGradeProgressList(gradeProgress));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code LessonPlan} of the {@code Person} that we are building.
     */
    public PersonBuilder withLessonPlan(String lessonPlan) {
        this.lessonPlan = new LessonPlan(lessonPlan);
        return this;
    }

    public Person build() {
        return new Person(name, phone, lessonPlan, homeworkList, attendanceList, durationList, gradeProgressList, tags);
    }

}
