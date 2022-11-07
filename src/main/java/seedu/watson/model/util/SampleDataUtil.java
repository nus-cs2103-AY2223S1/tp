package seedu.watson.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.watson.model.Database;
import seedu.watson.model.ReadOnlyDatabase;
import seedu.watson.model.student.Address;
import seedu.watson.model.student.Attendance;
import seedu.watson.model.student.Email;
import seedu.watson.model.student.Name;
import seedu.watson.model.student.Phone;
import seedu.watson.model.student.Remark;
import seedu.watson.model.student.Student;
import seedu.watson.model.student.StudentClass;
import seedu.watson.model.student.subject.SubjectHandler;
import seedu.watson.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Database} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[]{
            new Student(new Name("Alex Yeoh"), new Phone("87438807"),
                       new Email("alexyeoh@example.com"),
                       new Address("Blk 30 Geylang Street 29, #06-40"), new StudentClass("1.2"),
                       new Attendance(), getRemarkSet("Good at Math"),
                       new SubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, "
                               + "50.0, 0.4, 2.0]%%math: CA1:[81.0, 100.0, 0.2, 1.0],"
                                          + "CA2:[44.0, 50.0, 0.4, 2.0]"),
                       getTagSet("Student Council")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"),
                       new Email("berniceyu@example.com"),
                       new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new StudentClass("2.1"),
                       new Attendance(), getRemarkSet("Friendly"),
                       new SubjectHandler("english: CA1:[75.0, 100.0, 0.2, 1.0], CA2:[36.0, "
                               + "50.0, 0.4, 2.0]%%math: CA1:[66.0, 100.0, 0.2, 1.0],"
                                          + "CA2:[40.0, 50.0, 0.4, 2.0]"),
                       getTagSet("Student Council", "Dean's List")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                       new Email("charlotte@example.com"),
                       new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new StudentClass("3.6"),
                       new Attendance(), getRemarkSet(),
                       new SubjectHandler("english: CA1:[92.0, 100.0, 0.2, 1.0], CA2:[32.0, "
                               + "50.0, 0.4, 2.0]%%math: CA1:[86.0, 100.0, 0.2, 1.0],"
                                          + "CA2:[31.0, 50.0, 0.4, 2.0]"), new HashSet<>()),
            new Student(new Name("David Li"), new Phone("91031282"),
                       new Email("lidavid@example.com"),
                       new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new StudentClass("4.2"),
                       new Attendance(), getRemarkSet("Good at English"),
                       new SubjectHandler("english: CA1:[60.0, 100.0, 0.2, 1.0], CA2:[25.0, "
                               + "50.0, 0.4, 2.0]%%math: CA1:[90.0, 100.0, 0.2, 1.0],"
                                          + "CA2:[31.0, 50.0, 0.4, 2.0]"), new HashSet<>()),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"),
                       new Email("irfan@example.com"),
                       new Address("Blk 47 Tampines Street 20, #17-35"), new StudentClass("2.6"),
                       new Attendance(), getRemarkSet("Introverted"),
                       new SubjectHandler("english: CA1:[55.0, 100.0, 0.2, 1.0], CA2:[50.0, "
                               + "50.0, 0.4, 2.0]%%math: CA1:[76.0, 100.0, 0.2, 1.0],"
                                          + "CA2:[20.0, 50.0, 0.4, 2.0]"),
                       getTagSet("Dean's List", "Financial Aid")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"),
                       new Email("royb@example.com"),
                       new Address("Blk 45 Aljunied Street 85, #11-31"), new StudentClass("3.10"),
                       new Attendance(), getRemarkSet(),
                       new SubjectHandler("english: CA1:[88.0, 100.0, 0.2, 1.0], CA2:[38.0, "
                               + "50.0, 0.4, 2.0]%%math: CA1:[91.0, 100.0, 0.2, 1.0],"
                                          + "CA2:[40.0, 50.0, 0.4, 2.0]"), new HashSet<>())
        };
    }

    public static ReadOnlyDatabase getSampleAddressBook() {
        Database sampleAb = new Database();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addPerson(sampleStudent);
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
     * Returns a remark set containing the list of strings given.
     */
    public static Set<Remark> getRemarkSet(String... strings) {
        return Arrays.stream(strings)
                     .map(Remark::new)
                     .collect(Collectors.toSet());
    }
}
