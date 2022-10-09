package seedu.address.model.comment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class Comment {
    public final CommentTitle title;
    public final CommentDescription description;

    public Comment(CommentTitle title, CommentDescription description) {
        requireAllNonNull(title, description);
        this.title = title;
        this.description = description;
    }

    public CommentTitle getTitle() {
        return title;
    }

    public CommentDescription getDescription() {
        return description;
    }

    /**
     * Returns true if both comments have the same identity and data fields.
     * This defines a stronger notion of equality between two comments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Comment)) {
            return false;
        }

        Comment otherStudent = (Comment) other;
        return otherStudent.getTitle().equals(getTitle())
                && otherStudent.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription());

        return builder.toString();
    }
}
