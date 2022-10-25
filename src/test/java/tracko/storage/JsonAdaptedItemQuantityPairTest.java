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
import tracko.model.order.ItemQuantityPair;

public class JsonAdaptedItemQuantityPairTest {
    private static final Integer INVALID_QUANTITY = -111;
    private static final String INVALID_ITEM_NAME = "NOT AN ITEM NAME IN INVENTORY LIST";
    private static final Double INVALID_COST_PRICE = -1.11;
    private static final Double INVALID_SELL_PRICE = 1.1111;

    private static final Integer VALID_QUANTITY = PAIR_2.getQuantityValue();
    private static final String VALID_ITEM_NAME = PAIR_2.getItemName();
    private static final Double VALID_COST_PRICE = PAIR_2.getItem().getCostPrice().value;
    private static final Double VALID_SELL_PRICE = PAIR_2.getItem().getSellPrice().value;
    private static final InventoryList VALID_INVENTORY_LIST = INVENTORY_LIST;

    private static final Double INCONSISTENT_COST_PRICE = VALID_COST_PRICE + 1.00;
    private static final Double INCONSISTENT_SELL_PRICE = VALID_SELL_PRICE + 1.00;

    @Test
    public void toModelTypeWithInventoryList_validItemDetails_returnsItemQuantityPair() throws Exception {
        JsonAdaptedItemQuantityPair pair = new JsonAdaptedItemQuantityPair(PAIR_2);
        assertEquals(PAIR_2, pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelTypeWithoutInventoryList_validItemDetails_returnsItemQuantityPair() throws Exception {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, VALID_QUANTITY, VALID_COST_PRICE, VALID_SELL_PRICE);
        ItemQuantityPair validPair = PAIR_2.getImmutableItemCopy();
        assertEquals(validPair, pair.toModelType());
    }

    @Test
    public void toModelTypeWithInventoryList_nullItemName_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(null, VALID_QUANTITY, VALID_COST_PRICE, VALID_SELL_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "ItemName");
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelTypeWithoutInventoryList_nullItemName_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(null, VALID_QUANTITY, VALID_COST_PRICE, VALID_SELL_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "ItemName");
        assertThrows(IllegalValueException.class, expectedMessage, pair::toModelType);
    }

    @Test
    public void toModelTypeWithInventoryList_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, null, VALID_COST_PRICE, VALID_SELL_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Quantity");
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelTypeWithoutInventoryList_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, null, VALID_COST_PRICE, VALID_SELL_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Quantity");
        assertThrows(IllegalValueException.class, expectedMessage, pair::toModelType);
    }

    @Test
    public void toModelTypeWithInventoryList_nullCostPrice_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, VALID_QUANTITY, null, VALID_SELL_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Price");
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelTypeWithoutInventoryList_nullCostPrice_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, VALID_QUANTITY, null, VALID_SELL_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Price");
        assertThrows(IllegalValueException.class, expectedMessage, pair::toModelType);
    }

    @Test
    public void toModelTypeWithInventoryList_nullSellPrice_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, VALID_QUANTITY, VALID_COST_PRICE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Price");
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelTypeWithoutInventoryList_nullSellPrice_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, VALID_QUANTITY, VALID_COST_PRICE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Price");
        assertThrows(IllegalValueException.class, expectedMessage, pair::toModelType);
    }

    @Test
    public void toModelTypeWithInventoryList_invalidItemName_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(INVALID_ITEM_NAME, VALID_QUANTITY, VALID_COST_PRICE, VALID_SELL_PRICE);
        String expectedMessage = String.format(MESSAGE_ITEM_NOT_FOUND, INVALID_ITEM_NAME);
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelTypeWithInventoryList_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, INVALID_QUANTITY, VALID_COST_PRICE, VALID_SELL_PRICE);
        String expectedMessage = String.format(Quantity.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelTypeWithInventoryList_invalidCostPrice_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, INVALID_QUANTITY, INVALID_COST_PRICE, VALID_SELL_PRICE);
        String expectedMessage = String.format(Quantity.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelTypeWithInventoryList_invalidSellPrice_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, INVALID_QUANTITY, INVALID_COST_PRICE, INVALID_SELL_PRICE);
        String expectedMessage = String.format(Quantity.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelTypeWithInventoryList_inconsistentCostPrice_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, VALID_QUANTITY,
                INCONSISTENT_COST_PRICE, VALID_SELL_PRICE);
        String expectedMessage = String.format(MESSAGE_ITEM_NOT_FOUND, VALID_ITEM_NAME);
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }

    @Test
    public void toModelTypeWithInventoryList_inconsistentSellPrice_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair pair =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, VALID_QUANTITY,
                VALID_COST_PRICE, INCONSISTENT_SELL_PRICE);
        String expectedMessage = String.format(MESSAGE_ITEM_NOT_FOUND, VALID_ITEM_NAME);
        assertThrows(IllegalValueException.class, expectedMessage, () -> pair.toModelType(VALID_INVENTORY_LIST));
    }
}
