package foodwhere.model.review;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import foodwhere.logic.commands.CommandTestUtil;
import foodwhere.model.review.exceptions.DuplicateReviewException;
import foodwhere.model.review.exceptions.ReviewNotFoundException;
import foodwhere.testutil.TypicalReviews;

public class UniqueReviewListTest {

    private final UniqueReviewList uniqueReviewList = new UniqueReviewList();

    @Test
    public void contains_nullReview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReviewList.contains(null));
    }

    @Test
    public void contains_reviewNotInList_returnsFalse() {
        assertFalse(uniqueReviewList.contains(TypicalReviews.ALICE));
    }

    @Test
    public void contains_reviewInList_returnsTrue() {
        uniqueReviewList.add(TypicalReviews.ALICE);
        assertTrue(uniqueReviewList.contains(TypicalReviews.ALICE));
    }

    @Test
    public void contains_reviewWithSameIdentityFieldsInList_returnsFalse() {
        uniqueReviewList.add(TypicalReviews.ALICE);
        Review editedAlice =
                new ReviewBuilder(TypicalReviews.ALICE)
                        .withDate(CommandTestUtil.VALID_DATE_BOB)
                        .withContent(CommandTestUtil.VALID_CONTENT_BOB)
                        .withTags(CommandTestUtil.VALID_TAG_HUSBAND)
                        .build();
        assertFalse(uniqueReviewList.contains(editedAlice));
    }

    @Test
    public void add_nullReview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReviewList.add(null));
    }

    @Test
    public void add_duplicateReview_throwsDuplicateReviewException() {
        uniqueReviewList.add(TypicalReviews.ALICE);
        assertThrows(DuplicateReviewException.class, () -> uniqueReviewList.add(TypicalReviews.ALICE));
    }

    @Test
    public void setReview_nullTargetReview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReviewList.setReview(null, TypicalReviews.ALICE));
    }

    @Test
    public void setReview_nullEditedReview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueReviewList.setReview(TypicalReviews.ALICE, null));
    }

    @Test
    public void setReview_targetReviewNotInList_throwsReviewNotFoundException() {
        assertThrows(ReviewNotFoundException.class, () ->
                uniqueReviewList.setReview(TypicalReviews.ALICE, TypicalReviews.ALICE));
    }

    @Test
    public void setReview_editedReviewIsSameReview_success() {
        uniqueReviewList.add(TypicalReviews.ALICE);
        uniqueReviewList.setReview(TypicalReviews.ALICE, TypicalReviews.ALICE);
        UniqueReviewList expectedUniqueReviewList = new UniqueReviewList();
        expectedUniqueReviewList.add(TypicalReviews.ALICE);
        assertEquals(expectedUniqueReviewList, uniqueReviewList);
    }

    @Test
    public void setReview_editedReviewHasSameIdentity_success() {
        uniqueReviewList.add(TypicalReviews.ALICE);
        Review editedAlice = new ReviewBuilder(TypicalReviews.ALICE)
                .withDate(CommandTestUtil.VALID_DATE_BOB)
                .withContent(CommandTestUtil.VALID_CONTENT_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        uniqueReviewList.setReview(TypicalReviews.ALICE, editedAlice);
        UniqueReviewList expectedUniqueReviewList = new UniqueReviewList();
        expectedUniqueReviewList.add(editedAlice);
        assertEquals(expectedUniqueReviewList, uniqueReviewList);
    }

    @Test
    public void setReview_editedReviewHasDifferentIdentity_success() {
        uniqueReviewList.add(TypicalReviews.ALICE);
        uniqueReviewList.setReview(TypicalReviews.ALICE, TypicalReviews.BOB);
        UniqueReviewList expectedUniqueReviewList = new UniqueReviewList();
        expectedUniqueReviewList.add(TypicalReviews.BOB);
        assertEquals(expectedUniqueReviewList, uniqueReviewList);
    }

    @Test
    public void setReview_editedReviewHasNonUniqueIdentity_throwsDuplicateReviewException() {
        uniqueReviewList.add(TypicalReviews.ALICE);
        uniqueReviewList.add(TypicalReviews.BOB);
        assertThrows(DuplicateReviewException.class, () ->
                uniqueReviewList.setReview(TypicalReviews.ALICE, TypicalReviews.BOB));
    }

    @Test
    public void remove_nullReview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReviewList.remove(null));
    }

    @Test
    public void remove_reviewDoesNotExist_throwsReviewNotFoundException() {
        assertThrows(ReviewNotFoundException.class, () -> uniqueReviewList.remove(TypicalReviews.ALICE));
    }

    @Test
    public void remove_existingReview_removesReview() {
        uniqueReviewList.add(TypicalReviews.ALICE);
        uniqueReviewList.remove(TypicalReviews.ALICE);
        UniqueReviewList expectedUniqueReviewList = new UniqueReviewList();
        assertEquals(expectedUniqueReviewList, uniqueReviewList);
    }

    @Test
    public void setReviews_nullUniqueReviewList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReviewList.setReviews((UniqueReviewList) null));
    }

    @Test
    public void setReviews_uniqueReviewList_replacesOwnListWithProvidedUniqueReviewList() {
        uniqueReviewList.add(TypicalReviews.ALICE);
        UniqueReviewList expectedUniqueReviewList = new UniqueReviewList();
        expectedUniqueReviewList.add(TypicalReviews.BOB);
        uniqueReviewList.setReviews(expectedUniqueReviewList);
        assertEquals(expectedUniqueReviewList, uniqueReviewList);
    }

    @Test
    public void setReviews_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReviewList.setReviews((List<Review>) null));
    }

    @Test
    public void setReviews_list_replacesOwnListWithProvidedList() {
        uniqueReviewList.add(TypicalReviews.ALICE);
        List<Review> reviewList = Collections.singletonList(TypicalReviews.BOB);
        uniqueReviewList.setReviews(reviewList);
        UniqueReviewList expectedUniqueReviewList = new UniqueReviewList();
        expectedUniqueReviewList.add(TypicalReviews.BOB);
        assertEquals(expectedUniqueReviewList, uniqueReviewList);
    }

    @Test
    public void setReviews_listWithDuplicateReviews_throwsDuplicateReviewException() {
        List<Review> listWithDuplicateReviews = Arrays.asList(TypicalReviews.ALICE, TypicalReviews.ALICE);
        assertThrows(DuplicateReviewException.class, () -> uniqueReviewList.setReviews(listWithDuplicateReviews));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueReviewList.asUnmodifiableObservableList().remove(0));
    }
}
