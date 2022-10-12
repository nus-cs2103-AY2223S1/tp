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
    public boolean isBuy() {
        return false;
    }
    @Override
    public String toString() {
        return "You sold " + quantity + " quantity of "
                + goods + " at " + price + " each";
    }
}
