package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.ALICE;
import static seedu.address.testutil.TypicalBuyers.BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.PersonBuilder;

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
        Buyer editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .buildBuyer();
        assertTrue(uniqueBuyerList.contains(editedAlice));
    }

    @Test
    public void add_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.add(null));
    }

    @Test
    public void add_duplicateBuyer_throwsDuplicatePersonException() {
        uniqueBuyerList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueBuyerList.add(ALICE));
    }

    @Test
    public void setBuyer_nullTargetBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetBuyerNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueBuyerList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSameBuyer_success() {
        uniqueBuyerList.add(ALICE);
        uniqueBuyerList.setPerson(ALICE, ALICE);
        UniqueBuyerList expectedUniqueBuyerList = new UniqueBuyerList();
        expectedUniqueBuyerList.add(ALICE);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }

    @Test
    public void setBuyer_editedPersonHasSameIdentity_success() {
        uniqueBuyerList.add(ALICE);
        Buyer editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .buildBuyer();
        uniqueBuyerList.setPerson(ALICE, editedAlice);
        UniqueBuyerList expectedUniqueBuyerList = new UniqueBuyerList();
        expectedUniqueBuyerList.add(editedAlice);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }

    @Test
    public void setBuyer_editedPersonHasDifferentIdentity_success() {
        uniqueBuyerList.add(ALICE);
        uniqueBuyerList.setPerson(ALICE, BENSON);
        UniqueBuyerList expectedUniqueBuyerList = new UniqueBuyerList();
        expectedUniqueBuyerList.add(BENSON);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }

    @Test
    public void setBuyer_editedBuyerHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueBuyerList.add(ALICE);
        uniqueBuyerList.add(BENSON);
        assertThrows(DuplicatePersonException.class, () -> uniqueBuyerList.setPerson(ALICE, BENSON));
    }

    @Test
    public void remove_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.remove(null));
    }

    @Test
    public void remove_buyerDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueBuyerList.remove(ALICE));
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
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.setPersons((UniqueBuyerList) null));
    }

    @Test
    public void setBuyers_uniqueBuyerList_replacesOwnListWithProvidedUniqueBuyerList() {
        uniqueBuyerList.add(ALICE);
        UniqueBuyerList expectedUniqueBuyerList = new UniqueBuyerList();
        expectedUniqueBuyerList.add(BENSON);
        uniqueBuyerList.setPersons(expectedUniqueBuyerList);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }

    @Test
    public void setBueyrs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.setPersons((List<Buyer>) null));
    }

    @Test
    public void setBuyers_list_replacesOwnListWithProvidedList() {
        uniqueBuyerList.add(ALICE);
        List<Buyer> buyerList = Collections.singletonList(BENSON);
        uniqueBuyerList.setPersons(buyerList);
        UniqueBuyerList expectedUniquePersonList = new UniqueBuyerList();
        expectedUniquePersonList.add(BENSON);
        assertEquals(expectedUniquePersonList, uniqueBuyerList);
    }

    @Test
    public void setBuyers_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Buyer> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueBuyerList.setPersons(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueBuyerList.asUnmodifiableObservableList().remove(0));
    }
}
