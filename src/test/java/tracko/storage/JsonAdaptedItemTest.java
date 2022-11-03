package tracko.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tracko.storage.JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT;
import static tracko.testutil.Assert.assertThrows;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_2;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import tracko.commons.exceptions.IllegalValueException;
import tracko.model.item.Description;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;

public class JsonAdaptedItemTest {
    private static final String INVALID_ITEM_NAME = "!@#$%^&*()";
    private static final String INVALID_DESCRIPTION = "";
    private static final Integer INVALID_QUANTITY = -1;
    private static final Double INVALID_COST_PRICE = -1.00;
    private static final Double INVALID_SELL_PRICE = -1.00;

    private static final String VALID_ITEM_NAME = INVENTORY_ITEM_2.getItemName().value;
    private static final String VALID_DESCRIPTION = INVENTORY_ITEM_2.getDescription().value;
    private static final Integer VALID_QUANTITY = INVENTORY_ITEM_2.getTotalQuantity().value;
    private static final Double VALID_COST_PRICE = INVENTORY_ITEM_2.getCostPrice().value;
    private static final Double VALID_SELL_PRICE = INVENTORY_ITEM_2.getSellPrice().value;
    private static final List<JsonAdaptedTag> VALID_TAGS = new ArrayList<>();

    @Test
    public void toModelType_validItemDetails_returnsInventoryItem() throws Exception {
        JsonAdaptedItem item = new JsonAdaptedItem(INVENTORY_ITEM_2);
        assertEquals(INVENTORY_ITEM_2, item.toModelType());
    }

    @Test
    public void toModelType_nullItemName_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(null, VALID_QUANTITY, VALID_DESCRIPTION, VALID_SELL_PRICE,
                VALID_COST_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "ItemName");
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidItemName_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(INVALID_ITEM_NAME, VALID_QUANTITY, VALID_DESCRIPTION, VALID_SELL_PRICE,
                VALID_COST_PRICE, VALID_TAGS);
        String expectedMessage = ItemName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_ITEM_NAME, null, VALID_DESCRIPTION, VALID_SELL_PRICE,
                VALID_COST_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Quantity");
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_ITEM_NAME, INVALID_QUANTITY, VALID_DESCRIPTION, VALID_SELL_PRICE,
                VALID_COST_PRICE, VALID_TAGS);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_ITEM_NAME, VALID_QUANTITY, null, VALID_SELL_PRICE,
                VALID_COST_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Description");
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_ITEM_NAME, VALID_QUANTITY, INVALID_DESCRIPTION, VALID_SELL_PRICE,
                VALID_COST_PRICE, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullSellPrice_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_ITEM_NAME, VALID_QUANTITY, VALID_DESCRIPTION, null,
                VALID_COST_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Price");
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidSellPrice_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_ITEM_NAME, VALID_QUANTITY, VALID_DESCRIPTION, INVALID_SELL_PRICE,
                VALID_COST_PRICE, VALID_TAGS);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullCostPrice_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_ITEM_NAME, VALID_QUANTITY, VALID_DESCRIPTION, VALID_COST_PRICE,
                null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Price");
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidCostPrice_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_ITEM_NAME, VALID_QUANTITY, VALID_DESCRIPTION, VALID_SELL_PRICE,
                INVALID_COST_PRICE, VALID_TAGS);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }
}
