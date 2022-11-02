package seedu.masslinkers.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.masslinkers.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.masslinkers.testutil.Assert.assertThrows;
import static seedu.masslinkers.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.masslinkers.logic.parser.exceptions.ParseException;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Email;
import seedu.masslinkers.model.student.GitHub;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Name;
import seedu.masslinkers.model.student.Phone;
import seedu.masslinkers.model.student.Telegram;

//@@author
public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "   ";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_GITHUB = "-invalid-";
    private static final String INVALID_TELEGRAM = "s";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_INTEREST = "#tennis";
    private static final String INVALID_MOD = "#CS2103";

    private static final String VALID_GITHUB = "racheltan";
    private static final String VALID_TELEGRAM = "racheltan";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE_1 = "12345678";
    private static final String VALID_PHONE_2 = "+14782342";
    private static final String VALID_PHONE_3 = "this is a valid phone";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_INTEREST_1 = "tennis";
    private static final String VALID_INTEREST_2 = "baking";
    private static final String VALID_MOD_1 = "CS2103";
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
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
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

    //the only time an exception is thrown is when phone is either null or blank after trimming
    //otherwise all other inputs are considered valid, but a warning will be given if they fail the regex check
    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parseGitHub_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGitHub(INVALID_GITHUB));
    }

    @Test
    public void parsePhone_validSingapore_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE_1);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE_1));
    }

    @Test
    public void parsePhone_validInternational_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE_2);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE_2));
    }

    //An "incorrect" number that is still considered a valid input (warning to user issued)
    @Test
    public void parsePhone_validButWithWarning_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE_3);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE_3));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE_1 + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE_1);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseGitHub_valid_returnsGitHub() throws Exception {
        GitHub expectedGitHub = new GitHub(VALID_GITHUB);
        assertEquals(expectedGitHub, ParserUtil.parseGitHub(VALID_GITHUB));
    }

    @Test
    public void parseTelegram_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTelegram((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTelegram(INVALID_ADDRESS));
    }

    @Test
    public void parseTelegram_validValueWithoutWhitespace_returnsTelegram() throws Exception {
        Telegram expectedTelegram = new Telegram(VALID_TELEGRAM);
        assertEquals(expectedTelegram, ParserUtil.parseTelegram(VALID_TELEGRAM));
    }

    @Test
    public void parseTelegram_validValueWithWhitespace_returnsTelegram() throws Exception {
        String telegramWithWhitespace = WHITESPACE + VALID_TELEGRAM + WHITESPACE;
        Telegram expectedTelegram = new Telegram(VALID_TELEGRAM);
        assertEquals(expectedTelegram, ParserUtil.parseTelegram(telegramWithWhitespace));
    }

    @Test
    public void parseTelegram_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_TELEGRAM));
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
    public void parseInterest_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInterest(null));
    }

    @Test
    public void parseInterest_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInterest(INVALID_INTEREST));
    }

    @Test
    public void parseInterest_validValueWithoutWhitespace_returnsInterest() throws Exception {
        Interest expectedInterest = new Interest(VALID_INTEREST_1);
        assertEquals(expectedInterest, ParserUtil.parseInterest(VALID_INTEREST_1));
    }

    @Test
    public void parseInterest_validValueWithWhitespace_returnsTrimmedInterest() throws Exception {
        String interestWithWhitespace = WHITESPACE + VALID_INTEREST_1 + WHITESPACE;
        Interest expectedInterest = new Interest(VALID_INTEREST_1);
        assertEquals(expectedInterest, ParserUtil.parseInterest(interestWithWhitespace));
    }

    @Test
    public void parseInterests_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInterests(null));
    }

    @Test
    public void parseInterests_collectionWithInvalidInterests_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInterests(
                Arrays.asList(VALID_INTEREST_1, INVALID_INTEREST)));
    }

    @Test
    public void parseInterests_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseInterests(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseInterests_collectionWithValidInterests_returnsInterestSet() throws Exception {
        Set<Interest> actualInterestSet = ParserUtil.parseInterests(Arrays.asList(VALID_INTEREST_1, VALID_INTEREST_2));
        Set<Interest> expectedInterestSet = new HashSet<>(
                Arrays.asList(new Interest(VALID_INTEREST_1), new Interest(VALID_INTEREST_2)));

        assertEquals(expectedInterestSet, actualInterestSet);
    }

    @Test
    public void parseMod_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMod(null));
    }

    @Test
    public void parseMod_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMod(INVALID_MOD));
    }

    @Test
    public void parseMod_validValueWithoutWhitespace_returnsInterest() throws Exception {
        Mod expectedMod = new Mod(VALID_MOD_1);
        assertEquals(expectedMod, ParserUtil.parseMod(VALID_MOD_1));
    }

    @Test
    public void parseMod_validValueWithWhitespace_returnsTrimmedInterest() throws Exception {
        String modWithWhitespace = WHITESPACE + VALID_MOD_1 + WHITESPACE;
        Mod expectedMod = new Mod(VALID_MOD_1);
        assertEquals(expectedMod, ParserUtil.parseMod(modWithWhitespace));
    }

    @Test
    public void parseMods_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMods(null));
    }

    @Test
    public void parseMods_collectionWithInvalidMods_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMods(Arrays.asList(VALID_MOD_1, INVALID_MOD)));
    }

    @Test
    public void parseMods_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseMods(Collections.emptyList()).isEmpty());
    }

    @SuppressWarnings("checkstyle:SingleSpaceSeparator")
    @Test
    public void parseMods_collectionWithValidMods_returnsModSet() throws Exception {
        ObservableList<Mod> actualModSet = ParserUtil.parseMods(Arrays.asList(VALID_MOD_1, VALID_MOD_2));
        ObservableList<Mod> expectedModSet = FXCollections.observableArrayList(Arrays.asList(
                new Mod(VALID_MOD_2), new Mod(VALID_MOD_1)));

        assertEquals(expectedModSet, actualModSet);
    }

    @Test
    public void parseMods_collectionWithValidDuplicateMods_returnsModSet() throws Exception {
        ObservableList<Mod> actualModSet = ParserUtil.parseMods(Arrays.asList(VALID_MOD_1, VALID_MOD_1));
        ObservableList<Mod> expectedModSet = FXCollections.observableArrayList(Arrays.asList(new Mod(VALID_MOD_1)));

        assertEquals(expectedModSet, actualModSet);
    }

    /**
     * Tests the behaviour of the {@code parseModsToCategory} method
     */
    @Test
    public void parseMods_correctAssigmentOfCategory() {
        // CS Mods
        assertEquals(Mod.ModCategory.COMP, ParserUtil.parseModsToCategory("CS2100"));
        assertEquals(Mod.ModCategory.COMP, ParserUtil.parseModsToCategory("CS2103T"));
        assertEquals(Mod.ModCategory.COMP, ParserUtil.parseModsToCategory("CP2106"));
        assertEquals(Mod.ModCategory.COMP, ParserUtil.parseModsToCategory("IS1108"));

        // Math Mods
        assertEquals(Mod.ModCategory.MATH, ParserUtil.parseModsToCategory("ST2334"));
        assertEquals(Mod.ModCategory.MATH, ParserUtil.parseModsToCategory("MA1521"));
        assertEquals(Mod.ModCategory.MATH, ParserUtil.parseModsToCategory("MA2001"));

        // Sci Mods
        assertEquals(Mod.ModCategory.SCI, ParserUtil.parseModsToCategory("LSM1301"));
        assertEquals(Mod.ModCategory.SCI, ParserUtil.parseModsToCategory("CM1102"));
        assertEquals(Mod.ModCategory.SCI, ParserUtil.parseModsToCategory("PC1202"));

        // GE Mods
        assertEquals(Mod.ModCategory.GE, ParserUtil.parseModsToCategory("GEA1000"));
        assertEquals(Mod.ModCategory.GE, ParserUtil.parseModsToCategory("UTC1102B"));
        assertEquals(Mod.ModCategory.GE, ParserUtil.parseModsToCategory("GESS1025"));

        // COMMS Mods
        assertEquals(Mod.ModCategory.COMMS, ParserUtil.parseModsToCategory("ES2660"));

        // UE Mods
        assertEquals(Mod.ModCategory.UE, ParserUtil.parseModsToCategory("CFG1002"));
    }
}
