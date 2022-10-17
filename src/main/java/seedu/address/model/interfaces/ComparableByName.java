package seedu.address.model.interfaces;

/**
 * Interface for an object that is comparable by a string representation (name)
 * @param <T> type of object
 */
public interface ComparableByName<T> {

    /**
     * This defines a lower level of equality - comparison by name/description
     * for relevant objects that can be compared by such.
     * @param other other object
     * @return true if the objects have the same name.
     */
    boolean hasSameName(T other);
}
