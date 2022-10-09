package jarvis.model.util;

import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.Student;
import jarvis.model.StudentBook;
import jarvis.model.StudentName;

/**
 * Contains utility methods for populating {@code StudentBook} with sample data.
 */
public class SampleStudentUtil {
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

    public static ReadOnlyStudentBook getSampleStudentBook() {
        StudentBook sampleStudentBook = new StudentBook();
        for (Student sampleStudent : getSamplePersons()) {
            sampleStudentBook.addStudent(sampleStudent);
        }
        return sampleStudentBook;
    }
}
