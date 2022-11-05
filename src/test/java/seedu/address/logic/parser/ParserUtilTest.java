package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Country;
import seedu.address.model.person.Email;
import seedu.address.model.person.GameType;
import seedu.address.model.person.ITimesAvailable;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Server;
import seedu.address.model.person.Social;
import seedu.address.model.person.Tag;
import seedu.address.model.person.TimeInterval;

public class ParserUtilTest {
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_SERVER = "amy";
    private static final String INVALID_SOCIAL = "amy123";
    private static final String INVALID_TIME_INTERVAL = "why@2000-no@2300";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_COUNTRY = "singapore";
    private static final String VALID_GAME_TYPE_1 = "hard mode";
    private static final String VALID_GAME_TYPE_2 = "easy mode";
    private static final String VALID_MINECRAFT_SERVER_1 = "kai@111.222.111.222";
    private static final String VALID_MINECRAFT_SERVER_2 = "tan@123.456";
    private static final String VALID_SOCIAL_1 = "Fb@amy123";
    private static final String VALID_SOCIAL_2 = "Ig@bob456";
    private static final String VALID_TIME_INTERVAL_1 = "mon@2000-mon@2200";
    private static final String VALID_TIME_INTERVAL_2 = "tue@2000-wed@2200";
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
    public void parseCountry_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCountry((String) null));
    }

    @Test
    public void parseCountry_validValueWithoutWhitespace_returnsCountry() throws Exception {
        Country expectedCountry = new Country(VALID_COUNTRY);
        assertEquals(expectedCountry, ParserUtil.parseCountry(VALID_COUNTRY));
    }

    @Test
    public void parseCountry_validValueWithWhitespace_returnsTrimmedCountry() throws Exception {
        String countryWithWhitespace = WHITESPACE + VALID_COUNTRY + WHITESPACE;
        Country expectedCountry = new Country(VALID_COUNTRY);
        assertEquals(expectedCountry, ParserUtil.parseCountry(countryWithWhitespace));
    }

    @Test
    public void parseGameType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGameType(null));
    }


    @Test
    public void parseGameType_validValueWithoutWhitespace_returnsGameType() throws Exception {
        GameType expectedGameType = new GameType(VALID_GAME_TYPE_1);
        assertEquals(expectedGameType, ParserUtil.parseGameType(VALID_GAME_TYPE_1));
    }

    @Test
    public void parseGameType_validValueWithWhitespace_returnsTrimmedGameType() throws Exception {
        String gameTypeWithWhitespace = WHITESPACE + VALID_GAME_TYPE_1 + WHITESPACE;
        GameType expectedGameType = new GameType(VALID_GAME_TYPE_1);
        assertEquals(expectedGameType, ParserUtil.parseGameType(gameTypeWithWhitespace));
    }

    @Test
    public void parseGameTypes_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGameTypes(null));
    }

    @Test
    public void parseGameTypes_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseGameTypes(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseGameTypes_collectionWithValidGameTypes_returnsGameTypeSet() throws Exception {
        Set<GameType> actualGameTypeSet =
                ParserUtil.parseGameTypes(Arrays.asList(VALID_GAME_TYPE_1, VALID_GAME_TYPE_2));
        Set<GameType> expectedGameTypeSet = new HashSet<GameType>(Arrays.asList(
                new GameType(VALID_GAME_TYPE_1), new GameType(VALID_GAME_TYPE_2)));

        assertEquals(expectedGameTypeSet, actualGameTypeSet);
    }

    @Test
    public void parseServer_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseServer(null));
    }

    @Test
    public void parseServer_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseServer(INVALID_SERVER));
    }

    @Test
    public void parseServer_validValueWithoutWhitespace_returnsServer() throws Exception {
        Server expectedServer = new Server(VALID_MINECRAFT_SERVER_1);
        assertEquals(expectedServer, ParserUtil.parseServer(VALID_MINECRAFT_SERVER_1));
    }

    @Test
    public void parseServer_validValueWithWhitespace_returnsTrimmedServer() throws Exception {
        String serverWithWhitespace = WHITESPACE + VALID_MINECRAFT_SERVER_1 + WHITESPACE;
        Server expectedServer = new Server(VALID_MINECRAFT_SERVER_1);
        assertEquals(expectedServer, ParserUtil.parseServer(serverWithWhitespace));
    }

    @Test
    public void parseServers_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseServers(null));
    }

    @Test
    public void parseServers_collectionWithInvalidServers_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseServers(Arrays.asList(VALID_MINECRAFT_SERVER_1, INVALID_SERVER)));
    }

    @Test
    public void parseServers_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseServers(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseServers_collectionWithValidServers_returnsServerSet() throws Exception {
        Set<Server> actualServerSet =
                ParserUtil.parseServers(Arrays.asList(VALID_MINECRAFT_SERVER_1, VALID_MINECRAFT_SERVER_2));
        Set<Server> expectedServerSet = new HashSet<Server>(
                Arrays.asList(new Server(VALID_MINECRAFT_SERVER_1), new Server(VALID_MINECRAFT_SERVER_2)));

        assertEquals(expectedServerSet, actualServerSet);
    }

    @Test
    public void parseSocial_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSocial(null));
    }

    @Test
    public void parseSocial_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSocial(INVALID_SOCIAL));
    }

    @Test
    public void parseSocial_validValueWithoutWhitespace_returnsSocial() throws Exception {
        Social expectedSocial = new Social(VALID_SOCIAL_1);
        assertEquals(expectedSocial, ParserUtil.parseSocial(VALID_SOCIAL_1));
    }

    @Test
    public void parseSocial_validValueWithWhitespace_returnsTrimmedSocial() throws Exception {
        String socialWithWhitespace = WHITESPACE + VALID_SOCIAL_1 + WHITESPACE;
        Social expectedSocial = new Social(VALID_SOCIAL_1);
        assertEquals(expectedSocial, ParserUtil.parseSocial(socialWithWhitespace));
    }

    @Test
    public void parseSocials_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSocials(null));
    }

    @Test
    public void parseSocials_collectionWithInvalidSocials_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseSocials(Arrays.asList(VALID_SOCIAL_1, INVALID_SOCIAL)));
    }

    @Test
    public void parseSocials_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseSocials(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseSocials_collectionWithValidSocials_returnsSocialSet() throws Exception {
        Set<Social> actualSocialSet =
                ParserUtil.parseSocials(Arrays.asList(VALID_SOCIAL_1, VALID_SOCIAL_2));
        Set<Social> expectedSocialSet = new HashSet<Social>(
                Arrays.asList(new Social(VALID_SOCIAL_1), new Social(VALID_SOCIAL_2)));

        assertEquals(expectedSocialSet, actualSocialSet);
    }

    @Test
    public void parseTimeInterval_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTimeInterval(null));
    }

    @Test
    public void parseTimeInterval_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTimeInterval(INVALID_TIME_INTERVAL));
    }

    @Test
    public void parseTimeInterval_validValueWithoutWhitespace_returnsTimeInterval() throws Exception {
        TimeInterval expectedTimeInterval = new TimeInterval(VALID_TIME_INTERVAL_1);
        assertEquals(expectedTimeInterval, ParserUtil.parseTimeInterval(VALID_TIME_INTERVAL_1));
    }

    @Test
    public void parseTimeInterval_validValueWithWhitespace_returnsTrimmedTimeInterval() throws Exception {
        String timeIntervalWithWhitespace = WHITESPACE + VALID_TIME_INTERVAL_1 + WHITESPACE;
        TimeInterval expectedTimeInterval = new TimeInterval(VALID_TIME_INTERVAL_1);
        assertEquals(expectedTimeInterval, ParserUtil.parseTimeInterval(timeIntervalWithWhitespace));
    }

    @Test
    public void parseTimeIntervals_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTimeIntervals(null));
    }

    @Test
    public void parseTimeIntervals_collectionWithInvalidTimeIntervals_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseTimeIntervals(Arrays.asList(VALID_TIME_INTERVAL_1, INVALID_TIME_INTERVAL)));
    }

    @Test
    public void parseTimeIntervals_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTimeIntervals(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTimeIntervals_collectionWithValidTimeIntervals_returnsTimeIntervalSet() throws Exception {
        Set<ITimesAvailable> actualTimeIntervalSet =
                ParserUtil.parseTimeIntervals(Arrays.asList(VALID_TIME_INTERVAL_1, VALID_TIME_INTERVAL_2));
        Set<TimeInterval> expectedTimeIntervalSet = new HashSet<TimeInterval>(
                Arrays.asList(new TimeInterval(VALID_TIME_INTERVAL_1), new TimeInterval(VALID_TIME_INTERVAL_2)));

        assertEquals(expectedTimeIntervalSet, actualTimeIntervalSet);
    }
}
