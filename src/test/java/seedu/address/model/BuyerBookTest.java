package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRIORITY_HIGH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.ALICE;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.exceptions.DuplicateBuyerException;
import seedu.address.testutil.BuyerBuilder;

public class BuyerBookTest {

    private final BuyerBook buyerBook = new BuyerBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), buyerBook.getBuyerList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> buyerBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyBuyerBook_replacesData() {
        BuyerBook newData = getTypicalBuyersBook();
        buyerBook.resetData(newData);
        assertEquals(newData, buyerBook);
    }

    @Test
    public void resetData_withDuplicateBuyers_throwsDuplicateBuyerException() {
        // Two buyers with the same identity fields
        Buyer editedAlice = new BuyerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withPriority(VALID_PRIORITY_HIGH)
                .build();
        List<Buyer> newBuyers = Arrays.asList(ALICE, editedAlice);
        BuyerBookStub newData = new BuyerBookStub(newBuyers);

        assertThrows(DuplicateBuyerException.class, () -> buyerBook.resetData(newData));
    }

    @Test
    public void hasBuyer_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> buyerBook.hasBuyer(null));
    }

    @Test
    public void hasBuyer_buyerNotInBuyerBook_returnsFalse() {
        assertFalse(buyerBook.hasBuyer(ALICE));
    }

    @Test
    public void hasBuyer_buyerInBuyerBook_returnsTrue() {
        buyerBook.addBuyer(ALICE);
        assertTrue(buyerBook.hasBuyer(ALICE));
    }

    @Test
    public void hasBuyer_buyerWithSameIdentityFieldsInBuyerBook_returnsTrue() {
        buyerBook.addBuyer(ALICE);
        Buyer editedAlice = new BuyerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withPriority(VALID_PRIORITY_HIGH)
                .build();
        assertTrue(buyerBook.hasBuyer(editedAlice));
    }

    @Test
    public void getBuyerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> buyerBook.getBuyerList().remove(0));
    }

    /**
     * A stub ReadOnlyBuyerBook whose buyers list can violate interface constraints.
     */
    private static class BuyerBookStub implements ReadOnlyBuyerBook {
        private final ObservableList<Buyer> buyers = FXCollections.observableArrayList();

        BuyerBookStub(Collection<Buyer> buyers) {
            this.buyers.setAll(buyers);
        }

        @Override
        public ObservableList<Buyer> getBuyerList() {
            return buyers;
        }
    }
}
