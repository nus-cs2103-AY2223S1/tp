package jeryl.fyp.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import jeryl.fyp.model.FypManager;
import jeryl.fyp.model.ReadOnlyFypManager;
import jeryl.fyp.model.student.Address;
import jeryl.fyp.model.student.Email;
import jeryl.fyp.model.student.Name;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentID;
import jeryl.fyp.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FypManager} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new StudentID("A1438807T"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), "neural network",
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"), new StudentID("A1272758C"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), "decision tree",
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"), new StudentID("A1210283B"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), "genetic algorithm",
                getTagSet("neighbours")),
            new Student(new Name("David Li"), new StudentID("A1031282L"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), "computer network",
                getTagSet("family")),
            new Student(new Name("Irfan Ibrahim"), new StudentID("A1492021I"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), "computer graphics",
                getTagSet("classmates")),
            new Student(new Name("Roy Balakrishnan"), new StudentID("A1624417P"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), "circuit design",
                getTagSet("colleagues"))
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
}
