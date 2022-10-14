package seedu.waddle.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.waddle.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_FIRST_ITINERARY;

import org.junit.jupiter.api.Test;

import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Name;
import seedu.waddle.model.itinerary.People;

public class ParserUtilTest {
    private static final String INVALID_NAME = "W!nter";
    private static final String INVALID_COUNTRY = "+651234";
    private static final String INVALID_START_DATE = "2022/03/15";
    private static final String INVALID_END_DATE = "202-04-15";
    private static final String INVALID_PEOPLE = "five";

    private static final String VALID_NAME = "Winter Trip";
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
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_START_DATE));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Date expectedAddress = new Date(VALID_START_DATE);
        assertEquals(expectedAddress, ParserUtil.parseDate(VALID_START_DATE));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_START_DATE + WHITESPACE;
        Date expectedAddress = new Date(VALID_START_DATE);
        assertEquals(expectedAddress, ParserUtil.parseDate(addressWithWhitespace));
    }

    /* Same as Start date //TODO
    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parse((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }
    */

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

    /* Tags different from People //TODO
    @Test
    public void parsePeoples_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePeople(null));
    }

    @Test
    public void parsePeople_collectionWithInvalidPeoples_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePeople(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parsePeople_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parsePeople(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parsePeoples_collectionWithValidPeoples_returnsPeopleSet() throws Exception {
        Set<People> actualPeopleSet = ParserUtil.parsePeoples(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<People> expectedPeopleSet = new HashSet<People>(Arrays.asList(new People(VALID_TAG_1), new People(VALID_TAG_2)));

        assertEquals(expectedPeopleSet, actualPeopleSet);
    }
    */
}
