package seedu.address.model.transaction;

/**
 * Store the buy transaction process.
 */
public class BuyTransaction extends Transaction {
    private static final double NEGATIVE = -1;

    public BuyTransaction(Goods goods, Price price, Quantity quantity) {
        super(goods, price, quantity);
    }

    /**
     * Calculates the total cost of transaction
     *
     * @return total cost
     */
    @Override
    double totalCost() {
        return NEGATIVE * quantity.value() * price.value();
    }

    @Override
    public String toString() {
        return "You bought " + quantity + " quantity of "
                + goods + " at " + price + " each";
    }

    /**
     * Returns true if both transactions have the same identity and data fields.
     * This defines a stronger notion of equality between two transactions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof BuyTransaction)) {
            return false;
        }

        BuyTransaction otherTransaction = (BuyTransaction) other;
        return otherTransaction.getGoods().equals(getGoods())
                && otherTransaction.getQuantity().equals(getQuantity())
                && otherTransaction.getPrice().equals(getPrice());
    }
}
