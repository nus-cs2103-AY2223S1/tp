package seedu.address.testutil;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BuyCommand;
import seedu.address.logic.commands.SellCommand;
import seedu.address.model.transaction.Transaction;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GOODS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

/**
 * A utility class for Transaction.
 */
public class TransactionUtil {

    /**
     * Returns a buy command string for adding the {@code transaction}.
     */
    public static String getBuyCommand(Index index, Transaction transaction) {
        return BuyCommand.COMMAND_WORD + " " + index.getOneBased() + " " + getTransactionDetails(transaction);
    }

    /**
     * Returns a sell command string for adding the {@code transaction}.
     */
    public static String getSellCommand(Index index, Transaction transaction) {
        return SellCommand.COMMAND_WORD + " " + index.getOneBased() + " " + getTransactionDetails(transaction);
    }

    /**
     * Returns the part of command string for the given {@code transaction}'s details.
     */
    public static String getTransactionDetails(Transaction transaction) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_QUANTITY + transaction.getQuantity().quantity + " ");
        sb.append(PREFIX_GOODS + transaction.getGoods().goodsName + " ");
        sb.append(PREFIX_PRICE + transaction.getPrice().price);
        return sb.toString();
    }

}
