package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.UniqueTagTypeMap;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagType;
import seedu.address.model.tag.UniqueTagList;

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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static UniqueTagList parseTagList(List<String> tags) throws ParseException {
        requireNonNull(tags);
        UniqueTagList tagList = new UniqueTagList();
        for (String t : tags) {
            if (!Tag.isValidTagName(t.trim())) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }
            tagList.add(new Tag(t.trim()));
        }
        return tagList;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static UniqueTagTypeMap parseTags(Map<Prefix, List<String>> tags) throws ParseException {
        requireNonNull(tags);
        final UniqueTagTypeMap tagMap = new UniqueTagTypeMap();
        Map<TagType, UniqueTagList> tagTypeMap = new HashMap<>();
        for (Prefix tagName : tags.keySet()) {
            tagTypeMap.put(UniqueTagTypeMap.getTagType(tagName), parseTagList(tags.get(tagName)));
        }
        tagMap.setTagTypeMap(tagMap);
        return tagMap;
    }

    /**
     * Parses a {@code String prefix} into a {@code Prefix}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code prefix} is invalid.
     */
    public static Prefix parsePrefix(String prefix) throws ParseException {
        requireNonNull(prefix);
        String trimmedPrefix = prefix.trim();
        if (!Prefix.isValidPrefixName(trimmedPrefix)) {
            throw new ParseException(Prefix.MESSAGE_CONSTRAINTS);
        }
        return new Prefix(trimmedPrefix + "/");
    }

    /**
     * Parses a {@code String tagType} into a {@code TagType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tagType} is invalid.
     */
    public static TagType parseTagType(String tagType, String prefix) throws ParseException {
        requireNonNull(tagType);
        String trimmedTagType = tagType.trim();
        if (!TagType.isValidTagType(trimmedTagType)) {
            throw new ParseException(TagType.MESSAGE_CONSTRAINTS);
        }
        Prefix pref = parsePrefix(prefix);
        return new TagType(trimmedTagType, pref);
    }

    /**
     * Parses a {@code String tagType} into a {@code TagType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tagType} is invalid.
     */
    public static TagType parseTagType(String tagType, Prefix prefix) throws ParseException {
        requireNonNull(tagType);
        String trimmedTagType = tagType.trim();
        if (!TagType.isValidTagType(trimmedTagType)) {
            throw new ParseException(TagType.MESSAGE_CONSTRAINTS);
        }
        return new TagType(trimmedTagType, prefix);
    }

    /**
     * Splits a hyphen.
     * @param oldNew Hyphenated String.
     * @return Array of Strings consisting of strings preceding and following the hyphen.
     * @throws ParseException If argument format incorrect.
     */
    public static String[] parseHyphen(String oldNew) throws ParseException {
        String[] oldNewPair = oldNew.split("\\s+-\\s+", 2);
        if (oldNewPair.length != 2) {
            throw new ParseException("Invalid command format!");
        }
        return oldNewPair;
    }
}
