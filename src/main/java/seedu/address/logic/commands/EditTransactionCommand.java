package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameEqualsKeywordPredicate;
import seedu.address.model.transaction.BuyTransaction;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Goods;
import seedu.address.model.transaction.Price;
import seedu.address.model.transaction.Quantity;
import seedu.address.model.transaction.SellTransaction;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionLog;


/**
 * Edits the details of an existing transaction in one client in the address book.
 */
public class EditTransactionCommand extends EditCommand {

    public static final String MESSAGE_EDIT_TRANSACTION_SUCCESS = "Edited Transaction: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one Transaction field to edit must be provided.\n"
            + "E.g. [q/QUANTITY] [g/GOODS] [price/PRICE] [d/DATE]";
    public static final String MESSAGE_INVALID_USAGE =
            "Use 'view' command to view a specific client before applying this command\n";

    private final Index index;
    private final EditTransactionDescriptor editTransactionDescriptor;

    /**
     * @param index of the transaction in the viewed client to edit.
     * @param editTransactionDescriptor details to edit the transaction with.
     */
    public EditTransactionCommand(Index index, EditTransactionDescriptor editTransactionDescriptor) {
        requireNonNull(index);
        requireNonNull(editTransactionDescriptor);

        this.index = index;
        this.editTransactionDescriptor = new EditTransactionDescriptor(editTransactionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();
        if (lastShownList.size() != 1) {
            throw new CommandException(MESSAGE_INVALID_USAGE);
        }
        Client currentClient = lastShownList.get(0);
        TransactionLog transactionLog = currentClient.getTransactions();

        if (index.getZeroBased() >= transactionLog.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX);
        }

        Transaction transactionToEdit = transactionLog.getTransaction(index.getZeroBased());
        Transaction editedTransaction = createEditedTransaction(transactionToEdit, editTransactionDescriptor);
        transactionLog.setTransaction(index.getZeroBased(), editedTransaction);

        model.updateFilteredClientList(new NameEqualsKeywordPredicate(currentClient));
        return new CommandResult(String.format(MESSAGE_EDIT_TRANSACTION_SUCCESS, editedTransaction));
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToEdit}
     * edited with {@code editClientDescriptor}.
     */
    private static Transaction createEditedTransaction(Transaction transactionToEdit,
                                                       EditTransactionDescriptor editTransactionDescriptor) {
        assert transactionToEdit != null;

        Goods updatedGoods = editTransactionDescriptor.getGoods().orElse(transactionToEdit.getGoods());
        Quantity updatedQuantity = editTransactionDescriptor.getQuantity().orElse(transactionToEdit.getQuantity());
        Price updatedPrice = editTransactionDescriptor.getPrice().orElse(transactionToEdit.getPrice());
        Date updatedDate = editTransactionDescriptor.getDate().orElse(transactionToEdit.getDate());

        return transactionToEdit instanceof BuyTransaction
                ? new BuyTransaction(updatedGoods, updatedPrice, updatedQuantity, updatedDate)
                : new SellTransaction(updatedGoods, updatedPrice, updatedQuantity, updatedDate);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTransactionCommand)) {
            return false;
        }

        // state check
        EditTransactionCommand e = (EditTransactionCommand) other;
        return index.equals(e.index)
                && editTransactionDescriptor.equals(e.editTransactionDescriptor);
    }

    /**
     * Stores the details to edit the client with. Each non-empty field value will replace the
     * corresponding field value of the client.
     */
    public static class EditTransactionDescriptor {
        private Goods goods;
        private Quantity quantity;
        private Price price;
        private Date date;

        public EditTransactionDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTransactionDescriptor(EditTransactionDescriptor toCopy) {
            setGoods(toCopy.goods);
            setQuantity(toCopy.quantity);
            setPrice(toCopy.price);
            setDate(toCopy.date);
            setDate(toCopy.date);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(goods, quantity, price, date);
        }

        public void setGoods(Goods goods) {
            this.goods = goods;
        }

        public Optional<Goods> getGoods() {
            return Optional.ofNullable(goods);
        }

        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }
        public Optional<Quantity> getQuantity() {
            return Optional.ofNullable(quantity);
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTransactionDescriptor)) {
                return false;
            }

            // state check
            EditTransactionDescriptor e = (EditTransactionDescriptor) other;

            return getGoods().equals(e.getGoods())
                    && getQuantity().equals(e.getQuantity())
                    && getPrice().equals(e.getPrice()) && getDate().equals(e.getDate());
        }
    }
}
