package jarvis.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import jarvis.model.AddressBook;
import jarvis.model.ReadOnlyAddressBook;
import jarvis.model.student.*;
import jarvis.model.student.Student;
import jarvis.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new Name("Alex Yeoh")),
            new Student(new Name("Bernice Yu")),
            new Student(new Name("Charlotte Oliveiro")),
            new Student(new Name("David Li")),
            new Student(new Name("Irfan Ibrahim")),
            new Student(new Name("Roy Balakrishnan"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
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

}
