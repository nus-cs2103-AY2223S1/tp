package friday.model.alias;

import static friday.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import friday.model.alias.exceptions.AliasNotFoundException;
import friday.model.alias.exceptions.DuplicateAliasException;

/**
 * A map of alias to reserved keywords.
 */
public class AliasMap {

    private final HashMap<String, String> internalMap = new HashMap<>();

    /**
     * Returns true if the hashmap contains an alias with value {@code key}.
     */
    public boolean contains(String key) {
        requireNonNull(key);
        return internalMap.containsKey(key);
    }

    /**
     * Returns true if the hashmap contains an alias.
     */
    public boolean contains(Alias alias) {
        requireNonNull(alias);
        return internalMap.containsKey(alias.toString());
    }

    /**
     * Adds an alias that maps to keyword to the hashmap.
     * The alias must not already exist in the hashmap.
     * The keyword must be a valid reserved keyword.
     */
    public void add(Alias toAdd, ReservedKeyword keyword) {
        requireNonNull(toAdd);
        requireNonNull(keyword);
        assert ReservedKeyword.isValidReservedKeyword(keyword);
        if (contains(toAdd)) {
            throw new DuplicateAliasException();
        }
        internalMap.put(toAdd.toString(), keyword.toString());
    }

    /**
     * Removes the alias from the hashmap.
     * The alias must exist in the hashmap.
     */
    public void remove(Alias toRemove) {
        requireNonNull(toRemove);
        if (!contains(toRemove)) {
            throw new AliasNotFoundException();
        }
        internalMap.remove(toRemove.toString());
    }

    /**
     * Gets the keyword mapping of an alias if it exists.
     * If no existing mapping, return the same alias.
     */
    public String getKeyword(String toFind) {
        requireNonNull(toFind);
        if (contains(toFind)) {
            return internalMap.get(toFind);
        }
        return toFind;
    }

    /**
     * Returns a Set view of the mappings contained in alias map.
     * This set will not contain any duplicate aliases.
     */
    public Set<Map.Entry<String, String>> entrySet() {
        return internalMap.entrySet();
    }

    /**
     * Replaces the contents of this map with alias, keyword pair.
     */
    public void setAliases(Set<Map.Entry<String, String>> aliases) {
        requireAllNonNull(aliases);
        for (Map.Entry<String, String> aliasKeywordPair : aliases) {
            Alias alias = new Alias(aliasKeywordPair.getKey());
            ReservedKeyword keyword = new ReservedKeyword(aliasKeywordPair.getValue());
            add(alias, keyword);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AliasMap // instanceof handles nulls
                && internalMap.equals(((AliasMap) other).internalMap));
    }

    @Override
    public int hashCode() {
        return internalMap.hashCode();
    }
}
