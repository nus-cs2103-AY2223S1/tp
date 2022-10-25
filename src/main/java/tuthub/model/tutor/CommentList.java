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
     * @param comment Comment to be added to the CommentList.
     * @return The CommentList itself.
     */
    public CommentList addComment(Comment comment) {
        this.comments.add(comment);
        return this;
    }

    /**
     * Deletes a Comment from the CommentList.
     *
     * @param index The index of the Comment in the CommentList.
     */
    public void deleteComment(int index) {
        comments.remove(index);
    }


    /**
     * Converts the CommentList to a string for display in the Tutor Card.
     *
     * @return A string where every Comment is appended with a newline.
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
     * Converts the CommentList into a string.
     *
     * @return A string where every Comment is padded with their position in the CommentList.
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
}
