package tracko.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

import org.junit.jupiter.api.Test;

import tracko.model.ModelManager;
import tracko.model.UserPrefs;

public class OrderTest {

    private ModelManager model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());

    @Test
    public void test_calculateTotalOrderPrice_success() {
        Order firstOrder = model.getFilteredOrderList().get(0);
        Order secondOrder = model.getFilteredOrderList().get(1);

        Double firstTotalOrderPrice = firstOrder.calculateTotalOrderPrice();
        Double secondTotalOrderPrice = secondOrder.calculateTotalOrderPrice();

        assertEquals(firstTotalOrderPrice, 750.00);
        assertEquals(secondTotalOrderPrice, 1000.00);
    }
}
