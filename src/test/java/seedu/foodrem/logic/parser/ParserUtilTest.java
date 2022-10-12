package seedu.foodrem.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.foodrem.testutil.Assert.assertThrows;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.foodrem.model.item.ItemBoughtDate;
import seedu.foodrem.model.item.ItemExpiryDate;
import seedu.foodrem.model.item.ItemName;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.model.item.ItemUnit;

public class ParserUtilTest {
    public static final String VALID_ITEM_NAME = "Potatoes";
    public static final String VALID_ITEM_QUANTITY = "10";
    public static final String VALID_ITEM_UNIT = "kg";
    public static final String VALID_ITEM_BOUGHT_DATE = "11-11-2022";
    public static final String VALID_ITEM_EXPIRY_DATE = "11-11-2022";

    public static final String INVALID_ITEM_NAME = "Potatoes|/";
    public static final String INVALID_ITEM_QUANTITY = "10|/";
    public static final String INVALID_ITEM_UNIT = "kg|/";
    public static final String INVALID_ITEM_BOUGHT_DATE = "11-11-2022|/";
    public static final String INVALID_ITEM_EXPIRY_DATE = "11-11-2022|/";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, MESSAGE_INVALID_INDEX, ()
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
        ItemBoughtDate expectedBoughtDate = new ItemBoughtDate(VALID_ITEM_BOUGHT_DATE);
        assertEquals(expectedBoughtDate, ParserUtil.parseBoughtDate(VALID_ITEM_BOUGHT_DATE));
    }

    @Test
    public void parseBoughtDate_validValueWithWhitespace_returnsTrimmedBoughtDate() {
        String boughtDateWithWhitespace = WHITESPACE + VALID_ITEM_BOUGHT_DATE + WHITESPACE;
        ItemBoughtDate expectedBoughtDate = new ItemBoughtDate(VALID_ITEM_BOUGHT_DATE);
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
        ItemExpiryDate expectedExpiryDate = new ItemExpiryDate(VALID_ITEM_BOUGHT_DATE);
        assertEquals(expectedExpiryDate, ParserUtil.parseExpiryDate(VALID_ITEM_BOUGHT_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithWhitespace_returnsTrimmedExpiryDate() {
        String expireDateWithWhitespace = WHITESPACE + VALID_ITEM_BOUGHT_DATE + WHITESPACE;
        ItemExpiryDate expectedExpiryDate = new ItemExpiryDate(VALID_ITEM_BOUGHT_DATE);
        assertEquals(expectedExpiryDate, ParserUtil.parseExpiryDate(expireDateWithWhitespace));
    }

    // TODO: Implement test for tags one functionality is added
    //@Test
    //public void parseTag_null_throwsNullPointerException() {
    //    assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    //}
    //
    //@Test
    //public void parseTag_invalidValue_throwsIllegalArgumentException() {
    //    assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    //}
    //
    //@Test
    //public void parseTag_validValueWithoutWhitespace_returnsTag() {
    //    Tag expectedTag = new Tag(VALID_TAG_1);
    //    assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    //}
    //
    //@Test
    //public void parseTag_validValueWithWhitespace_returnsTrimmedTag() {
    //    String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
    //    Tag expectedTag = new Tag(VALID_TAG_1);
    //    assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    //}

    //@Test
    //public void parseTags_null_throwsNullPointerException() {
    //    assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    //}
    //
    //@Test
    //public void parseTags_collectionWithInvalidTags_throwsIllegalArgumentException() {
    //    assertThrows(IllegalArgumentException.class,
    //    () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    //}
    //
    //@Test
    //public void parseTags_emptyCollection_returnsEmptySet() {
    //    assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    //}
    //
    //@Test
    //public void parseTags_collectionWithValidTags_returnsTagSet() {
    //    Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
    //    Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));
    //
    //    assertEquals(expectedTagSet, actualTagSet);
    //}
}
