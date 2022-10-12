package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Store all transactions in a list.
 */
public class TransactionLog {
    private final List<Transaction> transactionList;
    /**
     * Creates a log to store all transactions.
     * @param transactionList list to store transactions.
     */
    public TransactionLog(List<Transaction> transactionList) {
        requireAllNonNull(transactionList);
        this.transactionList = transactionList;
    }

    public TransactionLog() {
        this(new ArrayList<Transaction>());
    }

    /**
     * Gets the list of transactions.
     * @return list of transactions.
     */
    public List<Transaction> getTransactionLog() {
        return this.transactionList;
    }

    /**
     * Calculates the total net transaction in the list.
     * @return the calculated net transactions.
     */
    public double calculateNetTransacted() {
        double result = 0;
        for (Transaction t : transactionList) {
            result += t.totalCost();
        }
        return result;
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    /**
     * Checks if the transaction list is empty.
     * @return the boolean value true if the list is empty.
     */
    public boolean isEmpty() {
        return transactionList.isEmpty();
    }

    /**
     * Creates an observable list of transactions to be used by MainWindow
     * @return an unmodifiableObservableList of transactions
     */
    public ObservableList<Transaction> listTransactions() {
        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        for (Transaction transation : transactionList) {
            internalList.add(transation);
        }
        return FXCollections.unmodifiableObservableList(internalList);
    }

}
