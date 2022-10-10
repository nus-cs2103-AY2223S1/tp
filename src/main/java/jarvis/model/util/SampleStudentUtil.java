package jarvis.model.util;

import jarvis.model.MasteryCheckStatus;
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
            new Student(new StudentName("Alex Yeoh"), MasteryCheckStatus.getDefault()),
            new Student(new StudentName("Bernice Yu"), MasteryCheckStatus.getDefault()),
            new Student(new StudentName("Charlotte Oliveiro"), MasteryCheckStatus.getDefault()),
            new Student(new StudentName("David Li"), MasteryCheckStatus.getDefault()),
            new Student(new StudentName("Irfan Ibrahim"), MasteryCheckStatus.getDefault()),
            new Student(new StudentName("Roy Balakrishnan"), MasteryCheckStatus.getDefault())
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
