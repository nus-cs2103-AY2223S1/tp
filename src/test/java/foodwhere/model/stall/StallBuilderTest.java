package foodwhere.model.stall;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import foodwhere.model.review.Review;
import foodwhere.model.review.exceptions.ReviewNotFoundException;
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

    @Test
    public void withName_withReview_renamesReviews() {
        String newName = "Another Food Place";
        Stall stall = new StallBuilder(TypicalStalls.ALICE)
                .addReview(TypicalReviews.ALICE)
                .withName(newName).build();
        for (Review review : stall.getReviews()) {
            assertEquals(newName, review.getName().fullName);
        }
    }
}
