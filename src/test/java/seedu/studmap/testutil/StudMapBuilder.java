package seedu.studmap.testutil;

import seedu.studmap.model.StudMap;
import seedu.studmap.model.student.Student;

/**
 * A utility class to help with building StudMap objects.
 * Example usage: <br>
 *     {@code StudMap sm = new StudMapBuilder().withStudent("John", "Doe").build();}
 */
public class StudMapBuilder {

    private StudMap studMap;

    public StudMapBuilder() {
        studMap = new StudMap();
    }

    public StudMapBuilder(StudMap studMap) {
        this.studMap = studMap;
    }

    /**
     * Adds a new {@code Student} to the {@code StudMap} that we are building.
     */
    public StudMapBuilder withStudent(Student student) {
        studMap.addStudent(student);
        return this;
    }

    public StudMap build() {
        return studMap;
    }
}
