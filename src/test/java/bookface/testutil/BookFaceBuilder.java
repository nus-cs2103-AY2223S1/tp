package bookface.testutil;

import bookface.model.BookFace;
import bookface.model.person.Person;

/**
 * A utility class to help with building BookFace objects.
 * Example usage: <br>
 *     {@code BookFace ab = new BookFaceBuilder().withPerson("John", "Doe").build();}
 */
public class BookFaceBuilder {

    private final BookFace bookFace;

    public BookFaceBuilder() {
        bookFace = new BookFace();
    }

    public BookFaceBuilder(BookFace bookFace) {
        this.bookFace = bookFace;
    }

    /**
     * Adds a new {@code Person} to the {@code BookFace} that we are building.
     */
    public BookFaceBuilder withPerson(Person person) {
        bookFace.addPerson(person);
        return this;
    }

    public BookFace build() {
        return bookFace;
    }
}
