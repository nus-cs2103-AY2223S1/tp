package seedu.rc4hdb.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_FIRST_RESIDENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ROOM = "0-0";
    private static final String INVALID_GENDER = "NB";
    private static final String INVALID_HOUSE = "Z";
    private static final String INVALID_MATRIC_NUMBER = "B0000000U";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "91234567";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_ROOM = "03-05";
    private static final String VALID_GENDER = "F";
    private static final String VALID_HOUSE = "A";
    private static final String VALID_MATRIC_NUMBER = "A0000000U";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

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
        assertEquals(INDEX_FIRST_RESIDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_RESIDENT, ParserUtil.parseIndex("  1  "));
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
    public void parseGender_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGender((String) null));
    }

    @Test
    public void parseGender_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGender(INVALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithoutWhitespace_returnsGender() throws Exception {
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(VALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithWhitespace_returnsTrimmedGender() throws Exception {
        String genderWithWhitespace = WHITESPACE + VALID_GENDER + WHITESPACE;
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(genderWithWhitespace));
    }

    @Test
    public void parseHouse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseHouse((String) null));
    }

    @Test
    public void parseHouse_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHouse(INVALID_HOUSE));
    }

    @Test
    public void parseHouse_validValueWithoutWhitespace_returnsHouse() throws Exception {
        House expectedHouse = new House(VALID_HOUSE);
        assertEquals(expectedHouse, ParserUtil.parseHouse(VALID_HOUSE));
    }

    @Test
    public void parseHouse_validValueWithWhitespace_returnsTrimmedHouse() throws Exception {
        String houseWithWhitespace = WHITESPACE + VALID_HOUSE + WHITESPACE;
        House expectedHouse = new House(VALID_HOUSE);
        assertEquals(expectedHouse, ParserUtil.parseHouse(houseWithWhitespace));
    }

    @Test
    public void parseMatricNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMatricNumber((String) null));
    }

    @Test
    public void parseMatricNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMatricNumber(INVALID_MATRIC_NUMBER));
    }

    @Test
    public void parseMatricNumber_validValueWithoutWhitespace_returnsMatricNumber() throws Exception {
        MatricNumber expectedMatricNumber = new MatricNumber(VALID_MATRIC_NUMBER);
        assertEquals(expectedMatricNumber, ParserUtil.parseMatricNumber(VALID_MATRIC_NUMBER));
    }

    @Test
    public void parseMatricNumber_validValueWithWhitespace_returnsTrimmedMatricNumber() throws Exception {
        String matricNumberWithWhitespace = WHITESPACE + VALID_MATRIC_NUMBER + WHITESPACE;
        MatricNumber expectedMatricNumber = new MatricNumber(VALID_MATRIC_NUMBER);
        assertEquals(expectedMatricNumber, ParserUtil.parseMatricNumber(matricNumberWithWhitespace));
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
}
