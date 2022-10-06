package friday.testutil;

import friday.model.Friday;
import friday.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Friday addressBook;

    public AddressBookBuilder() {
        addressBook = new Friday();
    }

    public AddressBookBuilder(Friday addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Student student) {
        addressBook.addPerson(student);
        return this;
    }

    public Friday build() {
        return addressBook;
    }
}
