package tuthub.model.tutor;

import java.util.ArrayList;

/**
 * Represents the list of comments attached to each tutor.
 *
 */
public class CommentList {
    private final ArrayList<Comment> comments = new ArrayList<>();

    public CommentList addComment(Comment comment) {
        this.comments.add(comment);
        return this;
    }

    public Comment getComment(int index) {
        return comments.get(index);
    }

    public void deleteComment(int index) {
        comments.remove(index);
    }

    public void clearComments() {
        comments.clear();
    }

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