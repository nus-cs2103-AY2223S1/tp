package seedu.address.model.listing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GARDEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POOL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalListings.ALICE;
import static seedu.address.testutil.TypicalListings.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ListingBuilder;

public class ListingTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Listing listing = new ListingBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> listing.getTags().remove(0));
    }

    @Test
    public void isSameListing() {
        // same object -> returns true
        assertTrue(ALICE.isSameListing(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameListing(null));

        // same id, all other attributes different -> returns true
        Listing editedAlice = new ListingBuilder(ALICE).withOwner(VALID_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB).withAskingPrice(VALID_PRICE_1).withTags(VALID_TAG_POOL).build();
        assertTrue(ALICE.isSameListing(editedAlice));

        // different id, all other attributes same -> returns true
        editedAlice = new ListingBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertTrue(ALICE.isSameListing(editedAlice));

        // same address, all other attributes different -> returns true
        editedAlice = new ListingBuilder(ALICE).withId(VALID_ID_BOB).withOwner(VALID_NAME_BOB)
                .withAskingPrice(VALID_PRICE_1).withTags(VALID_TAG_POOL).build();
        assertTrue(ALICE.isSameListing(editedAlice));

        // different address, all other attributes same -> returns true
        editedAlice = new ListingBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE.isSameListing(editedAlice));

        // different id, different address, all other attributes same -> return false
        editedAlice = new ListingBuilder(ALICE).withId(VALID_ID_BOB).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.isSameListing(editedAlice));

        // id differs in case, address diffrent in case, all other attributes same -> returns false
        Listing editedBob = new ListingBuilder(BOB).withId(VALID_ID_BOB.toUpperCase())
                .withAddress(VALID_ADDRESS_BOB.toUpperCase()).build();
        assertFalse(BOB.isSameListing(editedBob));

        // id and address has trailing spaces, all other attributes same -> returns false
        String idWithTrailingSpaces = VALID_ID_BOB + " ";
        String addressWithTrailingSpaces = VALID_ADDRESS_BOB + " ";
        editedBob = new ListingBuilder(BOB).withId(idWithTrailingSpaces)
                .withAddress(addressWithTrailingSpaces).build();
        assertFalse(BOB.isSameListing(editedBob));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Listing aliceCopy = new ListingBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different listing -> returns false
        assertFalse(ALICE.equals(BOB));

        // different id different address -> returns false
        Listing editedAlice = new ListingBuilder(ALICE).withId("bob").withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different id same address -> returns true
        editedAlice = new ListingBuilder(ALICE).withId("bob").build();
        assertTrue(ALICE.equals(editedAlice));

        // different address same id -> returns true
        editedAlice = new ListingBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE.equals(editedAlice));

        // different owner -> returns true
        editedAlice = new ListingBuilder(ALICE).withOwner(VALID_NAME_BOB).build();
        assertTrue(ALICE.equals(editedAlice));

        // different asking price -> returns true
        editedAlice = new ListingBuilder(ALICE).withAskingPrice(VALID_PRICE_1).build();
        assertTrue(ALICE.equals(editedAlice));

        // different tags -> returns true
        editedAlice = new ListingBuilder(ALICE).withTags(VALID_TAG_GARDEN).build();
        assertTrue(ALICE.equals(editedAlice));
    }
}
