package bookface.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import bookface.commons.exceptions.IllegalValueException;
import bookface.model.BookFace;
import bookface.model.ReadOnlyBookFace;
import bookface.model.book.Book;
import bookface.model.person.Person;

/**
 * An Immutable BookFace that is serializable to JSON format.
 */
@JsonRootName(value = "bookface")
class JsonSerializableBookFace {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    public static final String MESSAGE_DUPLICATE_BOOK = "Books list contains duplicate book(s).";

    public static final String MESSAGE_INVALID_LOANED_BOOK = "A Book is detected as a loaned Book when it "
            + "should not be loaned.";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    private final List<JsonAdaptedBook> books = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableBookFace} with the given persons and books.
     */
    @JsonCreator
    public JsonSerializableBookFace(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("books") List<JsonAdaptedBook> books) {
        this.persons.addAll(persons);
        this.books.addAll(books);
    }

    /**
     * Converts a given {@code ReadOnlyBookFace} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableBookFace}.
     */
    public JsonSerializableBookFace(ReadOnlyBookFace source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        books.addAll(source.getBookList().stream().filter(x -> !x.isLoaned())
                .map(JsonAdaptedBook::new).collect(Collectors.toList()));
    }

    /**
     * Converts bookFace into the model's {@code BookFace} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BookFace toModelType() throws IllegalValueException {
        BookFace bookFace = new BookFace();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (bookFace.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            bookFace.addPerson(person);
            if (person.hasBooksOnLoan()) {
                Set<Book> loanedBooks = person.getLoanedBooksSet();
                for (Book book : loanedBooks) {
                    Date returnDate = book.getReturnDate()
                            .orElseGet(bookface.commons.util.Date::getFourteenDaysLaterDate);
                    book.loanTo(person, returnDate);
                    bookFace.addBook(book);
                }
            }
        }

        for (JsonAdaptedBook jsonAdaptedBook : books) {
            Book book = jsonAdaptedBook.toModelType();
            if (bookFace.hasBook(book)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BOOK);
            } else if (book.isLoaned() || book.getReturnDateString() != null) {
                throw new IllegalValueException((MESSAGE_INVALID_LOANED_BOOK));
            }
            bookFace.addBook(book);
        }
        return bookFace;
    }

}
