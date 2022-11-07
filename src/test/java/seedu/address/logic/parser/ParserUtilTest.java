package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_210_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_22_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLANTAG_SAVINGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Location;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.NormalTag;
import seedu.address.model.tag.PlanTag;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String WHITESPACE = " \t\r\n";
    private static final String INVALID_NAME = "R@chel";
    private static final String DUPLICATE_NAME = "Rachel     Walker";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_ADDRESS_WITH_WHITESPACE = "123 Main          Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_LOCATION = "123 Main Street #0505";
    private static final String VALID_LOCATION_WITH_WHITESPACE = "123 Main          Street #0505";
    private static final String INVALID_LOCATION_1 = "";
    private static final String INVALID_LOCATION_2 = " ";
    private static final ArgumentMultimap VALID_ARGUMENT_MULTIMAP = new ArgumentMultimap();
    private static final ArgumentMultimap INVALID_DATE_ARGUMENT_MULTIMAP = new ArgumentMultimap();
    private static final ArgumentMultimap INVALID_LOCATION_ARGUMENT_MULTIMAP = new ArgumentMultimap();
    private static final String VALID_PLANTAG_WITH_WHITESPACE = "Savings     Plan";
    private static final String VALID_PLANTAG_PLAN_ALL_CAPS = "Savings PLAN";
    private static final String VALID_PLANTAG_PLAN_MIXED_CASE = "Savings pLAn";
    private static final String INVALID_PLAN_TAG_1 = "Savings Plans";
    private static final String INVALID_PLAN_TAG_2 = "Savings Plann";
    private static final String INVALID_PLAN_TAG_3 = "Savings";
    private static final String INVALID_PLAN_TAG_4 = "";
    private static final String INVALID_PLAN_TAG_5 = "  ";

    @BeforeAll
    public static void setup() {
        VALID_ARGUMENT_MULTIMAP.put(PREFIX_APPOINTMENT_DATE, VALID_DATETIME_22_JAN_2023);
        VALID_ARGUMENT_MULTIMAP.put(PREFIX_APPOINTMENT_LOCATION, VALID_LOCATION);
        INVALID_DATE_ARGUMENT_MULTIMAP.put(PREFIX_APPOINTMENT_DATE, INVALID_DATETIME_210_JAN_2023);
        INVALID_DATE_ARGUMENT_MULTIMAP.put(PREFIX_APPOINTMENT_LOCATION, VALID_LOCATION);
        INVALID_LOCATION_ARGUMENT_MULTIMAP.put(PREFIX_APPOINTMENT_DATE, VALID_DATETIME_22_JAN_2023);
        INVALID_LOCATION_ARGUMENT_MULTIMAP.put(PREFIX_APPOINTMENT_LOCATION, INVALID_LOCATION_1);
    }

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
    public void parseName_duplicateNameWithoutWhiteSpace_returnsDuplicateName() throws Exception {
        String nameWithWhitespace = DUPLICATE_NAME;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseName_duplicateNameWithWhiteSpace_returnsTrimmedDuplicateName() throws Exception {
        String nameWithWhitespace = WHITESPACE + DUPLICATE_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseName_validValueWithWhitespaceBetween_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = DUPLICATE_NAME;
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
    public void parseAddress_validValueWithWhitespaceBetween_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = VALID_ADDRESS_WITH_WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress , ParserUtil.parseAddress(addressWithWhitespace));
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
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new NormalTag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new NormalTag(VALID_TAG_1);
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
        Set<NormalTag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<NormalTag> expectedTagSet = new HashSet<NormalTag>(Arrays.asList(new NormalTag(VALID_TAG_1),
                new NormalTag(VALID_TAG_2)));
        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseLocation_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLocation((String) null));
    }

    @Test
    public void parseLocation_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLocation(INVALID_LOCATION_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocation(INVALID_LOCATION_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocation(INVALID_LOCATION_2 + WHITESPACE));
    }

    @Test
    public void parseLocation_validValueWithoutWhitespace_returnsLocation() throws Exception {
        Location expectedLocation = new Location(VALID_LOCATION);
        assertEquals(expectedLocation, ParserUtil.parseLocation(VALID_LOCATION));
    }

    @Test
    public void parseLocation_validValueWithWhitespace_returnsTrimmedLocation() throws Exception {
        String locationWithWhitespace = WHITESPACE + VALID_LOCATION + WHITESPACE;
        Location expectedLocation = new Location(VALID_LOCATION);
        assertEquals(expectedLocation, ParserUtil.parseLocation(locationWithWhitespace));
    }

    @Test
    public void parseLocation_validValueWithWhitespaceBetween_returnsTrimmedLocation() throws Exception {
        String locationWithWhitespace = VALID_LOCATION_WITH_WHITESPACE;
        Location expectedLocation = new Location(VALID_LOCATION);
        assertEquals(expectedLocation, ParserUtil.parseLocation(locationWithWhitespace));
    }

    @Test
    public void parseAppointment_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAppointment((ArgumentMultimap) null));
    }

    @Test
    public void parseAppointment_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointment(INVALID_DATE_ARGUMENT_MULTIMAP));
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointment(INVALID_LOCATION_ARGUMENT_MULTIMAP));
    }

    @Test
    public void parseAppointment_validValueWithoutWhitespace_returnsAppointment() throws Exception {
        DateTime dateTime = new DateTime(DateTimeParser.parseLocalDateTimeFromString(VALID_DATETIME_22_JAN_2023));
        Location location = new Location(VALID_LOCATION);
        Appointment expectedAppointment = new Appointment(dateTime, location);
        assertEquals(expectedAppointment, ParserUtil.parseAppointment(VALID_ARGUMENT_MULTIMAP));
    }

    @Test
    public void parseAppointment_validValueWithWhitespace_returnsTrimmedAppointment() throws Exception {
        DateTime dateTime = new DateTime(DateTimeParser.parseLocalDateTimeFromString(VALID_DATETIME_22_JAN_2023));
        Location location = new Location(VALID_LOCATION + WHITESPACE);
        Appointment expectedAppointment = new Appointment(dateTime, location);
        assertEquals(expectedAppointment, ParserUtil.parseAppointment(VALID_ARGUMENT_MULTIMAP));
    }

    @Test
    public void parseAppointment_validValueWithWhitespaceBetween_returnsTrimmedAppointment() throws Exception {
        DateTime dateTime = new DateTime(DateTimeParser.parseLocalDateTimeFromString(VALID_DATETIME_22_JAN_2023));
        Location location = new Location(VALID_LOCATION_WITH_WHITESPACE);
        Appointment expectedAppointment = new Appointment(dateTime, location);
        assertEquals(expectedAppointment, ParserUtil.parseAppointment(VALID_ARGUMENT_MULTIMAP));
    }


    @Test
    public void parsePlanTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePlanTag((String) null));
    }

    @Test
    public void parsePlanTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePlanTag(INVALID_PLAN_TAG_1));
        assertThrows(ParseException.class, () -> ParserUtil.parsePlanTag(INVALID_PLAN_TAG_2));
        assertThrows(ParseException.class, () -> ParserUtil.parsePlanTag(INVALID_PLAN_TAG_3));
        assertThrows(ParseException.class, () -> ParserUtil.parsePlanTag(INVALID_PLAN_TAG_4));
        assertThrows(ParseException.class, () -> ParserUtil.parsePlanTag(INVALID_PLAN_TAG_5));
        assertThrows(ParseException.class, () -> ParserUtil.parsePlanTag(INVALID_PLAN_TAG_2 + WHITESPACE));
    }

    @Test
    public void parsePlanTag_validValueWithoutWhitespace_returnsPlanTag() throws Exception {
        PlanTag expectedPlanTag = new PlanTag(VALID_PLANTAG_SAVINGS);
        assertEquals(expectedPlanTag, ParserUtil.parsePlanTag(VALID_PLANTAG_SAVINGS));
    }

    @Test
    public void parsePlanTag_validValueWithWhitespace_returnsTrimmedPlanTag() throws Exception {
        String PlanTagWithWhitespace = WHITESPACE + VALID_PLANTAG_SAVINGS + WHITESPACE;
        PlanTag expectedPlanTag = new PlanTag(VALID_PLANTAG_SAVINGS);
        assertEquals(expectedPlanTag, ParserUtil.parsePlanTag(PlanTagWithWhitespace));
    }

    @Test
    public void parsePlanTag_validValueWithWhitespaceBetween_returnsTrimmedPlanTag() throws Exception {
        String PlanTagWithWhitespace = VALID_PLANTAG_WITH_WHITESPACE;
        PlanTag expectedPlanTag = new PlanTag(VALID_PLANTAG_WITH_WHITESPACE);
        assertEquals(expectedPlanTag, ParserUtil.parsePlanTag(PlanTagWithWhitespace));
    }

    @Test
    public void parsePlanTag_validValueWithDifferentPlanCases_returnsPlanTag() throws Exception {
        PlanTag expectedPlanTag = new PlanTag(VALID_PLANTAG_PLAN_ALL_CAPS);
        assertEquals(expectedPlanTag, ParserUtil.parsePlanTag(VALID_PLANTAG_PLAN_ALL_CAPS));

        PlanTag expectedPlanTag2 = new PlanTag(VALID_PLANTAG_PLAN_MIXED_CASE);
        assertEquals(expectedPlanTag2, ParserUtil.parsePlanTag(VALID_PLANTAG_PLAN_MIXED_CASE));
    }

}
