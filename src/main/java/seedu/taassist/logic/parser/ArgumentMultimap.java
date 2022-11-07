package seedu.taassist.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
     * @param prefix   Prefix key with which the specified argument value is to be associated.
     * @param argValue Argument value to be associated with the specified prefix key.
     */
    public void put(Prefix prefix, String argValue) {
        List<String> argValues = getAllValues(prefix);
        argValues.add(argValue);
        argMultimap.put(prefix, argValues);
    }

    /**
     * Returns the last value of {@code prefix}.
     *
     * @param prefix Prefix to get its value from.
     * @return Last value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns all values of {@code prefix}.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     *
     * @param prefix Prefix to get its values from.
     * @return All values of {@code prefix}.
     */
    public List<String> getAllValues(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Returns all unique values of {@code prefix}, ignoring case.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     *
     * @param prefix Prefix to get its values from.
     * @return All unique values of {@code prefix}, ignoring case.
     */
    public List<String> getAllValuesIgnoreCase(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }

        HashSet<String> visitedStrings = new HashSet<>();
        List<String> inputStrings = argMultimap.get(prefix);
        List<String> uniqueStrings = new ArrayList<>();

        // Start from the end of the list, so that only the last occurrence will be taken
        for (int i = inputStrings.size() - 1; i >= 0; i--) {
            String str = inputStrings.get(i);
            if (visitedStrings.contains(str.toUpperCase())) {
                continue;
            }
            visitedStrings.add(str.toUpperCase());
            uniqueStrings.add(str);
        }

        return uniqueStrings;
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() {
        return getValue(new Prefix("")).orElse("");
    }

    /**
     * Returns true if all the {@code Prefix} in {@code prefixes} are present
     * and do not contain empty {@code Optional} values.
     *
     * @param prefixes Prefixes to check for presence.
     * @return True if all prefixes in {@code prefixes} are present.
     */
    public boolean containsPrefixes(Prefix... prefixes) {
        requireNonNull(prefixes);
        return Stream.of(prefixes).allMatch(prefix -> getValue(prefix).isPresent());
    }
}
