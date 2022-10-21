package seedu.address.logic.parser;

import static java.util.Objects.isNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    private Prefix firstPrefix = null;

    private boolean phoneIdentifier = false;
    private boolean emailIdentifier = false;
    private int prefixCount = 0;

    /**
     * Associates the specified argument value with {@code prefix} key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param prefix   Prefix key with which the specified argument value is to be associated
     * @param argValue Argument value to be associated with the specified prefix key
     */
    public void put(Prefix prefix, String argValue) {
        if (isNull(firstPrefix) || firstPrefix.getPrefix().isEmpty()) {
            firstPrefix = prefix;
        }
        if (prefix.equals(PREFIX_PHONE) && !emailIdentifier) {
            phoneIdentifier = true;
        } else if (prefix.equals(PREFIX_EMAIL) && !phoneIdentifier) {
            emailIdentifier = true;
        }
        List<String> argValues = getAllValues(prefix);
        argValues.add(argValue);
        argMultimap.put(prefix, argValues);
        prefixCount++;
    }

    /**
     * Returns the last value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Checks if the {@code prefix} only occurs once in the user input.
     *
     * @param prefix the prefix to check against
     * @return true if the {@code prefix} only occurs once, otherwise return false
     */
    public boolean isUniquePrefix(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.size() == 1;
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
        return getValue(new Prefix("")).orElse("");
    }

    /**
     * Returns the first prefix input by the user.
     *
     * @return the first prefix input by the user.
     */
    public Prefix getFirstPrefix() {
        return this.firstPrefix;
    }

    /**
     * Returns if the phone number of a person exists as an identifier.
     *
     * @return whether phone number exists as an identifier.
     */
    public boolean getPhoneIdentifier() {
        return phoneIdentifier;
    }

    /**
     * Returns if the email address of a person exists as an identifier.
     *
     * @return whether email address exists as an identifier.
     */
    public boolean getEmailIdentifier() {
        return emailIdentifier;
    }

    /**
     * Returns number of prefixes found in the command.
     *
     * @return number of prefixes found in the command.
     */
    public int getPrefixCount() {
        return this.prefixCount;
    }
}
