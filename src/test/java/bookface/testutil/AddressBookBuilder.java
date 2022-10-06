package bookface.testutil;

import bookface.model.BookFace;
import bookface.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private BookFace bookFace;

    public AddressBookBuilder() {
        bookFace = new BookFace();
    }

    public AddressBookBuilder(BookFace bookFace) {
        this.bookFace = bookFace;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        bookFace.addPerson(person);
        return this;
    }

    public BookFace build() {
        return bookFace;
    }
}
