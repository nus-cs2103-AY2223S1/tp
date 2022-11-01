package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRIORITY_HIGH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.ALICE;
import static seedu.address.testutil.TypicalBuyers.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.buyer.exceptions.BuyerNotFoundException;
import seedu.address.model.buyer.exceptions.DuplicateBuyerException;
import seedu.address.testutil.BuyerBuilder;

public class UniqueBuyerListTest {

    private final UniqueBuyerList uniqueBuyerList = new UniqueBuyerList();

    @Test
    public void contains_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.contains(null));
    }

    @Test
    public void contains_buyerNotInList_returnsFalse() {
        assertFalse(uniqueBuyerList.contains(ALICE));
    }

    @Test
    public void contains_buyerInList_returnsTrue() {
        uniqueBuyerList.add(ALICE);
        assertTrue(uniqueBuyerList.contains(ALICE));
    }

    @Test
    public void contains_buyerWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBuyerList.add(ALICE);
        Buyer editedAlice = new BuyerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withPriority(VALID_PRIORITY_HIGH)
                .build();
        assertTrue(uniqueBuyerList.contains(editedAlice));
    }

    @Test
    public void add_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.add(null));
    }

    @Test
    public void add_duplicateBuyer_throwsDuplicateBuyerException() {
        uniqueBuyerList.add(ALICE);
        assertThrows(DuplicateBuyerException.class, () -> uniqueBuyerList.add(ALICE));
    }

    @Test
    public void setBuyer_nullTargetBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.setBuyer(null, ALICE));
    }

    @Test
    public void setBuyer_nullEditedBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.setBuyer(ALICE, null));
    }

    @Test
    public void setBuyer_targetBuyerNotInList_throwsBuyerNotFoundException() {
        assertThrows(BuyerNotFoundException.class, () -> uniqueBuyerList.setBuyer(ALICE, ALICE));
    }

    @Test
    public void setBuyer_editedBuyerIsSameBuyer_success() {
        uniqueBuyerList.add(ALICE);
        uniqueBuyerList.setBuyer(ALICE, ALICE);
        UniqueBuyerList expectedUniqueBuyerList = new UniqueBuyerList();
        expectedUniqueBuyerList.add(ALICE);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }

    @Test
    public void setBuyer_editedBuyerHasSameIdentity_success() {
        uniqueBuyerList.add(ALICE);
        Buyer editedAlice = new BuyerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withPriority(VALID_PRIORITY_HIGH)
                .build();
        uniqueBuyerList.setBuyer(ALICE, editedAlice);
        UniqueBuyerList expectedUniqueBuyerList = new UniqueBuyerList();
        expectedUniqueBuyerList.add(editedAlice);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }

    @Test
    public void setBuyer_editedBuyerHasDifferentIdentity_success() {
        uniqueBuyerList.add(ALICE);
        uniqueBuyerList.setBuyer(ALICE, BOB);
        UniqueBuyerList expectedUniqueBuyerList = new UniqueBuyerList();
        expectedUniqueBuyerList.add(BOB);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }

    @Test
    public void setBuyer_editedBuyerHasNonUniqueIdentity_throwsDuplicateBuyerException() {
        uniqueBuyerList.add(ALICE);
        uniqueBuyerList.add(BOB);
        assertThrows(DuplicateBuyerException.class, () -> uniqueBuyerList.setBuyer(ALICE, BOB));
    }

    @Test
    public void remove_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.remove(null));
    }

    @Test
    public void remove_buyerDoesNotExist_throwsBuyerNotFoundException() {
        assertThrows(BuyerNotFoundException.class, () -> uniqueBuyerList.remove(ALICE));
    }

    @Test
    public void remove_existingBuyer_removesBuyer() {
        uniqueBuyerList.add(ALICE);
        uniqueBuyerList.remove(ALICE);
        UniqueBuyerList expectedUniqueBuyerList = new UniqueBuyerList();
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }

    @Test
    public void setBuyers_nullUniqueBuyerList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.setBuyers((UniqueBuyerList) null));
    }

    @Test
    public void setBuyers_uniqueBuyerList_replacesOwnListWithProvidedUniqueBuyerList() {
        uniqueBuyerList.add(ALICE);
        UniqueBuyerList expectedUniqueBuyerList = new UniqueBuyerList();
        expectedUniqueBuyerList.add(BOB);
        uniqueBuyerList.setBuyers(expectedUniqueBuyerList);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }

    @Test
    public void setBuyers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.setBuyers((List<Buyer>) null));
    }

    @Test
    public void setBuyers_list_replacesOwnListWithProvidedList() {
        uniqueBuyerList.add(ALICE);
        List<Buyer> buyerList = Collections.singletonList(BOB);
        uniqueBuyerList.setBuyers(buyerList);
        UniqueBuyerList expectedUniqueBuyerList = new UniqueBuyerList();
        expectedUniqueBuyerList.add(BOB);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }

    @Test
    public void setBuyers_listWithDuplicateBuyers_throwsDuplicateBuyerException() {
        List<Buyer> listWithDuplicateBuyers = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateBuyerException.class, () -> uniqueBuyerList.setBuyers(listWithDuplicateBuyers));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBuyerList.asUnmodifiableObservableList().remove(0));
    }
}
