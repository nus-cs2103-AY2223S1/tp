package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameEqualsKeywordPredicate;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionLog;

/**
 * Deletes a transaction from a client.
 */
public class DeleteTransactionCommand extends DeleteCommand {

    public static final String MESSAGE_DELETE_TRANSACTION_SUCCESS = "Deleted Transaction: %1$s";

    public static final String MESSAGE_INVALID_USAGE =
            "Use 'view' command to view a specific client before applying this command\n";

    private final Index targetIndex;

    public DeleteTransactionCommand(Index index) {
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (lastShownList.size() != 1) {
            throw new CommandException(MESSAGE_INVALID_USAGE);
        }

        Client focusedClient = lastShownList.get(0);
        TransactionLog transactionLog = focusedClient.getTransactions();

        if (targetIndex.getZeroBased() >= transactionLog.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX);
        }

        Transaction deletedTransaction = transactionLog.deleteTransaction(targetIndex.getZeroBased());

        model.updateFilteredClientList(new NameEqualsKeywordPredicate(focusedClient));

        return new CommandResult(String.format(MESSAGE_DELETE_TRANSACTION_SUCCESS, deletedTransaction));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTransactionCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTransactionCommand) other).targetIndex)); // state check
    }
}
