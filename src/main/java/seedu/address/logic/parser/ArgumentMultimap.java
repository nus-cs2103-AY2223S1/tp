package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PREAMBLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Stores mapping of prefixes to their respective arguments.
 * Each key may be associated with multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values may contain duplicate argument values, i.e. the same argument value
 * can be inserted multiple times for the same prefix.
 */
public class ArgumentMultimap {

    /**
     * Prefixes mapped to their respective arguments
     **/
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
     * Returns the last value of optional {@code prefix}.
     */
    public String getOptionalValue(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return null;
        } else {
            List<String> values = getAllValues(prefix);
            return values.isEmpty() ? null : values.get(values.size() - 1);
        }
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
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() {
        return getValue(PREFIX_PREAMBLE).orElse("");
    }

    /**
     * Returns the {@code prefix} if there is only one prefix in the argument multimap.
     * Otherwise, return nothing.
     */
    public Optional<Prefix> getOnlyPrefixPresent() {

        ArrayList<String> empty = new ArrayList<>(Collections.singleton(""));

        argMultimap.values().removeAll(Collections.singleton(empty));

        if (argMultimap.size() == 1) {
            return Optional.of(argMultimap.keySet().iterator().next());
        } else {
            return Optional.empty();
        }
    }

    /**
     * Returns true if and only if the argument multimap only contains the prefixes passed into the method.
     */
    public boolean hasOnlySpecifiedPrefixPresent(Prefix... prefixes) {

        ArrayList<String> empty = new ArrayList<>(Collections.singleton(""));
        argMultimap.values().removeAll(Collections.singleton(empty));

        Set<Prefix> receivedSet = new HashSet<>(argMultimap.keySet());
        Set<Prefix> wantedSet = Arrays.stream(prefixes).collect(Collectors.toSet());
        receivedSet.removeAll(wantedSet);

        return receivedSet.isEmpty();
    }
}
