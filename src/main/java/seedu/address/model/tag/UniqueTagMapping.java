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
 *  * A list of tags that enforces uniqueness between its elements and does not allow nulls.
 *  * A tag is considered unique by comparing using {@code Tag#isSameTag(Tag)}. As such, adding and updating of
 *  * tags uses Tag#isSameTag(Tag) for equality so as to ensure that the tag being added or updated is
 *  * unique in terms of identity in the UniqueTagList. However, the removal of a tag uses Tag#equals(Object) so
 *  * as to ensure that the tag with exactly the same fields will be removed.
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
     * @return
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

    public void setTags(UniqueTagMapping replacement) {
        requireNonNull(replacement);
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
        /* for (int i = 0; i < tags.size() - 1; i++) {
            for (int j = i + 1; j < tags.size(); j++) {
                if (tags.get(i).equals(tags.get(j))) {
                    return false;
                }
            }
        }
        */
        return tags.values().stream().distinct().count() == tags.size();
    }
}
