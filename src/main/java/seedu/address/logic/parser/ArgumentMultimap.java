package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stores mapping of prefixes to their respective arguments.
 * Each key may be associated with multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values may contain duplicate argument values, i.e. the same argument value
 * can be inserted multiple times for the same prefix.
 */
public class ArgumentMultimap {

    /** Prefixes mapped to their respective arguments**/
    private final Map<Prefix, List<String>> argMultimap = new HashMap<>();

    /**
     * Associates the specified argument value with {@code prefix} key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param prefix   Prefix key with which the specified argument value is to be associated
     * @param argValue Argument value to be associated with the specified prefix key
     */
    public void put(Prefix prefix, String argValue) {
        List<String> argValues = getAllValues(prefix);
        argValues.add(argValue);
        argMultimap.put(prefix, argValues);
    }

    /**
     * Returns the last value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns all values of {@code prefix}.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     */
    public List<String> getAllValues(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Returns all values of {@code prefix} separated by spaces.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     */
    public List<String> getAllValuesSeparatedByRegex(Prefix prefix, String regex) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        String value = getValue(prefix).get().toLowerCase();
        return Arrays.stream(value.split(regex)).collect(Collectors.toList());
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() {
        return getValue(new Prefix("")).orElse("");
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if any of the prefixes are present {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if none of the prefixes are present {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean noPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
