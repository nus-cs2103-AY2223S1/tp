package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 *  A list of tags that enforces uniqueness between its elements and does not allow nulls.
 *  A tag is considered unique by comparing using {@code Tag#equal(Tag)}. As such,
 *  adding, updating and the removal of a tag uses {@code Tag#equals(Tag)}
 *  in order to ensure that the tag with exactly the same fields will be removed.
 *  {@code Tag#tagsAreUnique(tags)} will enforce a uniqueness check to guarantee that
 *  added Tags are unique, even if they are given by unique keys paired with non-unique tags.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueTagMapping implements Iterable<Tag> {

    private final ObservableMap<String, Tag> internalMap = FXCollections.observableHashMap();
    private final ObservableMap<String, Tag> internalUnmodifiableMap =
            FXCollections.unmodifiableObservableMap(internalMap);


    /**
     * Returns true if the list contains an equivalent tag as the given argument.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return internalMap.values().stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if the map contains the given tagName
     * @param tagName the name of the tag to check against
     * @return whether the mapping contains this tagName
     */
    public boolean contains(String tagName) {
        requireNonNull(tagName);
        return internalMap.containsKey(tagName);
    }

    /**
     * Adds a tag to the list.
     */
    public void add(Tag toAdd) {
        requireNonNull(toAdd);
        if (!contains(toAdd.tagName)) {
            internalMap.put(toAdd.tagName, toAdd);
        }
    }

    /**
     * Removes the equivalent tag from the list.
     * The tag must exist in the list.
     */
    public void remove(Tag toRemove) {
        requireNonNull(toRemove);
        if (!internalMap.containsKey(toRemove.tagName)) {
            throw new TagNotFoundException();
        }
        internalMap.remove(toRemove.tagName);
    }

    /**
     * Sets the internal mapping of this object to the same internal
     * mapping of the replacement object, only if the tags are unique
     * @param replacement the UniqueTagMapping to replace this object with
     */
    public void setTags(UniqueTagMapping replacement) {
        requireNonNull(replacement);
        if (!tagsAreUnique(replacement.internalMap)) {
            throw new DuplicateTagException();
        }
        internalMap.clear();
        internalMap.putAll(replacement.internalMap);
    }

    /**
     * Replaces the contents of this list with {@code tags}.
     * {@code tags} must not contain duplicate persons.
     */
    public void setTags(Map<String, Tag> tags) {
        requireAllNonNull(tags);

        if (!tagsAreUnique(tags)) {
            throw new DuplicateTagException();
        }
        internalMap.clear();
        internalMap.putAll(tags);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableMap<String, Tag> asUnmodifiableObservableMap() {
        return internalUnmodifiableMap;
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalMap.values().iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTagMapping // instanceof handles nulls
                        && internalMap.equals(((UniqueTagMapping) other).internalMap));
    }

    @Override
    public int hashCode() {
        return internalMap.hashCode();
    }

    /**
     * Returns true if {@code tags} contains only unique tags.
     */
    private boolean tagsAreUnique(Map<String, Tag> tags) {
        return tags.values().stream().distinct().count() == tags.size();
    }
}
