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

    public Transaction deleteTransaction(int index) {
        return transactionList.remove(index);
    }

    public int size() {
        return transactionList.size();
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
        quickSort(internalList, 0, internalList.size() - 1, 1);
        return FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Creates an observable list of transactions to be used by MainWindow.
     * @return an unmodifiableObservableList of sorted transactions by latest.
     */
    public ObservableList<Transaction> getLatestTransactionList() {
        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        internalList.addAll(transactionList);
        quickSort(internalList, 0, internalList.size() - 1, 0);
        return FXCollections.unmodifiableObservableList(internalList);
    }

    private static void swap(ObservableList<Transaction> arr, int i, int j)
    {
        Transaction temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    private static int partition(ObservableList<Transaction> arr, int low, int high, int old)
    {

        Transaction pivot = arr.get(high);

        int i = (low - 1);


        for (int j = low; j <= high - 1; j++) {

            //if sort by oldest, old == 1, else old == 0.
            if (old == 1) {
                if (arr.get(j).isOlderThan(pivot)) {

                    i++;
                    swap(arr, i, j);
                }
            } else {
                if (!arr.get(j).isOlderThan(pivot)) {

                    i++;
                    swap(arr, i, j);
                }
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    private static void quickSort(ObservableList<Transaction> arr, int low, int high, int old)
    {
        //if sort by oldest, old == 1 else old == 0
        if (low < high) {
            int pi;

            if (old == 1) {
                pi = partition(arr, low, high, old);
            } else {
                pi = partition(arr, low, high, old);
            }

            quickSort(arr, low, pi - 1, old);
            quickSort(arr, pi + 1, high, old);
        }
    }
}
