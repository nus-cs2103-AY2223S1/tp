package foodwhere.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import foodwhere.commons.core.GuiSettings;
import foodwhere.model.review.Review;
import foodwhere.model.stall.Stall;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Stall> PREDICATE_SHOW_ALL_STALLS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Review> PREDICATE_SHOW_ALL_REVIEWS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path in FoodWhere.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path in FoodWhere.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook} in FoodWhere.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook in FoodWhere. */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a stall with the same identity as {@code stall} exists in the address book.
     */
    boolean hasStall(Stall stall);

    /**
     * Deletes the given stall.
     * The stall must exist in the address book in FoodWhere.
     */
    void deleteStall(Stall target);

    /**
     * Adds the given stall.
     * {@code stall} must not already exist in the address book in FoodWhere.
     */
    void addStall(Stall stall);

    /**
     * Replaces the given stall {@code target} with {@code editedStall}.
     * {@code target} must exist in the address book in FoodWhere.
     * The stall identity of {@code editedStall} must not be the same as another existing stall in the address book.
     */
    void setStall(Stall target, Stall editedStall);

    /**
     * Sorts the stall list by {@code comparator}.
     */
    void sortStalls(Comparator<Stall> comparator);

    /**
     * Returns true if a review with the same identity as {@code review} exists in the address book in FoodWhere.
     */
    boolean hasReview(Review review);

    /**
     * Deletes the given review.
     * The review must exist in the address book in FoodWhere.
     */
    void deleteReview(Review target);

    /**
     * Adds the given review.
     * {@code review} must not already exist in the address book in FoodWhere.
     */
    void addReview(Review review);

    /**
     * Adds the given review to the stall.
     * {@code review} must not already exist in the address book in FoodWhere.
     */
    void addReviewToStall(Review review, Stall stall);

    /**
     * Replaces the given review {@code target} with {@code editedReview}.
     * {@code target} must exist in the address book in FoodWhere.
     * The review identity of {@code editedReview} must not be the same as another existing review in the address book.
     */
    void setReview(Review target, Review editedReview);

    /**
     * Sorts the review list by {@code comparator}.
     */
    void sortReviews(Comparator<Review> comparator);

    /** Returns an unmodifiable view of the filtered stall list */
    ObservableList<Stall> getFilteredStallList();

    /** Returns an unmodifiable view of the filtered review list */
    ObservableList<Review> getFilteredReviewList();

    /**
     * Updates the filter of the filtered stall list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStallList(Predicate<Stall> predicate);


    /**
     * Updates the filter of the filtered review list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredReviewList(Predicate<Review> predicate);

}
