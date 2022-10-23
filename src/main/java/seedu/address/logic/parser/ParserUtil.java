package seedu.address.logic.parser;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.AllPrefixesMissingException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.SomePrefixesMissingException;

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
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static void assertAllPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes)
            throws AllPrefixesMissingException {
        Prefix[] missingPrefixes = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isEmpty())
                .map(prefix -> new Prefix(prefix.getPrefix()))
                .toArray(Prefix[]::new);

        if (missingPrefixes.length != 0) {
            throw new AllPrefixesMissingException(missingPrefixes);
        }
    }

    /**
     * Returns true if at least one of the prefixes has non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static void assertAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes)
            throws SomePrefixesMissingException {
        Prefix[] missingPrefixes = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isEmpty())
                .map(prefix -> new Prefix(prefix.getPrefix()))
                .toArray(Prefix[]::new);

        if (missingPrefixes.length == prefixes.length) {
            throw new SomePrefixesMissingException(prefixes);
        }
    }

}
