package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Abstract transaction class
 */
public abstract class Transaction {

    protected final Goods goods;
    protected final Price price;
    protected final Quantity quantity;

    Transaction(Goods goods, Price price, Quantity quantity) {
        requireAllNonNull(goods, price, quantity);
        this.goods = goods;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Calculates the total cost of transaction
     * @return total cost
     */
    abstract double totalCost();

    public Goods getGoods() {
        return goods;
    }

    public Price getPrice() {
        return price;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
