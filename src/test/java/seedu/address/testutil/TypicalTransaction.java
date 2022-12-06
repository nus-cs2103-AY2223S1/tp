package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOODS_BUY_ORANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOODS_SELL_PAPAYA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BUY_ORANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_SELL_PAPAYA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_BUY_ORANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_SELL_PAPAYA;

import seedu.address.model.transaction.Transaction;

/**
 * A utility class containing a list of {@code Remark} objects to be used in tests.
 */
public class TypicalTransaction {

    public static final Transaction BUY_BOOKS = new TransactionBuilder()
            .withTransactionType("buy")
            .withGoods("Books")
            .withPrice("12.3")
            .withQuantity("10")
            .withDate("09/12/2001")
            .build();
    public static final Transaction BUY_SHELVES = new TransactionBuilder()
            .withTransactionType("buy")
            .withGoods("Shelves")
            .withPrice("123")
            .withQuantity("15")
            .withDate("21/01/2019")
            .build();
    public static final Transaction BUY_BURGERS = new TransactionBuilder()
            .withTransactionType("buy")
            .withGoods("Burgers")
            .withPrice("5.50")
            .withQuantity("100")
            .withDate("11/11/2022")
            .build();
    public static final Transaction SELL_CLOTHES = new TransactionBuilder()
            .withTransactionType("sell")
            .withGoods("Clothes")
            .withPrice("33.99")
            .withQuantity("19")
            .withDate("18/09/2010")
            .build();
    public static final Transaction SELL_PANTS = new TransactionBuilder()
            .withTransactionType("sell")
            .withGoods("Pants")
            .withPrice("59.99")
            .withQuantity("19")
            .withDate("09/12/2012")
            .build();
    public static final Transaction SELL_SOCKS = new TransactionBuilder()
            .withTransactionType("sell")
            .withGoods("Socks")
            .withPrice("399")
            .withQuantity("5")
            .withDate("09/09/2001")
            .build();
    public static final Transaction BUY_TOYS = new TransactionBuilder()
            .withTransactionType("buy")
            .withGoods("Toys")
            .withPrice("150")
            .withQuantity("2")
            .withDate("08/08/1990")
            .build();

    // Manually added - Remark's details found in {@code CommandTestUtil}
    public static final Transaction BUY_ORANGE = new TransactionBuilder().withTransactionType("buy")
            .withGoods(VALID_GOODS_BUY_ORANGE)
            .withPrice(VALID_PRICE_BUY_ORANGE)
            .withQuantity(VALID_QUANTITY_BUY_ORANGE)
            .withDate(VALID_DATE)
            .build();
    public static final Transaction SELL_PAPAYA = new TransactionBuilder().withTransactionType("sell")
            .withGoods(VALID_GOODS_SELL_PAPAYA)
            .withPrice(VALID_PRICE_SELL_PAPAYA)
            .withQuantity(VALID_QUANTITY_SELL_PAPAYA)
            .withDate(VALID_DATE)
            .build();

    private TypicalTransaction() {} // prevents instantiation

}
