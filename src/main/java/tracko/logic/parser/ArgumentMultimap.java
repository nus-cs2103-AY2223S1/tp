package tracko.logic.parser;

import java.util.ArrayList;
import java.util.HashMap;
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
    private final Map<ArgumentToken, List<String>> argMultimap = new HashMap<>();

    /**
     * Associates the specified argument value with {@code argToken} key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param argToken   Prefix key with which the specified argument value is to be associated
     * @param argValue Argument value to be associated with the specified argToken key
     */
    public void put(ArgumentToken argToken, String argValue) {
        List<String> argValues = getAllValues(argToken);
        argValues.add(argValue);
        argMultimap.put(argToken, argValues);
    }

    /**
     * Returns the last value of {@code prefix}.
     */
    public Optional<String> getValue(ArgumentToken prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns all values of {@code ArgumentToken}.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     */
    public List<String> getAllValues(ArgumentToken prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() {
        return getValue(new Prefix(""))
                .orElseGet(() -> getValue(new Flag("")).orElse(""));
    }

    /**
     * Returns true if at least one flag does not contain empty {@Optional} value in the
     * {@code ArgumentMultimap}
     * @param flags flags to be checked for input present
     * @return true if at least one flag is present in the {@code ArgumentMultimap}
     */
    public boolean isAnyFlagPresent(Flag... flags) {
        return Stream.of(flags).anyMatch(flag -> this.getValue(flag).isPresent());
    }
}
