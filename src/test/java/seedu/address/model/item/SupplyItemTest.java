package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Item;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;

public class SupplyItemTest {

    @Test
    public void constructor_nullArgument_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SupplyItem(null, 5, 2, new Person(new Name("Ya Shu Egg"),
                        new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new HashSet<>()), new HashSet<>()));

        assertThrows(NullPointerException.class, () -> new SupplyItem("Egg", 5, 2, new Person(null,
                new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new HashSet<>()), new HashSet<>()));

        assertThrows(NullPointerException.class, () -> new SupplyItem("Egg", 5, 2, new Person(new Name("Ya Shu Egg"),
               null, new Price("$1.10"), new Item("Egg"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new HashSet<>()), new HashSet<>()));

        assertThrows(NullPointerException.class, () -> new SupplyItem("Egg", 5, 2, new Person(new Name("Ya Shu Egg"),
                new Phone("63450864"), null, new Item("Egg"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new HashSet<>()), new HashSet<>()));

        assertThrows(NullPointerException.class, () -> new SupplyItem("Egg", 5, 2, new Person(new Name("Ya Shu Egg"),
                new Phone("63450864"), new Price("$1.10"), null,
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new HashSet<>()), new HashSet<>()));

        assertThrows(NullPointerException.class, () -> new SupplyItem("Egg", 5, 2, new Person(new Name("Ya Shu Egg"),
                new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
               null, new HashSet<>()), new HashSet<>()));

        assertThrows(NullPointerException.class, () -> new SupplyItem("Egg", 5, 2, new Person(new Name("Ya Shu Egg"),
                new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), null), new HashSet<>()));

        assertThrows(NullPointerException.class, () -> new SupplyItem("Egg", 5, 2, null, new HashSet<>()));

        assertThrows(NullPointerException.class, () -> new SupplyItem("Egg", 5, 2, new Person(new Name("Ya Shu Egg"),
                new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new HashSet<>()), null));

    }

    @Test
    public void constructor_validArgument_createCorrectSupplyItem() {
        SupplyItem a = new SupplyItem("Egg", 5, 2, new Person(new Name("Ya Shu Egg"),
                new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new HashSet<>()), new HashSet<>());
        assertEquals("Egg", a.getName());
        assertEquals(5, a.getCurrentStock());
        assertEquals(2, a.getMinStock());
        assertEquals(new Price("$1.10"), a.getPrice());
        assertEquals(new Person(new Name("Ya Shu Egg"),
                new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new HashSet<>()), a.getSupplier());
        assertEquals(Collections.emptySet(), a.getTags());
        assertEquals("Item: Egg, Supplier: Ya Shu Egg; Phone: 63450864; "
                + "Price: $1.10; Item: Egg; "
                + "Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18, Price: $1.10, Stock: 5", a.toString());
    }

    @Test
    public void isValidSupplyItem() {
        // null item
        assertThrows(NullPointerException.class, () -> SupplyItem.isValidSupplyItem(null));

        // invalid items
        assertFalse(SupplyItem.isValidSupplyItem("")); // empty string
        assertFalse(SupplyItem.isValidSupplyItem(" ")); // spaces only
        assertFalse(SupplyItem.isValidSupplyItem("11")); // numbers only

        // valid items
        assertTrue(SupplyItem.isValidSupplyItem("Broccoli"));
        assertTrue(SupplyItem.isValidSupplyItem("Celery"));
        assertTrue(SupplyItem.isValidSupplyItem("Luncheon Meat")); // two words
        assertTrue(SupplyItem.isValidSupplyItem("Chicken 65 Masala")); // items with numbers in them
    }

    @Test
    public void equals() {
        SupplyItem a = new SupplyItem("Egg", 5, 2, new Person(new Name("Ya Shu Egg"),
                new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new HashSet<>()), new HashSet<>());
        SupplyItem b = new SupplyItem("Egg", 5, 2, new Person(new Name("Ya Shu Egg"),
                new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new HashSet<>()), new HashSet<>());
        assertEquals(a, b);
    }
}
