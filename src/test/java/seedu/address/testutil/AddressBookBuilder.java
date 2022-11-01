package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.question.Question;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;

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
     * Adds a new {@code Student} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withStudent(Student student) {
        addressBook.addStudent(student);
        return this;
    }

    /**
     * Adds a new {@code Question} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withQuestion(Question question) {
        addressBook.addQuestion(question);
        return this;
    }

    /**
     * Adds a new {@code Tutorial} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withTutorial(Tutorial tutorial) {
        addressBook.addTutorial(tutorial);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
