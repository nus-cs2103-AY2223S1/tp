package seedu.address.testutil;

import seedu.address.model.PersonBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building PersonBook objects.
 */
public class PersonBookBuilder {

    private PersonBook personBook;

    public PersonBookBuilder() {
        personBook = new PersonBook();
    }

    public PersonBookBuilder(PersonBook personBook) {
        this.personBook = personBook;
    }

    /**
     * Adds a new {@code Person} to the {@code PersonBook} that we are building.
     */
    public PersonBookBuilder withPerson(Person person) {
        personBook.addPerson(person);
        return this;
    }

    public PersonBook build() {
        return personBook;
    }
}
