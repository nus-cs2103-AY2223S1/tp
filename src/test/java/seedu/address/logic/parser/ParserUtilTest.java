package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_INDEXES;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEXES;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Class;
import seedu.address.model.student.Email;
import seedu.address.model.student.Money;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_NOK_PHONE = "+659999";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final Integer INVALID_MONEY_OWED = -1;
    private static final Integer INVALID_MONEY_PAID = -1;
    private static final String INVALID_CLASS_DATE_TIME = "2022 05 10 1500-1600";
    private static final String INVALID_EMPTY_CLASS_DATE_TIME = "";
    private static final String INVALID_CLASS_DATE_TIME_DURATION = "2022-10-10 1500-1200";
    private static final String INVALID_FLEXIBLE_CLASS_DAY_OF_WEEK = "Tues 1500-1600";
    private static final String INVALID_FLEXIBLE_CLASS_TIME = "Tue 1500 1600";
    private static final String INVALID_FLEXIBLE_CLASS_DATE_TIME_DURATION = "Tue 1500-1200";
    private static final String INVALID_TAG = "#python";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "82345678";
    private static final String VALID_NOK_PHONE = "99999999";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final Integer VALID_MONEY_OWED = 10;
    private static final Integer VALID_MONEY_PAID = 100;
    private static final String VALID_CLASS_DATE_TIME = "2022-05-10 1500-1600";
    private static final String VALID_FLEXIBLE_CLASS_DATE_TIME = "Sun 1500-1600";
    private static final String VALID_TAG_1 = "beginner";
    private static final String VALID_TAG_2 = "javaScript";

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
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseIndexes_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndexes("b 9"));
    }

    @Test
    public void parseIndexes_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEXES, ()
                -> ParserUtil.parseIndexes(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndexes_validInput_success() throws ParseException {
        List<Index> indexes = new ArrayList<>();
        indexes.add(Index.fromOneBased(2));
        indexes.add(Index.fromOneBased(3));
        assertEquals(ParserUtil.parseIndexes("2 3"), indexes);
    }

    @Test
    public void parseIndexes_duplicateInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_DUPLICATE_INDEXES, ()
                -> ParserUtil.parseIndexes("1 1"));
    }

    @Test
    public void parseIndexes_duplicateAndOutOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_DUPLICATE_INDEXES, ()
                -> ParserUtil.parseIndexes(Long.toString(Integer.MAX_VALUE + 1) + " 1 1"));
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
    public void parseNokPhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parseNokPhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_NOK_PHONE));
    }

    @Test
    public void parseNokPhone_validValueWithoutWhitespace_returnsNokPhone() throws Exception {
        Phone expectedNokPhone = new Phone(VALID_NOK_PHONE);
        assertEquals(expectedNokPhone, ParserUtil.parsePhone(VALID_NOK_PHONE));
    }

    @Test
    public void parseNokPhone_validValueWithWhitespace_returnsTrimmedNokPhone() throws Exception {
        String nokPhoneWithWhitespace = WHITESPACE + VALID_NOK_PHONE + WHITESPACE;
        Phone expectedNokPhone = new Phone(VALID_NOK_PHONE);
        assertEquals(expectedNokPhone, ParserUtil.parsePhone(nokPhoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
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
    public void parseMoneyOwed_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMoney((String) null));
    }

    @Test
    public void parseMoneyOwed_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMoney(INVALID_MONEY_OWED.toString()));
    }

    @Test
    public void parseMoneyOwed_validValue_returnsMoneyOwed() throws Exception {
        Money expectedMoneyOwed = new Money(VALID_MONEY_OWED);
        assertEquals(expectedMoneyOwed, ParserUtil.parseMoney(VALID_MONEY_OWED.toString()));
    }

    @Test
    public void parseMoneyPaid_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMoney((String) null));
    }

    @Test
    public void parseMoneyPaid_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMoney(INVALID_MONEY_PAID.toString()));
    }

    @Test
    public void parseMoneyPaid_validValue_returnsMoneyOwed() throws Exception {
        Money expectedMoneyPaid = new Money(VALID_MONEY_PAID);
        assertEquals(expectedMoneyPaid, ParserUtil.parseMoney(VALID_MONEY_PAID.toString()));
    }

    @Test
    public void parseClassDateTime_validDateTime_returnsClass() throws Exception {
        Class expectedClass = new Class(LocalDate.of(2022, 5, 10),
                LocalTime.of(15, 0), LocalTime.of(16, 0), VALID_CLASS_DATE_TIME);
        assertEquals(expectedClass, ParserUtil.parseClass(VALID_CLASS_DATE_TIME));
    }

    @Test
    public void parseClassDateTime_invalidDateTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClass(INVALID_CLASS_DATE_TIME));
        assertThrows(ParseException.class, () -> ParserUtil.parseClass(INVALID_EMPTY_CLASS_DATE_TIME));
        assertThrows(ParseException.class, () -> ParserUtil.parseClass(INVALID_CLASS_DATE_TIME_DURATION));
    }

    @Test
    public void parseClassDateTime_invalidFlexibleDateTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClass(INVALID_FLEXIBLE_CLASS_DAY_OF_WEEK));
        assertThrows(ParseException.class, () -> ParserUtil.parseClass(INVALID_FLEXIBLE_CLASS_TIME));
        assertThrows(ParseException.class, () -> ParserUtil.parseClass(INVALID_FLEXIBLE_CLASS_DATE_TIME_DURATION));
    }

    @Test
    public void getTargetClassDate_nextWeekSuccessful() {
        // This date falls on a saturday
        LocalDateTime dateTime = LocalDateTime.of(2022, 10, 15, 15, 0);
        ParserUtil.setTargetDayOfWeek(6);
        LocalTime startTime = LocalTime.of(14, 0);
        // 7 days later since the start time is after 3pm
        LocalDate date = ParserUtil.getTargetClassDate(dateTime, startTime);
        assertEquals(date, LocalDate.of(2022, 10, 22));
    }

    @Test
    public void getTargetClassDate_nextTwoDaysSuccessful() {
        // This date falls on a saturday
        LocalDateTime dateTime = LocalDateTime.of(2022, 10, 15, 15, 0);
        ParserUtil.setTargetDayOfWeek(1);
        LocalTime startTime = LocalTime.of(14, 0);
        // monday -> 2 more days
        LocalDate date = ParserUtil.getTargetClassDate(dateTime, startTime);
        assertEquals(date, LocalDate.of(2022, 10, 17));
    }

    @Test
    public void getTargetClassDate_tomorrowSuccessful() {
        // This date falls on a saturday
        LocalDateTime dateTime = LocalDateTime.of(2022, 10, 15, 15, 0);
        ParserUtil.setTargetDayOfWeek(7);
        LocalTime startTime = LocalTime.of(14, 0);
        // tomorrow
        LocalDate date = ParserUtil.getTargetClassDate(dateTime, startTime);
        assertEquals(date, LocalDate.of(2022, 10, 16));
    }

    @Test
    public void getTargetClassDate_todaySuccessful() {
        // This date falls on a saturday
        LocalDateTime dateTime = LocalDateTime.of(2022, 10, 15, 15, 0);
        ParserUtil.setTargetDayOfWeek(6);
        LocalTime startTime = LocalTime.of(16, 0);
        // tomorrow
        LocalDate date = ParserUtil.getTargetClassDate(dateTime, startTime);
        assertEquals(date, LocalDate.of(2022, 10, 15));
    }

    @Test
    public void parseDate_invalidString_throwsParseException() {
        assertThrows(ParseException.class, Class.INVALID_DATE_ERROR_MESSAGE, () -> ParserUtil.parseDate("2022-02-31"));
    }

    @Test
    public void parseTime_invalidString_throwsParseException() {
        assertThrows(ParseException.class, Class.INVALID_TIME_ERROR_MESSAGE, () -> ParserUtil.parseTime("0980"));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseTagsList_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTagsList(null));
    }

    @Test
    public void parseTagsList_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTagsList(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTagsList_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTagsList(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTagsList_collectionWithValidTags_returnsTagSet() throws Exception {
        List<String> actualTagSet = ParserUtil.parseTagsList(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        List<String> expectedTagSet = Arrays.asList(VALID_TAG_1, VALID_TAG_2);

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void adjustDateToTomorrowSuccessful() {
        // This date falls on a saturday
        LocalDate date = LocalDate.of(2022, 10, 15);
        ParserUtil.setTargetDayOfWeek(7);
        LocalDate expectedDate = LocalDate.of(2022, 10, 16);
        assertEquals(date.with(ParserUtil.DATE_ADJUSTER), expectedDate);
    }

    @Test
    public void adjustDateToNextMondaySuccessful() {
        LocalDate date = LocalDate.of(2022, 10, 15);
        ParserUtil.setTargetDayOfWeek(1);
        LocalDate expectedDate = LocalDate.of(2022, 10, 17);
        assertEquals(date.with(ParserUtil.DATE_ADJUSTER), expectedDate);
    }
}
