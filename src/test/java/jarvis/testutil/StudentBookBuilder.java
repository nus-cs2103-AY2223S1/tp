package jarvis.testutil;

import jarvis.model.Student;
import jarvis.model.StudentBook;

/**
 * A utility class to help with building StudentBook objects.
 * Example usage: <br>
 *     {@code StudentBook sb = new StudentBookBuilder().withStudent("John", "Doe").build();}
 */
public class StudentBookBuilder {

    private StudentBook studentBook;

    public StudentBookBuilder() {
        studentBook = new StudentBook();
    }

    public StudentBookBuilder(StudentBook studentBook) {
        this.studentBook = studentBook;
    }

    /**
     * Adds a new {@code Student} to the {@code StudentBook} that we are building.
     */
    public StudentBookBuilder withPerson(Student student) {
        studentBook.addStudent(student);
        return this;
    }

    public StudentBook build() {
        return studentBook;
    }
}
