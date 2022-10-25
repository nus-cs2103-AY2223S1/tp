package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

/**
 * Abstract transaction class
 */
public abstract class Transaction {

    protected final Goods goods;
    protected final Price price;
    protected final Quantity quantity;
    protected final Date date;
    protected final LocalDate localDate;

    Transaction(Goods goods, Price price, Quantity quantity, Date date) {
        requireAllNonNull(goods, price, quantity, date);
        this.goods = goods;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
        this.localDate = date.localDate;
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

    public Date getDate() {
        return date;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public boolean isOlderThan(Transaction transaction) {
        return this.date.isOlderThan(transaction);
    }

}
