package foodwhere.model.review;

import static foodwhere.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import foodwhere.model.review.exceptions.DuplicateReviewException;
import foodwhere.model.review.exceptions.ReviewNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of reviews that enforces uniqueness between its elements and does not allow nulls.
 * A review is considered unique by comparing using {@code Review#isSameReview(Review)}. As such, adding and updating
 * of reviews uses Review#isSameReview(Review) for equality so as to ensure that the review being added or updated is
 * unique in terms of identity in the UniqueReviewList. However, the removal of a Review uses Review#equals(Object) so
 * as to ensure that the review with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Review#isSameReview(Review)
 */
public class UniqueReviewList implements Iterable<Review> {

    private final ObservableList<Review> internalList = FXCollections.observableArrayList();
    private final ObservableList<Review> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent review as the given argument.
     */
    public boolean contains(Review toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameReview);
    }

    /**
     * Adds a review to the list.
     * The review must not already exist in the list.
     */
    public void add(Review toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateReviewException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the review {@code target} in the list with {@code editedReview}.
     * {@code target} must exist in the list.
     * The review identity of {@code editedReview} must not be the same as another existing review in the list.
     */
    public void setReview(Review target, Review editedReview) {
        requireAllNonNull(target, editedReview);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ReviewNotFoundException();
        }

        if (!target.isSameReview(editedReview) && contains(editedReview)) {
            throw new DuplicateReviewException();
        }

        internalList.set(index, editedReview);
    }

    /**
     * Removes the equivalent review from the list.
     * The review must exist in the list.
     */
    public void remove(Review toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ReviewNotFoundException();
        }
    }

    /** Sorts the review list by name. */
    public void sort() {
        internalList.sort(Comparator.comparing(o -> o.getName().fullName));
    }

    public void setStalls(UniqueReviewList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code reviews}.
     * {@code reviews} must not contain duplicate reviews.
     */
    public void setReviews(List<Review> reviews) {
        requireAllNonNull(reviews);
        if (!reviewsAreUnique(reviews)) {
            throw new DuplicateReviewException();
        }

        internalList.setAll(reviews);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Review> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Review> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueReviewList // instanceof handles nulls
                && internalList.equals(((UniqueReviewList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code reviews} contains only unique reviews.
     */
    private boolean reviewsAreUnique(List<Review> reviews) {
        for (int i = 0; i < reviews.size() - 1; i++) {
            for (int j = i + 1; j < reviews.size(); j++) {
                if (reviews.get(i).isSameReview(reviews.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
