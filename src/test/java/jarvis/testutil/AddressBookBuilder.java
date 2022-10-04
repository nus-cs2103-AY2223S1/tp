package jarvis.testutil;

import jarvis.model.StudentBook;
import jarvis.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private StudentBook studentBook;

    public AddressBookBuilder() {
        studentBook = new StudentBook();
    }

    public AddressBookBuilder(StudentBook studentBook) {
        this.studentBook = studentBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Student student) {
        studentBook.addStudent(student);
        return this;
    }

    public StudentBook build() {
        return studentBook;
    }
}
