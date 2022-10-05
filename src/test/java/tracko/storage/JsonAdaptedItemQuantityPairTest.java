package tracko.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import tracko.commons.exceptions.IllegalValueException;

import static tracko.storage.JsonAdaptedItemQuantityPair.MISSING_FIELD_MESSAGE_FORMAT;
import static tracko.testutil.Assert.assertThrows;
import static tracko.testutil.TypicalItemQuantityPairs.PAIR_2;

public class JsonAdaptedItemQuantityPairTest {
    private static final String VALID_ITEM_NAME = PAIR_2.getItem();
    private static final String VALID_QUANTITY = PAIR_2.getQuantity().toString();

    @Test
    public void toModelType_validitemDetails_returnsitemquantitypair() throws Exception {
        JsonAdaptedItemQuantityPair pair = new JsonAdaptedItemQuantityPair(PAIR_2);
        assertEquals(PAIR_2, pair.toModelType());
    }

    @Test
    public void toModelType_nullItemName_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair order =
            new JsonAdaptedItemQuantityPair(null, VALID_QUANTITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Item name");
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedItemQuantityPair order =
            new JsonAdaptedItemQuantityPair(VALID_ITEM_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Quantity");
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

}
