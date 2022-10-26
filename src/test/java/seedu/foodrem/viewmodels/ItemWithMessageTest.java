package seedu.foodrem.viewmodels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import seedu.foodrem.model.item.Item;
import seedu.foodrem.testutil.ItemBuilder;

class ItemWithMessageTest {
    private final Item initialItem = new ItemBuilder().build();
    private final String initialMessage = "Test message";

    @Test
    void getItem() {
        assertSame(new ItemWithMessage(initialItem, initialMessage).getItem(), initialItem);
    }

    @Test
    void getMessage() {
        assertSame(new ItemWithMessage(initialItem, initialMessage).getMessage(), initialMessage);
    }

    @Test
    void testEquals_sameContents() {
        final String messageCopy = initialMessage.concat("");
        final Item itemCopy = new ItemBuilder().build();

        assertEquals(new ItemWithMessage(initialItem, initialMessage),
                new ItemWithMessage(initialItem, initialMessage));
        assertEquals(new ItemWithMessage(initialItem, initialMessage),
                new ItemWithMessage(initialItem, messageCopy));
        assertEquals(new ItemWithMessage(initialItem, initialMessage),
                new ItemWithMessage(itemCopy, initialMessage));
        assertEquals(new ItemWithMessage(initialItem, initialMessage),
                new ItemWithMessage(itemCopy, messageCopy));
    }

    @Test
    void testEquals_differentContents() {
        final String message = initialMessage.concat("a");
        final Item item = new ItemBuilder().withItemName("a").build();

        assertNotEquals(new ItemWithMessage(initialItem, initialMessage),
                new ItemWithMessage(item, initialMessage));
        assertNotEquals(new ItemWithMessage(initialItem, initialMessage),
                new ItemWithMessage(initialItem, message));
    }

    @Test
    void testHashCode_equal() {
        final String messageCopy = initialMessage.concat("");
        final Item itemCopy = new ItemBuilder().build();

        assertEquals(new ItemWithMessage(initialItem, initialMessage).hashCode(),
                new ItemWithMessage(initialItem, initialMessage).hashCode());
        assertEquals(new ItemWithMessage(initialItem, initialMessage).hashCode(),
                new ItemWithMessage(initialItem, messageCopy).hashCode());
        assertEquals(new ItemWithMessage(initialItem, initialMessage).hashCode(),
                new ItemWithMessage(itemCopy, initialMessage).hashCode());
        assertEquals(new ItemWithMessage(initialItem, initialMessage).hashCode(),
                new ItemWithMessage(itemCopy, messageCopy).hashCode());
    }

    @Test
    void testHashCode_notEqual() {
        final String message = initialMessage.concat("a");
        final Item item = new ItemBuilder().withItemName("a").build();

        assertNotEquals(new ItemWithMessage(initialItem, initialMessage).hashCode(),
                new ItemWithMessage(item, initialMessage).hashCode());
        assertNotEquals(new ItemWithMessage(initialItem, initialMessage).hashCode(),
                new ItemWithMessage(initialItem, message).hashCode());
    }
}
