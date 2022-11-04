package seedu.uninurse.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;

    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String toString() {
        return getPrefix();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Prefix)) {
            return false;
        }
        if (other == this) {
            return true;
        }

        Prefix o = (Prefix) other;
        return o.getPrefix().equals(prefix);
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }
}
