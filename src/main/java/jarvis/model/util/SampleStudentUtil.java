package jarvis.model.util;

import jarvis.model.MasteryCheckStatus;
import jarvis.model.MatricNum;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.Student;
import jarvis.model.StudentBook;
import jarvis.model.StudentName;

/**
 * Contains utility methods for populating {@code StudentBook} with sample data.
 */
public class SampleStudentUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new StudentName("Alex Yeoh"), new MatricNum("A0123456A"), MasteryCheckStatus.getDefault()),
            new Student(new StudentName("Bernice Yu"), new MatricNum("A0000000D"), MasteryCheckStatus.getDefault()),
            new Student(new StudentName("Charlotte Oliveiro"), new MatricNum("A4325833C"),
                    MasteryCheckStatus.getDefault()),
            new Student(new StudentName("David Li"), new MatricNum("A8353285P"), MasteryCheckStatus.getDefault()),
            new Student(new StudentName("Irfan Ibrahim"), new MatricNum("A2833958J"), MasteryCheckStatus.getDefault()),
            new Student(new StudentName("Roy Balakrishnan"), new MatricNum("A0033458L"),
                    MasteryCheckStatus.getDefault())
        };
    }

    public static ReadOnlyStudentBook getSampleStudentBook() {
        StudentBook sampleStudentBook = new StudentBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleStudentBook.addStudent(sampleStudent);
        }
        return sampleStudentBook;
    }
}
