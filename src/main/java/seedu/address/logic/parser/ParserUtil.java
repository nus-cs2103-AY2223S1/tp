package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.TagCommandGroup;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.TagMatchesQueryPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String COMMA = "\\s*,\\s*";

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
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
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
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        if (TagCommandGroup.isBannedTagName(trimmedTag)) {
            throw new ParseException(String.format(TagCommandGroup.MESSAGE_BANNED_TAG_NAME, trimmedTag));
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
     * Parses {@code Collection<String> names} to into {@code NameContainsKeywordsPredicate}.
     *
     * @param names Collection of names to parse into {@code NameContainsKeywordsPredicate}.
     * @return Set of {@code NameContainsKeywordPredicate} that filters based on {@code names}.
     * @throws ParseException If an error occurs during parsing.
     */
    public static Set<NameContainsKeywordsPredicate> parseNameQueryPredicate(Collection<String> names)
            throws ParseException {
        requireNonNull(names);
        return names.stream()
                .map(name -> new NameContainsKeywordsPredicate(name))
                .collect(Collectors.toSet());
    }

    /**
     * Parses {@code Collection<String> names} to into {@code TagMatchesQueryPredicate}.
     *
     * @param tags Collection of tags to parse into {@code TagMatchesKeywordsPredicate}.
     * @return Set of {@code TagMatchesQueryPredicate} that filters based on {@code tags}.
     * @throws ParseException If an error occurs during parsing.
     */
    public static Set<TagMatchesQueryPredicate> parseTagsQueryPredicate(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        Set<Tag> tagSetQuery = parseTags(tags);
        return tagSetQuery.stream()
                .map(tag -> new TagMatchesQueryPredicate(tag))
                .collect(Collectors.toSet());
    }

    /**
     * Parses {@code String keywords} separated by commas into a {@code List<String>}.
     *
     * @param keywords Comma separated strings.
     * @return A list of separated keywords without commas.
     */
    public static List<String> parseCommaSeparatedKeywords(String keywords) {
        return List.of(keywords.split(COMMA));
    }

    /**
     * Parses the collection {@code String keywords} in each entry into a {@code List<String>}.
     *
     * @param keywords List of comma separated keywords
     * @return A list of separated keywords without commas.
     */
    public static List<String> parseCommaSeparatedKeywords(Collection<String> keywords) {
        return keywords.stream()
                .flatMap((keyword) -> parseCommaSeparatedKeywords(keyword).stream())
                .collect(Collectors.toList());
    }
}
