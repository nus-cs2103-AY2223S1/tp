package seedu.address.model.transaction;

/**
 * Store the sell transaction process.
 */
public class SellTransaction extends Transaction {

    public SellTransaction(Goods goods, Price price, Quantity quantity) {
        super(goods, price, quantity);
    }

    /**
     * Calculates the total cost of transaction
     *
     * @return total cost
     */
    @Override
    public double totalCost() {
        return quantity.value() * price.value();
    }

    @Override
    public String toString() {
        return "You sold " + quantity + " quantity of "
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

        if (!(other instanceof SellTransaction)) {
            return false;
        }

        SellTransaction otherTransaction = (SellTransaction) other;
        return otherTransaction.getGoods().equals(getGoods())
                && otherTransaction.getQuantity().equals(getQuantity())
                && otherTransaction.getPrice().equals(getPrice());
    }
}
