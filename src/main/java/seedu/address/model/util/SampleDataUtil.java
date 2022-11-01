package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.TaskBook;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.AttendanceList;
import seedu.address.model.student.ClassGroup;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Assignment;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.FormatDate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;
import seedu.address.model.task.ToDo;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final ClassGroup EMPTY_CLASS_GROUP = new ClassGroup("");
    public static final AttendanceList EMPTY_ATTENDANCE = new AttendanceList();
    public static AttendanceList filledAttendance = new AttendanceList("CS2030S", "10");

    public static Student[] getSampleStudents() {
        filledAttendance.mark("1", new Attendance("1"));
        filledAttendance.mark("2", new Attendance("0"));
        filledAttendance.mark("3", new Attendance("1"));
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new ClassGroup("CS2030S Lab 32"), new StudentId("e0712345"),
                    getTagSet("punctual"), filledAttendance),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new ClassGroup("CS2030S Lab 32"), new StudentId("e0123456"),
                    getTagSet("quiet", "experienced"), EMPTY_ATTENDANCE),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new ClassGroup("CS2040S Tutorial 3"), new StudentId("e0132456"),
                    getTagSet("hardworking"), filledAttendance),
            new Student(new Name("David Li"), new Phone("NA"), new Email("lidavid@example.com"),
                new ClassGroup("CS2040S Tutorial 3"), new StudentId("e0987123"),
                    getTagSet("family"), filledAttendance),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("NA"),
                new ClassGroup("CS2030S Lab 32"), new StudentId("e0984750"),
                    getTagSet("struggling"), EMPTY_ATTENDANCE),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new ClassGroup("CS2040S Tutorial 3"), new StudentId("e0453933"),
                getTagSet("junior"), EMPTY_ATTENDANCE)
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Deadline(
                    new TaskTitle("Grade assignments"),
                    new TaskDescription("Complete by tonight"),
                    new FormatDate("2022-10-23")),
            new ToDo(
                    new TaskTitle("Prepare slides for studio"),
                    new TaskDescription("Topic Environment Model")),
            new Assignment(
                    new TaskTitle("Assignment 2"),
                    new TaskDescription("Essence of Recursion"),
                    Arrays.asList("Alex Yeoh", "Bernice Yu", "Irfan Ibrahim", "Roy Balakrishnan")),
            new ToDo(
                    new TaskTitle("Collect robot"),
                    new TaskDescription("At MakersLab")),
            new Deadline(
                    new TaskTitle("Go through tutorial sheet"),
                    new TaskDescription("By this friday"),
                    new FormatDate("2022-10-21"))
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
