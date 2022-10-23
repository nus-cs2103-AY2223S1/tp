package foodwhere.model;

import static foodwhere.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static foodwhere.testutil.Assert.assertThrows;
import static foodwhere.testutil.TypicalStalls.ALICE;
import static foodwhere.testutil.TypicalStalls.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import foodwhere.model.review.Review;
import foodwhere.model.review.ReviewBuilder;
import foodwhere.model.stall.Stall;
import foodwhere.model.stall.StallBuilder;
import foodwhere.model.stall.exceptions.DuplicateStallException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getStallList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateStalls_throwsDuplicateStallException() {
        // Two stalls with the same identity fields
        Stall editedAlice = new StallBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Stall> newStalls = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newStalls);

        assertThrows(DuplicateStallException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasStall_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStall(null));
    }

    @Test
    public void hasStall_stallNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStall(ALICE));
    }

    @Test
    public void hasStall_stallInAddressBook_returnsTrue() {
        addressBook.addStall(ALICE);
        assertTrue(addressBook.hasStall(ALICE));
    }

    @Test
    public void hasStall_stallWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addStall(ALICE);
        Stall editedAlice = new StallBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasStall(editedAlice));
    }

    @Test
    public void getStallList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStallList().remove(0));
    }

    @Test
    public void addStallAddReview_generalTesting_success() {
        AddressBook addressBook = new AddressBook();
        String testName = "test stall";
        Stall testStall = new StallBuilder().withName(testName).build();
        Review testReview = new ReviewBuilder().withName(testName).build();
        addressBook.addStall(testStall);
        addressBook.addReview(testReview);
        Set<Review> reviews = addressBook.getStallList().get(0).getReviews();
        assertEquals(1, reviews.size());
        for (Review review : reviews) {
            assertTrue(testReview.equals(review));
        }
    }

    /**
     * A stub ReadOnlyAddressBook whose stalls list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Stall> stalls = FXCollections.observableArrayList();
        private final ObservableList<Review> reviews = FXCollections.observableArrayList();

        AddressBookStub(Collection<Stall> stalls) {
            this.stalls.setAll(stalls);
        }

        @Override
        public ObservableList<Stall> getStallList() {
            return stalls;
        }

        @Override
        public ObservableList<Review> getReviewList() {
            return reviews;
        }
    }

}
