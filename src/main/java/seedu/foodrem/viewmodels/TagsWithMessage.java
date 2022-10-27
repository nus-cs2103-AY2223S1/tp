package seedu.foodrem.viewmodels;

import java.util.List;
import java.util.Objects;

import seedu.foodrem.model.tag.Tag;

/**
 * A view model for generating a view with tags as well as a message.
 * @author Richard Dominick
 */
public class TagsWithMessage {
    private final List<Tag> tags;
    private final String message;

    /**
     * Creates a view model containing the message and the tags to display.
     * @param tags the tags to display.
     * @param message the message to display.
     */
    public TagsWithMessage(List<Tag> tags, String message) {
        this.tags = tags;
        this.message = message;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof TagsWithMessage
                && tags.equals(((TagsWithMessage) other).tags)
                && message.equals(((TagsWithMessage) other).message));
    }

    @Override
    public int hashCode() {
        return Objects.hash(tags, message);
    }
}
