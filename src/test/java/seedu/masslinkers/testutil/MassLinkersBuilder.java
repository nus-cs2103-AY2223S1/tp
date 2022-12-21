package seedu.masslinkers.testutil;

import seedu.masslinkers.model.MassLinkers;
import seedu.masslinkers.model.student.Student;
//@@author
/**
 * A utility class to help with building massLinkers objects.
 * Example usage: <br>
 *     {@code MassLinkers ab = new MassLinkersBuilder().withStudent("John", "Doe").build();}
 */
public class MassLinkersBuilder {

    private MassLinkers massLinkers;

    public MassLinkersBuilder() {
        massLinkers = new MassLinkers();
    }

    public MassLinkersBuilder(MassLinkers massLinkers) {
        this.massLinkers = massLinkers;
    }

    /**
     * Adds a new {@code Student} to the {@code MassLinkers} that we are building.
     */
    public MassLinkersBuilder withStudent(Student student) {
        massLinkers.addStudent(student);
        return this;
    }

    public MassLinkers build() {
        return massLinkers;
    }
}
