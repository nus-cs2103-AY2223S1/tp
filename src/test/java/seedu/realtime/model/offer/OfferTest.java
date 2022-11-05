package seedu.realtime.model.offer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_LISTING_ID_BEDOK;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_PRICE_1;
import static seedu.realtime.testutil.TypicalOffers.ALICE;
import static seedu.realtime.testutil.TypicalOffers.BOB;

import org.junit.jupiter.api.Test;

import seedu.realtime.testutil.OfferBuilder;



public class OfferTest {

    @Test
    public void isSameOffer() {
        // same object -> returns true
        assertTrue(ALICE.isSameOffer(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameOffer(null));

        // same name, all other attributes different -> returns false
        Offer editedAlice = new OfferBuilder(ALICE).withOfferPrice("1234567").withListing("1234").build();
        assertFalse(ALICE.isSameOffer(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new OfferBuilder(ALICE).withBuyer(VALID_NAME_AMY).build();
        assertFalse(ALICE.isSameOffer(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Offer editedBob = new OfferBuilder(BOB).withBuyer(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameOffer(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new OfferBuilder(BOB).withBuyer(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameOffer(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Offer aliceCopy = new OfferBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different Offer -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Offer editedAlice = new OfferBuilder(ALICE).withBuyer(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different offer Price -> returns false
        editedAlice = new OfferBuilder(ALICE).withOfferPrice(VALID_PRICE_1).build();
        assertFalse(ALICE.equals(editedAlice));

        // different listing ID -> returns false
        editedAlice = new OfferBuilder(ALICE).withListing(VALID_LISTING_ID_BEDOK).build();
        assertFalse(ALICE.equals(editedAlice));

    }
}
