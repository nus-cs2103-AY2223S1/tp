package seedu.modquik.logic.parser;

import java.util.Objects;
import java.util.stream.Stream;

import seedu.modquik.commons.core.index.Index;
import seedu.modquik.commons.util.StringUtil;
import seedu.modquik.logic.parser.exceptions.AllPrefixesMissingException;
import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.logic.parser.exceptions.PrefixesEmptyException;
import seedu.modquik.logic.parser.exceptions.SomePrefixesMissingException;
import seedu.modquik.model.commons.ModuleCode;
import seedu.modquik.model.commons.Venue;

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
     * Throws ParseException if any of the prefixes are not present in the given
     * {@code ArgumentMultimap}.
     */
    public static void assertAllPrefixesPresent(ArgumentMultimap argumentMultimap, String messageUsage,
                                                Prefix... prefixes) throws AllPrefixesMissingException {
        Prefix[] missingPrefixes = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isEmpty())
                .map(prefix -> new Prefix(prefix.getPrefix()))
                .toArray(Prefix[]::new);

        if (missingPrefixes.length != 0) {
            throw new AllPrefixesMissingException(missingPrefixes, messageUsage);
        }
    }

    /**
     * Throws ParseException if at none of the prefixes has non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static void assertAnyPrefixesPresent(ArgumentMultimap argumentMultimap, String messageUsage,
                                                Prefix... prefixes) throws SomePrefixesMissingException {
        Prefix[] missingPrefixes = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isEmpty())
                .map(prefix -> new Prefix(prefix.getPrefix()))
                .toArray(Prefix[]::new);

        if (missingPrefixes.length == prefixes.length) {
            throw new SomePrefixesMissingException(prefixes, messageUsage);
        }
    }

    /**
     * Throws ParseException if a prefix that is present has non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static void assertPrefixesPresentNotEmpty(ArgumentMultimap argumentMultimap, String messageUsage,
                                                     Prefix... prefixes) throws PrefixesEmptyException {
        Prefix[] prefixesPresentNotEmpty = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .filter(prefix -> argumentMultimap.getValue(prefix).get().equals(""))
                .toArray(Prefix[]::new);

        if (prefixesPresentNotEmpty.length != 0) {
            throw new PrefixesEmptyException(prefixesPresentNotEmpty, messageUsage);
        }
    }

    /**
     * Parses a {@code String moduleCode} into a {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleCode} is invalid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        Objects.requireNonNull(moduleCode);
        String trimmedName = moduleCode.trim();
        if (!ModuleCode.isValidModule(trimmedName)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        String formattedName = trimmedName.toUpperCase();
        return new ModuleCode(formattedName);
    }

    /**
     * Parses a {@code String venue} into a {@code Venue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static Venue parseVenue(String venue) throws ParseException {
        Objects.requireNonNull(venue);
        String trimmedName = venue.trim();
        if (!Venue.isValidVenue(trimmedName)) {
            throw new ParseException(Venue.MESSAGE_CONSTRAINTS);
        }
        return new Venue(trimmedName);
    }
}
