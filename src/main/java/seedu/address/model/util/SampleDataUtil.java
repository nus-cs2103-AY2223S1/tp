package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.TaskBook;
import seedu.address.model.attendance.AttendanceList;
import seedu.address.model.student.ClassGroup;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final ClassGroup EMPTY_CLASS_GROUP = new ClassGroup("");
    public static final AttendanceList EMPTY_ATTENDANCE = new AttendanceList();

    public static Student[] getSampleStudents() {

        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new ClassGroup("CS2030S Lab 32"), new StudentId("e0712345"),
                    getTagSet("friends"), EMPTY_ATTENDANCE),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new ClassGroup("CS2030S Lab 32"), new StudentId("e0123456"),
                    getTagSet("colleagues", "friends"), EMPTY_ATTENDANCE),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new ClassGroup("CS2040S Tutorial 3"), new StudentId("e0132456"),
                    getTagSet("neighbours"), EMPTY_ATTENDANCE),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new ClassGroup("CS2040S Tutorial 3"), new StudentId("e0987123"),
                    getTagSet("family"), EMPTY_ATTENDANCE),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new ClassGroup("CS2030S Lab 32"), new StudentId("e0984750"),
                    getTagSet("classmates"), EMPTY_ATTENDANCE),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new ClassGroup("CS2040S Tutorial 3"), new StudentId("e0453933"),
                getTagSet("colleagues"), EMPTY_ATTENDANCE)
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new TaskTitle("Grade assignments"), new TaskDescription("Complete by tonight")),
            new Task(new TaskTitle("Prepare slides for studio"), new TaskDescription("Topic Environment Model")),
            new Task(new TaskTitle("Review CS1101S topics with Adam"), new TaskDescription("Essence of Recursion")),
            new Task(new TaskTitle("Collect robot"), new TaskDescription("At MakersLab")),
            new Task(new TaskTitle("Go through tutorial sheet"), new TaskDescription("By this friday"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    public static ReadOnlyTaskBook getSampleTaskBook() {
        TaskBook sampleTb = new TaskBook();
        for (Task sampleTask : getSampleTasks()) {
            sampleTb.addTask(sampleTask);
        }
        return sampleTb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
