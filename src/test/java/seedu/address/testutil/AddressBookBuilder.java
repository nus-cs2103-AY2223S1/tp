package seedu.address.testutil;

import seedu.address.model.ProfNus;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ProfNus ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ProfNus profNus;

    public AddressBookBuilder() {
        profNus = new ProfNus();
    }

    public AddressBookBuilder(ProfNus profNus) {
        this.profNus = profNus;
    }

    /**
     * Adds a new {@code Person} to the {@code ProfNus} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        profNus.addPerson(person);
        return this;
    }

    public ProfNus build() {
        return profNus;
    }
}
