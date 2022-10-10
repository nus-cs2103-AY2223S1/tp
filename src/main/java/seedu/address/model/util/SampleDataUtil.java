package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyStudentRecord;
import seedu.address.model.StudentRecord;
import seedu.address.model.student.Address;
import seedu.address.model.student.Class;
import seedu.address.model.student.Id;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code StudentRecord} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[]{
            new Student(new Name("Alex Yeoh"), new Id("123A"), new Class("1A"), new Name("Bernice Yu"), new Phone("99272758"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getTagSet("siblings")),
            new Student(new Name("David Yeoh"), new Id("456B"), new Class("1A"), new Name("Bernice Yu"), new Phone("87438807"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getTagSet("siblings")),
            new Student(new Name("Charlotte Oliveiro"), new Id("123A"), new Class("2A"), new Name("Bernice Yu"), new Phone("93210283"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagSet("peanutallergy")),
            new Student(new Name("Irfan Ibrahim"), new Id("189A"), new Class("3A"), new Name("Roy Balakrishnan"), new Phone("91031282"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("shy")),
        };
    }

    public static ReadOnlyStudentRecord getSampleStudentRecord() {
        StudentRecord sampleSr = new StudentRecord();
        for (Student sampleStudent : getSampleStudents()) {
            sampleSr.addStudent(sampleStudent);
        }
        return sampleSr;
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
