package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

/**
 * Abstract transaction class
 */
public abstract class Transaction implements Comparable<Transaction> {

    protected final Goods goods;
    protected final Price price;
    protected final Quantity quantity;
    protected final Date date;

    Transaction(Goods goods, Price price, Quantity quantity, Date date) {
        requireAllNonNull(goods, price, quantity, date);
        this.goods = goods;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
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
        return date.getLocalDate();
    }


    /**
     * Compares two LocalDates and checks if this LocalDate is before the other LocalDate.
     * @param transaction the object to be compared.
     * @return 1 if is before and 0 if is after.
     */
    @Override
    public int compareTo(Transaction transaction) {
        if (this.getLocalDate().isBefore(transaction.getLocalDate())) {
            return 1;
        } else {
            return 0;
        }
    }

}
