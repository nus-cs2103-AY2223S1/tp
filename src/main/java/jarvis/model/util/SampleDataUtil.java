package jarvis.model.util;

import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.StudentBook;
import jarvis.model.student.Name;
import jarvis.model.student.Student;

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

    public static ReadOnlyStudentBook getSampleAddressBook() {
        StudentBook sampleAb = new StudentBook();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }
}
