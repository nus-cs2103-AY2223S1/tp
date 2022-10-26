package swift.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;
    private String userPrompt;

    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Constructs a {@code Prefix} with given prefix and user prompt.
     * @param prefix Prefix
     * @param userPrompt User prompt for prefix
     */
    public Prefix(String prefix, String userPrompt) {
        this.prefix = prefix;
        this.userPrompt = userPrompt;
    }

    public String getPrefix() {
        return prefix;
    }

    public String toString() {
        return getPrefix();
    }

    public String getUserPrompt() {
        return userPrompt == null ? "" : "<" + userPrompt + ">";
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
        return otherPrefix.getPrefix().equals(getPrefix());
    }
}
