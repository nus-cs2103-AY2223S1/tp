package bookface.testutil;

import static bookface.testutil.TypicalDates.TYPICAL_DATE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bookface.model.book.Book;
import bookface.model.person.Email;
import bookface.model.person.Name;
import bookface.model.person.Person;
import bookface.model.person.Phone;
import bookface.model.tag.Tag;
import bookface.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    private final Set<Book> booksToLoan;
    private Name name;
    private Phone phone;
    private Email email;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
        booksToLoan = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        tags = new HashSet<>(personToCopy.getTags());
        booksToLoan = new HashSet<>(personToCopy.getLoanedBooksSet());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getSetFromStringArray(Tag::new, tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the loaned books of the {@code Person} that we are building.
     */
    public PersonBuilder withBooksToLoan(List<Book> loanedBooks) {
        this.booksToLoan.addAll(loanedBooks);
        return this;
    }

    /**
     * Creates a {@code Person} object.
     * @return A {@code Person} that is built from the fields
     */
    public Person build() {
        Person newPerson = new Person(name, phone, email, booksToLoan, tags);
        for (Book book: booksToLoan) {
            book.loanTo(newPerson, TYPICAL_DATE);
            newPerson.addLoanedBook(book);
        }
        return newPerson;
    }
}
