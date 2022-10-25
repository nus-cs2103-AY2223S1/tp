package swift.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;
    private String user_prompt;

    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    public Prefix(String prefix, String user_prompt) {
        this.prefix = prefix;
        this.user_prompt = user_prompt;
    }

    public String getPrefix() {
        return prefix;
    }

    public String toString() {
        return getPrefix();
    }

    public String getUserPrompt() {
        return user_prompt == null ? "" : "<" + user_prompt + ">";
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
