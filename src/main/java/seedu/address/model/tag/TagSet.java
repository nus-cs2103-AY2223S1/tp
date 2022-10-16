package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * A set of tags that does not allow nulls.
 */
public class TagSet implements Iterable<Tag> {
    private final Set<Tag> internalSet = new HashSet<>();

    /**
     * Adds a tag to the set.
     */
    public void add(Tag toAdd) {
        requireNonNull(toAdd);
        internalSet.add(toAdd);
    }

    /**
     * Removes the specified tag from the set.
     */
    public Tag remove(Tag toRemove) {
        requireNonNull(toRemove);
        boolean hasChanged = internalSet.remove(toRemove);
        if (!hasChanged) {
            return null;
        }
        return toRemove;
    }

    /**
     * Removes the specified tags from the set.
     */
    public Set<Tag> remove(Set<Tag> toRemove) {
        requireNonNull(toRemove);
        Set<Tag> intersection = new HashSet<>(internalSet);
        intersection.retainAll(toRemove);
        internalSet.removeAll(toRemove);
        return intersection;
    }

    /**
     * Returns true if the set contains an equivalent tag as the given argument.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return internalSet.stream().anyMatch(toCheck::equals);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagSet // instanceof handles nulls
                        && internalSet.equals(((TagSet) other).internalSet));
    }

    /**
     * Returns the backing set as an unmodifiable {@code UnmodifiableSet}.
     */
    public Set<Tag> asUnmodifiableSet() {
        return Collections.unmodifiableSet(internalSet);
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalSet.iterator();
    }

    @Override
    public int hashCode() {
        return internalSet.hashCode();
    }
}
