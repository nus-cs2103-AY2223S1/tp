package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.comment.Comment;

public interface ReadOnlyTaskBook {

    ObservableList<Comment> getCommentList();
}
