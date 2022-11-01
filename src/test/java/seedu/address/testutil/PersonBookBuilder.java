package seedu.address.testutil;

import seedu.address.model.BuyerBook;
import seedu.address.model.buyer.Buyer;

/**
 * A utility class to help with building BuyerBook objects.
 */
public class PersonBookBuilder {

    private BuyerBook buyerBook;

    public PersonBookBuilder() {
        buyerBook = new BuyerBook();
    }

    public PersonBookBuilder(BuyerBook buyerBook) {
        this.buyerBook = buyerBook;
    }

    /**
     * Adds a new {@code Buyer} to the {@code BuyerBook} that we are building.
     */
    public PersonBookBuilder withPerson(Buyer buyer) {
        buyerBook.addBuyer(buyer);
        return this;
    }

    public BuyerBook build() {
        return buyerBook;
    }
}
