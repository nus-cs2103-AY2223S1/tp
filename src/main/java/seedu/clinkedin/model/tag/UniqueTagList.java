package seedu.clinkedin.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.clinkedin.commons.util.StringUtil;
import seedu.clinkedin.model.tag.exceptions.DuplicateTagException;
import seedu.clinkedin.model.tag.exceptions.TagNotFoundException;

/**
 * A list of tags that enforces uniqueness between its elements and does not
 * allow nulls.
 * A tag is considered unique by comparing using
 * {@code Tag#equals(Object)}. As such, adding and updating of
 * tags uses Tag#equals(Object) for equality so as to ensure that
 * the tag being added or updated is
 * unique in terms of identity in the UniqueTagList. The removal of
 * a tag also uses Tag#equals(Object) so
 * as to ensure that the tag with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Tag#equals(Object)
 */
public class UniqueTagList implements Iterable<Tag> {
    private final ObservableList<Tag> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tag> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tag as the given argument.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if the list contains an equivalent tag with a value containing the given argument as a sequence.
     */
    public boolean hasSequenceMatch(String toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(tag -> StringUtil.containsSequenceIgnoreCase(tag.tagName, toCheck));
    }

    /**
     * Returns true if the list contains all the tags of the given argument.
     * @param toCheck List whose elements are being checked.
     * @return true if check passes, else false.
     */
    public boolean containsAll(UniqueTagList toCheck) {
        requireNonNull(toCheck);
        return toCheck.toStream().allMatch(this::contains);
    }
    /**
     * Returns true if the list contains any of the tags of the given argument.
     * @param toCheck List whose elements are being checked.
     * @return true if check passes, else false.
     */
    public boolean containsAny(UniqueTagList toCheck) {
        requireNonNull(toCheck);
        return toCheck.toStream().anyMatch(this::contains);
    }
    /**
     * Adds a tag to the list.
     * The tag must not already exist in the list.
     */
    public void add(Tag toAdd) throws DuplicateTagException {
        requireNonNull(toAdd);
        if (this.contains(toAdd)) {
            throw new DuplicateTagException();
        }
        internalList.add(toAdd);
    }

    /**
     * Adds a tag to the list.
     * The tag must not already exist in the list.
     */
    public void merge(UniqueTagList toAdd) throws DuplicateTagException {
        requireNonNull(toAdd);
        if (this.containsAny(toAdd)) {
            throw new DuplicateTagException();
        }
        for (Tag t: toAdd) {
            this.add(t);
        }
    }

    /**
     * Replaces the tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the list.
     * The tag name of {@code editedTag} must not be the same as another
     * existing tag in the list.
     */
    public void setTag(Tag target, Tag editedTag) throws TagNotFoundException, DuplicateTagException {
        requireAllNonNull(target, editedTag);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TagNotFoundException();
        }

        if (!target.equals(editedTag) && contains(editedTag)) {
            throw new DuplicateTagException();
        }

        internalList.set(index, editedTag);
    }

    /**
     * Removes the equivalent tag from the list.
     * The tag must exist in the list.
     */
    public void remove(Tag toRemove) throws TagNotFoundException {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TagNotFoundException();
        }
    }

    /**
     * Removes all the tags of the argument list from the list.
     * @param toRemove List whose tags need to be removed.
     * @throws TagNotFoundException If any of the tags in the argument list aren't present in the list.
     */
    public void removeAll(UniqueTagList toRemove) throws TagNotFoundException {
        requireNonNull(toRemove);
        if (!this.containsAll(toRemove)) {
            throw new TagNotFoundException();
        }
        for (Tag t: toRemove) {
            this.remove(t);
        }
    }

    public void setTags(UniqueTagList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) throws DuplicateTagException {
        requireAllNonNull(tags);
        if (!tagsAreUnique(tags)) {
            throw new DuplicateTagException();
        }
        internalList.setAll(tags);
    }

    /**
     * Get the count of unique tags in the list. Used for displaying information
     * on total count of tags of a particular type.
     * @return the count of unique tags in the list.
     */
    public int getCount() {
        return internalList.size();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tag> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTagList // instanceof handles nulls
                && internalList.equals(((UniqueTagList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tags} contains only unique tags.
     */
    private boolean tagsAreUnique(List<Tag> tags) {
        for (int i = 0; i < tags.size() - 1; i++) {
            for (int j = i + 1; j < tags.size(); j++) {
                if (tags.get(i).equals(tags.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public Stream<Tag> toStream() {
        return internalList.stream();
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        internalList.forEach(tag -> builder.append(", " + tag.toString()));
        if (builder.toString().length() < 2) {
            return builder.toString();
        }
        return builder.substring(2);
    }

    public List<String> getAsList() {
        return internalList.stream().map(tag -> tag.getTagName()).collect(Collectors.toList());
    }

    /**
     * Returns a copy of the UniqueTagList.
     */
    public UniqueTagList copy() {
        UniqueTagList clone = new UniqueTagList();
        for (Tag t : this) {
            clone.add(t.copy());
        }
        return clone;
    }
}
