package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.server.Server;
import seedu.address.model.tag.Tag;

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
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
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
     * Parses a {@code String social} into an {@code Social}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code socialStr} is invalid.
     */

    public static Set<Social> parseSocials(Collection<String> socialStr) throws ParseException {
        requireNonNull(socialStr);
        final Set<Social> socialSet = new HashSet<>();
        for (String str : socialStr) {
            socialSet.add(parseSocial(str));
        }
        return socialSet;
    }

    /**
     * Parses a {@code String socialStr} into a {@code Social}.
     *
     * @param socialStr the social media platform to be parsed
     * @return the social media platform
     * @throws ParseException if the given {@code socialStr} is invalid.
     */
    public static Social parseSocial(String socialStr) throws ParseException {
        requireNonNull(socialStr);

        if (!Social.isValidSocial(socialStr)) {
            throw new ParseException(Social.MESSAGE_CONSTRAINTS);
        }

        String[] strArray = socialStr.split("@");
        Platform platform = PlatformConverter.stringToPlatform(strArray[0]);
        String handle = strArray[1];

        return new Social(handle, platform);

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
     * Parses a {@code String timezone offset} into a {@code TimeZone object}.
     * @param offset the offset of the timezone
     * @return the parsed offset as a TimeZone
     * @throws ParseException if the given {@code offset} is invalid.
     */
    public static TimeZone parseTimeZone(String offset) throws ParseException {
        requireNonNull(offset);
        String trimmedOffset = offset.trim();
        if (!TimeZone.isValidTimeZone(trimmedOffset)) {
            throw new ParseException(TimeZone.getMessageConstraints());
        }
        return new TimeZone(trimmedOffset);
    }

    public static GameType parseGameType(String gameType) throws ParseException {
        requireNonNull(gameType);
        String trimmedGameType = gameType.trim();
        if (!GameType.isValidGameType(trimmedGameType)) {
            throw new ParseException(GameType.getMessageConstraints());
        }
        return new GameType(trimmedGameType);
    }

    public static Set<GameType> parseGameType(Collection<String> gameTypes) throws ParseException {
        requireNonNull(gameTypes);
        final Set<GameType> gameTypeSet = new HashSet<>();
        for (String gameType : gameTypes) {
            gameTypeSet.add(parseGameType(gameType));
        }
        return gameTypeSet;
    }
}
