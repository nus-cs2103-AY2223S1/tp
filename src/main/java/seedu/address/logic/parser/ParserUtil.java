package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.logic.parser.exceptions.SocialNotFoundException;
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
     * Parses {@code Collection<String> socialStrs} into a {@code Socials}.
     */

    public static Set<Social> parseSocials(Collection<String> socialStrs) throws ParseException {
        requireNonNull(socialStrs);
        final Set<Social> socialSet = new HashSet<>();
        for (String str : socialStrs) {
            socialSet.add(parseSocial(str));
        }
        return socialSet;
    }

    public static Social parseSocial(String socialStr) throws ParseException {
        String[] strArray = socialStr.split("@");
        String rawPlatform = strArray[0];
        String handle = strArray[1];

        switch (rawPlatform) {
            case "fb":
                return new Social(handle, Platform.FACEBOOK);
            case "ig":
                return new Social(handle, Platform.INSTAGRAM);
            case "sc":
                return new Social(handle, Platform.SNAPCHAT);
            default:
                throw new SocialNotFoundException();
        }

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

    public static Server parseServer(String server) throws ParseException {
        requireNonNull(server);
        String trimmedServer = server.trim();
        if (!Server.isValidServerName(trimmedServer)) {
            throw new ParseException(Server.getServerConstraints());
        }
        return new Server(trimmedServer);
    }

    public static Set<Server> parseServers(Collection<String> servers) throws ParseException {
        requireNonNull(servers);
        final Set<Server> serverSet = new HashSet<>();
        for (String serverName: servers) {
            serverSet.add(parseServer(serverName));
        }
        return serverSet;
    }

    public static TimeZone parseTimeZone(String offset) throws ParseException {
        requireNonNull(offset);
        String trimmedOffset = offset.trim();
        if (!TimeZone.isValidTimeZone(trimmedOffset)) {
            throw new ParseException(TimeZone.getMessageConstraints());
        }
        return new TimeZone(trimmedOffset);
    }
}
