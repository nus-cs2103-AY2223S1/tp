package seedu.address.testutil;

import seedu.address.model.StudentRecord;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building StudentRecord objects.
 * Example usage: <br>
 *     {@code StudentRecord sr = new StudentRecord().withStudent("John", "Doe").build();}
 */
public class StudentRecordBuilder {

    private StudentRecord studentRecord;

    public StudentRecordBuilder() {
        studentRecord = new StudentRecord();
    }

    public StudentRecordBuilder(StudentRecord studentRecord) {
        this.studentRecord = studentRecord;
    }

    /**
     * Adds a new {@code Student} to the {@code StudentRecord} that we are building.
     */
    public StudentRecordBuilder withStudent(Student student) {
        studentRecord.addStudent(student);
        return this;
    }

    public StudentRecord build() {
        return studentRecord;
    }
}
