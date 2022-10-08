package modtrekt.testutil;

import modtrekt.model.TaskBook;
import modtrekt.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private TaskBook addressBook;

    public AddressBookBuilder() {
        addressBook = new TaskBook();
    }

    public AddressBookBuilder(TaskBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public TaskBook build() {
        return addressBook;
    }
}
