package tracko.logic.parser;

/**
 * A flag that marks the argument as true in an arguments string.
 * E.g. '-p' in 'marko 1 -p'.
 */
public class Flag extends ArgumentToken {
    private final String flag;

    public Flag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public String toString() {
        return getFlag();
    }

    @Override
    public int hashCode() {
        return flag == null ? 0 : flag.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Flag)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Flag otherPrefix = (Flag) obj;
        return otherPrefix.getFlag().equals(getFlag());
    }
}

