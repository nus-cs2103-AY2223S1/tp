package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTeachersPet;
import seedu.address.model.TeachersPet;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code TeachersPet} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Phone("89705432"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("python")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Phone("93706732"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("Java", "python")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Phone("66554163"),
                    new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("Java")),
            new Student(new Name("David Li"), new Phone("91031282"), new Phone("91726932"),
                    new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("python")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Phone("84311256"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("javascript")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Phone("99703332"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("c"))
        };
    }

    public static ReadOnlyTeachersPet getSampleTeachersPet() {
        TeachersPet sampleAb = new TeachersPet();
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

}
