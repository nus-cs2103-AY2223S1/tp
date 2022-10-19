package foodwhere.model.stall;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import foodwhere.model.review.exceptions.ReviewNotFoundException;
import org.junit.jupiter.api.Test;

import foodwhere.logic.commands.CommandTestUtil;
import foodwhere.testutil.TypicalReviews;
import foodwhere.testutil.TypicalStalls;


import foodwhere.logic.commands.CommandTestUtil;
import foodwhere.model.review.Review;
import foodwhere.testutil.TypicalReviews;
import foodwhere.testutil.TypicalStalls;

public class StallBuilderTest {

    @Test
    public void addReview_normalBehavior_success() {
        StallBuilder stall = new StallBuilder(TypicalStalls.ALICE);
        stall.addReview(TypicalReviews.BOB);
        stall.removeReview(TypicalReviews.BOB);
        assertEquals(TypicalStalls.ALICE, stall.build());
    }

    @Test
    public void removeReview_removeNotPresent_throwsError() {
        StallBuilder stall = new StallBuilder(TypicalStalls.ALICE);
        assertThrows(ReviewNotFoundException.class, () -> stall.removeReview(TypicalReviews.BOB));
    }
}
