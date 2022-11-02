package foodwhere.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import foodwhere.commons.core.Messages;
import foodwhere.commons.core.index.Index;
import foodwhere.commons.util.StringUtil;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Content;
import foodwhere.model.review.Date;
import foodwhere.model.review.Rating;
import foodwhere.model.review.comparator.ReviewsComparatorList;
import foodwhere.model.stall.comparator.StallsComparatorList;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(Messages.MESSAGE_INVALID_INDEX);
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
        trimmedName = trimmedName.replaceAll("\\s+", " ");
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses {@code String input} into a {@code Set<Names>}, where each name in input is separated by space.
     */
    public static Set<Name> parseNameList(String input) throws ParseException {
        requireNonNull(input);
        String[] names = input.split(" ");
        final Set<Name> nameSet = new HashSet<>();
        for (String name : names) {
            nameSet.add(parseName(name));
        }
        return nameSet;
    }

    /**
     * Parses {@code String input} into a {@code Set<Tag>}, where each Tag in input is separated by space.
     */
    public static Set<Tag> parseTagList(String input) throws ParseException {
        requireNonNull(input);
        String[] tags = input.split(" ");
        final Set<Tag> tagSet = new HashSet<>();
        for (String tag : tags) {
            tagSet.add(parseTag(tag));
        }
        return tagSet;
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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTag(trimmedTag)) {
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
        for (String tag : tags) {
            tagSet.add(parseTag(tag));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String date} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String content} into an {@code Content}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code content} is invalid.
     */
    public static Content parseContent(String content) throws ParseException {
        requireNonNull(content);
        String trimmedContent = content.trim();
        if (!Content.isValidContent(trimmedContent)) {
            throw new ParseException(Content.MESSAGE_CONSTRAINTS);
        }
        return new Content(trimmedContent);
    }

    /**
     * Parses a {@code String rating} into an {@code Rating}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code rating} is invalid.
     */
    public static Rating parseRating(String rating) throws ParseException {
        requireNonNull(rating);
        if (!Rating.isValidRating(rating)) {
            throw new ParseException(Rating.MESSAGE_CONSTRAINTS);
        }
        return new Rating(rating);
    }

    /**
     * Parses a {@code String criteria} into an {@code StallsComparatorList}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code criteria} is invalid.
     */
    public static StallsComparatorList parseStallCriteria(String criteria) throws ParseException {
        requireNonNull(criteria);
        String trimmedCriteria = criteria.trim();
        try {
            return StallsComparatorList.valueOf(trimmedCriteria.toUpperCase());
        } catch (IllegalArgumentException iae) {
            throw new ParseException(StallsComparatorList.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String criteria} into an {@code ReviewsComparatorList}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code criteria} is invalid.
     */
    public static ReviewsComparatorList parseReviewCriteria(String criteria) throws ParseException {
        requireNonNull(criteria);
        String trimmedCriteria = criteria.trim();
        try {
            return ReviewsComparatorList.valueOf(trimmedCriteria.toUpperCase());
        } catch (IllegalArgumentException iae) {
            throw new ParseException(ReviewsComparatorList.MESSAGE_CONSTRAINTS);
        }
    }
}
