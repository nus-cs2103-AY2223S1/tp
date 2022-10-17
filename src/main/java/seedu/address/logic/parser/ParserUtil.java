package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.GraphType;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
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
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String entryType} into a {@code EntryType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code entryType} is invalid.
     */
    public static EntryType parseEntryType(String entryType) throws ParseException {
        requireNonNull(entryType);
        String trimmedEntryType = entryType.trim();
        if (!EntryType.isValidEntryType(trimmedEntryType)) {
            throw new ParseException(EntryType.MESSAGE_CONSTRAINTS);
        }
        return new EntryType(trimmedEntryType);
    }

    /**
     * Parses a {@code String graphType} into a {@code GraphType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code graphType} is invalid.
     */
    public static GraphType parseGraphType(String graphType) throws ParseException {
        requireNonNull(graphType);
        String trimmedGraphType = graphType.trim();
        if (!GraphType.isValidGraphType(trimmedGraphType)) {
            throw new ParseException(GraphType.MESSAGE_CONSTRAINTS);
        }
        return new GraphType(trimmedGraphType);
    }

    /**
     * Parses a {@code String amount} into a {@code Amount}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code amount} is invalid.
     */
    public static Amount parseAmount(String amount) throws ParseException {
        requireNonNull(amount);
        String trimmedAmount = amount.trim();
        if (!Amount.isValidAmount(amount)) {
            throw new ParseException(Amount.MESSAGE_CONSTRAINTS);
        }
        return new Amount(trimmedAmount);
    }

    /**
     * Parses a {@code String date} into a {@code Date}.
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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(EntryType type, String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(type, trimmedTag)) {
            switch (type.getEntryType()) {
            case INCOME:
                throw new ParseException(Tag.INCOME_CONSTRAINTS);
            case EXPENDITURE:
                throw new ParseException(Tag.EXPENDITURE_CONSTRAINTS);
            default:
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }
        }
        return new Tag(type, trimmedTag);
    }

    ///**
    // * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
    // */
    //public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
    //    requireNonNull(tags);
    //    final Set<Tag> tagSet = new HashSet<>();
    //    for (String tagName : tags) {
    //        tagSet.add(parseTag(tagName));
    //    }
    //    return tagSet;
    //}

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
