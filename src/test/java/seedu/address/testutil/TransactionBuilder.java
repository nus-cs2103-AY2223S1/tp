package seedu.address.testutil;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.transaction.BuyTransaction;
import seedu.address.model.transaction.Goods;
import seedu.address.model.transaction.Price;
import seedu.address.model.transaction.Quantity;
import seedu.address.model.transaction.SellTransaction;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class to help with building Poc objects.
 */
public class TransactionBuilder {

    public static final String DEFAULT_GOODS = "Apples";
    public static final String DEFAULT_QUANTITY = "2103";
    public static final String DEFAULT_PRICE = "5.00";
    public static final String TRANSACTION_TYPE_BUY = "buy";
    public static final String TRANSACTION_TYPE_SELL = "sell";
    private String transactionType;
    private Goods goods;
    private Quantity quantity;
    private Price price;

    /**
     * Creates a {@code TransactionBuilder} with the default details.
     */
    public TransactionBuilder() {
        goods = new Goods(DEFAULT_GOODS);
        quantity = new Quantity(DEFAULT_QUANTITY);
        price = new Price(DEFAULT_PRICE);
        transactionType = TRANSACTION_TYPE_BUY;
    }

    /**
     * Initializes the TransactionBuilder with the data of {@code transactionToCopy}.
     */
    public TransactionBuilder(Transaction transactionToCopy) {
        goods = transactionToCopy.getGoods();
        quantity = transactionToCopy.getQuantity();
        price = transactionToCopy.getPrice();
    }

    /**
     * Sets the {@code Goods} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withGoods(String goods) {
        this.goods = new Goods(goods);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    /**
     * Sets the {@code TransactionType} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withTransactionType(String type) {
        this.transactionType = type;
        return this;
    }

    public Transaction build() throws CommandException {
        if (this.transactionType.equals(TRANSACTION_TYPE_BUY)) {
            return new BuyTransaction(goods, price, quantity);
        } else if (this.transactionType.equals(TRANSACTION_TYPE_SELL)) {
            return new SellTransaction(goods, price, quantity);
        } else {
            throw new CommandException("Transaction type not recognised");
        }
    }

}
