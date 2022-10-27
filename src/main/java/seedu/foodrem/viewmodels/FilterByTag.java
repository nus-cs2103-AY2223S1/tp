package seedu.foodrem.viewmodels;

import java.util.Objects;

import seedu.foodrem.model.tag.Tag;

/**
 * A view model for generating a view with tags as well as two messages.
 * @author Richard Dominick
 */
public class FilterByTag {
    private final Tag tag;
    private final String primaryMessage;
    private final String secondaryMessage;

    /**
     * Creates a view model containing the message and the tags to rename.
     * @param tag the tag.
     * @param primaryMessage the message to be displayed above the tags.
     * @param secondaryMessage the message to displayed under the tags.
     */
    public FilterByTag(Tag tag, String primaryMessage, String secondaryMessage) {
        this.tag = tag;
        this.primaryMessage = primaryMessage;
        this.secondaryMessage = secondaryMessage;
    }

    public Tag getTag() {
        return tag;
    }

    public String getPrimaryMessage() {
        return primaryMessage;
    }

    public String getSecondaryMessage() {
        return secondaryMessage;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof FilterByTag
                && tag.equals(((FilterByTag) other).tag)
                && primaryMessage.equals(((FilterByTag) other).primaryMessage)
                && secondaryMessage.equals(((FilterByTag) other).secondaryMessage));
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, primaryMessage, secondaryMessage);
    }
}
