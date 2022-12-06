package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.transaction.BuyTransaction;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Goods;
import seedu.address.model.transaction.Price;
import seedu.address.model.transaction.Quantity;
import seedu.address.model.transaction.SellTransaction;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionType;

/**
 * Jackson-friendly version of {@link Transaction}.
 */
public class JsonAdaptedTransaction {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Transaction's %s field is missing!";

    private final String goods;
    private final String price;
    private final String quantity;
    private final String date;

    private final TransactionType type;

    /**
     * Constructs a {@code JsonAdaptedTransaction} with the given transaction details.
     */
    @JsonCreator
    public JsonAdaptedTransaction(@JsonProperty("goods") String goods, @JsonProperty("price") String price,
                                  @JsonProperty("quantity") String quantity,
                                  @JsonProperty("type") TransactionType type,
                                  @JsonProperty("date") String date) {
        this.goods = goods;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.date = date;
    }

    /**
     * Converts a given {@code Transaction} into this class for Jackson use.
     */
    public JsonAdaptedTransaction(Transaction transaction) {
        goods = transaction.getGoods().goodsName;
        price = transaction.getPrice().price;
        quantity = transaction.getQuantity().quantity;
        date = transaction.getDate().getDateInDdMmYyyy();

        type = transaction instanceof BuyTransaction
                ? TransactionType.BUY
                : TransactionType.SELL;
    }

    /**
     * Converts this Jackson-friendly adapted transaction object into the model's {@code Transaction} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted transaction.
     */
    public Transaction toModelType() throws IllegalValueException {
        if (goods == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Goods.class.getSimpleName()));
        }
        if (!Goods.isValidName(goods)) {
            throw new IllegalValueException(Goods.MESSAGE_CONSTRAINTS);
        }
        final Goods modelGoods = new Goods(goods);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS_GENERAL);
        }
        final Price modelPrice = new Price(price);

        if (quantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Date.class.getSimpleName()));
        }
        if (!Date.isValidDateFormat(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS_WRONG_FORMAT);
        }
        if (!Date.isValidDateInput(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS_WRONG_DATE);
        }
        final Date modelDate = new Date(date);

        return type == TransactionType.BUY
                ? new BuyTransaction(modelGoods, modelPrice, modelQuantity, modelDate)
                : new SellTransaction(modelGoods, modelPrice, modelQuantity, modelDate);
    }
}
