package friday.testutil;

import friday.model.Friday;
import friday.model.student.Student;

/**
 * A utility class to help with building Friday objects.
 * Example usage: <br>
 *     {@code Friday friday = new FridayBuilder().withStudent("John", "Doe").build();}
 */
public class FridayBuilder {

    private Friday friday;

    public FridayBuilder() {
        friday = new Friday();
    }

    public FridayBuilder(Friday friday) {
        this.friday = friday;
    }

    /**
     * Adds a new {@code Student} to the {@code Friday} that we are building.
     */
    public FridayBuilder withStudent(Student student) {
        friday.addStudent(student);
        return this;
    }

    public Friday build() {
        return friday;
    }
}
