package seedu.address.model.transaction;

/**
 * Abstract transaction class
 */
public abstract class Transaction {

    protected final Goods goods;
    protected final Price price;
    protected final Quantity quantity;

    Transaction(Goods goods, Price price, Quantity quantity) {
        this.goods = goods;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Calculates the total cost of transaction
     * @return total cost
     */
    abstract double totalCost();


}
