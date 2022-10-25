package tracko.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

import org.junit.jupiter.api.Test;

import tracko.model.ModelManager;
import tracko.model.UserPrefs;
import tracko.model.item.Price;

public class OrderTest {

    private ModelManager model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());

    @Test
    public void test_calculateTotalOrderPrice_success() {
        Order firstOrder = model.getFilteredOrderList().get(0);
        Order secondOrder = model.getFilteredOrderList().get(1);

        Price firstTotalOrderPrice = firstOrder.getTotalOrderPrice();
        Price secondTotalOrderPrice = secondOrder.getTotalOrderPrice();

        assertEquals(firstTotalOrderPrice.getPrice(), 750.00);
        assertEquals(secondTotalOrderPrice.getPrice(), 1000.00);
    }
}
