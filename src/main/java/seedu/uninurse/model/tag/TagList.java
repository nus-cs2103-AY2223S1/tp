package seedu.uninurse.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import seedu.uninurse.model.GenericList;
import seedu.uninurse.model.tag.exceptions.DuplicateTagException;
import seedu.uninurse.model.tag.exceptions.TagNotFoundException;

/**
 * Represents a unique list of tags for a particular patient.
 * Two tags with the same name are considered duplicates and are hence, not allowed.
 * Supports a minimal set of list operations.
 */
public class TagList implements GenericList<Tag> {
    public static final String MESSAGE_UNSUPPORTED_EDIT = "Tags cannot be edited, only added or deleted";

    private final List<Tag> internalTagList;

    /**
     * Constructs an empty {@code TagList}.
     */
    public TagList() {
        internalTagList = new ArrayList<>();
    }

    /**
     * Constructs a {@code TagList}.
     * @param tags The given list of tags.
     */
    public TagList(List<Tag> tags) {
        requireNonNull(tags);
        // sorts the list of tags by lexicographical order
        internalTagList = tags.stream().sorted(Comparator.comparing(Tag::getValue)).collect(Collectors.toList());
    }

    @Override
    public TagList add(Tag tag) {
        requireNonNull(tag);
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
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED_EDIT);
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
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        internalTagList.forEach(builder::append);
        return builder.toString();
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
