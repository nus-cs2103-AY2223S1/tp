package seedu.studmap.model.student;

import static seedu.studmap.commons.util.AppUtil.checkArgument;
import static seedu.studmap.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Abstract class to generalize the idea of an attribute with an identifier that also bears a state.
 * Guarantees: Is immutable.
 * @param <S> Type of the identifier, e.g. String.
 * @param <T> Type of the state.
 */
public abstract class MultiStateAttribute<S, T> {

    public final S identifier;
    public final T state;

    /**
     * Constructor for MultiStateAttribute.
     * @param identifier Identifier for this attribute.
     * @param state State of this attribute.
     */
    public MultiStateAttribute(S identifier, T state) {
        requireAllNonNull(identifier, state);
        checkArgument(isValidAttributeIdentifier(identifier), getIdentifierConstraintsMessage());
        this.identifier = identifier;
        this.state = state;
    }

    /**
     * Checks equality with another MultiStateAttribute with the same type parameters.
     */
    public abstract boolean isSameAttribute(MultiStateAttribute<S, T> other);

    /**
     * Checks equality of identifier with another.
     */
    public abstract boolean isValidAttributeIdentifier(S identifier);

    /**
     * Returns a message that describes the constraints of the identifier.
     */
    public abstract String getIdentifierConstraintsMessage();

    public String getString() {
        return identifier + ":" + state;
    }

    /**
     * Returns a human-readable format of the identifier.
     */
    public String getAttributeName() {
        return identifier.toString();
    }

    @Override
    public String toString() {
        return "[" + getString() + "]";
    }
}
