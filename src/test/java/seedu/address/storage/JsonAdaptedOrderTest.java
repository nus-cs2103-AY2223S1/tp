package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;
import seedu.address.model.order.Request;
import seedu.address.model.person.Buyer;
import seedu.address.testutil.TypicalOrders;

public class JsonAdaptedOrderTest {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private static final Double DEFAULT_SETTLED_PRICE = 0.0;

    private static final String INVALID_BY_DATE = "2030-16-100";
    private static final Double INVALID_SETTLED_PRICE = -8888.674;

    private static final JsonAdaptedBuyer VALID_BUYER = new JsonAdaptedBuyer(TypicalOrders.ORDER_1.getBuyer());
    private static final JsonAdaptedPriceRange VALID_PRICE_RANGE = new JsonAdaptedPriceRange(TypicalOrders.ORDER_1
            .getRequestedPriceRange());
    private static final JsonAdaptedRequest VALID_REQUEST = new JsonAdaptedRequest(TypicalOrders.ORDER_1.getRequest());
    private static final List<String> VALID_ADDITIONAL_REQUEST = TypicalOrders.ORDER_1.getAdditionalRequests()
            .getAdditionalRequestsToString();
    private static final String VALID_BY_DATE = TypicalOrders.ORDER_1.getByDate().toString();
    private static final Double VALID_SETTLED_PRICE = TypicalOrders.ORDER_1.getSettledPrice().getPrice();
    private static final String VALID_ORDER_STATUS = TypicalOrders.ORDER_1.getOrderStatus().getStatus();
    private static final String VALID_UNIQUE_ID = TypicalOrders.ORDER_1.getId().getIdToString();

    @Test
    public void toModelType_validOrderDetails_returnsOrder() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(TypicalOrders.ORDER_1);
        assertEquals(TypicalOrders.ORDER_1, order.toModelType());
    }

    @Test
    public void toModelType_nullBuyer_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(null, VALID_PRICE_RANGE, VALID_REQUEST, VALID_ADDITIONAL_REQUEST,
                VALID_BY_DATE, VALID_SETTLED_PRICE, VALID_ORDER_STATUS, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Buyer.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullPriceRange_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_BUYER, null, VALID_REQUEST, VALID_ADDITIONAL_REQUEST,
                VALID_BY_DATE, VALID_SETTLED_PRICE, VALID_ORDER_STATUS, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PriceRange.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullRequest_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_BUYER, VALID_PRICE_RANGE, null, VALID_ADDITIONAL_REQUEST,
                VALID_BY_DATE, VALID_SETTLED_PRICE, VALID_ORDER_STATUS, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Request.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullByDate_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_BUYER, VALID_PRICE_RANGE, VALID_REQUEST,
                VALID_ADDITIONAL_REQUEST, null, VALID_SETTLED_PRICE, VALID_ORDER_STATUS, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                LocalDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidByDate_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_BUYER, VALID_PRICE_RANGE, VALID_REQUEST,
                VALID_ADDITIONAL_REQUEST, INVALID_BY_DATE, VALID_SETTLED_PRICE, VALID_ORDER_STATUS, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_BUYER, VALID_PRICE_RANGE, VALID_REQUEST,
                VALID_ADDITIONAL_REQUEST, VALID_BY_DATE, null, VALID_ORDER_STATUS, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_defaultPrice() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_BUYER, VALID_PRICE_RANGE, VALID_REQUEST,
                VALID_ADDITIONAL_REQUEST, VALID_BY_DATE, INVALID_SETTLED_PRICE, VALID_ORDER_STATUS, VALID_UNIQUE_ID);
        JsonAdaptedOrder expected = new JsonAdaptedOrder(VALID_BUYER, VALID_PRICE_RANGE, VALID_REQUEST,
                VALID_ADDITIONAL_REQUEST, VALID_BY_DATE, DEFAULT_SETTLED_PRICE, VALID_ORDER_STATUS, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullOrderStatus_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_BUYER, VALID_PRICE_RANGE, VALID_REQUEST,
                VALID_ADDITIONAL_REQUEST, VALID_BY_DATE, VALID_SETTLED_PRICE, null, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, OrderStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullUniqueId_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_BUYER, VALID_PRICE_RANGE, VALID_REQUEST,
                VALID_ADDITIONAL_REQUEST, VALID_BY_DATE, VALID_SETTLED_PRICE, VALID_ORDER_STATUS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, UniqueId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }
}
