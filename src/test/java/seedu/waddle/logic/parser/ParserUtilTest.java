package seedu.waddle.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.waddle.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_FIRST_ITINERARY;

import org.junit.jupiter.api.Test;

import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.model.itinerary.People;

public class ParserUtilTest {
    private static final String INVALID_DESCRIPTION = "W!nter";
    private static final String INVALID_COUNTRY = "+651234";
    private static final String INVALID_START_DATE = "2022/03/15";
    private static final String INVALID_END_DATE = "202-04-15";
    private static final String INVALID_PEOPLE = "five";

    private static final String VALID_DESCRIPTION = "Winter Trip";
    private static final String VALID_COUNTRY = "Finland";
    private static final String VALID_START_DATE = "2023-03-15";
    private static final String VALID_END_DATE = "2023-04-15";
    private static final String VALID_PEOPLE = "5";

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
        assertEquals(INDEX_FIRST_ITINERARY, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_ITINERARY, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Description expectedName = new Description(VALID_DESCRIPTION);
        assertEquals(expectedName, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedName = new Description(VALID_DESCRIPTION);
        assertEquals(expectedName, ParserUtil.parseDescription(nameWithWhitespace));
    }

    @Test
    public void parseCountry_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCountry((String) null));
    }

    @Test
    public void parseCountry_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCountry(INVALID_COUNTRY));
    }

    @Test
    public void parseCountry_validValueWithoutWhitespace_returnsCountry() throws Exception {
        Country expectedCountry = new Country(VALID_COUNTRY);
        assertEquals(expectedCountry, ParserUtil.parseCountry(VALID_COUNTRY));
    }

    @Test
    public void parseCountry_validValueWithWhitespace_returnsTrimmedCountry() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_COUNTRY + WHITESPACE;
        Country expectedCountry = new Country(VALID_COUNTRY);
        assertEquals(expectedCountry, ParserUtil.parseCountry(phoneWithWhitespace));
    }

    @Test
    public void parseStartDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseStartDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_START_DATE));
    }

    @Test
    public void parseStartDate_validValueWithoutWhitespace_returnsStartDate() throws Exception {
        Date expectedStartDate = new Date(VALID_START_DATE);
        assertEquals(expectedStartDate, ParserUtil.parseDate(VALID_START_DATE));
    }

    @Test
    public void parseStartDate_validValueWithWhitespace_returnsTrimmedStartDate() throws Exception {
        String startDateWithWhitespace = WHITESPACE + VALID_START_DATE + WHITESPACE;
        Date expectedStartDate = new Date(VALID_START_DATE);
        assertEquals(expectedStartDate, ParserUtil.parseDate(startDateWithWhitespace));
    }

    @Test
    public void parseEndDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseEndDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_END_DATE));
    }

    @Test
    public void parseEndDate_validValueWithoutWhitespace_returnsEndDate() throws Exception {
        Date expectedEndDate = new Date(VALID_END_DATE);
        assertEquals(expectedEndDate, ParserUtil.parseDate(VALID_END_DATE));
    }

    @Test
    public void parseEndDate_validValueWithWhitespace_returnsTrimmedEndDate() throws Exception {
        String endDateWithWhitespace = WHITESPACE + VALID_END_DATE + WHITESPACE;
        Date expectedEndDate = new Date(VALID_END_DATE);
        assertEquals(expectedEndDate, ParserUtil.parseDate(endDateWithWhitespace));
    }


    @Test
    public void parsePeople_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePeople(null));
    }

    @Test
    public void parsePeople_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePeople(INVALID_PEOPLE));
    }

    @Test
    public void parsePeople_validValueWithoutWhitespace_returnsPeople() throws Exception {
        People expectedPeople = new People(VALID_PEOPLE);
        assertEquals(expectedPeople, ParserUtil.parsePeople(VALID_PEOPLE));
    }

    @Test
    public void parsePeople_validValueWithWhitespace_returnsTrimmedPeople() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_PEOPLE + WHITESPACE;
        People expectedPeople = new People(VALID_PEOPLE);
        assertEquals(expectedPeople, ParserUtil.parsePeople(tagWithWhitespace));
    }
}
