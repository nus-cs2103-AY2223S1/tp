package seedu.address.model.transaction;

/**
 * Store the buy transaction process.
 */
public class BuyTransaction extends Transaction {

    BuyTransaction(Goods goods, Price price, Quantity quantity) {
        super(goods, price, quantity);
    }

    /**
     * Calculates the total cost of transaction
     *
     * @return total cost
     */
    @Override
    double totalCost() {
        return quantity.value() * price.value();
    }

    @Override
    public String toString() {
        return "You bought " + quantity + " quantity of "
                + goods + " at $" + price;
    }
}
