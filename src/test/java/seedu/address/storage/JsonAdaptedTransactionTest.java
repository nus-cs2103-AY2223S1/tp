package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTransaction.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransaction.BUY_SHELVES;
import static seedu.address.testutil.TypicalTransaction.SELL_PAPAYA;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Goods;
import seedu.address.model.transaction.Price;
import seedu.address.model.transaction.Quantity;
import seedu.address.model.transaction.TransactionType;

public class JsonAdaptedTransactionTest {
    private static final String INVALID_GOODS = "@pple";
    private static final String INVALID_PRICE = "Eighteen Dollars";
    private static final String INVALID_QUANTITY = "Fifteen";
    private static final String INVALID_DATE_FORMAT = "5th January 2021";

    private static final String INVALID_DATE_WRONG_DATE = "31/02/2000";

    private static final String VALID_GOODS = BUY_SHELVES.getGoods().toString();
    private static final String VALID_PRICE = Double.toString(BUY_SHELVES.getPrice().value());
    private static final String VALID_QUANTITY = BUY_SHELVES.getQuantity().toString();
    private static final String VALID_DATE = "20/01/2000";

    @Test
    public void toModelType_validTransactionDetails_returnsTransaction() throws Exception {
        JsonAdaptedTransaction buyTransaction = new JsonAdaptedTransaction(BUY_SHELVES);
        assertEquals(BUY_SHELVES, buyTransaction.toModelType());

        JsonAdaptedTransaction sellTransaction = new JsonAdaptedTransaction(SELL_PAPAYA);
        assertEquals(SELL_PAPAYA, sellTransaction.toModelType());
    }

    @Test
    public void toModelType_invalidGoods_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(INVALID_GOODS, VALID_PRICE, VALID_QUANTITY, TransactionType.BUY, VALID_DATE);
        String expectedMessage = Goods.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_nullGoods_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(null, VALID_PRICE, VALID_QUANTITY, TransactionType.SELL, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Goods.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(VALID_GOODS, INVALID_PRICE, VALID_QUANTITY,
                        TransactionType.SELL, VALID_DATE);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS_GENERAL;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(VALID_GOODS, null, VALID_QUANTITY, TransactionType.BUY, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(VALID_GOODS, VALID_PRICE, INVALID_QUANTITY,
                        TransactionType.SELL, VALID_DATE);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(VALID_GOODS, VALID_PRICE, null, TransactionType.BUY, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction1 =
                new JsonAdaptedTransaction(VALID_GOODS, VALID_PRICE, VALID_QUANTITY, TransactionType.BUY,
                        INVALID_DATE_FORMAT);
        String expectedMessage1 = Date.MESSAGE_CONSTRAINTS_WRONG_FORMAT;
        JsonAdaptedTransaction transaction2 =
                new JsonAdaptedTransaction(VALID_GOODS, VALID_PRICE, VALID_QUANTITY, TransactionType.BUY,
                        INVALID_DATE_WRONG_DATE);
        String expectedMessage2 = Date.MESSAGE_CONSTRAINTS_WRONG_DATE;
        assertThrows(IllegalValueException.class, expectedMessage2, transaction2::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(VALID_GOODS, VALID_PRICE, VALID_QUANTITY, TransactionType.BUY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

}
