package foodwhere.model;

import static foodwhere.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static foodwhere.testutil.Assert.assertThrows;
import static foodwhere.testutil.TypicalStalls.ALICE;
import static foodwhere.testutil.TypicalStalls.getTypicalAddressBook;
import static foodwhere.testutil.TypicalStalls.getTypicalStalls;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import foodwhere.model.review.comparator.ReviewsComparatorList;
import org.junit.jupiter.api.Test;

import foodwhere.model.review.Review;
import foodwhere.model.review.ReviewBuilder;
import foodwhere.model.stall.Stall;
import foodwhere.model.stall.StallBuilder;
import foodwhere.model.stall.comparator.StallsComparatorList;
import foodwhere.model.stall.exceptions.DuplicateStallException;
import foodwhere.model.stall.exceptions.StallNotFoundException;
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
    public void constructor_withReadOnlyBookData() {
        AddressBook newData = getTypicalAddressBook();
        AddressBook addressBook = new AddressBook(newData);

        // same address book data -> equals to each other
        assertEquals(addressBook, newData);

        // null address book -> not equal to each other
        assertNotEquals(addressBook, null);
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
        String testName = "test stall";
        Stall testStall = new StallBuilder().withName(testName).build();
        Review testReview = new ReviewBuilder().withName(testName).build();
        addressBook.addStall(testStall);
        addressBook.addReview(testReview);
        Set<Review> reviews = addressBook.getStallList().get(0).getReviews();
        assertEquals(1, reviews.size());
        for (Review review : reviews) {
            assertEquals(testReview, review);
        }
    }

    @Test
    public void removeStallRemoveReview_generalTesting_success() {
        String testName = "test stall";
        Stall testStall = new StallBuilder().withName(testName).build();
        Review testReview = new ReviewBuilder().withName(testName).build();
        addressBook.addStall(testStall);
        addressBook.addReview(testReview);

        Set<Review> reviews = addressBook.getStallList().get(0).getReviews();
        assertEquals(1, reviews.size());
        assertEquals(1, addressBook.getStallList().size());

        addressBook.removeReview(testReview);
        Set<Review> newReviewsSet = addressBook.getStallList().get(0).getReviews();
        assertEquals(0, newReviewsSet.size());

        addressBook.removeStall(testStall);
        assertEquals(0, addressBook.getStallList().size());
    }

    @Test
    public void removeStallRemoveReview_notInBook_throwsStallNotFoundException() {
        String testName = "test stall";
        Stall testStall = new StallBuilder().withName(testName).build();
        Review testReview = new ReviewBuilder().withName(testName).build();

        assertThrows(StallNotFoundException.class, () -> addressBook.removeReview(testReview));
        assertThrows(StallNotFoundException.class, () -> addressBook.removeStall(testStall));
    }

    @Test
    public void setStalls_success() {
        List<Stall> sampleData = getTypicalStalls();
        addressBook.setStalls(sampleData);

        // same list of stalls -> return true
        assertEquals(sampleData, addressBook.getStallList());

        // null -> return false
        assertNotEquals(null, addressBook.getStallList());

        // remove one stall from the new list
        List<Stall> sampleDataNotEqual = getTypicalStalls();
        sampleDataNotEqual.remove(ALICE);

        assertNotEquals(sampleDataNotEqual, addressBook.getStallList());
    }

    @Test
    public void setStallSetReview_generalTesting_success() {
        // initialise address book with 1 stall and 1 review
        String testName = "test stall";
        Stall testStall = new StallBuilder().withName(testName).build();
        Review testReview = new ReviewBuilder().withName(testName).build();
        addressBook.addStall(testStall);
        addressBook.addReview(testReview);

        // check size of stall and review list = 1
        Set<Review> reviews = addressBook.getStallList().get(0).getReviews();
        assertEquals(1, reviews.size());
        assertEquals(1, addressBook.getStallList().size());

        // build a new Review with edited content and setReview
        String contentEdited = "edited content";
        Review testReviewEdited = new ReviewBuilder(testReview).withContent(contentEdited).build();

        addressBook.setReview(testReview, testReviewEdited);

        // check whether size of stall and review list = 1
        Set<Review> reviewsAfterSet = addressBook.getStallList().get(0).getReviews();
        assertEquals(1, reviewsAfterSet.size());
        assertEquals(1, addressBook.getStallList().size());

        // check whether new review is the edited version
        for (Review review: reviewsAfterSet) {
            assertEquals(review, testReviewEdited);
        }

        // build a new Stall with edited name and setStall
        String testNameEdited2 = "edited 2 stall";
        Stall testStallEdited = new StallBuilder().withName(testNameEdited2).build();

        Stall stallInAddressBook = addressBook.getStallList().get(0);
        addressBook.setStall(stallInAddressBook, testStallEdited);

        // check whether new stall is the edited version
        assertEquals(testStallEdited, addressBook.getStallList().get(0));
    }

    @Test
    public void setStall_nullStall_throwsNullPointerException() {
        String testName = "test stall";
        Stall testStall = new StallBuilder().withName(testName).build();
        Review testReview = new ReviewBuilder().withName(testName).build();
        addressBook.addStall(testStall);
        addressBook.addReview(testReview);

        assertThrows(NullPointerException.class, () -> addressBook.setStall(testStall, null));
    }

    @Test
    public void setStall_stallNotInBook_throwsStallNotFoundException() {
        String testName = "test stall";
        Stall testStall = new StallBuilder().withName(testName).build();

        String testNameEdited = "edited stall";
        Stall testStallEdited = new StallBuilder().withName(testNameEdited).build();

        assertThrows(StallNotFoundException.class, () -> addressBook.setStall(testStall, testStallEdited));
    }

    @Test
    public void setReview_nullReview_throwsNullPointerException() {
        String testName = "test stall";
        Stall testStall = new StallBuilder().withName(testName).build();
        Review testReview = new ReviewBuilder().withName(testName).build();
        addressBook.addStall(testStall);
        addressBook.addReview(testReview);

        assertThrows(NullPointerException.class, () -> addressBook.setReview(testReview, null));
    }

    @Test
    public void setReview_reviewNotInBook_throwsStallNotFoundException() {
        String testName = "test stall";
        Review testReview = new ReviewBuilder().withName(testName).build();

        String testNameEdited = "edited stall";
        Review testReviewEdited = new ReviewBuilder().withName(testNameEdited).build();

        assertThrows(StallNotFoundException.class, () -> addressBook.setReview(testReview, testReviewEdited));
    }

    @Test
    public void addReviewToStall_generalTesting_success() {
        String testName = "test stall";
        Stall testStall = new StallBuilder().withName(testName).build();
        Review testReview = new ReviewBuilder().withName(testName).build();
        addressBook.addStall(testStall);

        // check size of stall = 1 and size of reviews = 0
        Set<Review> reviews = addressBook.getStallList().get(0).getReviews();
        assertEquals(0, reviews.size());
        assertEquals(1, addressBook.getStallList().size());

        addressBook.addReviewToStall(testReview, testStall);

        Set<Review> reviewsNew = addressBook.getStallList().get(0).getReviews();
        assertEquals(1, reviewsNew.size());
        assertEquals(1, addressBook.getStallList().size());

        for (Review review: reviewsNew) {
            assertTrue(review.equals(testReview));
        }
    }

    @Test
    public void addReviewToStall_reviewNull_throwsNullPointerException() {
        String testName = "test stall";
        Stall testStall = new StallBuilder().withName(testName).build();
        addressBook.addStall(testStall);
        assertThrows(NullPointerException.class, () -> addressBook.addReviewToStall(null, testStall));
    }

    @Test
    public void addReviewToStall_reviewInBook_throwsStallNotFoundException() {
        String testName = "test stall";
        Stall testStall = new StallBuilder().withName(testName).build();
        Review testReview = new ReviewBuilder().withName(testName).build();
        addressBook.addStall(testStall);
        addressBook.addReview(testReview);
        assertThrows(StallNotFoundException.class, () -> addressBook.addReviewToStall(testReview, testStall));
    }

    @Test
    public void addReviewToStall_stallNotInBook_throwsStallNotFoundException() {
        String testName = "test stall";
        Stall testStall = new StallBuilder().withName(testName).build();
        Review testReview = new ReviewBuilder().withName(testName).build();
        assertThrows(StallNotFoundException.class, () -> addressBook.addReviewToStall(testReview, testStall));
    }

    @Test
    public void sortStall_generalTesting_success() {
        Stall[] sampleStallsOrdered = new Stall[] {
            new StallBuilder().withName("A for Apple").build(),
            new StallBuilder().withName("B for Bee").build(),
            new StallBuilder().withName("C for Cat").build(),
            new StallBuilder().withName("D for Dog").build(),
            new StallBuilder().withName("E for Elephant").build(),
            new StallBuilder().withName("G for Giraffe").build()
        };

        Stall[] sampleStallsOrderedReversed = new Stall[] {
            new StallBuilder().withName("G for Giraffe").build(),
            new StallBuilder().withName("E for Elephant").build(),
            new StallBuilder().withName("D for Dog").build(),
            new StallBuilder().withName("C for Cat").build(),
            new StallBuilder().withName("B for Bee").build(),
            new StallBuilder().withName("A for Apple").build()
        };

        Stall[] sampleStallsUnOrdered = new Stall[] {
            new StallBuilder().withName("E for Elephant").build(),
            new StallBuilder().withName("D for Dog").build(),
            new StallBuilder().withName("B for Bee").build(),
            new StallBuilder().withName("G for Giraffe").build(),
            new StallBuilder().withName("A for Apple").build(),
            new StallBuilder().withName("C for Cat").build(),
        };
        List<Stall> sampleStallsListOrdered = List.of(sampleStallsOrdered);
        List<Stall> sampleStallsListOrderedReversed = List.of(sampleStallsOrderedReversed);
        List<Stall> sampleStallsListUnOrdered = List.of(sampleStallsUnOrdered);

        for (Stall s: sampleStallsUnOrdered) {
            addressBook.addStall(s);
        }

        // sort based on ascending order
        addressBook.sortStalls(StallsComparatorList.NAME.getComparator());
        assertEquals(sampleStallsListOrdered, addressBook.getStallList());

        // make sure it's not equal to original
        assertNotEquals(sampleStallsListUnOrdered, addressBook.getStallList());

        // sort based on descending order
        addressBook.sortStalls(StallsComparatorList.REVERSEDNAME.getComparator());
        assertEquals(sampleStallsListOrderedReversed, addressBook.getStallList());

        // null comparator -> throw NullPointer
        assertThrows(NullPointerException.class, () -> addressBook.sortStalls(null));
    }

    @Test
    public void sortReviews_nameComparator_success() {
        Review[] sampleReviewsOrdered = new Review[]{
            new ReviewBuilder().withName("A for Apple").build(),
            new ReviewBuilder().withName("B for Bee").build(),
            new ReviewBuilder().withName("C for Cat").build(),
            new ReviewBuilder().withName("D for Dog").build(),
            new ReviewBuilder().withName("E for Elephant").build(),
            new ReviewBuilder().withName("G for Giraffe").build()
        };

        Review[] sampleReviewsOrderedReversed = new Review[] {
            new ReviewBuilder().withName("G for Giraffe").build(),
            new ReviewBuilder().withName("E for Elephant").build(),
            new ReviewBuilder().withName("D for Dog").build(),
            new ReviewBuilder().withName("C for Cat").build(),
            new ReviewBuilder().withName("B for Bee").build(),
            new ReviewBuilder().withName("A for Apple").build()
        };

        Review[] sampleReviewsUnOrdered = new Review[] {
            new ReviewBuilder().withName("E for Elephant").build(),
            new ReviewBuilder().withName("D for Dog").build(),
            new ReviewBuilder().withName("B for Bee").build(),
            new ReviewBuilder().withName("G for Giraffe").build(),
            new ReviewBuilder().withName("A for Apple").build(),
            new ReviewBuilder().withName("C for Cat").build()
        };

        List<Review> sampleReviewListOrdered = List.of(sampleReviewsOrdered);
        List<Review> sampleReviewListOrderedReversed = List.of(sampleReviewsOrderedReversed);
        List<Review> sampleReviewListUnOrdered = List.of(sampleReviewsUnOrdered);

        for (Review s: sampleReviewsUnOrdered) {
            addressBook.addStall(new StallBuilder().withName(s.getName().fullName).build());
            addressBook.addReview(s);
        }

        // sort based on ascending order
        addressBook.sortReviews(ReviewsComparatorList.NAME.getComparator());
        assertEquals(sampleReviewListOrdered, addressBook.getReviewList());

        // make sure it's not equal to original
        assertNotEquals(sampleReviewListUnOrdered, addressBook.getReviewList());

        // sort based on descending order
        addressBook.sortReviews(ReviewsComparatorList.REVERSEDNAME.getComparator());
        assertEquals(sampleReviewListOrderedReversed, addressBook.getReviewList());

        // null comparator -> throw NullPointer
        assertThrows(NullPointerException.class, () -> addressBook.sortReviews(null));
    }

    @Test
    public void sortReviews_dateComparator_success() {
        String testName = "test stall";
        Review[] sampleReviewsOrdered = new Review[]{
            new ReviewBuilder().withName(testName).withDate("20/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("21/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("22/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("23/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("24/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("25/09/2022").build()
        };

        Review[] sampleReviewsOrderedReversed = new Review[] {
            new ReviewBuilder().withName(testName).withDate("25/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("24/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("23/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("22/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("21/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("20/09/2022").build()
        };

        Review[] sampleReviewsUnOrdered = new Review[] {
            new ReviewBuilder().withName(testName).withDate("23/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("25/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("20/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("21/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("22/09/2022").build(),
            new ReviewBuilder().withName(testName).withDate("24/09/2022").build()
        };

        List<Review> sampleReviewListOrdered = List.of(sampleReviewsOrdered);
        List<Review> sampleReviewListOrderedReversed = List.of(sampleReviewsOrderedReversed);
        List<Review> sampleReviewListUnOrdered = List.of(sampleReviewsUnOrdered);

        addressBook.addStall(new StallBuilder().withName(testName).build());
        for (Review s: sampleReviewsUnOrdered) {
            addressBook.addReview(s);
        }

        // sort based on ascending order
        addressBook.sortReviews(ReviewsComparatorList.DATE.getComparator());
        assertEquals(sampleReviewListOrdered, addressBook.getReviewList());

        // make sure it's not equal to original
        assertNotEquals(sampleReviewListUnOrdered, addressBook.getReviewList());

        // sort based on descending order
        addressBook.sortReviews(ReviewsComparatorList.REVERSEDDATE.getComparator());
        assertEquals(sampleReviewListOrderedReversed, addressBook.getReviewList());
    }

    @Test
    public void sortReviews_ratingComparator_success() {
        String testName = "test stall";
        Review[] sampleReviewsOrdered = new Review[]{
            new ReviewBuilder().withName(testName).withDate("20/09/2022").withRating(0).build(),
            new ReviewBuilder().withName(testName).withDate("21/09/2022").withRating(1).build(),
            new ReviewBuilder().withName(testName).withDate("22/09/2022").withRating(2).build(),
            new ReviewBuilder().withName(testName).withDate("23/09/2022").withRating(3).build(),
            new ReviewBuilder().withName(testName).withDate("24/09/2022").withRating(4).build(),
            new ReviewBuilder().withName(testName).withDate("25/09/2022").withRating(5).build()
        };

        Review[] sampleReviewsOrderedReversed = new Review[] {
            new ReviewBuilder().withName(testName).withDate("25/09/2022").withRating(5).build(),
            new ReviewBuilder().withName(testName).withDate("24/09/2022").withRating(4).build(),
            new ReviewBuilder().withName(testName).withDate("23/09/2022").withRating(3).build(),
            new ReviewBuilder().withName(testName).withDate("22/09/2022").withRating(2).build(),
            new ReviewBuilder().withName(testName).withDate("21/09/2022").withRating(1).build(),
            new ReviewBuilder().withName(testName).withDate("20/09/2022").withRating(0).build()
        };

        Review[] sampleReviewsUnOrdered = new Review[] {
            new ReviewBuilder().withName(testName).withDate("24/09/2022").withRating(4).build(),
            new ReviewBuilder().withName(testName).withDate("22/09/2022").withRating(2).build(),
            new ReviewBuilder().withName(testName).withDate("25/09/2022").withRating(5).build(),
            new ReviewBuilder().withName(testName).withDate("21/09/2022").withRating(1).build(),
            new ReviewBuilder().withName(testName).withDate("23/09/2022").withRating(3).build(),
            new ReviewBuilder().withName(testName).withDate("20/09/2022").withRating(0).build()
        };

        List<Review> sampleReviewListOrdered = List.of(sampleReviewsOrdered);
        List<Review> sampleReviewListOrderedReversed = List.of(sampleReviewsOrderedReversed);
        List<Review> sampleReviewListUnOrdered = List.of(sampleReviewsUnOrdered);

        addressBook.addStall(new StallBuilder().withName(testName).build());
        for (Review s: sampleReviewsUnOrdered) {
            addressBook.addReview(s);
        }

        // sort based on ascending order
        addressBook.sortReviews(ReviewsComparatorList.RATING.getComparator());
        assertEquals(sampleReviewListOrdered, addressBook.getReviewList());

        // make sure it's not equal to original
        assertNotEquals(sampleReviewListUnOrdered, addressBook.getReviewList());

        // sort based on descending order
        addressBook.sortReviews(ReviewsComparatorList.REVERSEDRATING.getComparator());
        assertEquals(sampleReviewListOrderedReversed, addressBook.getReviewList());
    }

    @Test
    public void equals() {
        // same object -> equals return true
        assertTrue(addressBook.equals(addressBook));

        // null -> equals return false
        assertFalse(addressBook.equals(null));

        // different stall or review list -> equals return false
        AddressBook addressBookDiff = getTypicalAddressBook();
        assertFalse(addressBook.equals(addressBookDiff));

        // different object -> equals return false
        String testName = "test stall";
        Review testReview = new ReviewBuilder().withName(testName).build();
        assertFalse(addressBook.equals(testReview));
    }

    @Test
    public void hashCode_test() {
        // same object -> hashCode same
        assertEquals(addressBook.hashCode(), addressBook.hashCode());

        // different stall and review list -> hashCode not same
        AddressBook addressBookDiff = getTypicalAddressBook();
        assertNotEquals(addressBook.hashCode(), addressBookDiff.hashCode());
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
