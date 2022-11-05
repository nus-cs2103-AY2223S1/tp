package seedu.modquik.testutil;

import seedu.modquik.model.ModQuik;
import seedu.modquik.model.student.Student;

/**
 * A utility class to help with building ModQuik objects.
 * Example usage: <br>
 *     {@code ModQuik ab = new ModQuikBuilder().withPerson("John", "Doe").build();}
 */
public class ModQuikBuilder {

    private ModQuik modQuik;

    public ModQuikBuilder() {
        modQuik = new ModQuik();
    }

    public ModQuikBuilder(ModQuik modQuik) {
        this.modQuik = modQuik;
    }

    /**
     * Adds a new {@code Person} to the {@code ModQuik} that we are building.
     */
    public ModQuikBuilder withPerson(Student student) {
        modQuik.addPerson(student);
        return this;
    }

    public ModQuik build() {
        return modQuik;
    }
}
