package seedu.uninurse.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import seedu.uninurse.model.GenericList;
import seedu.uninurse.model.ListModificationPair;
import seedu.uninurse.model.ModificationType;
import seedu.uninurse.model.tag.exceptions.DuplicateTagException;
import seedu.uninurse.model.tag.exceptions.TagNotFoundException;

/**
 * Represents a unique list of tags for a particular patient.
 * Two tags with the same name are considered duplicates and are hence, not allowed.
 * Supports a minimal set of list operations.
 */
public class TagList implements GenericList<Tag> {
    private final List<Tag> internalTagList;

    /**
     * Constructs an empty TagList.
     */
    public TagList() {
        this.internalTagList = new ArrayList<>();
    }

    /**
     * Constructs a TagList.
     * @param tags The given list of tags.
     */
    public TagList(List<Tag> tags) {
        requireNonNull(tags);
        // sorts the list of tags by lexicographical order
        this.internalTagList = tags.stream().sorted(Comparator.comparing(Tag::getValue)).collect(Collectors.toList());
    }

    @Override
    public TagList add(Tag tag) {
        requireAllNonNull(tag);
        if (internalTagList.contains(tag)) {
            throw new DuplicateTagException();
        }

        List<Tag> updatedTags = new ArrayList<>(internalTagList);
        updatedTags.add(tag);
        return new TagList(updatedTags);
    }

    @Override
    public TagList delete(int index) {
        try {
            List<Tag> updatedTags = new ArrayList<>(internalTagList);
            updatedTags.remove(index);
            return new TagList(updatedTags);
        } catch (IndexOutOfBoundsException e) {
            throw new TagNotFoundException();
        }
    }

    @Override
    public TagList edit(int index, Tag tag) {
        requireAllNonNull(tag);
        try {
            List<Tag> updatedTags = new ArrayList<>(internalTagList);
            updatedTags.set(index, tag);

            if (internalTagList.contains(tag)) {
                throw new DuplicateTagException();
            }

            return new TagList(updatedTags);
        } catch (IndexOutOfBoundsException e) {
            throw new TagNotFoundException();
        }
    }

    @Override
    public Tag get(int index) {
        try {
            return internalTagList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TagNotFoundException();
        }
    }

    @Override
    public int size() {
        return internalTagList.size();
    }

    @Override
    public boolean isEmpty() {
        return internalTagList.isEmpty();
    }

    @Override
    public List<Tag> getInternalList() {
        // returns an immutable tag list to prevent modification to original one
        return Collections.unmodifiableList(internalTagList);
    }

    @Override
    public List<ListModificationPair> getDiff(GenericList<Tag> otherTagList) {
        List<ListModificationPair> listModificationPairs = new ArrayList<>();

        if (equals(otherTagList)) {
            return listModificationPairs;
        }

        if (size() == 0 && otherTagList.size() == 1) {
            listModificationPairs.add(new ListModificationPair(ModificationType.ADD, 0));
            return listModificationPairs;
        }

        if (size() == 1 && otherTagList.size() == 0) {
            listModificationPairs.add(new ListModificationPair(ModificationType.DELETE, 0));
            return listModificationPairs;
        }

        int pointer = 0;
        while (get(pointer).equals(otherTagList.get(pointer))) {
            pointer++;
            if (pointer == size() || pointer == otherTagList.size()) {
                if (size() < otherTagList.size()) {
                    listModificationPairs.add(new ListModificationPair(ModificationType.ADD, pointer));
                } else if (otherTagList.size() < size()) {
                    listModificationPairs.add(new ListModificationPair(ModificationType.DELETE, pointer));
                }
                break;
            }
        }
        listModificationPairs.add(new ListModificationPair(ModificationType.EDIT, pointer));
        return listModificationPairs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        internalTagList.forEach(t -> sb.append("[").append(t).append("]"));
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagList // instanceof handles nulls
                && internalTagList.equals(((TagList) other).internalTagList));
    }

    @Override
    public int hashCode() {
        return internalTagList.hashCode();
    }
}
