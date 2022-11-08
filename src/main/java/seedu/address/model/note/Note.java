package seedu.address.model.note;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a note in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Note {

    //Identity field
    private final Title title;

    //Data fields
    private final Content content;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructor for a Note object
     *
     * @param title Title of the note.
     * @param content Contents of the note.
     * @param tags Tags of the note.
     */
    public Note(Title title, Content content, Set<Tag> tags) {
        requireAllNonNull(title, content, tags);
        this.title = title;
        this.content = content;
        this.tags.addAll(tags);
    }

    public Title getTitle() {
        return this.title;
    }

    public Content getContent() {
        return this.content;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return otherNote.getTitle().equals(getTitle())
                && otherNote.getContent().equals(getContent())
                && otherNote.getTags().equals(getTags());

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, content, tags);
    }

    /**
     * Returns true if both notes have the same title.
     * This defines a weaker notion of equality between two notes.
     *
     * @param otherNote The other note to be compared to.
     * @return True if both notes have same title.
     */
    public boolean isSameNote(Note otherNote) {
        if (otherNote == this) {
            return true;
        }

        return otherNote != null
                && otherNote.getTitle().equals(getTitle());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Title: ")
                .append(getTitle())
                .append(", Content: ")
                .append(getContent());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }


}
