package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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

        Transaction transaction = new SellTransaction(goods, price, quantity);
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

        Transaction transaction = new SellTransaction(goods, price, quantity);

        TransactionLog log = new TransactionLog(new ArrayList<>());
        log.addTransaction(transaction);
        assertEquals(log.calculateNetTransacted(), 8);

        Goods goods2 = new Goods("Mango");
        Price price2 = new Price("1.5");
        Quantity quantity2 = new Quantity("10");

        Transaction buyTransaction = new BuyTransaction(goods2, price2, quantity2);
        log.addTransaction(buyTransaction);

        assertEquals(log.calculateNetTransacted(), -7);
    }

    @Test
    public void testListTransactions() {
        Goods goods = new Goods("Apple");
        Price price = new Price("0.8");
        Quantity quantity = new Quantity("10");

        Transaction transaction = new SellTransaction(goods, price, quantity);
        TransactionLog log = new TransactionLog(new ArrayList<>());
        log.addTransaction(transaction);

        ObservableList<Transaction> internalList = FXCollections.observableArrayList();
        internalList.add(transaction);
        assertEquals(log.listTransactions(), FXCollections.unmodifiableObservableList(internalList));
    }

}
