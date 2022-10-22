package jeryl.fyp.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import jeryl.fyp.model.FypManager;
import jeryl.fyp.model.ReadOnlyFypManager;
import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.DeadlineList;
import jeryl.fyp.model.student.DeadlineName;
import jeryl.fyp.model.student.Email;
import jeryl.fyp.model.student.ProjectName;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.student.StudentName;
import jeryl.fyp.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FypManager} with sample data.
 */
public class SampleDataUtil {

    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new StudentName("Alex Yeoh"), new StudentId("A1438807T"), new Email("alexyeoh@example.com"),
                new ProjectName("neural network"), new ProjectStatus("YTS"),
                    getDeadlineList("CS2103 TP", "2022-12-15 12:30"),
                    getTagSet("friends")),
            new Student(new StudentName("Bernice Yu"), new StudentId("A1272758C"), new Email("berniceyu@example.com"),
                new ProjectName("Decision Tree"), new ProjectStatus("IP"),
                    getDeadlineList("CS2103 IP", "2022-11-15 23:30"),
                    getTagSet("colleagues", "friends")),
            new Student(new StudentName("Charlotte Oliveiro"), new StudentId("A1210283B"),
                new Email("charlotte@example.com"), new ProjectName("genetic algorithm"), new ProjectStatus("DONE"),
                    getDeadlineList("midterm report", "2022-10-18 11:30"),
                    getTagSet("neighbours")),
            new Student(new StudentName("David Li"), new StudentId("A1031282L"), new Email("lidavid@example.com"),
                new ProjectName("Computer Networks"), new ProjectStatus("YTS"),
                    getDeadlineList("Final check", "2022-12-08 16:00"), getTagSet("family")),
            new Student(new StudentName("Irfan Ibrahim"), new StudentId("A1492021I"), new Email("irfan@example.com"),
                new ProjectName("computer graphics"), new ProjectStatus("IP"),
                    getDeadlineList("resterization", "2022-11-15 18:00"), getTagSet("classmates")),
            new Student(new StudentName("Roy Balakrishnan"), new StudentId("A1624417P"), new Email("royb@example.com"),
                new ProjectName("circuit design"), new ProjectStatus("DONE"),
                    getDeadlineList("CS2100 lab", "2022-11-15 18:00"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyFypManager getSampleFypManager() {
        FypManager sampleAb = new FypManager();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
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

    public static DeadlineList getDeadlineList(String name, String datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DeadlineList deadlines = new DeadlineList();
        new DeadlineList().add(new Deadline(new DeadlineName(name), LocalDateTime.parse(datetime, formatter)));
        return deadlines;
    }

    public static Deadline getDeadline(String name, String datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new Deadline(new DeadlineName(name), LocalDateTime.parse(datetime, formatter));
    }
}
