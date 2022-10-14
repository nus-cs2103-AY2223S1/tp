package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.AttendanceList;
import seedu.address.model.person.Duration;
import seedu.address.model.person.DurationList;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.GradeProgressList;
import seedu.address.model.person.Homework;
import seedu.address.model.person.HomeworkList;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new LessonPlan("Sec 4 Biology"),
                        new HomeworkList(), new AttendanceList(),
                        new DurationList(),
                        new GradeProgressList(), getTagSet("Science", "Math")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new LessonPlan("Focus on trigo"),
                        new HomeworkList(), new AttendanceList(), new DurationList(),
                        new GradeProgressList(), getTagSet()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new LessonPlan("Java and C#"), new HomeworkList(),
                        new AttendanceList(), new DurationList(),
                        new GradeProgressList(),
                        getTagSet("Coding")),
            new Person(new Name("David Li"), new Phone("91031282"), new LessonPlan("Sec 3 biology"),
                        new HomeworkList(), new AttendanceList(), new DurationList(),
                        new GradeProgressList(), getTagSet()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new LessonPlan("Math"),
                        new HomeworkList(), new AttendanceList(), new DurationList(),
                        new GradeProgressList(),
                        getTagSet()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new LessonPlan("Test papers"), new HomeworkList(), new AttendanceList(),
                        new DurationList(),
                        new GradeProgressList(), getTagSet())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static List<Homework> getHomeworkList(String... strings) {
        return Arrays.stream(strings)
                .map(Homework::new)
                .collect(Collectors.toList());
    }

    public static List<Attendance> getAttendanceList(String... strings) {
        return Arrays.stream(strings)
                .map(Attendance::new)
                .collect(Collectors.toList());
    }

    public static List<Duration> getDurationList(String... strings) {
        return Arrays.stream(strings)
                .map(Duration::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static List<GradeProgress> getGradeProgressList(String... strings) {
        return Arrays.stream(strings)
                .map(GradeProgress::new)
                .collect(Collectors.toList());
    }
}
