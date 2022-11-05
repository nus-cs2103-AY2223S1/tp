package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Country;
import seedu.address.model.person.DayTimeInWeek;
import seedu.address.model.person.Email;
import seedu.address.model.person.GameType;
import seedu.address.model.person.ITimesAvailable;
import seedu.address.model.person.Keyword;
import seedu.address.model.person.MinecraftName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Server;
import seedu.address.model.person.Social;
import seedu.address.model.person.Tag;
import seedu.address.model.person.TimeInterval;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String minecraftName} into a {@code MinecraftName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code minecraftName} is invalid.
     */
    public static MinecraftName parseMinecraftName(String minecraftName) throws ParseException {
        requireNonNull(minecraftName);
        String trimmedName = minecraftName.trim();
        if (!MinecraftName.isValidMinecraftName(trimmedName)) {
            throw new ParseException(MinecraftName.MESSAGE_CONSTRAINTS);
        }
        return new MinecraftName(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses day times of week.
     * @param dayTimesInWeek The collection of the days of week.
     * @return The set of days of week.
     * @throws ParseException if the given {@code daysOfWeek} is invalid.
     */
    public static Set<DayTimeInWeek> parseDayTimesInWeek(Collection<String> dayTimesInWeek) throws ParseException {
        requireNonNull(dayTimesInWeek);
        final Set<DayTimeInWeek> set = new HashSet<>();
        for (String str : dayTimesInWeek) {
            set.add(parseDayTimeInWeek(str));
        }
        return set;
    }

    /**
     * Parses a {@code String dayTimeInWeek} into an {@code DayTimeInWeek}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dayTimeInWeek} is invalid.
     */
    public static DayTimeInWeek parseDayTimeInWeek(String dayTimeInWeek) throws ParseException {
        requireNonNull(dayTimeInWeek);
        String trimmed = dayTimeInWeek.trim();
        if (DayTimeInWeek.isFindTimeNow(trimmed)) {
            trimmed = CurrentTimeParser.findTimeNowInString();
        }
        if (!DayTimeInWeek.isValidDayTimeInWeekRegex(trimmed)) {
            throw new ParseException(DayTimeInWeek.MESSAGE_CONSTRAINTS);
        }
        if (!DayTimeInWeek.isValidDayTimeInWeekParsing(trimmed)) {
            throw new ParseException(DayTimeInWeek.ILLEGAL_TIME_CONSTRAINTS);
        }
        return new DayTimeInWeek(trimmed);
    }

    /**
     * Parses a {@code String socialStrs} into a set of {@code Social}.
     * @param socialStrs All the Strings for the socials.
     * @return A set of social media handles
     * @throws ParseException if the given {@code socialStr} is invalid.
     */
    public static Set<Social> parseSocials(Collection<String> socialStrs) throws ParseException {
        requireNonNull(socialStrs);
        final Set<Social> socialSet = new HashSet<>();
        for (String str : socialStrs) {
            socialSet.add(parseSocial(str));
        }
        return socialSet;
    }

    /**
     * Parses a {@code String socialStr} into a {@code Social}.
     *
     * @param socialStr the social media platform to be parsed
     * @return The social media platform
     * @throws ParseException if the given {@code socialStr} is invalid.
     */
    public static Social parseSocial(String socialStr) throws ParseException {
        requireNonNull(socialStr);
        String trimmedSocial = socialStr.trim();
        if (!Social.isValidSocial(trimmedSocial)) {
            throw new ParseException(Social.MESSAGE_CONSTRAINTS);
        }
        String[] strArray = trimmedSocial.split("@");
        String platform = strArray[0];
        String handle = strArray[1];
        return new Social(handle, platform);
    }

    /**
     * Parses a {@code String keywordStrs} into a set of {@code Keyword}.
     * @param keywordStrs All the Strings for the keywords.
     * @return A set of keywords.
     * @throws ParseException if the given {@code keywordStrs} is invalid.
     */
    public static Set<Keyword> parseKeywords(Collection<String> keywordStrs) throws ParseException {
        requireNonNull(keywordStrs);
        final Set<Keyword> keywordSet = new HashSet<>();
        for (String str : keywordStrs) {
            keywordSet.add(parseKeyword(str));
        }
        return keywordSet;
    }

    /**
     * Parses a {@code String keywordStr} into a {@code Keyword}.
     *
     * @param keywordStr the social media platform to be parsed
     * @return The keyword
     * @throws ParseException if the given {@code keywordStr} is invalid
     */
    public static Keyword parseKeyword(String keywordStr) throws ParseException {
        requireNonNull(keywordStr);

        if (!Keyword.isValidKeyword(keywordStr)) {
            throw new ParseException(Keyword.MESSAGE_CONSTRAINTS);
        }

        return new Keyword(keywordStr);

    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String server} into a {@code Server}.
     *
     * @param server the server to be parsed
     * @return the server
     * @throws ParseException if the given {@code server} is invalid.
     */
    public static Server parseServer(String server) throws ParseException {
        requireNonNull(server);
        String trimmedServer = server.trim();
        if (!Server.isValidServerName(trimmedServer)) {
            throw new ParseException(Server.getServerConstraints());
        }
        return new Server(trimmedServer);
    }

    /**
     * Parses {@code Collection<String> servers} into a {@code Set<Server>}.
     *
     * @param servers Collection of servers to be parsed.
     * @return Set of servers.
     * @throws ParseException if any of the servers are invalid.
     */
    public static Set<Server> parseServers(Collection<String> servers) throws ParseException {
        requireNonNull(servers);
        final Set<Server> serverSet = new HashSet<>();
        for (String serverName: servers) {
            serverSet.add(parseServer(serverName));
        }
        return serverSet;
    }

    /**
     * Parses a {@code String Country} into a {@code Country object}.
     * @param country the country
     * @return the parsed Country
     * @throws ParseException if the given {@code country} is invalid.
     */
    public static Country parseCountry(String country) throws ParseException {
        requireNonNull(country);
        String trimmedCountry = country.trim();
        if (!Country.isValidCountry(trimmedCountry)) {
            throw new ParseException(Country.getMessageConstraints());
        }
        return new Country(trimmedCountry);
    }

    /**
     * Parses a {@code String game type} into a {@code GameType object}.
     * @param gameType the preferred game type.
     * @return the parsed game type.
     * @throws ParseException if the given {@code gameType} is invalid.
     */

    public static GameType parseGameType(String gameType) throws ParseException {
        requireNonNull(gameType);
        String trimmedGameType = gameType.trim();
        if (!GameType.isValidGameType(trimmedGameType)) {
            throw new ParseException(GameType.getMessageConstraints());
        }
        return new GameType(trimmedGameType);
    }

    /**
     * Parses {@code Collection<String> game types} into a {@code Set<GameType>}.
     *
     * @param gameTypes Collection of game types to be parsed.
     * @return Set of game types.
     * @throws ParseException if any of the game types are invalid.
     */

    public static Set<GameType> parseGameTypes(Collection<String> gameTypes) throws ParseException {
        requireNonNull(gameTypes);
        final Set<GameType> gameTypeSet = new HashSet<>();
        for (String gameType : gameTypes) {
            gameTypeSet.add(parseGameType(gameType));
        }
        return gameTypeSet;
    }

    /**
     * Parses a {@code String time interval} into a {@code ITimesAvailable object}.
     * @param timeInterval the time interval where the Person is online.
     * @return the parsed time interval.
     * @throws ParseException if the given {@code timeInterval} is invalid.
     */
    public static ITimesAvailable parseTimeInterval(String timeInterval) throws ParseException {
        requireNonNull(timeInterval);
        String trimmedTimeInterval = timeInterval.trim();
        if (!TimeInterval.isValidTimeInterval(trimmedTimeInterval)) {
            throw new ParseException(TimeInterval.getTimeIntervalConstraints());
        }
        String startTime = TimeInterval.getStartingDayTimeInWeek(trimmedTimeInterval);
        if (!DayTimeInWeek.isValidDayTimeInWeekParsing(startTime)) {
            throw new ParseException(DayTimeInWeek.ILLEGAL_TIME_CONSTRAINTS);
        }
        String endTime = TimeInterval.getEndingDayTimeInWeek(trimmedTimeInterval);
        if (!DayTimeInWeek.isValidDayTimeInWeekParsing(endTime)) {
            throw new ParseException(DayTimeInWeek.ILLEGAL_TIME_CONSTRAINTS);
        }
        return new TimeInterval(trimmedTimeInterval);
    }

    /**
     * Parses {@code Collection<String> time interval} into a {@code Set<ITimesAvailable>}.
     *
     * @param timeIntervals Collection of time intervals to be parsed.
     * @return Set of time intervals.
     * @throws ParseException if any of the time intervals are invalid.
     */
    public static Set<ITimesAvailable> parseTimeIntervals(Collection<String> timeIntervals) throws ParseException {
        requireNonNull(timeIntervals);
        final Set<ITimesAvailable> timeIntervalSet = new HashSet<>();
        for (String timeInterval: timeIntervals) {
            timeIntervalSet.add(parseTimeInterval(timeInterval));
        }
        return timeIntervalSet;
    }
}
