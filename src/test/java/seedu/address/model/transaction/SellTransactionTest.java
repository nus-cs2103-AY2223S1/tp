package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class SellTransactionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SellTransaction(null, null, null, null));
    }

    @Test
    public void totalCost() {
        Goods goods = new Goods("Apple");
        Price price = new Price("0.8");
        Quantity quantity = new Quantity("10");
        Date date = new Date("17/05/2000");

        Transaction transaction = new SellTransaction(goods, price, quantity, date);
        assertEquals(transaction.totalCost(), 8);
        assertFalse(transaction.totalCost() == -8);
    }

    @Test
    public void testToString() {
        Goods goods = new Goods("Apple");
        Price price = new Price("0.8");
        Quantity quantity = new Quantity("10");
        Date date = new Date("17/05/2000");

        Transaction transaction = new SellTransaction(goods, price, quantity, date);
        assertTrue(transaction.toString().equals("You sold 10 quantity of Apple at $0.80 each on 17 May 2000"));
    }

    @Test
    public void testEquals() {
        Goods goods = new Goods("Apple");
        Price price = new Price("0.8");
        Quantity quantity = new Quantity("10");
        Date date = new Date("17/05/2000");

        Transaction transaction = new SellTransaction(goods, price, quantity, date);
        assertEquals(transaction, new SellTransaction(goods, price, quantity, date));
        assertFalse(transaction.equals(new Object()));
    }

    @Test
    public void testDateEquals() {
        Goods goods = new Goods("Apple");
        Price price = new Price("0.8");
        Quantity quantity = new Quantity("10");
        Date date = new Date("17/05/2000");

        Transaction transaction = new SellTransaction(goods, price, quantity, date);
        assertEquals(transaction.getDate(), new SellTransaction(goods, price, quantity, date).getDate());
        assertEquals(transaction.getDate().toString(),
                new SellTransaction(goods, price, quantity, date).getDate().toString());
    }

    @Test
    public void testDateNotEquals() {
        Goods goods = new Goods("Apple");
        Price price = new Price("0.8");
        Quantity quantity = new Quantity("10");
        Date date = new Date("17/05/2000");
        Date date2 = new Date("18/05/2000");

        Transaction transaction = new SellTransaction(goods, price, quantity, date);
        assertNotEquals(transaction.getDate(), new SellTransaction(goods, price, quantity, date2).getDate());
        assertNotEquals(transaction.getDate().toString(),
                new SellTransaction(goods, price, quantity, date2).getDate().toString());

    }
}
