package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_DESIRED_CHARACTERISTICS_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRICE_RANGE_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRIORITY_HIGH;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRIORITY_LOW;
import static seedu.address.testutil.TypicalBuyers.ALICE;
import static seedu.address.testutil.TypicalBuyers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BuyerBuilder;

public class BuyerTest {

    //    @Test
    //    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
    //        Buyer buyer = new BuyerBuilder().build();
    //        assertThrows(UnsupportedOperationException.class, () -> buyer.getName().toString());
    //    }

    @Test
    public void isSameBuyer() {
        // same object -> returns true
        assertTrue(ALICE.isSameBuyer(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameBuyer(null));

        // same name, all other attributes different -> returns false
        Buyer editedAlice = new BuyerBuilder(ALICE)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withPriceRange(VALID_PRICE_RANGE_BOB)
                .withDesiredCharacteristics(VALID_DESIRED_CHARACTERISTICS_BOB)
                .withPriority(VALID_PRIORITY_HIGH).build();
        assertFalse(ALICE.isSameBuyer(editedAlice));

        // same phone, all other attributes different -> returns true
        editedAlice = new BuyerBuilder(ALICE)
                .withName(VALID_NAME_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withPriceRange(VALID_PRICE_RANGE_BOB)
                .withDesiredCharacteristics(VALID_DESIRED_CHARACTERISTICS_BOB)
                .withPriority(VALID_PRIORITY_HIGH).build();
        assertTrue(ALICE.isSameBuyer(editedAlice));

        // same name, same phone, all other attributes different -> returns true
        editedAlice = new BuyerBuilder(ALICE)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withPriceRange(VALID_PRICE_RANGE_BOB)
                .withDesiredCharacteristics(VALID_DESIRED_CHARACTERISTICS_BOB)
                .withPriority(VALID_PRIORITY_HIGH).build();
        assertTrue(ALICE.isSameBuyer(editedAlice));

        // different name, different phone, same email, all other attributes same -> returns true
        editedAlice = new BuyerBuilder(ALICE).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();
        assertTrue(ALICE.isSameBuyer(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Buyer editedBob = new BuyerBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameBuyer(editedBob));

        // name has trailing spaces, all other attributes same -> returns true (since we are matching by email or phone)
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new BuyerBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSameBuyer(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Buyer aliceCopy = new BuyerBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different buyer -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Buyer editedAlice = new BuyerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new BuyerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new BuyerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new BuyerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different price range -> returns false
        editedAlice = new BuyerBuilder(ALICE).withPriceRange(VALID_PRICE_RANGE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different desired characteristics -> returns false
        editedAlice = new BuyerBuilder(ALICE).withAddress(VALID_DESIRED_CHARACTERISTICS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new BuyerBuilder(ALICE).withPriority(VALID_PRIORITY_LOW).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
