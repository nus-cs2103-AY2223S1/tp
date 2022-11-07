package seedu.taassist.model.uniquelist;

/**
 * A generic interface for classes that have an identity.
 * The identity defines a weaker notion of equality than the {@code equals} method.
 */
public interface Identity<T> {

    /**
     * Returns true if this object and {@code other} have the same identity.
     */
    boolean isSame(T other);
}
