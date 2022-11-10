package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OrderStatusTest {
    @Test
    public void getStatus() {
        String expectedPending = "Pending";
        String expectedNegotiating = "Negotiating";
        String expectedDelivering = "Delivering";

        assertEquals(OrderStatus.PENDING.getStatus(), expectedPending);
        assertEquals(OrderStatus.NEGOTIATING.getStatus(), expectedNegotiating);
        assertEquals(OrderStatus.DELIVERING.getStatus(), expectedDelivering);
    }

    @Test
    public void isValidOrderStatus_invalidArgs_returnsFalse() {
        assertFalse(OrderStatus.isValidOrderStatus(""));
        assertFalse(OrderStatus.isValidOrderStatus("pending"));
        assertFalse(OrderStatus.isValidOrderStatus("this is not a valid order status"));
    }

    @Test
    void isValidOrderStatus_validArgs_returnsTrue() {
        assertTrue(OrderStatus.isValidOrderStatus("Pending"));
        assertTrue(OrderStatus.isValidOrderStatus("Delivering"));
        assertTrue(OrderStatus.isValidOrderStatus("Negotiating"));
    }
}
