package tracko.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix implements ArgumentToken {
    private final String prefix;

    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    public String getToken() {
        return prefix;
    }

    public String toString() {
        return getToken();
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
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
        return otherPrefix.getToken().equals(getToken());
    }
}
