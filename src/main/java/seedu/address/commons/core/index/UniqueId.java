package seedu.address.commons.core.index;

/**
 * Represents a unique id that does not change throughout the life cycle of an Object
 */
public class UniqueId implements Comparable<UniqueId> {
    private final String id;

    /**
     * Constructs a unique id object;
     *
     * @param id The string as the key for id.
     */
    public UniqueId(String id) {
        this.id = id;
    }

    public String getIdToString() {
        return this.id;
    }

    @Override
    public int compareTo(UniqueId other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UniqueId)) {
            return false;
        }
        return this.id.equals(((UniqueId) other).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
