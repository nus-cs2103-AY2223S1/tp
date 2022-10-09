package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tag.exceptions.DuplicateTagTypeException;
import seedu.address.model.tag.exceptions.TagTypeNotFoundException;

/**
 * A list of tagtypes that enforces uniqueness between its elements and does not
 * allow nulls.
 * A tagtype is considered unique by comparing using
 * {@code TagType#equals(Object)}. As such, adding and updating of
 * tag types uses TagType#equals(Object) for equality so as to ensure that
 * the tag type being added or updated is
 * unique in terms of identity in the UniqueTagTypeList. The removal of
 * a tag type also uses TagType#equals(Object) so
 * as to ensure that the tag type with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see TagType#equals(Object)
 */
public class UniqueTagTypeList implements Iterable<TagType> {
    private final ObservableList<TagType> internalList = FXCollections.observableArrayList();
    private final ObservableList<TagType> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tag type as the given argument.
     */
    public boolean contains(TagType toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a tag type to the list.
     * The tag type must not already exist in the list.
     */
    public void add(TagType toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTagTypeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the tag type {@code target} in the list with {@code editedTagType}.
     * {@code target} must exist in the list.
     * The tag type name of {@code editedTagType} must not be the same as another
     * existing tag type in the list.
     */
    public void setTag(TagType target, TagType editedTagType) {
        requireAllNonNull(target, editedTagType);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TagTypeNotFoundException();
        }

        if (!target.equals(editedTagType) && contains(editedTagType)) {
            throw new DuplicateTagTypeException();
        }

        internalList.set(index, editedTagType);
    }

    /**
     * Removes the equivalent tag type from the list.
     * The tag type must exist in the list.
     */
    public void remove(TagType toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TagTypeNotFoundException();
        }
    }

    public void setTagTypes(UniqueTagTypeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tagTypes}.
     * {@code tagTypes} must not contain duplicate tags.
     */
    public void setTags(List<TagType> tagTypes) {
        requireAllNonNull(tagTypes);
        if (!tagTypesAreUnique(tagTypes)) {
            throw new DuplicateTagTypeException();
        }
        internalList.setAll(tagTypes);
    }

    /**
     * Get the count of unique tag types in the list. Used for displaying information
     * on total count of tag types.
     * @return the count of unique tag types in the list.
     */
    public int getCount() {
        return internalList.size();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TagType> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TagType> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTagList // instanceof handles nulls
                && internalList.equals(((UniqueTagTypeList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tagTypes} contains only unique tagTypes.
     */
    private boolean tagTypesAreUnique(List<TagType> tagTypes) {
        for (int i = 0; i < tagTypes.size() - 1; i++) {
            for (int j = i + 1; j < tagTypes.size(); j++) {
                if (tagTypes.get(i).equals(tagTypes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
