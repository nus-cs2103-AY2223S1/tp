package jarvis.testutil;

import jarvis.model.MasteryCheckStatus;
import jarvis.model.Student;
import jarvis.model.StudentName;

/**
 * A utility class to help with building Person objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";

    private StudentName studentName;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public StudentBuilder() {
        studentName = new StudentName(DEFAULT_NAME);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        studentName = studentToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.studentName = new StudentName(name);
        return this;
    }


    public Student build() {
        return new Student(studentName, MasteryCheckStatus.getDefault());
    }

}
