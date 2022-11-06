package seedu.foodrem.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.foodrem.model.item.ItemBoughtDate;
import seedu.foodrem.model.item.ItemExpiryDate;
import seedu.foodrem.model.item.ItemName;
import seedu.foodrem.model.item.ItemPrice;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.model.item.ItemRemark;
import seedu.foodrem.model.item.ItemUnit;
import seedu.foodrem.testutil.TypicalItems;
import seedu.foodrem.testutil.TypicalTags;

public class JsonAdaptedItemTest {
    private static final String MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT =
            "The item unit should only contain alphanumeric characters, spaces and the following symbols "
                    + "[]{}()-+*=.,_'\"^$?@!#%&:;";
    private static final String MESSAGE_FOR_QUANTITY_NOT_A_NUMBER =
            "The item quantity should be a number.";
    private static final String MESSAGE_FOR_PRICE_NOT_A_NUMBER =
            "The item price should be a number.";
    private static final String MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME =
            "The item name should only contain alphanumeric characters, spaces and the following symbols "
                    + "[]{}()-+*=.,_'\"^$?@!#%&:;";
    private static final String MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE =
            "The item expiry date must follow the format dd-mm-yyyy.";
    private static final String MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE =
            "The item bought date must follow the format dd-mm-yyyy.";
    private static final String MESSAGE_FOR_INVALID_CHARACTERS_IN_REMARKS =
            "The item remark should only contain alphanumeric characters, spaces and the following symbols "
                    + "[]{}()-+*=.,_'\"^$?@!#%&:;";

    private static final String INVALID_NAME = "Po|a|oes\\";
    private static final String INVALID_QUANTITY = "1/2";
    private static final String INVALID_UNIT = "||//";
    private static final String INVALID_BOUGHT_DATE = "september11";
    private static final String INVALID_EXPIRY_DATE = "september11";
    private static final String INVALID_PRICE = "$10";
    private static final String INVALID_REMARKS = "@.@||";
    private static final String INVALID_TAG = "//";

    private static final String VALID_NAME = "Potatoes";
    private static final String VALID_QUANTITY = "10";
    private static final String VALID_UNIT = "kg";
    private static final String VALID_BOUGHT_DATE = "11-10-2022";
    private static final String VALID_EXPIRY_DATE = "11-11-2022";
    private static final String VALID_PRICE = "10.50";
    private static final String VALID_REMARKS = "For Mashed Potatoes";
    private static final List<JsonAdaptedTag> VALID_TAG_SET = Stream.of(TypicalTags.FRUITS, TypicalTags.VEGETABLES)
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toCollection(ArrayList::new));

    @Test
    public void toModelType_validItemDetails_returnsItem() {
        JsonAdaptedItem item = new JsonAdaptedItem(TypicalItems.CUCUMBERS);
        assertEquals(TypicalItems.CUCUMBERS, item.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(INVALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE,
                VALID_PRICE,
                VALID_REMARKS,
                VALID_TAG_SET);
        assertThrows(IllegalArgumentException.class, MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME, item::toModelType);
        assert true;
    }

    @Test
    public void toModelType_nullName_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(null,
                VALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE,
                VALID_PRICE,
                VALID_REMARKS,
                VALID_TAG_SET);
        String expectedMessage = String.format(JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT,
                ItemName.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                INVALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE,
                VALID_PRICE,
                VALID_REMARKS,
                VALID_TAG_SET);
        assertThrows(IllegalArgumentException.class, MESSAGE_FOR_QUANTITY_NOT_A_NUMBER, item::toModelType);
        assert true;
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(
                VALID_NAME,
                null,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE,
                VALID_PRICE,
                VALID_REMARKS,
                VALID_TAG_SET);
        String expectedMessage = String.format(JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT,
                ItemQuantity.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidUnit_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                INVALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE,
                VALID_PRICE,
                VALID_REMARKS,
                VALID_TAG_SET);
        assertThrows(IllegalArgumentException.class, MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT, item::toModelType);
        assert true;
    }

    @Test
    public void toModelType_nullUnit_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                null,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE,
                VALID_PRICE,
                VALID_REMARKS,
                VALID_TAG_SET);
        String expectedMessage = String.format(JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT,
                ItemUnit.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidBoughtDate_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                INVALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE,
                VALID_PRICE,
                VALID_REMARKS,
                VALID_TAG_SET);
        assertThrows(IllegalArgumentException.class, MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE, item::toModelType);
    }

    @Test
    public void toModelType_nullBoughtDate_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                null,
                VALID_EXPIRY_DATE,
                VALID_PRICE,
                VALID_REMARKS,
                VALID_TAG_SET);
        String expectedMessage = String.format(JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT,
                ItemBoughtDate.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidExpiryDate_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                INVALID_EXPIRY_DATE,
                VALID_PRICE,
                VALID_REMARKS,
                VALID_TAG_SET);
        assertThrows(IllegalArgumentException.class, MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE, item::toModelType);
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                null,
                VALID_PRICE,
                VALID_REMARKS,
                VALID_TAG_SET);
        String expectedMessage = String.format(JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT,
                ItemExpiryDate.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE,
                INVALID_PRICE,
                VALID_REMARKS,
                VALID_TAG_SET);
        assertThrows(IllegalArgumentException.class, MESSAGE_FOR_PRICE_NOT_A_NUMBER, item::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE,
                null,
                VALID_REMARKS,
                VALID_TAG_SET);
        String expectedMessage = String.format(JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT,
                ItemPrice.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidRemarks_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE,
                VALID_PRICE,
                INVALID_REMARKS,
                VALID_TAG_SET);
        assertThrows(IllegalArgumentException.class, MESSAGE_FOR_INVALID_CHARACTERS_IN_REMARKS, item::toModelType);
    }

    @Test
    public void toModelType_nullRemarks_throwsIllegalArgumentException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE,
                VALID_PRICE,
                null,
                VALID_TAG_SET);
        String expectedMessage = String.format(JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT,
                ItemRemark.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalArgumentException() {
        List<JsonAdaptedTag> invalidTags = VALID_TAG_SET;

        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE,
                VALID_PRICE,
                VALID_REMARKS,
                invalidTags);
        assertThrows(IllegalArgumentException.class, item::toModelType);
    }
}
