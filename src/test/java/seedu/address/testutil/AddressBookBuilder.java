package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Module} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withModule(Module module) {
        addressBook.addModule(module);
        return this;
    }

    /**
     * Adds a new {@code Task} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withTask(Task task) {
        addressBook.addTask(task);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
