package seedu.foodrem.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.model.item.itemvalidators.ItemBoughtDateValidator.MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE;
import static seedu.foodrem.model.item.itemvalidators.ItemExpiryDateValidator.MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE;
import static seedu.foodrem.model.item.itemvalidators.ItemNameValidator.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME;
import static seedu.foodrem.model.item.itemvalidators.ItemQuantityValidator.MESSAGE_FOR_NOT_A_NUMBER;
import static seedu.foodrem.model.item.itemvalidators.ItemUnitValidator.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT;
import static seedu.foodrem.storage.JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.foodrem.testutil.Assert.assertThrows;
import static seedu.foodrem.testutil.TypicalItems.CUCUMBERS;

import org.junit.jupiter.api.Test;

import seedu.foodrem.model.item.ItemBoughtDate;
import seedu.foodrem.model.item.ItemExpiryDate;
import seedu.foodrem.model.item.ItemName;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.model.item.ItemUnit;

public class JsonAdaptedItemTest {
    private static final String INVALID_NAME = "Po|a|oes\\";
    private static final String INVALID_QUANTITY = "1/2";
    private static final String INVALID_UNIT = "||//";
    private static final String INVALID_BOUGHT_DATE = "september11";
    private static final String INVALID_EXPIRY_DATE = "september11";

    private static final String VALID_NAME = "Potatoes";
    private static final String VALID_QUANTITY = "10";
    private static final String VALID_UNIT = "kg";
    private static final String VALID_BOUGHT_DATE = "11-11-2022";
    private static final String VALID_EXPIRY_DATE = "11-11-2022";

    @Test
    public void toModelType_validItemDetails_returnsItem() {
        JsonAdaptedItem item = new JsonAdaptedItem(CUCUMBERS);
        assertEquals(CUCUMBERS, item.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalArgumentException() {
        JsonAdaptedItem item =
                new JsonAdaptedItem(INVALID_NAME, VALID_QUANTITY, VALID_UNIT, VALID_BOUGHT_DATE, VALID_EXPIRY_DATE);
        assertThrows(IllegalArgumentException.class, MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME, item::toModelType);
        assert true;
    }

    @Test
    public void toModelType_nullName_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(null,
                VALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemName.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalArgumentException() {
        JsonAdaptedItem item =
                new JsonAdaptedItem(VALID_NAME,
                        INVALID_QUANTITY,
                        VALID_UNIT,
                        VALID_BOUGHT_DATE,
                        VALID_EXPIRY_DATE);
        assertThrows(IllegalArgumentException.class, MESSAGE_FOR_NOT_A_NUMBER, item::toModelType);
        assert true;
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(
                VALID_NAME,
                null,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemQuantity.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidUnit_throwsIllegalArgumentException() {
        JsonAdaptedItem item =
                new JsonAdaptedItem(VALID_NAME,
                        VALID_QUANTITY,
                        INVALID_UNIT,
                        VALID_BOUGHT_DATE,
                        VALID_EXPIRY_DATE);
        assertThrows(IllegalArgumentException.class, MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT, item::toModelType);
        assert true;
    }

    @Test
    public void toModelType_nullUnit_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                null,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemUnit.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidBoughtDate_throwsIllegalArgumentException() {
        JsonAdaptedItem item =
                new JsonAdaptedItem(VALID_NAME,
                        VALID_QUANTITY,
                        VALID_UNIT,
                        INVALID_BOUGHT_DATE,
                        VALID_EXPIRY_DATE);
        assertThrows(IllegalArgumentException.class, MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE, item::toModelType);
    }

    @Test
    public void toModelType_nullBoughtDate_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                null,
                VALID_EXPIRY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemBoughtDate.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidExpiryDate_throwsIllegalArgumentException() {
        JsonAdaptedItem item =
                new JsonAdaptedItem(VALID_NAME,
                        VALID_QUANTITY,
                        VALID_UNIT,
                        VALID_BOUGHT_DATE,
                        INVALID_EXPIRY_DATE);
        assertThrows(IllegalArgumentException.class, MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE, item::toModelType);
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemExpiryDate.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, item::toModelType);
    }

    //@Test
    //public void toModelType_invalidTags_throwsIllegalArgumentException() {
    //    List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
    //    invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
    //    JsonAdaptedItem item =
    //            new JsonAdaptedItem(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags);
    //    assertThrows(IllegalArgumentException.class, item::toModelType);
    //}

}
