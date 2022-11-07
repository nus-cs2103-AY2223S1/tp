package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.IntegerUtil.getStringFromInt;
import static seedu.address.storage.JsonAdaptedSupplyItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSupplyItems.EGGS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;

public class JsonAdaptedSupplyItemTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_PRICE = "1.20";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = EGGS.getName();
    private static final String VALID_CURRENT_STOCK = getStringFromInt(EGGS.getCurrentStock());
    private static final String VALID_MIN_STOCK = getStringFromInt(EGGS.getMinStock());
    private static final String VALID_SUPPLIER_NAME = EGGS.getSupplier().getName().fullName;
    private static final String VALID_SUPPLIER_PHONE = EGGS.getSupplier().getPhone().value;
    private static final String VALID_PRICE = EGGS.getPrice().toString();
    private static final String VALID_ITEM = EGGS.getSupplier().getItem().toString();
    private static final String VALID_ADDRESS = EGGS.getSupplier().getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = EGGS.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validSupplyItemDetails_returnsSupplyItem() throws Exception {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(EGGS);
        assertEquals(EGGS, supplyItem.toModelType());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(null, VALID_CURRENT_STOCK, VALID_MIN_STOCK,
                VALID_SUPPLIER_NAME, VALID_SUPPLIER_PHONE, VALID_PRICE, VALID_ITEM, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "name");
        assertThrows(IllegalValueException.class, expectedMessage, supplyItem::toModelType);
    }

    @Test
    public void toModelType_nullCurrentStock_throwsIllegalValueException() {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(VALID_NAME, null, VALID_MIN_STOCK,
                VALID_SUPPLIER_NAME, VALID_SUPPLIER_PHONE, VALID_PRICE, VALID_ITEM, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "currentStock");
        assertThrows(IllegalValueException.class, expectedMessage, supplyItem::toModelType);
    }

    @Test
    public void toModelType_nullMinStock_throwsIllegalValueException() {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(VALID_NAME, VALID_CURRENT_STOCK, null,
                VALID_SUPPLIER_NAME, VALID_SUPPLIER_PHONE, VALID_PRICE, VALID_ITEM, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "minStock");
        assertThrows(IllegalValueException.class, expectedMessage, supplyItem::toModelType);
    }

    @Test
    public void toModelType_nullSupplierName_throwsIllegalValueException() {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(VALID_NAME, VALID_CURRENT_STOCK, VALID_MIN_STOCK,
                null, VALID_SUPPLIER_PHONE, VALID_PRICE, VALID_ITEM, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "supplierName");
        assertThrows(IllegalValueException.class, expectedMessage, supplyItem::toModelType);
    }

    @Test
    public void toModelType_invalidSupplierName_throwsIllegalValueException() {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(VALID_NAME, VALID_CURRENT_STOCK, VALID_MIN_STOCK,
                INVALID_NAME, VALID_SUPPLIER_PHONE, VALID_PRICE, VALID_ITEM, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, supplyItem::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(VALID_NAME, VALID_CURRENT_STOCK, VALID_MIN_STOCK,
                VALID_SUPPLIER_NAME, VALID_SUPPLIER_PHONE, null, VALID_ITEM, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "price");
        assertThrows(IllegalValueException.class, expectedMessage, supplyItem::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(VALID_NAME, VALID_CURRENT_STOCK, VALID_MIN_STOCK,
                VALID_SUPPLIER_NAME, VALID_SUPPLIER_PHONE, INVALID_PRICE, VALID_ITEM, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, supplyItem::toModelType);
    }

    @Test
    public void toModelType_nullItem_throwsIllegalValueException() {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(VALID_NAME, VALID_CURRENT_STOCK, VALID_MIN_STOCK,
                VALID_SUPPLIER_NAME, VALID_SUPPLIER_PHONE, VALID_PRICE, null, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "personItem");
        assertThrows(IllegalValueException.class, expectedMessage, supplyItem::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(VALID_NAME, VALID_CURRENT_STOCK, VALID_MIN_STOCK,
                VALID_SUPPLIER_NAME, VALID_SUPPLIER_PHONE, VALID_PRICE, VALID_ITEM, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "address");
        assertThrows(IllegalValueException.class, expectedMessage, supplyItem::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(VALID_NAME, VALID_CURRENT_STOCK, VALID_MIN_STOCK,
                VALID_SUPPLIER_NAME, VALID_SUPPLIER_PHONE, VALID_PRICE, VALID_ITEM, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, supplyItem::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(VALID_NAME, VALID_CURRENT_STOCK, VALID_MIN_STOCK,
                VALID_SUPPLIER_NAME, null, VALID_PRICE, VALID_ITEM, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "phone");
        assertThrows(IllegalValueException.class, expectedMessage, supplyItem::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(VALID_NAME, VALID_CURRENT_STOCK, VALID_MIN_STOCK,
                VALID_SUPPLIER_NAME, INVALID_PHONE, VALID_PRICE, VALID_ITEM, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, supplyItem::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedSupplyItem supplyItem = new JsonAdaptedSupplyItem(VALID_NAME, VALID_CURRENT_STOCK, VALID_MIN_STOCK,
                VALID_SUPPLIER_NAME, VALID_SUPPLIER_PHONE, VALID_PRICE, null, VALID_ADDRESS, invalidTags);
        assertThrows(IllegalValueException.class, supplyItem::toModelType);
    }
}
