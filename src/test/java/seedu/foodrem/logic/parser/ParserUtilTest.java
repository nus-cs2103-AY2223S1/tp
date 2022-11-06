package seedu.foodrem.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.testutil.Assert.assertThrows;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.foodrem.model.item.ItemBoughtDate;
import seedu.foodrem.model.item.ItemExpiryDate;
import seedu.foodrem.model.item.ItemName;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.model.item.ItemUnit;

/**
 * A class to test the ParserUtil.
 */
public class ParserUtilTest {
    private static final String VALID_ITEM_NAME = "Potatoes";
    private static final String VALID_ITEM_QUANTITY = "10";
    private static final String VALID_ITEM_UNIT = "kg";
    private static final String VALID_ITEM_BOUGHT_DATE = "11-11-2022";

    private static final String INVALID_ITEM_NAME = "Potatoes|/";
    private static final String INVALID_ITEM_QUANTITY = "10|/";
    private static final String INVALID_ITEM_UNIT = "kg|/";
    private static final String INVALID_ITEM_BOUGHT_DATE = "11-11-2022|/";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ParserUtil.MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() {
        // No whitespaces
        assertEquals(INDEX_FIRST_ITEM, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_ITEM, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseItemName(null));
    }

    @Test
    public void parseName_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseItemName(INVALID_ITEM_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() {
        ItemName expectedName = new ItemName(VALID_ITEM_NAME);
        assertEquals(expectedName, ParserUtil.parseItemName(VALID_ITEM_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() {
        String nameWithWhitespace = WHITESPACE + VALID_ITEM_NAME + WHITESPACE;
        ItemName expectedName = new ItemName(VALID_ITEM_NAME);
        assertEquals(expectedName, ParserUtil.parseItemName(nameWithWhitespace));
    }

    @Test
    public void parseQuantity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuantity(null));
    }

    @Test
    public void parseQuantity_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseQuantity(INVALID_ITEM_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithoutWhitespace_returnsQuantity() {
        ItemQuantity expectedQuantity = new ItemQuantity(VALID_ITEM_QUANTITY);
        assertEquals(expectedQuantity, ParserUtil.parseQuantity(VALID_ITEM_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithWhitespace_returnsTrimmedQuantity() {
        String expectedQuantityWithWhitespace = WHITESPACE + VALID_ITEM_QUANTITY + WHITESPACE;
        ItemQuantity expectedQuantity = new ItemQuantity(VALID_ITEM_QUANTITY);
        assertEquals(expectedQuantity, ParserUtil.parseQuantity(expectedQuantityWithWhitespace));
    }

    @Test
    public void parseUnit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseUnit(null));
    }

    @Test
    public void parseUnit_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseUnit(INVALID_ITEM_UNIT));
    }

    @Test
    public void parseUnit_validValueWithoutWhitespace_returnsUnit() {
        ItemUnit expectedUnit = new ItemUnit(VALID_ITEM_UNIT);
        assertEquals(expectedUnit, ParserUtil.parseUnit(VALID_ITEM_UNIT));
    }

    @Test
    public void parseUnit_validValueWithWhitespace_returnsTrimmedUnit() {
        String unitWithWhitespace = WHITESPACE + VALID_ITEM_UNIT + WHITESPACE;
        ItemUnit expectedUnit = new ItemUnit(VALID_ITEM_UNIT);
        assertEquals(expectedUnit, ParserUtil.parseUnit(unitWithWhitespace));
    }

    @Test
    public void parseBoughtDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBoughtDate(null));
    }

    @Test
    public void parseBoughtDate_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseBoughtDate(INVALID_ITEM_BOUGHT_DATE));
    }

    @Test
    public void parseBoughtDate_validValueWithoutWhitespace_returnsBoughtDate() {
        ItemBoughtDate expectedBoughtDate = ItemBoughtDate.of(VALID_ITEM_BOUGHT_DATE);
        assertEquals(expectedBoughtDate, ParserUtil.parseBoughtDate(VALID_ITEM_BOUGHT_DATE));
    }

    @Test
    public void parseBoughtDate_validValueWithWhitespace_returnsTrimmedBoughtDate() {
        String boughtDateWithWhitespace = WHITESPACE + VALID_ITEM_BOUGHT_DATE + WHITESPACE;
        ItemBoughtDate expectedBoughtDate = ItemBoughtDate.of(VALID_ITEM_BOUGHT_DATE);
        assertEquals(expectedBoughtDate, ParserUtil.parseBoughtDate(boughtDateWithWhitespace));
    }

    @Test
    public void parseExpiryDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExpiryDate(null));
    }

    @Test
    public void parseExpiryDate_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseExpiryDate(INVALID_ITEM_BOUGHT_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithoutWhitespace_returnsExpiryDate() {
        ItemExpiryDate expectedExpiryDate = ItemExpiryDate.of(VALID_ITEM_BOUGHT_DATE);
        assertEquals(expectedExpiryDate, ParserUtil.parseExpiryDate(VALID_ITEM_BOUGHT_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithWhitespace_returnsTrimmedExpiryDate() {
        String expireDateWithWhitespace = WHITESPACE + VALID_ITEM_BOUGHT_DATE + WHITESPACE;
        ItemExpiryDate expectedExpiryDate = ItemExpiryDate.of(VALID_ITEM_BOUGHT_DATE);
        assertEquals(expectedExpiryDate, ParserUtil.parseExpiryDate(expireDateWithWhitespace));
    }
}
