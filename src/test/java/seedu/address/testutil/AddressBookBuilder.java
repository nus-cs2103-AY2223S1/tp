package seedu.address.testutil;

import seedu.address.model.Database;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code Database ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Database database;

    public AddressBookBuilder() {
        database = new Database();
    }

    public AddressBookBuilder(Database database) {
        this.database = database;
    }

    /**
     * Adds a new {@code Person} to the {@code Database} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        database.addPerson(person);
        return this;
    }

    public Database build() {
        return database;
    }
}
