package seedu.foodrem.viewmodels;

import java.util.Arrays;
import java.util.Objects;

import seedu.foodrem.model.tag.Tag;

/**
 * A view model for generating a view with tags as well as a message.
 * @author Tan Yi Xian
 */
public class TagsWithMessage {
    private final Tag[] tags;
    private final String message;

    /**
     * Creates a view model containing the message and the tags to display.
     * @param tags the tags to display.
     * @param message the message to display.
     */
    public TagsWithMessage(String message, Tag... tags) {
        this.tags = tags;
        this.message = message;
    }

    /**
     * Returns the tags.
     */
    public Tag[] getTags() {
        return tags;
    }

    /**
     * Returns the message.
     */
    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof TagsWithMessage
                && Arrays.equals(tags, ((TagsWithMessage) other).tags)
                && message.equals(((TagsWithMessage) other).message));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(tags), message);
    }
}
