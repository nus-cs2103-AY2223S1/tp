package seedu.address.testutil;

import seedu.address.model.TeachersPet;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building TeachersPet objects.
 * Example usage: <br>
 *     {@code TeachersPet ab = new TeachersPetBuilder().withStudent("John", "Doe").build();}
 */
public class TeachersPetBuilder {

    private TeachersPet teachersPet;

    public TeachersPetBuilder() {
        teachersPet = new TeachersPet();
    }

    public TeachersPetBuilder(TeachersPet teachersPet) {
        this.teachersPet = teachersPet;
    }

    /**
     * Adds a new {@code Student} to the {@code TeachersPet} that we are building.
     */
    public TeachersPetBuilder withStudent(Student student) {
        teachersPet.addStudent(student);
        return this;
    }

    public TeachersPet build() {
        return teachersPet;
    }
}
