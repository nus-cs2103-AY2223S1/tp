package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransaction.BUY_BOOKS;
import static seedu.address.testutil.TypicalTransaction.BUY_BURGERS;
import static seedu.address.testutil.TypicalTransaction.BUY_ORANGE;
import static seedu.address.testutil.TypicalTransaction.BUY_SHELVES;
import static seedu.address.testutil.TypicalTransaction.SELL_CLOTHES;
import static seedu.address.testutil.TypicalTransaction.SELL_PAPAYA;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


class TransactionLogTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TransactionLog(null));
    }

    @Test
    public void addTransaction() {
        Goods goods = new Goods("Apple");
        Price price = new Price("0.8");
        Quantity quantity = new Quantity("10");
        Date date = new Date("17/05/2000");

        Transaction transaction = new SellTransaction(goods, price, quantity, date);
        TransactionLog log = new TransactionLog();
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        log.addTransaction(transaction);
        assertTrue(log.getTransactionLog().equals(transactionList));
    }

    @Test
    public void calculateNetTransacted() {
        Goods goods = new Goods("Apple");
        Price price = new Price("0.8");
        Quantity quantity = new Quantity("10");
        Date date = new Date("17/05/2000");

        Transaction transaction = new SellTransaction(goods, price, quantity, date);

        TransactionLog log = new TransactionLog(new ArrayList<>());
        log.addTransaction(transaction);
        assertEquals(log.calculateNetTransacted(), 8);

        Goods goods2 = new Goods("Mango");
        Price price2 = new Price("1.5");
        Quantity quantity2 = new Quantity("10");
        Date date2 = new Date("17/05/2000");

        Transaction buyTransaction = new BuyTransaction(goods2, price2, quantity2, date2);
        log.addTransaction(buyTransaction);

        assertEquals(log.calculateNetTransacted(), -7);
    }

    @Test
    public void testAsUnmodifiableObservableList() {
        Goods goods = new Goods("Apple");
        Price price = new Price("0.8");
        Quantity quantity = new Quantity("10");
        Date date = new Date("17/05/2000");

        Transaction transaction = new SellTransaction(goods, price, quantity, date);
        TransactionLog log = new TransactionLog(new ArrayList<>());
        log.addTransaction(transaction);

        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        internalList.add(transaction);
        assertEquals(log.asUnmodifiableObservableList(), FXCollections.unmodifiableObservableList(internalList));
    }

    @Test
    public void deleteByIndex_removesCorrectTransaction_success() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(BUY_BOOKS);
        transactions.add(SELL_CLOTHES);

        TransactionLog transactionLog = new TransactionLog(transactions);
        Transaction deletedTransaction = transactionLog.deleteTransaction(1);

        assertEquals(deletedTransaction, SELL_CLOTHES);
        assertEquals(transactionLog.size(), 1);
    }

    public void size_returnsCorrectSize_success() {
        TransactionLog transactionLog = new TransactionLog();
        assertEquals(transactionLog.size(), 0);

        transactionLog.addTransaction(BUY_ORANGE);
        assertEquals(transactionLog.size(), 1);

        transactionLog.addTransaction(BUY_SHELVES);
        assertEquals(transactionLog.size(), 2);
    }

    public void getSellTransactionList() {
        TransactionLog log = new TransactionLog();
        log.addTransaction(BUY_BURGERS);
        log.addTransaction(SELL_PAPAYA);

        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        internalList.add(SELL_PAPAYA);
        assertEquals(log.getSellTransactionList(), internalList);

        ObservableList<Transaction> list2 = FXCollections.observableArrayList();
        list2.add(BUY_ORANGE);
        assertNotEquals(log.getSellTransactionList(), list2);

        ObservableList<Transaction> list3 = FXCollections.observableArrayList();
        list3.add(BUY_BURGERS);
        assertNotEquals(log.getSellTransactionList(), list3);
    }

    @Test
    public void getBuyTransactionList() {
        TransactionLog log = new TransactionLog();
        log.addTransaction(BUY_BURGERS);
        log.addTransaction(SELL_PAPAYA);

        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        internalList.add(BUY_BURGERS);
        assertEquals(log.getBuyTransactionList(), internalList);

        ObservableList<Transaction> list2 = FXCollections.observableArrayList();
        list2.add(BUY_ORANGE);
        assertNotEquals(log.getBuyTransactionList(), list2);

        ObservableList<Transaction> list3 = FXCollections.observableArrayList();
        list3.add(SELL_PAPAYA);
        assertNotEquals(log.getBuyTransactionList(), list3);
    }
}
