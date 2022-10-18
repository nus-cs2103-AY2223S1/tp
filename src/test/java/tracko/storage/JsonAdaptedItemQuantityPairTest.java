package tracko.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tracko.storage.JsonAdaptedItemQuantityPair.MESSAGE_ITEM_NOT_FOUND;
import static tracko.storage.JsonAdaptedItemQuantityPair.MISSING_FIELD_MESSAGE_FORMAT;
import static tracko.testutil.Assert.assertThrows;
import static tracko.testutil.TypicalItems.INVENTORY_LIST;
import static tracko.testutil.TypicalOrders.PAIR_2;

import org.junit.jupiter.api.Test;

import tracko.commons.exceptions.IllegalValueException;
import tracko.model.item.InventoryList;
import tracko.model.item.Quantity;

public class JsonAdaptedItemQuantityPairTest {
    private static final Integer INVALID_QUANTITY = -111;
    private static final String INVALID_ITEM_NAME = "NOT AN ITEM NAME IN INVENTORY LIST";

    private static final Integer VALID_QUANTITY = PAIR_2.getQuantityValue();
    private static final String VALID_ITEM_NAME = PAIR_2.getItemName();
    private static final InventoryList VALID_INVENTORY_LIST = INVENTORY_LIST;

    @Test
    public void toModelType_validItemDetails_returnsItemQuantityPair() throws Exception {
        JsonAdaptedItemQuantityPair pair = new JsonAdaptedItemQuantityPair(PAIR_2);
        assertEquals(PAIR_2, pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelType_nullItemName_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(null, VALID_QUANTITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Item Name");
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Quantity");
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelType_invalidItemName_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(INVALID_ITEM_NAME, VALID_QUANTITY);
        String expectedMessage = String.format(MESSAGE_ITEM_NOT_FOUND, INVALID_ITEM_NAME);
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, INVALID_QUANTITY);
        String expectedMessage = String.format(Quantity.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }
}
