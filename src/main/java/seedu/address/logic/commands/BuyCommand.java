package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GOODS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.transaction.Transaction;

/**
 * Adds a buy transaction to an existing company in the address book.
 */
public class BuyCommand extends Command {
    public static final String COMMAND_WORD = "buy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a transaction and links to the company. "
            + "Parameters: "
            + "Index (must be a positive integer) "
            + PREFIX_QUANTITY + "QUANTITY "
            + PREFIX_GOODS + "GOODS "
            + PREFIX_PRICE + "PRICE\n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_QUANTITY + "1000 "
            + PREFIX_GOODS + "Apples "
            + PREFIX_PRICE + "1.50 ";

    public static final String MESSAGE_SUCCESS = "New transaction created: \nBought %2$s %3$s from %1$s for %4$s each";
    public static final String MESSAGE_TRANSACTION_INVALID = "Transaction cannot be created. "
            + "Enter a valid transaction:\n"
            + "index "
            + PREFIX_QUANTITY + "QUANTITY "
            + PREFIX_GOODS + "GOODS "
            + PREFIX_PRICE + "PRICE ";

    private final Index index;
    private final Transaction transaction;

    /**
     * @param index of the company in the company list to add the POC to
     * @param transaction to be added
     */
    public BuyCommand(Index index, Transaction transaction) {
        requireNonNull(index);
        requireNonNull(transaction);
        this.index = index;
        this.transaction = transaction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        // if index of company not valid
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());

        Client editedClient = clientToEdit;
        editedClient.addTransaction(transaction);
        model.setClient(clientToEdit, editedClient);

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedClient.getName(), transaction.getQuantity(),
                transaction.getGoods(), transaction.getPrice()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BuyCommand // instanceof handles nulls
                && index.equals(((BuyCommand) other).index)
                && transaction.equals(((BuyCommand) other).transaction));
    }

}
