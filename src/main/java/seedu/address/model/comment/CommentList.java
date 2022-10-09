package seedu.address.model.comment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;

public class CommentList implements Iterable<Comment> {
    private final ObservableList<Comment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Comment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a comment to the list.
     * The comment can already exist in the list.
     */
    public void add(Comment toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Comment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Comment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommentList // instanceof handles nulls
                && internalList.equals(((CommentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
