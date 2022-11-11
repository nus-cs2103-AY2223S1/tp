package seedu.address.testutil;

import seedu.address.model.TaskList;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code TaskList ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private TaskList addressBook;

    public AddressBookBuilder() {
        addressBook = new TaskList();
    }

    public AddressBookBuilder(TaskList addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Task} to the {@code TaskList} that we are building.
     */
    public AddressBookBuilder withPerson(Task task) {
        addressBook.addPerson(task);
        return this;
    }

    public TaskList build() {
        return addressBook;
    }
}
