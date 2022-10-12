package foodwhere.model.review;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foodwhere.logic.commands.CommandTestUtil;
import foodwhere.testutil.ReviewBuilder;
import foodwhere.testutil.TypicalReviews;

public class ReviewTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Review review = new ReviewBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> review.getTags().remove(0));
    }

    @Test
    public void isSameReview() {
        // same object -> returns true
        assertTrue(TypicalReviews.ALICE.isSameReview(TypicalReviews.ALICE));

        // null -> returns false
        assertFalse(TypicalReviews.ALICE.isSameReview(null));

        // different name -> returns false
        Review editedAlice = new ReviewBuilder(TypicalReviews.ALICE).withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(TypicalReviews.ALICE.equals(editedAlice));

        // different date -> returns false
        editedAlice = new ReviewBuilder(TypicalReviews.ALICE).withDate(CommandTestUtil.VALID_DATE_BOB).build();
        assertFalse(TypicalReviews.ALICE.equals(editedAlice));

        // different content -> returns false
        editedAlice = new ReviewBuilder(TypicalReviews.ALICE).withContent(CommandTestUtil.VALID_CONTENT_BOB).build();
        assertFalse(TypicalReviews.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ReviewBuilder(TypicalReviews.ALICE).withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        assertFalse(TypicalReviews.ALICE.equals(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Review aliceCopy = new ReviewBuilder(TypicalReviews.ALICE).build();
        assertTrue(TypicalReviews.ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TypicalReviews.ALICE.equals(TypicalReviews.ALICE));

        // null -> returns false
        assertFalse(TypicalReviews.ALICE.equals(null));

        // different type -> returns false
        assertFalse(TypicalReviews.ALICE.equals(5));

        // different review -> returns false
        assertFalse(TypicalReviews.ALICE.equals(TypicalReviews.BOB));

        // different name -> returns false
        Review editedAlice = new ReviewBuilder(TypicalReviews.ALICE).withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(TypicalReviews.ALICE.equals(editedAlice));

        // different date -> returns false
        editedAlice = new ReviewBuilder(TypicalReviews.ALICE).withDate(CommandTestUtil.VALID_DATE_BOB).build();
        assertFalse(TypicalReviews.ALICE.equals(editedAlice));

        // different content -> returns false
        editedAlice = new ReviewBuilder(TypicalReviews.ALICE).withContent(CommandTestUtil.VALID_CONTENT_BOB).build();
        assertFalse(TypicalReviews.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ReviewBuilder(TypicalReviews.ALICE).withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        assertFalse(TypicalReviews.ALICE.equals(editedAlice));
    }
}
