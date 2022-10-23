package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.project.Project;
import seedu.address.model.staff.Staff;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withProject("John", "Doe").build();}
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
     * Adds a new {@code Project} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withProject(Project project) {
        addressBook.addProject(project);
        return this;
    }

    /**
     * Adds a new {@code Staff} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withStaff(Staff staff) {
        addressBook.addStaff(staff);
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
