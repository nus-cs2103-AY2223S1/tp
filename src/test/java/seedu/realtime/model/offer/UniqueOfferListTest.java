package seedu.realtime.model.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_LISTING_ID_1;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_PRICE_3;
import static seedu.realtime.testutil.TypicalOffers.ALICE;
import static seedu.realtime.testutil.TypicalOffers.BOB;
import static seedu.realtime.testutil.TypicalOffers.HOON;
import static seedu.realtime.testutil.TypicalOffers.IDA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.realtime.model.offer.exceptions.DuplicateOfferException;
import seedu.realtime.model.offer.exceptions.OfferNotFoundException;
import seedu.realtime.testutil.OfferBuilder;

public class UniqueOfferListTest {

    private final UniqueOfferList uniqueOfferList = new UniqueOfferList();

    @Test
    public void contains_nullOffer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOfferList.contains(null));
    }

    @Test
    public void contains_offerNotInList_returnsFalse() {
        assertFalse(uniqueOfferList.contains(IDA));
    }

    @Test
    public void contains_offerInList_returnsTrue() {
        uniqueOfferList.add(HOON);
        assertTrue(uniqueOfferList.contains(HOON));
    }

    @Test
    public void contains_offerWithSameIdentityFieldsInList_returnsTrue() {
        uniqueOfferList.add(ALICE);
        Offer editedAlice = new OfferBuilder(ALICE).withListing(VALID_LISTING_ID_1).withOfferPrice(VALID_PRICE_3)
                .build();
        assertTrue(uniqueOfferList.contains(editedAlice));
    }

    @Test
    public void add_nullOffer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOfferList.add(null));
    }

    @Test
    public void add_duplicateOffer_throwsDuplicateOfferException() {
        uniqueOfferList.add(ALICE);
        assertThrows(DuplicateOfferException.class, () -> uniqueOfferList.add(ALICE));
    }

    @Test
    public void setOffer_nullTargetOffer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOfferList.setOffer(null, ALICE));
    }

    @Test
    public void setOffer_nullEditedOffer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOfferList.setOffer(ALICE, null));
    }

    @Test
    public void setOffer_targetOfferNotInList_throwsOfferNotFoundException() {
        assertThrows(OfferNotFoundException.class, () -> uniqueOfferList.setOffer(ALICE, ALICE));
    }

    @Test
    public void setOffer_editedOfferIsSameOffer_success() {
        uniqueOfferList.add(ALICE);
        uniqueOfferList.setOffer(ALICE, ALICE);
        UniqueOfferList expectedUniqueOfferList = new UniqueOfferList();
        expectedUniqueOfferList.add(ALICE);
        assertEquals(expectedUniqueOfferList, uniqueOfferList);
    }

    @Test
    public void setOffer_editedOfferHasSameIdentity_success() {
        uniqueOfferList.add(ALICE);
        Offer editedAlice = new OfferBuilder(ALICE).withListing(VALID_LISTING_ID_1).withOfferPrice(VALID_PRICE_3)
                .build();
        uniqueOfferList.setOffer(ALICE, editedAlice);
        UniqueOfferList expectedUniqueOfferList = new UniqueOfferList();
        expectedUniqueOfferList.add(editedAlice);
        assertEquals(expectedUniqueOfferList, uniqueOfferList);
    }

    @Test
    public void setOffer_editedOfferHasDifferentIdentity_success() {
        uniqueOfferList.add(ALICE);
        uniqueOfferList.setOffer(ALICE, BOB);
        UniqueOfferList expectedUniqueOfferList = new UniqueOfferList();
        expectedUniqueOfferList.add(BOB);
        assertEquals(expectedUniqueOfferList, uniqueOfferList);
    }

    @Test
    public void setOffer_editedOfferHasNonUniqueIdentity_throwsDuplicateOfferException() {
        uniqueOfferList.add(ALICE);
        uniqueOfferList.add(BOB);
        assertThrows(DuplicateOfferException.class, () -> uniqueOfferList.setOffer(ALICE, BOB));
    }

    @Test
    public void remove_nullOffer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOfferList.remove(null));
    }

    @Test
    public void remove_offerDoesNotExist_throwsOfferNotFoundException() {
        assertThrows(OfferNotFoundException.class, () -> uniqueOfferList.remove(ALICE));
    }

    @Test
    public void remove_existingOffer_removesOffer() {
        uniqueOfferList.add(ALICE);
        uniqueOfferList.remove(ALICE);
        UniqueOfferList expectedUniqueOfferList = new UniqueOfferList();
        assertEquals(expectedUniqueOfferList, uniqueOfferList);
    }

    @Test
    public void setOffers_nullUniqueOfferList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOfferList.setOffers((UniqueOfferList) null));
    }

    @Test
    public void setOffers_uniqueOfferList_replacesOwnListWithProvidedUniqueOfferList() {
        uniqueOfferList.add(ALICE);
        UniqueOfferList expectedUniqueOfferList = new UniqueOfferList();
        expectedUniqueOfferList.add(BOB);
        uniqueOfferList.setOffers(expectedUniqueOfferList);
        assertEquals(expectedUniqueOfferList, uniqueOfferList);
    }

    @Test
    public void setOffers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOfferList.setOffers((List<Offer>) null));
    }

    @Test
    public void setOffers_list_replacesOwnListWithProvidedList() {
        uniqueOfferList.add(ALICE);
        List<Offer> offerList = Collections.singletonList(BOB);
        uniqueOfferList.setOffers(offerList);
        UniqueOfferList expectedUniqueOfferList = new UniqueOfferList();
        expectedUniqueOfferList.add(BOB);
        assertEquals(expectedUniqueOfferList, uniqueOfferList);
    }

    @Test
    public void setOffers_listWithDuplicateOffers_throwsDuplicateOfferException() {
        List<Offer> listWithDuplicateOffers = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateOfferException.class, () -> uniqueOfferList.setOffers(listWithDuplicateOffers));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
        -> uniqueOfferList.asUnmodifiableObservableList().remove(0));
    }
}
