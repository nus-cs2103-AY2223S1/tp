package seedu.foodrem.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.foodrem.testutil.Assert.assertThrows;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.item.ItemBoughtDate;
import seedu.foodrem.model.item.ItemExpiryDate;
import seedu.foodrem.model.item.ItemName;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.model.item.ItemUnit;

public class ParserUtilTest {
    public static final String VALID_ITEM_NAME = "Potatoes";
    public static final String VALID_ITEM_QUANTITY = "10";
    public static final String VALID_ITEM_UNIT = "kg";
    public static final String VALID_ITEM_BOUGHT_DATE = "2022-11-11";
    public static final String VALID_ITEM_EXPIRY_DATE = "2022-11-11";

    public static final String INVALID_ITEM_NAME = "Potatoes|/";
    public static final String INVALID_ITEM_QUANTITY = "10|/";
    public static final String INVALID_ITEM_UNIT = "kg|/";
    public static final String INVALID_ITEM_BOUGHT_DATE = "2022-11-11|/";
    public static final String INVALID_ITEM_EXPIRY_DATE = "2022-11-11|/";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName(null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        // TODO: Rewrite once validation logic is done
        assert true;
        // assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_ITEM_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        ItemName expectedName = new ItemName(VALID_ITEM_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_ITEM_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_ITEM_NAME + WHITESPACE;
        ItemName expectedName = new ItemName(VALID_ITEM_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseQuantity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuantity(null));
    }

    @Test
    public void parseQuantity_invalidValue_throwsParseException() {
        // TODO: Rewrite once validation logic is done
        assert true;
        // assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_ITEM_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithoutWhitespace_returnsQuantity() throws Exception {
        ItemQuantity expectedQuantity = new ItemQuantity(VALID_ITEM_QUANTITY);
        assertEquals(expectedQuantity, ParserUtil.parseQuantity(VALID_ITEM_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithWhitespace_returnsTrimmedQuantity() throws Exception {
        String expectedQuantityWithWhitespace = WHITESPACE + VALID_ITEM_QUANTITY + WHITESPACE;
        ItemQuantity expectedQuantity = new ItemQuantity(VALID_ITEM_QUANTITY);
        assertEquals(expectedQuantity, ParserUtil.parseQuantity(expectedQuantityWithWhitespace));
    }

    @Test
    public void parseUnit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseUnit(null));
    }

    @Test
    public void parseUnit_invalidValue_throwsParseException() {
        // TODO: Rewrite once validation logic is done
        assert true;
        // assertThrows(ParseException.class, () -> ParserUtil.parseUnit(INVALID_ITEM_UNIT));
    }

    @Test
    public void parseUnit_validValueWithoutWhitespace_returnsUnit() throws Exception {
        ItemUnit expectedUnit = new ItemUnit(VALID_ITEM_UNIT);
        assertEquals(expectedUnit, ParserUtil.parseUnit(VALID_ITEM_UNIT));
    }

    @Test
    public void parseUnit_validValueWithWhitespace_returnsTrimmedUnit() throws Exception {
        String unitWithWhitespace = WHITESPACE + VALID_ITEM_UNIT + WHITESPACE;
        ItemUnit expectedUnit = new ItemUnit(VALID_ITEM_UNIT);
        assertEquals(expectedUnit, ParserUtil.parseUnit(unitWithWhitespace));
    }

    @Test
    public void parseBoughtDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBoughtDate(null));
    }

    @Test
    public void parseBoughtDate_invalidValue_throwsParseException() {
        // TODO: Rewrite once validation logic is done
        assert true;
        // assertThrows(ParseException.class, () -> ParserUtil.parseBoughtDate(INVALID_ITEM_BOUGHT_DATE));
    }

    @Test
    public void parseBoughtDate_validValueWithoutWhitespace_returnsBoughtDate() throws Exception {
        ItemBoughtDate expectedBoughtDate = new ItemBoughtDate(VALID_ITEM_BOUGHT_DATE);
        assertEquals(expectedBoughtDate, ParserUtil.parseBoughtDate(VALID_ITEM_BOUGHT_DATE));
    }

    @Test
    public void parseBoughtDate_validValueWithWhitespace_returnsTrimmedBoughtDate() throws Exception {
        String boughtDateWithWhitespace = WHITESPACE + VALID_ITEM_BOUGHT_DATE + WHITESPACE;
        ItemBoughtDate expectedBoughtDate = new ItemBoughtDate(VALID_ITEM_BOUGHT_DATE);
        assertEquals(expectedBoughtDate, ParserUtil.parseBoughtDate(boughtDateWithWhitespace));
    }

    @Test
    public void parseExpiryDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExpiryDate(null));
    }

    @Test
    public void parseExpiryDate_invalidValue_throwsParseException() {
        // TODO: Rewrite once validation logic is done
        assert true;
        // assertThrows(ParseException.class, () -> ParserUtil.parseExpiryDate(INVALID_ITEM_BOUGHT_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithoutWhitespace_returnsExpiryDate() throws Exception {
        ItemExpiryDate expectedExpiryDate = new ItemExpiryDate(VALID_ITEM_BOUGHT_DATE);
        assertEquals(expectedExpiryDate, ParserUtil.parseExpiryDate(VALID_ITEM_BOUGHT_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithWhitespace_returnsTrimmedExpiryDate() throws Exception {
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
    //public void parseTag_invalidValue_throwsParseException() {
    //    assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    //}
    //
    //@Test
    //public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
    //    Tag expectedTag = new Tag(VALID_TAG_1);
    //    assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    //}
    //
    //@Test
    //public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
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
    //public void parseTags_collectionWithInvalidTags_throwsParseException() {
    //    assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    //}
    //
    //@Test
    //public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
    //    assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    //}
    //
    //@Test
    //public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
    //    Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
    //    Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));
    //
    //    assertEquals(expectedTagSet, actualTagSet);
    //}
}
