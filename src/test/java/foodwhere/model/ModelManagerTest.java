package foodwhere.model;

import static foodwhere.model.Model.PREDICATE_SHOW_ALL_STALLS;
import static foodwhere.testutil.Assert.assertThrows;
import static foodwhere.testutil.TypicalReviews.CARL;
import static foodwhere.testutil.TypicalStalls.ALICE;
import static foodwhere.testutil.TypicalStalls.BENSON;
import static foodwhere.testutil.TypicalStalls.DANIEL;
import static foodwhere.testutil.TypicalStalls.ELLE;
import static foodwhere.testutil.TypicalStalls.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import foodwhere.model.review.Review;
import foodwhere.model.review.ReviewBuilder;
import foodwhere.model.review.comparator.ReviewsComparatorList;
import foodwhere.model.stall.comparator.StallsComparatorList;
import foodwhere.model.stall.exceptions.DuplicateStallException;
import foodwhere.model.stall.exceptions.StallNotFoundException;
import foodwhere.testutil.TypicalReviews;
import foodwhere.testutil.TypicalStalls;
import org.junit.jupiter.api.Test;

import foodwhere.commons.core.GuiSettings;
import foodwhere.model.commons.Name;
import foodwhere.model.stall.StallContainsKeywordsPredicate;
import foodwhere.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModelManager(null, null));
        assertThrows(NullPointerException.class, () -> new ModelManager(new AddressBook(), null));
        assertThrows(NullPointerException.class, () -> new ModelManager(null, new UserPrefs()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void setAddressBook_validBook_success() {
        modelManager.setAddressBook(getTypicalAddressBook());
        assertEquals(getTypicalAddressBook(), modelManager.getAddressBook());
    }

    @Test
    public void hasStall_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStall(null));
    }

    @Test
    public void hasStall_stallNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasStall(ALICE));
    }

    @Test
    public void hasStall_stallInAddressBook_returnsTrue() {
        modelManager.addStall(ALICE);
        assertTrue(modelManager.hasStall(ALICE));
    }

    @Test
    public void getFilteredStallList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStallList().remove(0));
    }

    @Test
    public void deleteStall_stallNotInAddressBook_throwsStallNotFoundException() {
        assertThrows(StallNotFoundException.class, () -> modelManager.deleteStall(ALICE));
    }

    @Test
    public void deleteStall_stallInAddressBook_success() {
        modelManager.addStall(ALICE);
        assertTrue(modelManager.hasStall(ALICE));

        modelManager.deleteStall(ALICE);
        assertFalse(modelManager.hasStall(ALICE));
    }

    @Test
    public void deleteStall_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteStall(null));
    }

    @Test
    public void addStall_stallNotInAddressBook_success() {
        assertFalse(modelManager.hasStall(ALICE));
        modelManager.addStall(ALICE);
        assertTrue(modelManager.hasStall(ALICE));
    }

    @Test
    public void addStall_stallInAddressBook_throwsDuplicateStallException() {
        modelManager.addStall(ALICE);
        assertThrows(DuplicateStallException.class, () -> modelManager.addStall(ALICE));
    }

    @Test
    public void addStall_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addStall(null));
    }

    @Test
    public void setStall_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setStall(null, ALICE));
        assertThrows(NullPointerException.class, () -> modelManager.setStall(ALICE, null));
        assertThrows(NullPointerException.class, () -> modelManager.setStall(null, null));
    }

    @Test
    public void sortStall_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.sortStalls(null));
    }

    @Test
    public void sortStall_generalTesting_success() {
        modelManager.addStall(DANIEL);
        modelManager.addStall(ELLE);
        modelManager.addStall(BENSON);
        modelManager.addStall(ALICE);

        assertNotEquals(ALICE, modelManager.getFilteredStallList().get(0));
        modelManager.sortStalls(StallsComparatorList.NAME.getComparator());
        assertEquals(ALICE, modelManager.getFilteredStallList().get(0));
    }

    @Test
    public void updateFilteredStallList_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredStallList(null));
    }

    @Test
    public void hasReview_nullReview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasReview(null));
    }

    @Test
    public void hasReview_reviewNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasReview(CARL));
    }

    @Test
    public void hasReview_reviewInAddressBook_returnsTrue() {
        modelManager.addStall(TypicalStalls.CARL);
        modelManager.addReview(CARL);
        assertTrue(modelManager.hasReview(CARL));
    }

    @Test
    public void addReview_generalTesting_success() {
        modelManager.addStall(TypicalStalls.CARL);

        assertFalse(modelManager.hasReview(CARL));
        modelManager.addReview(CARL);
        assertTrue(modelManager.hasReview(CARL));
    }

    @Test
    public void addReview_nullReview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addReview(null));
    }

    @Test
    public void addReviewToStall_generalTesting_success() {
        modelManager.addStall(ALICE);
        assertFalse(modelManager.hasReview(TypicalReviews.ALICE));

        modelManager.addReviewToStall(TypicalReviews.ALICE, ALICE);
        assertTrue(modelManager.hasReview(TypicalReviews.ALICE));
    }

    @Test
    public void addReviewToStall_stallNotInBook_throwsStallNotFoundException() {
        assertFalse(modelManager.hasReview(TypicalReviews.ALICE));
        assertFalse(modelManager.hasStall(ALICE));
        assertThrows(StallNotFoundException.class, () -> modelManager.addReviewToStall(TypicalReviews.ALICE, ALICE));
    }

    @Test
    public void addReviewToStall_duplicateReview_throwsStallNotFoundException() {
        modelManager.addStall(ALICE);
        modelManager.addReview(TypicalReviews.ALICE);
        assertThrows(StallNotFoundException.class, () -> modelManager.addReviewToStall(TypicalReviews.ALICE, ALICE));
    }

    @Test
    public void addReviewToStall_nullReview_throwsNullPointerException() {
        modelManager.addStall(ALICE);
        assertThrows(NullPointerException.class, () -> modelManager.addReviewToStall(null, ALICE));
    }

    @Test
    public void deleteReview_reviewNotInAddressBook_throwsStallNotFoundException() {
        assertThrows(StallNotFoundException.class, () -> modelManager.deleteReview(CARL));
    }

    @Test
    public void deleteReview_ReviewInAddressBook_success() {
        modelManager.addStall(TypicalStalls.CARL);
        modelManager.addReview(CARL);
        assertTrue(modelManager.hasReview(CARL));

        modelManager.deleteReview(CARL);
        assertFalse(modelManager.hasReview(CARL));
    }

    @Test
    public void deleteReview_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteReview(null));
    }

    @Test
    public void sortReview_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.sortReviews(null));
    }

    @Test
    public void sortReview_generalTesting_success() {
        modelManager.addStall(DANIEL);
        modelManager.addReview(TypicalReviews.DANIEL);

        modelManager.addStall(BENSON);
        modelManager.addReview(TypicalReviews.BENSON);

        modelManager.addStall(ALICE);
        modelManager.addReview(TypicalReviews.ALICE);

        assertNotEquals(TypicalReviews.ALICE, modelManager.getFilteredReviewList().get(0));
        modelManager.sortReviews(ReviewsComparatorList.NAME.getComparator());
        assertEquals(TypicalReviews.ALICE, modelManager.getFilteredReviewList().get(0));
    }

    @Test
    public void setReview_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setReview(null, null));
        assertThrows(NullPointerException.class, () -> modelManager.setReview(CARL, null));
        assertThrows(NullPointerException.class, () -> modelManager.setReview(null, CARL));
    }

    @Test
    public void setReview_generalTesting_success() {
        modelManager.addStall(ALICE);
        modelManager.addReview(TypicalReviews.ALICE);

        Review editedReview = new ReviewBuilder(TypicalReviews.ALICE).withContent("edited content").build();
        modelManager.setReview(TypicalReviews.ALICE, editedReview);

        assertTrue(modelManager.getFilteredReviewList().get(0).equals(editedReview));
    }

    @Test
    public void getFilteredReviewList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredReviewList().remove(0));
    }

    @Test
    public void updateFilteredReviewList_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredReviewList(null));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withStall(ALICE).withStall(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String name = ALICE.getName().toString().split(" ")[0];
        modelManager.updateFilteredStallList(new StallContainsKeywordsPredicate(Arrays.asList(new Name(name)),
                Collections.emptyList()));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStallList(PREDICATE_SHOW_ALL_STALLS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
