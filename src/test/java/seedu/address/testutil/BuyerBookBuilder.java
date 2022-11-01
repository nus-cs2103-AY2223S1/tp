package seedu.address.testutil;

import seedu.address.model.BuyerBook;
import seedu.address.model.buyer.Buyer;

/**
 * A utility class to help with building BuyerBook objects.
 */
public class BuyerBookBuilder {

    private BuyerBook buyerBook;

    public BuyerBookBuilder() {
        buyerBook = new BuyerBook();
    }

    public BuyerBookBuilder(BuyerBook buyerBook) {
        this.buyerBook = buyerBook;
    }

    /**
     * Adds a new {@code Buyer} to the {@code BuyerBook} that we are building.
     */
    public BuyerBookBuilder withBuyer(Buyer buyer) {
        buyerBook.addBuyer(buyer);
        return this;
    }

    public BuyerBook build() {
        return buyerBook;
    }
}
