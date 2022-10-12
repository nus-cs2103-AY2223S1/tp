package gim.logic.parser;

import static gim.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gim.logic.parser.exceptions.ParseException;
import gim.model.date.Date;
import gim.model.exercise.Name;
import gim.model.exercise.Reps;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;


public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_WEIGHT = "+651234";
    private static final String INVALID_REP = " ";
    private static final String INVALID_SETS = " ";
    private static final String INVALID_DATE = "11/111/2020";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_WEIGHT = "100";
    private static final String VALID_REP = "1";
    private static final String VALID_SETS = "3";
    private static final String VALID_DATE_1 = "11/01/2022";
    private static final String VALID_DATE_2 = "05/05/2022";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () -> ParserUtil.parseIndex(
                Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_EXERCISE, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_EXERCISE, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseWeight_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseWeight((String) null));
    }

    @Test
    public void parseWeight_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWeight(INVALID_WEIGHT));
    }

    @Test
    public void parseWeight_validValueWithoutWhitespace_returnsWeight() throws Exception {
        Weight expectedWeight = new Weight(VALID_WEIGHT);
        assertEquals(expectedWeight, ParserUtil.parseWeight(VALID_WEIGHT));
    }

    @Test
    public void parseWeight_validValueWithWhitespace_returnsTrimmedWeight() throws Exception {
        String weightWithWhitespace = WHITESPACE + VALID_WEIGHT + WHITESPACE;
        Weight expectedWeight = new Weight(VALID_WEIGHT);
        assertEquals(expectedWeight, ParserUtil.parseWeight(weightWithWhitespace));
    }

    @Test
    public void parseRep_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRep((String) null));
    }

    @Test
    public void parseRep_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRep(INVALID_REP));
    }

    @Test
    public void parseRep_validValueWithoutWhitespace_returnsRep() throws Exception {
        Reps expectedReps = new Reps(VALID_REP);
        assertEquals(expectedReps, ParserUtil.parseRep(VALID_REP));
    }

    @Test
    public void parseRep_validValueWithWhitespace_returnsTrimmedRep() throws Exception {
        String repWithWhitespace = WHITESPACE + VALID_REP + WHITESPACE;
        Reps expectedReps = new Reps(VALID_REP);
        assertEquals(expectedReps, ParserUtil.parseRep(repWithWhitespace));
    }

    @Test
    public void parseSets_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSets((String) null));
    }

    @Test
    public void parseSets_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSets(INVALID_SETS));
    }

    @Test
    public void parseSets_validValueWithoutWhitespace_returnsSets() throws Exception {
        Sets expectedSets = new Sets(VALID_SETS);
        assertEquals(expectedSets, ParserUtil.parseSets(VALID_SETS));
    }

    @Test
    public void parseSets_validValueWithWhitespace_returnsTrimmedSets() throws Exception {
        String setsWithWhitespace = WHITESPACE + VALID_SETS + WHITESPACE;
        Sets expectedSets = new Sets(VALID_SETS);
        assertEquals(expectedSets, ParserUtil.parseSets(setsWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Date expectedDate = new Date(VALID_DATE_1);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_DATE_1 + WHITESPACE;
        Date expectedDate = new Date(VALID_DATE_1);
        assertEquals(expectedDate, ParserUtil.parseDate(tagWithWhitespace));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate(null));
    }

    @Test
    public void parseDate_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_collectionWithValidTags_returnsTagSet() throws Exception {
        Date actualDate = ParserUtil.parseDate(VALID_DATE_1);
        Date expectedDate = new Date(VALID_DATE_1);

        assertEquals(expectedDate, actualDate);
    }
}
