package seedu.address.testutil;

import seedu.address.model.PersonBook;
import seedu.address.model.buyer.Buyer;

/**
 * A utility class to help with building PersonBook objects.
 */
public class PersonBookBuilder {

    private PersonBook personBook;

    public PersonBookBuilder() {
        personBook = new PersonBook();
    }

    public PersonBookBuilder(PersonBook personBook) {
        this.personBook = personBook;
    }

    /**
     * Adds a new {@code Buyer} to the {@code PersonBook} that we are building.
     */
    public PersonBookBuilder withPerson(Buyer buyer) {
        personBook.addPerson(buyer);
        return this;
    }

    public PersonBook build() {
        return personBook;
    }
}
