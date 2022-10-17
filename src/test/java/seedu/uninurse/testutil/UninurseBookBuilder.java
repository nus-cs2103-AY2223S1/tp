package seedu.uninurse.testutil;

import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.person.Patient;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code UninurseBook ab = new UninurseBookBuilder().withPerson("John", "Doe").build();}
 */
public class UninurseBookBuilder {

    private UninurseBook uninurseBook;

    public UninurseBookBuilder() {
        uninurseBook = new UninurseBook();
    }

    public UninurseBookBuilder(UninurseBook uninurseBook) {
        this.uninurseBook = uninurseBook;
    }

    /**
     * Adds a new {@code Patient} to the {@code UninurseBook} that we are building.
     */
    public UninurseBookBuilder withPerson(Patient person) {
        uninurseBook.addPerson(person);
        return this;
    }

    public UninurseBook build() {
        return uninurseBook;
    }
}
