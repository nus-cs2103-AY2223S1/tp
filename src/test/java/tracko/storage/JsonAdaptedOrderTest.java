package tracko.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tracko.storage.JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT;
import static tracko.testutil.Assert.assertThrows;
import static tracko.testutil.TypicalOrders.ORDER_2;

import org.junit.jupiter.api.Test;

import tracko.commons.exceptions.IllegalValueException;
import tracko.model.order.Address;
import tracko.model.order.Email;
import tracko.model.order.Name;
import tracko.model.order.Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonAdaptedOrderTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final List<JsonAdaptedItemQuantityPair> EMPTY_ITEM_LIST = new ArrayList<>();

    private static final String VALID_NAME = ORDER_2.getName().toString();
    private static final String VALID_PHONE = ORDER_2.getPhone().toString();
    private static final String VALID_EMAIL = ORDER_2.getEmail().toString();
    private static final String VALID_ADDRESS = ORDER_2.getAddress().toString();
    private static final List<JsonAdaptedItemQuantityPair> VALID_ITEM_LIST = ORDER_2.getItemList().stream()
            .map(JsonAdaptedItemQuantityPair::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validorderDetails_returnsorder() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(ORDER_2);
        assertEquals(ORDER_2, order.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ITEM_LIST);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedOrder order =
            new JsonAdaptedOrder(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ITEM_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ITEM_LIST);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedOrder order =
            new JsonAdaptedOrder(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_ITEM_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_ITEM_LIST);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedOrder order =
            new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_ITEM_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_ITEM_LIST);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedOrder order =
            new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_ITEM_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullItemList_throwsIllegalValueException() {
        JsonAdaptedOrder order =
            new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Item List");
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_emptyItemList_throwsIllegalValueException() {
        JsonAdaptedOrder order =
            new JsonAdaptedOrder(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, EMPTY_ITEM_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Item List");
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }
}
