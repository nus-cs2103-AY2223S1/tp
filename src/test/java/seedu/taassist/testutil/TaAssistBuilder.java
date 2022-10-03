package seedu.taassist.testutil;

import seedu.taassist.model.TaAssist;
import seedu.taassist.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withStudent("John", "Doe").build();}
 */
public class TaAssistBuilder {

    private TaAssist addressBook;

    public TaAssistBuilder() {
        addressBook = new TaAssist();
    }

    public TaAssistBuilder(TaAssist addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Student} to the {@code AddressBook} that we are building.
     */
    public TaAssistBuilder withStudent(Student student) {
        addressBook.addStudent(student);
        return this;
    }

    public TaAssist build() {
        return addressBook;
    }
}
