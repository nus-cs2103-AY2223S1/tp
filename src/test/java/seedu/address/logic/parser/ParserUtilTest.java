package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.entry.EntryType;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {

    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_ENTRYTYPE = "c";
    private static final String INVALID_AMOUNT = "1ex";
    private static final String INVALID_DATE = "1-00-00010";
    private static final String VALID_DATE = "20-08-2020";
    private static final String VALID_AMOUNT = "1.00";
    private static final String VALID_ENTRYTYPE = "e";
    private static final String VALID_INCOMETYPE = "i";
    private static final String VALID_DESCRIPTION = "Lunch";

    private static final String VALID_TAG_1 = "Food";
    private static final String VALID_TAG_2 = "Allowance";

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


    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription(null));
    }

    @Test
    public void parseDescrioption_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validInput_success() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validInputWithWhiteSpace_success() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(WHITESPACE + VALID_DESCRIPTION + WHITESPACE));
    }

    @Test
    public void parseEntryType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEntryType(null));
    }

    @Test
    public void parseDEntryType_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEntryType(INVALID_ENTRYTYPE));
    }
    @Test
    public void parseEntryType_validInput_success() throws Exception {
        EntryType expectedEntryType = new EntryType(VALID_ENTRYTYPE);
        assertEquals(expectedEntryType, ParserUtil.parseEntryType(VALID_ENTRYTYPE));
    }

    @Test
    public void parseEntryType_validInputWithWhiteSpace_success() throws Exception {
        EntryType expectedEntryType = new EntryType(VALID_ENTRYTYPE);
        assertEquals(expectedEntryType, ParserUtil.parseEntryType(WHITESPACE + VALID_ENTRYTYPE + WHITESPACE));
    }

    @Test
    public void parseAmount_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAmount(null));
    }

    @Test
    public void parseDAmount_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAmount(INVALID_AMOUNT));
    }
    @Test
    public void parseAmount_validInput_success() throws Exception {
        Amount expectedAmount = new Amount(VALID_AMOUNT);
        assertEquals(expectedAmount, ParserUtil.parseAmount(VALID_AMOUNT));
    }

    @Test
    public void parseAmount_validInputWithWhiteSpace_success() throws Exception {
        Amount expectedAmount = new Amount(VALID_AMOUNT);
        assertEquals(expectedAmount, ParserUtil.parseAmount(WHITESPACE + VALID_AMOUNT + WHITESPACE));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEntryType(null));
    }

    @Test
    public void parseDate_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEntryType(INVALID_DATE));
    }
    @Test
    public void parseDate_validInput_success() throws Exception {
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE));
    }

    @Test
    public void parseDate_validInputWithWhiteSpace_success() throws Exception {
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(WHITESPACE + VALID_DATE + WHITESPACE));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        EntryType expenditureType = new EntryType(VALID_ENTRYTYPE);
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(expenditureType, null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        EntryType expenditureType = new EntryType(VALID_ENTRYTYPE);
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(expenditureType, INVALID_TAG));
    }

    @Test
    public void parseExpenditureTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        EntryType expenditureType = new EntryType(VALID_ENTRYTYPE);
        Tag expectedTag = new Tag(expenditureType, VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(expenditureType, VALID_TAG_1));
    }

    @Test
    public void parseIncomeTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        EntryType incomeType = new EntryType(VALID_INCOMETYPE);
        Tag expectedTag = new Tag(incomeType, VALID_TAG_2);
        assertEquals(expectedTag, ParserUtil.parseTag(incomeType, VALID_TAG_2));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        EntryType expenditureType = new EntryType(VALID_ENTRYTYPE);
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(expenditureType, VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(expenditureType, tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        EntryType expenditureType = new EntryType(VALID_ENTRYTYPE);
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(expenditureType, null));
    }

    // @Test
    // public void parseTags_collectionWithInvalidTags_throwsParseException() {
    //     assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    //
    // @Test
    // public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
    //     assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    //
    // @Test
    // public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
    //     Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
    //     Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2) //
    //     assertEquals(expectedTagSet, actualTagSet);
    // }
}
