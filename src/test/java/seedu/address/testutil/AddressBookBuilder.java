package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.project.Project;

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

    public AddressBook build() {
        return addressBook;
    }
}
