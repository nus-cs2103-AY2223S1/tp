package seedu.taassist.testutil;

import seedu.taassist.model.TaAssist;
import seedu.taassist.model.student.Student;

/**
 * A utility class to help with building TaAssist objects.
 * Example usage: <br>
 *     {@code TaAssist ab = new TaAssistBuilder().withStudent("John", "Doe").build();}
 */
public class TaAssistBuilder {

    private TaAssist taAssist;

    public TaAssistBuilder() {
        taAssist = new TaAssist();
    }

    public TaAssistBuilder(TaAssist taAssist) {
        this.taAssist = taAssist;
    }

    /**
     * Adds a new {@code Student} to the {@code TaAssist} that we are building.
     */
    public TaAssistBuilder withStudent(Student student) {
        taAssist.addStudent(student);
        return this;
    }

    public TaAssist build() {
        return taAssist;
    }
}
