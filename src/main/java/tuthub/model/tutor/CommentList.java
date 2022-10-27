package tuthub.model.tutor;

import java.util.ArrayList;

/**
 * Represents the list of comments attached to each tutor.
 *
 */
public class CommentList {
    private final ArrayList<Comment> comments = new ArrayList<>();

    /**
     * Adds a Comment to the CommentList.
     *
     * @param comment Comment to be added to the {@code CommentList}.
     * @return The CommentList itself.
     */
    public CommentList addComment(Comment comment) {
        this.comments.add(comment);
        return this;
    }

    /**
     * Returns the number of comments in the {@code CommentList}.
     *
     * @return The size of the {@code CommentList}.
     */
    public int size() {
        return comments.size();
    }

    public boolean isEmpty() {
        return comments.size() == 0;
    }

    /**
     * Deletes a Comment from the {@code CommentList}.
     *
     * @param index The index of the {@code Comment} in the {@code CommentList}.
     */
    public Comment deleteComment(int index) {
        return comments.remove(index);
    }


    /**
     * Converts the {@code CommentList} to a string for display in the Tutor Card.
     *
     * @return A string where every {@code Comment} is appended with a newline.
     */
    public String toStringForTutorCard() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < comments.size(); i++) {
            if (i == 0) {
                result.append(i + 1).append(") ").append(comments.get(i).toString());
            } else {
                result.append("\n").append(i + 1).append(") ").append(comments.get(i).toString());
            }
        }
        return result.toString();
    }

    /**
     * Converts the {@code CommentList} into a string.
     *
     * @return A string where every {@code Comment} is padded with their position in the {@code CommentList}.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < comments.size(); i++) {
            if (i != 0) {
                result.append(" ").append(i + 1).append(") ").append(comments.get(i).toString());
            } else {
                result.append(i + 1).append(") ").append(comments.get(i).toString());
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommentList // instanceof handles nulls
                && comments.equals(((CommentList) other).comments)); // state check
    }

    @Override
    public int hashCode() {
        return comments.hashCode();
    }
}
