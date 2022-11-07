package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 * A prefix can contain multiple aliases such as 'name/', 'n/', '-n'. They all
 * indicate the same prefix.
 */
public class Prefix {
    private final ArrayList<String> aliases;

    /**
     * Construct a Prefix instance with a list of aliases.
     *
     * @param aliases a list of aliases to the prefix
     */
    public Prefix(String... aliases) {
        assert aliases.length > 0 : "Must have at least one valid prefix";
        this.aliases = new ArrayList<>(Arrays.asList(aliases));
    }

    /**
     * Returns the primary alias of the prefix, which is the first element in aliases array.
     */
    public String getAlias() {
        return aliases.get(0);
    }

    /**
     * Returns all aliases to this prefix.
     */
    public ArrayList<String> getAliases() {
        return aliases;
    }

    public String toString() {
        return getAlias();
    }

    @Override
    public int hashCode() {
        return aliases == null ? 0 : aliases.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Prefix)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Prefix otherPrefix = (Prefix) obj;
        return otherPrefix.getAliases().equals(getAliases());
    }
}
