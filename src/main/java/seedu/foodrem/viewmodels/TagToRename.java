package seedu.foodrem.viewmodels;

import java.util.Objects;

import seedu.foodrem.model.tag.Tag;

/**
 * A view model for generating a view with tags as well as a message.
 * @author Richard Dominick
 */
public class TagToRename {
    private final Tag originalTag;
    private final Tag renamedTag;
    private final String message;

    /**
     * Creates a view model containing the message and the tags to rename.
     * @param originalTag the original tag.
     * @param renamedTag the renamed tag.
     * @param message the message to display.
     */
    public TagToRename(Tag originalTag, Tag renamedTag, String message) {
        this.originalTag = originalTag;
        this.renamedTag = renamedTag;
        this.message = message;
    }

    public Tag getOriginalTag() {
        return originalTag;
    }

    public Tag getRenamedTag() {
        return renamedTag;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof TagToRename
                && originalTag.equals(((TagToRename) other).originalTag)
                && renamedTag.equals(((TagToRename) other).renamedTag)
                && message.equals(((TagToRename) other).message));
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalTag, renamedTag, message);
    }
}
