package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.*;
import seedu.address.model.module.Module;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GITHUB = "slim+shady";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_MOD = "#@@CS><>Z";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_GITHUB = "rachel-walker";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_MOD_1 = "CS2103T";
    private static final String VALID_MOD_2 = "CS2101";

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
    public void parseGithub_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGithub((String) null));
    }

    @Test
    public void parseGithub_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGithub(INVALID_GITHUB));
    }

    @Test
    public void parseGithub_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Github expectedGithub = new Github(VALID_GITHUB);
        assertEquals(expectedGithub, ParserUtil.parseGithub(VALID_GITHUB));
    }

    @Test
    public void parseGithub_validValueWithWhitespace_returnsTrimmedGithub() throws Exception {
        String githubWithWhitespace = WHITESPACE + VALID_GITHUB + WHITESPACE;
        Github expectedGithub = new Github(VALID_GITHUB);
        assertEquals(expectedGithub, ParserUtil.parseGithub(githubWithWhitespace));
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
    public void parseModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModule(null));
    }

    @Test
    public void parseModule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModule(INVALID_MOD));
    }

    @Test
    public void parseModule_validValueWithoutWhitespace_returnsMod() throws Exception {
        Module expectedModule = new Module(VALID_MOD_1);
        assertEquals(expectedModule, ParserUtil.parseModule(VALID_MOD_1));
    }

    @Test
    public void parseModule_collectionWithValidMods_returnsTrimmedMod() throws Exception {
        String modWithWhitespace = WHITESPACE + VALID_MOD_1 + WHITESPACE;
        Module expectedMod = new Module(VALID_MOD_1);
        assertEquals(expectedMod, ParserUtil.parseModule(modWithWhitespace));
    }

    @Test
    public void parseCurrentModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCurrentModule(null));
    }

    @Test
    public void parseCurrentModule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCurrentModule(INVALID_MOD));
    }

    @Test
    public void parseCurrentModule_validValueWithoutWhitespace_returnsMod() throws Exception {
        CurrentModule expectedModule = new CurrentModule(VALID_MOD_1);
        assertEquals(expectedModule, ParserUtil.parseCurrentModule(VALID_MOD_1));
    }

    @Test
    public void parseCurrentModule_collectionWithValidMods_returnsTrimmedMod() throws Exception {
        String modWithWhitespace = WHITESPACE + VALID_MOD_1 + WHITESPACE;
        CurrentModule expectedMod = new CurrentModule(VALID_MOD_1);
        assertEquals(expectedMod, ParserUtil.parseCurrentModule(modWithWhitespace));
    }

    @Test
    public void parsePreviousModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePreviousModule(null));
    }

    @Test
    public void parsePreviousModule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePreviousModule(INVALID_MOD));
    }

    @Test
    public void parsePreviousModule_validValueWithoutWhitespace_returnsMod() throws Exception {
        PreviousModule expectedModule = new PreviousModule(VALID_MOD_1);
        assertEquals(expectedModule, ParserUtil.parsePreviousModule(VALID_MOD_1));
    }

    @Test
    public void parsePreviousModule_collectionWithValidMods_returnsTrimmedMod() throws Exception {
        String modWithWhitespace = WHITESPACE + VALID_MOD_1 + WHITESPACE;
        PreviousModule expectedMod = new PreviousModule(VALID_MOD_1);
        assertEquals(expectedMod, ParserUtil.parsePreviousModule(modWithWhitespace));
    }

    @Test
    public void parsePlannedModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePlannedModule(null));
    }

    @Test
    public void parsePlannedModule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePlannedModule(INVALID_MOD));
    }

    @Test
    public void parsePlannedModule_validValueWithoutWhitespace_returnsMod() throws Exception {
        PlannedModule expectedModule = new PlannedModule(VALID_MOD_1);
        assertEquals(expectedModule, ParserUtil.parsePlannedModule(VALID_MOD_1));
    }

    @Test
    public void parsePlannedModule_collectionWithValidMods_returnsTrimmedMod() throws Exception {
        String modWithWhitespace = WHITESPACE + VALID_MOD_1 + WHITESPACE;
        PlannedModule expectedMod = new PlannedModule(VALID_MOD_1);
        assertEquals(expectedMod, ParserUtil.parsePlannedModule(modWithWhitespace));
    }

    @Test
    public void parseModules_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModules(null));
    }

    @Test
    public void parseModules_collectionWithInvalidMods_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModules(Arrays.asList(VALID_MOD_1, INVALID_MOD)));
    }

    @Test
    public void parseModules_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseModules(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseModules_collectionWithValidMods_returnsModSet() throws Exception {
        Set<Module> actualModSet = ParserUtil.parseModules(Arrays.asList(VALID_MOD_1, VALID_MOD_2));
        Set<Module> expectedModSet = new HashSet<Module>(Arrays.asList(new Module(VALID_MOD_1),
                new Module(VALID_MOD_2)));

        assertEquals(expectedModSet, actualModSet);
    }

    @Test
    public void parseCurrentModules_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCurrentModules(null));
    }

    @Test
    public void parseCurrentModules_collectionWithInvalidMods_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCurrentModules(Arrays.asList(VALID_MOD_1,
                INVALID_MOD)));
    }

    @Test
    public void parseCurrentModules_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseCurrentModules(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseCurrentModules_collectionWithValidMods_returnsModSet() throws Exception {
        Set<CurrentModule> actualModSet = ParserUtil.parseCurrentModules(Arrays.asList(VALID_MOD_1, VALID_MOD_2));
        Set<CurrentModule> expectedModSet = new HashSet<CurrentModule>(Arrays.asList(new CurrentModule(VALID_MOD_1),
                new CurrentModule(VALID_MOD_2)));

        assertEquals(expectedModSet, actualModSet);
    }

    @Test
    public void parsePreviousModules_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePreviousModules(null));
    }

    @Test
    public void parsePreviousModules_collectionWithInvalidMods_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePreviousModules(Arrays.asList(VALID_MOD_1,
                INVALID_MOD)));
    }

    @Test
    public void parsePreviousModules_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parsePreviousModules(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parsePreviousModules_collectionWithValidMods_returnsModSet() throws Exception {
        Set<PreviousModule> actualModSet = ParserUtil.parsePreviousModules(Arrays.asList(VALID_MOD_1, VALID_MOD_2));
        Set<PreviousModule> expectedModSet = new HashSet<PreviousModule>(Arrays.asList(new PreviousModule(VALID_MOD_1),
                new PreviousModule(VALID_MOD_2)));

        assertEquals(expectedModSet, actualModSet);
    }

    @Test
    public void parsePlannedModules_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePlannedModules(null));
    }

    @Test
    public void parsePlannedModules_collectionWithInvalidMods_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePlannedModules(Arrays.asList(VALID_MOD_1,
                INVALID_MOD)));
    }

    @Test
    public void parsePlannedModules_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parsePlannedModules(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parsePlannedModules_collectionWithValidMods_returnsModSet() throws Exception {
        Set<PlannedModule> actualModSet = ParserUtil.parsePlannedModules(Arrays.asList(VALID_MOD_1, VALID_MOD_2));
        Set<PlannedModule> expectedModSet = new HashSet<PlannedModule>(Arrays.asList(new PlannedModule(VALID_MOD_1),
                new PlannedModule(VALID_MOD_2)));

        assertEquals(expectedModSet, actualModSet);
    }

    @Test
    public void parseTime_invalidInput_throwsParseException() {
        // not time
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("test"));

        // invalid minute
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("00:70"));

        // invalid hour
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("25:00"));

        // wrong format
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("12/00"));

        // additional input
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("12:00 hello"));
    }

    @Test
    public void parseTime_validInput_returnsLocalTime() throws Exception {
        assertEquals(ParserUtil.parseTime("12:00"), LocalTime.parse("12:00"));
    }

    @Test
    public void parseDay_invalidInput_throwsParseException() {
        // not number
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("abc"));

        // negative number
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("-1"));

        // 0
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("0"));

        // greater than 7
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("8"));
    }

    @Test
    public void parseDay_validInput_returnsInt() throws Exception {
        assertEquals(ParserUtil.parseDay("1"), 1);

        assertEquals(ParserUtil.parseDay("7"), 7);
    }

    @Test
    public void parseLesson_invalidInput_throwsParseException() {
        // invalid mod
        assertThrows(ParseException.class, () -> ParserUtil.parseLesson("tut", INVALID_MOD,
                "1", "12:00", "13:00"));

        // invalid type
        assertThrows(ParseException.class, () -> ParserUtil.parseLesson("xxx", VALID_MOD_1,
                "1", "12:00", "13:00"));

        // invalid day
        assertThrows(ParseException.class, () -> ParserUtil.parseLesson("tut", VALID_MOD_1,
                "abc", "12:00", "13:00"));

        // invalid start
        assertThrows(ParseException.class, () -> ParserUtil.parseLesson("tut", VALID_MOD_1,
                "1", "25/70", "13:00"));

        // invalid end
        assertThrows(ParseException.class, () -> ParserUtil.parseLesson("tut", VALID_MOD_1,
                "1", "12:00", "27/69"));
    }

    @Test
    public void parseLesson_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLesson((String) null, VALID_MOD_1, "1",
                "12:00", "13:00"));

        assertThrows(NullPointerException.class, () -> ParserUtil.parseLesson("tut", (String) null, "1",
                "12:00", "13:00"));
    }

    @Test
    public void parseLesson_validInput_returnsLesson() throws Exception {
        Lesson expectedLesson = new Tutorial(VALID_MOD_1, 1, LocalTime.parse("12:00"), LocalTime.parse("13:00"));
        assertEquals(ParserUtil.parseLesson("tut", VALID_MOD_1, "1", "12:00", "13:00"),
                expectedLesson);
    }
}
