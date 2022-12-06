package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.transaction.exceptions.TransactionNotFoundException;


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

    public Transaction deleteTransaction(int index) {
        return transactionList.remove(index);
    }

    /**
     * Checks if the transaction list is empty.
     * @return the boolean value true if the list is empty.
     */
    public boolean isEmpty() {
        return transactionList.isEmpty();
    }

    /**
     * Creates an observable list of transactions to be used by MainWindow.
     * @return an unmodifiableObservableList of transactions.
     */
    public ObservableList<Transaction> asUnmodifiableObservableList() {
        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        for (Transaction transaction : transactionList) {
            internalList.add(transaction);
        }
        return FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * @return the number of transactions in the transaction log.
     */
    public int size() {
        return transactionList.size();
    }

    /**
     * Retrieves a transaction at specified index.
     *
     * @param index of transaction to be retrieved.
     * @return transaction at the index
     */
    public Transaction getTransaction(int index) {
        return transactionList.get(index);
    }

    /**
     * Sets a new transaction at the specified index of transaction list.
     *
     * @param index of transaction to be replaced.
     * @param editedTransaction the transaction to be used to replace the old transaction at the specified index.
     */
    public void setTransaction(int index, Transaction editedTransaction) {
        requireAllNonNull(editedTransaction);

        if (index == -1) {
            throw new TransactionNotFoundException();
        }
        transactionList.set(index, editedTransaction);
    }

    /**
     * Creates an observable list of transactions to be used by MainWindow.
     * @return an unmodifiableObservableList of sell transactions.
     */
    public ObservableList<Transaction> getSellTransactionList() {
        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        for (Transaction transaction : transactionList) {
            if (transaction instanceof SellTransaction) {
                internalList.add(transaction);
            }
        }
        return FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Creates an observable list of transactions to be used by MainWindow.
     * @return an unmodifiableObservableList of buy transactions.
     */
    public ObservableList<Transaction> getBuyTransactionList() {
        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        for (Transaction transaction : transactionList) {
            if (transaction instanceof BuyTransaction) {
                internalList.add(transaction);
            }
        }
        return FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Creates an observable list of transactions to be used by MainWindow.
     * @return an unmodifiableObservableList of sorted transactions by oldest.
     */
    public ObservableList<Transaction> getOldestTransactionList() {
        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        internalList.addAll(transactionList);
        Collections.sort(internalList);
        Collections.reverse(internalList);
        return FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Creates an observable list of transactions to be used by MainWindow.
     * @return an unmodifiableObservableList of sorted transactions by latest.
     */
    public ObservableList<Transaction> getLatestTransactionList() {
        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        internalList.addAll(transactionList);
        Collections.sort(internalList);
        return FXCollections.unmodifiableObservableList(internalList);
    }

}
