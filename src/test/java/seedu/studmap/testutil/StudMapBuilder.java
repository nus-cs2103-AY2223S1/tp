package seedu.studmap.testutil;

import seedu.studmap.model.AddressBook;
import seedu.studmap.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new StudMapBuilder().withStudent("John", "Doe").build();}
 */
public class StudMapBuilder {

    private AddressBook addressBook;

    public StudMapBuilder() {
        addressBook = new AddressBook();
    }

    public StudMapBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Student} to the {@code AddressBook} that we are building.
     */
    public StudMapBuilder withStudent(Student student) {
        addressBook.addStudent(student);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
