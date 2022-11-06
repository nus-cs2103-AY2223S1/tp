package foodwhere.model;

import static foodwhere.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import foodwhere.commons.core.GuiSettings;
import foodwhere.commons.core.LogsCenter;
import foodwhere.model.review.Review;
import foodwhere.model.stall.Stall;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the address book data in FoodWhere.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Stall> filteredStalls;
    private final FilteredList<Review> filteredReviews;

    /**
     * Initializes a ModelManager with the given addressBook in FoodWhere and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with data: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStalls = new FilteredList<>(this.addressBook.getStallList());
        filteredReviews = new FilteredList<>(this.addressBook.getReviewList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasStall(Stall stall) {
        requireNonNull(stall);
        return addressBook.hasStall(stall);
    }

    @Override
    public void deleteStall(Stall target) {
        addressBook.removeStall(target);
    }

    @Override
    public void addStall(Stall stall) {
        addressBook.addStall(stall);
        updateFilteredStallList(PREDICATE_SHOW_ALL_STALLS);
    }

    @Override
    public void setStall(Stall target, Stall editedStall) {
        requireAllNonNull(target, editedStall);

        addressBook.setStall(target, editedStall);
    }

    @Override
    public void sortStalls(Comparator<Stall> comparator) {
        addressBook.sortStalls(comparator);
    }

    @Override
    public boolean hasReview(Review review) {
        requireNonNull(review);
        return addressBook.hasReview(review);
    }

    @Override
    public void deleteReview(Review target) {
        addressBook.removeReview(target);
    }

    @Override
    public void addReview(Review review) {
        addressBook.addReview(review);
        updateFilteredReviewList(PREDICATE_SHOW_ALL_REVIEWS);
        updateFilteredStallList(PREDICATE_SHOW_ALL_STALLS);
    }

    @Override
    public void addReviewToStall(Review review, Stall stall) {
        addressBook.addReviewToStall(review, stall);
        updateFilteredReviewList(PREDICATE_SHOW_ALL_REVIEWS);
        updateFilteredStallList(PREDICATE_SHOW_ALL_STALLS);
    }

    @Override
    public void setReview(Review target, Review editedStall) {
        requireAllNonNull(target, editedStall);

        addressBook.setReview(target, editedStall);
    }

    @Override
    public void sortReviews(Comparator<Review> comparator) {
        addressBook.sortReviews(comparator);
    }

    //=========== Filtered Stall List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Stall} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Stall> getFilteredStallList() {
        return filteredStalls;
    }

    @Override
    public void updateFilteredStallList(Predicate<Stall> predicate) {
        requireNonNull(predicate);
        filteredStalls.setPredicate(predicate);
    }

    //=========== Filtered Review List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Review} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Review> getFilteredReviewList() {
        return filteredReviews;
    }

    @Override
    public void updateFilteredReviewList(Predicate<Review> predicate) {
        requireNonNull(predicate);
        filteredReviews.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredStalls.equals(other.filteredStalls);
    }


}
