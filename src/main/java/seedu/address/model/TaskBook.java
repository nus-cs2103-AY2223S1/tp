package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.comment.Comment;
import seedu.address.model.comment.CommentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class TaskBook implements ReadOnlyTaskBook {

    private final CommentList comments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        comments = new CommentList();
    }

    public TaskBook() {}

    /**
     * Creates an AddressBook using the Students in the {@code toBeCopied}
     */
    public TaskBook(ReadOnlyTaskBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setComments(List<Comment> comments) {
        this.comments.setComments(comments);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskBook newData) {
        requireNonNull(newData);

        setComments(newData.getCommentList());
    }

    //// student-level operations

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addComment(Comment c) {
        comments.add(c);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another
     * existing student in the address book.
     */
//    public void setStudent(Student target, Student editedStudent) {
//        requireNonNull(editedStudent);
//
//        students.setStudent(target, editedStudent);
//    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
//    public void removeStudent(Student key) {
//        students.remove(key);
//    }

    //// util methods

    @Override
    public String toString() {
        return comments.asUnmodifiableObservableList().size() + " comments";
        // TODO: refine later
    }

    @Override
    public ObservableList<Comment> getCommentList() {
        return comments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskBook // instanceof handles nulls
                && comments.equals(((TaskBook) other).comments));
    }

    @Override
    public int hashCode() {
        return comments.hashCode();
    }
}
