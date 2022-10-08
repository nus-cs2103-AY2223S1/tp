package jarvis.model.util;

import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.StudentBook;
import jarvis.model.StudentName;
import jarvis.model.Student;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new StudentName("Alex Yeoh")),
            new Student(new StudentName("Bernice Yu")),
            new Student(new StudentName("Charlotte Oliveiro")),
            new Student(new StudentName("David Li")),
            new Student(new StudentName("Irfan Ibrahim")),
            new Student(new StudentName("Roy Balakrishnan"))
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
