package seedu.guest.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.guest.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.guest.testutil.Assert.assertThrows;
import static seedu.guest.testutil.TypicalIndexes.INDEX_FIRST_GUEST;

import org.junit.jupiter.api.Test;

import seedu.guest.logic.parser.exceptions.ParseException;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.IsRoomClean;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;
import seedu.guest.model.guest.Room;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ROOM = "!06-73";
    private static final String INVALID_DATE_RANGE = "13/09/22 - 13/09/22";
    private static final String INVALID_NUMBER_OF_GUESTS = "5";
    private static final String INVALID_IS_ROOM_CLEAN = "false";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_ROOM = "05-73";
    private static final String VALID_DATE_RANGE = "13/09/22 - 15/09/22";
    private static final String VALID_NUMBER_OF_GUESTS = "4";
    private static final String VALID_IS_ROOM_CLEAN = "yes";

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
        assertEquals(INDEX_FIRST_GUEST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_GUEST, ParserUtil.parseIndex("  1  "));
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
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
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

    @Test
    public void parseRoom_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRoom((String) null));
    }

    @Test
    public void parseRoom_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRoom(INVALID_ROOM));
    }

    @Test
    public void parseRoom_validValueWithoutWhitespace_returnsRoom() throws Exception {
        Room expectedRoom = new Room(VALID_ROOM);
        assertEquals(expectedRoom, ParserUtil.parseRoom(VALID_ROOM));
    }

    @Test
    public void parseRoom_validValueWithWhitespace_returnsTrimmedRoom() throws Exception {
        String roomWithWhitespace = WHITESPACE + VALID_ROOM + WHITESPACE;
        Room expectedRoom = new Room(VALID_ROOM);
        assertEquals(expectedRoom, ParserUtil.parseRoom(roomWithWhitespace));
    }

    @Test
    public void parseDateRange_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDateRange((String) null));
    }

    @Test
    public void parseDateRange_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateRange(INVALID_DATE_RANGE));
    }

    @Test
    public void parseDateRange_validValueWithoutWhitespace_returnsDateRange() throws Exception {
        DateRange expectedDateRange = new DateRange(VALID_DATE_RANGE);
        assertEquals(expectedDateRange, ParserUtil.parseDateRange(VALID_DATE_RANGE));
    }

    @Test
    public void parseDateRange_validValueWithWhitespace_returnsTrimmedDateRange() throws Exception {
        String dateRangeWithWhitespace = WHITESPACE + VALID_DATE_RANGE + WHITESPACE;
        DateRange expectedDateRange = new DateRange(VALID_DATE_RANGE);
        assertEquals(expectedDateRange, ParserUtil.parseDateRange(dateRangeWithWhitespace));
    }

    @Test
    public void parseNumberOfGuests_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNumberOfGuests((String) null));
    }

    @Test
    public void parseNumberOfGuests_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNumberOfGuests(INVALID_NUMBER_OF_GUESTS));
    }

    @Test
    public void parseNumberOfGuests_validValueWithoutWhitespace_returnsNumberOfGuests() throws Exception {
        NumberOfGuests expectedNumberOfGuests = new NumberOfGuests(VALID_NUMBER_OF_GUESTS);
        assertEquals(expectedNumberOfGuests, ParserUtil.parseNumberOfGuests(VALID_NUMBER_OF_GUESTS));
    }

    @Test
    public void parseNumberOfGuests_validValueWithWhitespace_returnsTrimmedNumberOfGuests() throws Exception {
        String numberOfGuestsWithWhitespace = WHITESPACE + VALID_NUMBER_OF_GUESTS + WHITESPACE;
        NumberOfGuests expectedNumberOfGuests = new NumberOfGuests(VALID_NUMBER_OF_GUESTS);
        assertEquals(expectedNumberOfGuests, ParserUtil.parseNumberOfGuests(numberOfGuestsWithWhitespace));
    }

    @Test
    public void parseIsRoomClean_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIsRoomClean((String) null));
    }

    @Test
    public void parseIsRoomClean_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNumberOfGuests(INVALID_IS_ROOM_CLEAN));
    }

    @Test
    public void parseIsRoomClean_validValueWithoutWhitespace_returnsIsRoomClean() throws Exception {
        IsRoomClean expectedIsRoomClean = new IsRoomClean(VALID_IS_ROOM_CLEAN);
        assertEquals(expectedIsRoomClean, ParserUtil.parseIsRoomClean(VALID_IS_ROOM_CLEAN));
    }

    @Test
    public void parseIsRoomClean_validValueWithWhitespace_returnsTrimmedIsRoomClean() throws Exception {
        String isRoomCleanWithWhitespace = WHITESPACE + VALID_IS_ROOM_CLEAN + WHITESPACE;
        IsRoomClean expectedIsRoomClean = new IsRoomClean(VALID_IS_ROOM_CLEAN);
        assertEquals(expectedIsRoomClean, ParserUtil.parseIsRoomClean(isRoomCleanWithWhitespace));
    }
}
