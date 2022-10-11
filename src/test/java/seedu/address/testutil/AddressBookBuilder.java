package seedu.address.testutil;

import seedu.address.model.PersonModel;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code PersonModel ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private PersonModel personModel;

    public AddressBookBuilder() {
        personModel = new PersonModel();
    }

    public AddressBookBuilder(PersonModel personModel) {
        this.personModel = personModel;
    }

    /**
     * Adds a new {@code Person} to the {@code PersonModel} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        personModel.addPerson(person);
        return this;
    }

    public PersonModel build() {
        return personModel;
    }
}
