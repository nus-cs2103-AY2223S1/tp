package gim.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import gim.commons.core.index.Index;
import gim.commons.util.StringUtil;
import gim.logic.parser.exceptions.ParseException;
import gim.model.date.Date;
import gim.model.exercise.Name;
import gim.model.exercise.Reps;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;

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
     * Parses a {@code String weight} into a {@code Weight}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code weight} is invalid.
     */
    public static Weight parseWeight(String weight) throws ParseException {
        requireNonNull(weight);
        String trimmedWeight = weight.trim();
        if (!Weight.isValidWeight(trimmedWeight)) {
            throw new ParseException(Weight.MESSAGE_CONSTRAINTS);
        }
        return new Weight(trimmedWeight);
    }

    /**
     * Parses a {@code String reps} into an {@code Reps}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code reps} is invalid.
     */
    public static Reps parseRep(String reps) throws ParseException {
        requireNonNull(reps);
        String trimmedRep = reps.trim();
        if (!Reps.isValidReps(trimmedRep)) {
            throw new ParseException(Reps.MESSAGE_CONSTRAINTS);
        }
        return new Reps(trimmedRep);
    }

    /**
     * Parses a {@code String sets} into an {@code Sets}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sets} is invalid.
     */
    public static Sets parseSets(String sets) throws ParseException {
        requireNonNull(sets);
        String trimmedSets = sets.trim();
        if (!Sets.isValidSets(trimmedSets)) {
            throw new ParseException(Sets.MESSAGE_CONSTRAINTS);
        }
        return new Sets(trimmedSets);
    }

    /**
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        // Remove beginning or trailing whitespaces
        String trimmedDate = date.trim();
        if (!Date.isValidDateByRegex(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS_FORMAT);
        }
        Date parsedDate;
        // Prevent non-existent dates that follow the format from being added
        try {
            parsedDate = new Date(trimmedDate);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS_INVALID);
        }
        return parsedDate;
    }

    /**
     * Parses {@code Collection<String> names} into a {@code Set<Name>}.
     */
    public static Set<Name> parseNames(Collection<String> names) throws ParseException {
        requireNonNull(names);
        final Set<Name> nameSet = new HashSet<>();
        for (String name : names) {
            nameSet.add(parseName(name));
        }
        return nameSet;
    }
}
