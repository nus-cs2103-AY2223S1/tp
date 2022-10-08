package seedu.address.model.transaction;

/**
 * Store the sell transaction process.
 */
public class SellTransaction extends Transaction {
    private static final double NEGATIVE = -1;

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
        return NEGATIVE * quantity.value() * price.value();
    }

    @Override
    public String toString() {
        return "You sold %d quantity of %s at $%d";
    }
}
