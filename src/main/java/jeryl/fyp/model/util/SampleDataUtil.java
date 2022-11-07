package jeryl.fyp.model.util;

import static jeryl.fyp.commons.core.Datetimes.ACCEPTABLE_DATETIME_FORMATS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
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
            new Student(new StudentName("Alex Yeoh"), new StudentId("A0438807T"), new Email("alexyeoh@example.com"),
                new ProjectName("neural network"), new ProjectStatus("YTS"),
                    getDeadlineList("CS2103 TP", "15-12-2022 12:30"),
                    getTagSet("friends")),
            new Student(new StudentName("Bernice Yu"), new StudentId("A0272758C"), new Email("berniceyu@example.com"),
                new ProjectName("Decision Tree"), new ProjectStatus("IP"),
                    getDeadlineList("CS2103 IP", "2022-11-15 23:30"),
                    getTagSet("colleagues", "friends")),
            new Student(new StudentName("Charlotte Oliveiro"), new StudentId("A0210283B"),
                new Email("charlotte@example.com"), new ProjectName("genetic algorithm"), new ProjectStatus("DONE"),
                    getDeadlineList("midterm report", "2022-10-18 11:30"),
                    getTagSet("neighbours")),
            new Student(new StudentName("David Li"), new StudentId("A0131282L"), new Email("lidavid@example.com"),
                new ProjectName("Computer Networks"), new ProjectStatus("YTS"),
                    getDeadlineList("Final check", "2022-12-08 16:00"), getTagSet("family")),
            new Student(new StudentName("Irfan Ibrahim"), new StudentId("A1492021I"), new Email("irfan@example.com"),
                new ProjectName("computer graphics"), new ProjectStatus("IP"),
                    getDeadlineList("resterization", "15-11-2022 18:00"), getTagSet("classmates")),
            new Student(new StudentName("Roy Balakrishnan"), new StudentId("A1624417P"), new Email("royb@example.com"),
                new ProjectName("circuit design"), new ProjectStatus("DONE"),
                    getDeadlineList("CS2100 lab", "15-11-2022 18:00"), getTagSet("colleagues")),
            new Student(new StudentName("Zlatan Mufiz"), new StudentId("A0524417P"), new Email("zlmuf@example.com"),
                new ProjectName("Integration with Human"), new ProjectStatus("DONE"),
                    getDeadlineList("Submit supplementary", "2022/11/01 18:00"), getTagSet("hardworker")),
            new Student(new StudentName("Apple Setiawan"), new StudentId("A0293017S"), new Email("apps@example.com"),
                new ProjectName("into the future Techies"), new ProjectStatus("IP"),
                    getDeadlineList("print thesis", "13-11-2022 23:44"), getTagSet("ap", "bp")),
            new Student(new StudentName("Vivian Ahmad"), new StudentId("A0101987E"), new Email("viva@example.com"),
                new ProjectName("Circuit Design Second edition"), new ProjectStatus("IP"),
                    getDeadlineList("Make UML diagram", "31-10-2022 11:45"), getTagSet("VeryLate"))
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
        DeadlineList deadlines = new DeadlineList();
        deadlines.add(getDeadline(name, datetime));
        return deadlines;
    }

    public static Deadline getDeadline(String name, String datetime) {
        LocalDateTime parsedDatetime = null;
        for (String dateTimeFormat : ACCEPTABLE_DATETIME_FORMATS) {
            try {
                parsedDatetime = LocalDateTime.parse(datetime,
                        DateTimeFormatter.ofPattern(dateTimeFormat)
                                .withResolverStyle(ResolverStyle.STRICT));
                return new Deadline(new DeadlineName(name), parsedDatetime);
            } catch (DateTimeParseException dtpe) {
                // Go to the next dateTime format
            }
        }
        return new Deadline(new DeadlineName(name), parsedDatetime);
    }
}
