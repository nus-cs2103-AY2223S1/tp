package jeryl.fyp.testutil;

import jeryl.fyp.model.FypManager;
import jeryl.fyp.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code FypManager ab = new FypManagerBuilder().withStudent("John", "Doe").build();}
 */
public class FypManagerBuilder {

    private FypManager fypManager;

    public FypManagerBuilder() {
        fypManager = new FypManager();
    }

    public FypManagerBuilder(FypManager fypManager) {
        this.fypManager = fypManager;
    }

    /**
     * Adds a new {@code Student} to the {@code FypManager} that we are building.
     */
    public FypManagerBuilder withStudent(Student student) {
        fypManager.addStudent(student);
        return this;
    }

    public FypManager build() {
        return fypManager;
    }
}
