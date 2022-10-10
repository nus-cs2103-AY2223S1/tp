package seedu.address.model.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), Optional.ofNullable(new Phone("87438807")),
                    Optional.ofNullable(new Email("alexyeoh@example.com")),
                    Optional.ofNullable(new Address("Blk 30 Geylang Street 29, #06-40")),
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"), Optional.ofNullable(new Phone("99272758")),
                    Optional.ofNullable(new Email("berniceyu@example.com")),
                    Optional.ofNullable(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"), Optional.ofNullable(new Phone("93210283")),
                    Optional.ofNullable(new Email("charlotte@example.com")),
                    Optional.ofNullable(new Address("Blk 11 Ang Mo Kio Street 74, #11-04")),
                getTagSet("neighbours")),
            new Student(new Name("David Li"), Optional.ofNullable(new Phone("91031282")),
                    Optional.ofNullable(new Email("lidavid@example.com")),
                    Optional.ofNullable(new Address("Blk 436 Serangoon Gardens Street 26, #16-43")),
                getTagSet("family")),
            new Student(new Name("Irfan Ibrahim"), Optional.ofNullable(new Phone("92492021")),
                    Optional.ofNullable(new Email("irfan@example.com")),
                    Optional.ofNullable(new Address("Blk 47 Tampines Street 20, #17-35")),
                getTagSet("classmates")),
            new Student(new Name("Roy Balakrishnan"), Optional.ofNullable(new Phone("92624417")),
                    Optional.ofNullable(new Email("royb@example.com")),
                    Optional.ofNullable(new Address("Blk 45 Aljunied Street 85, #11-31")),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
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
