package soconnect.model.tag;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import soconnect.model.person.exceptions.DuplicatePersonException;

/**
 * A list of tags.
 */
public class UniqueTagList implements Iterable<Tag> {

    private final ObservableList<Tag> tagArrayList = FXCollections.observableArrayList();
    private final ObservableList<Tag> tagUnmodifiableList =
            FXCollections.unmodifiableObservableList(tagArrayList);

    public int size() {
        return tagArrayList.size();
    }

    public void addTagToList(Tag tag) {
        tagArrayList.add(tag);
    }

    /**
     * Edits the tag.
     *
     * @param oldTag The tag to be changed.
     * @param newTag The tag to be changed into.
     */
    public void editTag(Tag oldTag, Tag newTag) {
        requireAllNonNull(oldTag, newTag);
        int index = tagArrayList.indexOf(oldTag);
        tagArrayList.set(index, newTag);
    }

    /**
     * Checks if the tag exists.
     *
     * @param tag The tag to be checked.
     * @return True if the tag exists. False if otherwise.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        for (Tag value : tagArrayList) {
            if (value.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Changes the old tags to a new list of tags.
     *
     * @param tags The new list of tags.
     */
    public void setTags(List<Tag> tags) {
        requireAllNonNull(tags);
        if (!tagsAreUnique(tags)) {
            throw new DuplicatePersonException();
        }

        tagArrayList.setAll(tags);
    }

    /**
     * Returns an unmodifiable list of tags.
     *
     * @return The list of unmodifiable tags.
     */
    public ObservableList<Tag> asUnmodifiableObservableList() {
        return tagUnmodifiableList;
    }

    @Override
    public Iterator<Tag> iterator() {
        return tagArrayList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTagList // instanceof handles nulls
                && tagArrayList.equals(((UniqueTagList) other).tagArrayList));
    }

    @Override
    public int hashCode() {
        return tagArrayList.hashCode();
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
}
