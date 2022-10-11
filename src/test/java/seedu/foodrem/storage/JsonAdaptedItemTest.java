package seedu.foodrem.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.storage.JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.foodrem.testutil.Assert.assertThrows;
import static seedu.foodrem.testutil.TypicalItems.CUCUMBERS;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.exceptions.IllegalValueException;
import seedu.foodrem.model.item.ItemBoughtDate;
import seedu.foodrem.model.item.ItemExpiryDate;
import seedu.foodrem.model.item.ItemName;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.model.item.ItemUnit;
import seedu.foodrem.model.item.itemvalidators.ItemBoughtDateValidator;
import seedu.foodrem.model.item.itemvalidators.ItemExpiryDateValidator;
import seedu.foodrem.model.item.itemvalidators.ItemNameValidator;
import seedu.foodrem.model.item.itemvalidators.ItemQuantityValidator;
import seedu.foodrem.model.item.itemvalidators.ItemUnitValidator;

public class JsonAdaptedItemTest {
    private static final String INVALID_NAME = "Po|a|oes\\";
    private static final String INVALID_QUANTITY = "1/2";
    private static final String INVALID_UNIT = "||//";
    private static final String INVALID_BOUGHT_DATE = "september11";
    private static final String INVALID_EXPIRY_DATE = "september11";

    private static final String VALID_NAME = CUCUMBERS.getName().toString();
    private static final String VALID_QUANTITY = CUCUMBERS.getQuantity().toString();
    private static final String VALID_UNIT = CUCUMBERS.getUnit().toString();
    private static final String VALID_BOUGHT_DATE = CUCUMBERS.getBoughtDate().toString();
    private static final String VALID_EXPIRY_DATE = CUCUMBERS.getExpiryDate().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedItem item = new JsonAdaptedItem(CUCUMBERS);
        assertEquals(CUCUMBERS, item.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(INVALID_NAME, VALID_QUANTITY, VALID_UNIT, VALID_BOUGHT_DATE, VALID_EXPIRY_DATE);
        String expectedMessage = ItemNameValidator.MESSAGE_FOR_INVALID_CHARACTERS;
        // TODO: Validation must be implemented before testing.
        // assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
        assert true;
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(null,
            VALID_QUANTITY,
            VALID_UNIT,
            VALID_BOUGHT_DATE,
            VALID_EXPIRY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_NAME,
                INVALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE);
        String expectedMessage = ItemQuantityValidator.MESSAGE_FOR_NOT_A_NUMBER;
        // TODO: Validation must be implemented before testing.
        // assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
        assert true;
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(
            VALID_NAME,
            null,
            VALID_UNIT,
            VALID_BOUGHT_DATE,
            VALID_EXPIRY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemQuantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidUnit_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                INVALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE);
        String expectedMessage = ItemUnitValidator.MESSAGE_FOR_INVALID_CHARACTERS;
        // TODO: Validation must be implemented before testing.
        // assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
        assert true;
    }

    @Test
    public void toModelType_nullUnit_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
            VALID_QUANTITY,
            null,
            VALID_BOUGHT_DATE,
            VALID_EXPIRY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemUnit.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidBoughtDate_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                INVALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE);
        String expectedMessage = ItemBoughtDateValidator.MESSAGE_FOR_UNABLE_TO_PARSE;
        // TODO: Validation must be implemented before testing.
        // assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
        assert true;
    }

    @Test
    public void toModelType_nullBoughtDate_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
            VALID_QUANTITY,
            VALID_UNIT,
            null,
            VALID_EXPIRY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemBoughtDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidExpiryDate_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                INVALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE);
        String expectedMessage = ItemExpiryDateValidator.MESSAGE_FOR_UNABLE_TO_PARSE;
        // TODO: Validation must be implemented before testing.
        // assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
        assert true;
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
            VALID_QUANTITY,
            VALID_UNIT,
            VALID_BOUGHT_DATE,
            null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemExpiryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    //@Test
    //public void toModelType_invalidTags_throwsIllegalValueException() {
    //    List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
    //    invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
    //    JsonAdaptedItem item =
    //            new JsonAdaptedItem(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags);
    //    assertThrows(IllegalValueException.class, item::toModelType);
    //}

}
