package seedu.uninurse.testutil;

import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;

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
     * Adds a new Person to the UninurseBook that we are building.
     */
    public UninurseBookBuilder withPerson(Person person) {
        uninurseBook.addPerson(person);
        return this;
    }

    /**
     * Adds a new Person to the UninurseBook that we are building.
     */
    public UninurseBookBuilder withPatient(Patient patient) {
        uninurseBook.addPatient(patient);
        return this;
    }

    public UninurseBook build() {
        return uninurseBook;
    }
}
