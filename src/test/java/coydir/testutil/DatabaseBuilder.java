package coydir.testutil;

import coydir.model.Database;
import coydir.model.person.Person;

/**
 * A utility class to help with building database objects.
 * Example usage: <br>
 *     {@code Database ab = new DatabaseBuilder().withPerson("John", "Doe").build();}
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
     * Adds a new {@code Person} to the {@code database} that we are building.
     */
    public DatabaseBuilder withPerson(Person person) {
        database.addPerson(person);
        return this;
    }

    public Database build() {
        return database;
    }
}
