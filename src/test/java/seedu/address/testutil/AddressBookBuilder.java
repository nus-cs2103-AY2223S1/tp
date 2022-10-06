package seedu.address.testutil;

import seedu.address.model.StudentRecord;
import seedu.address.model.student.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private StudentRecord addressBook;

    public AddressBookBuilder() {
        addressBook = new StudentRecord();
    }

    public AddressBookBuilder(StudentRecord addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addStudent(person);
        return this;
    }

    public StudentRecord build() {
        return addressBook;
    }
}
