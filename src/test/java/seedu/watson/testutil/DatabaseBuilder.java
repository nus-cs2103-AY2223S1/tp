package seedu.watson.testutil;

import seedu.watson.model.Database;
import seedu.watson.model.student.Student;

/**
 * A utility class to help with building Database objects.
 * Example usage: <br>
 * {@code Database ab = new DatabaseBuilder().withPerson("John", "Doe").build();}
 */
public class DatabaseBuilder {

    private Database database;

    public DatabaseBuilder() {
        database = new Database();
    }

    public DatabaseBuilder(Database database) {
        this.database = database;
    }

    /**
     * Adds a new {@code Student} to the {@code Database} that we are building.
     */
    public DatabaseBuilder withPerson(Student student) {
        database.addPerson(student);
        return this;
    }

    public Database build() {
        return database;
    }
}
