package seedu.address.model.poc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPoc.ALICE;
import static seedu.address.testutil.TypicalPoc.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.poc.exceptions.DuplicatePocException;
import seedu.address.model.poc.exceptions.PocNotFoundException;
import seedu.address.testutil.PocBuilder;

public class UniquePocListTest {

    private final UniquePocList uniquePocList = new UniquePocList();

    @Test
    public void contains_nullPoc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePocList.contains(null));
    }

    @Test
    public void contains_pocNotInList_returnsFalse() {
        assertFalse(uniquePocList.contains(ALICE));
    }

    @Test
    public void contains_pocInList_returnsTrue() {
        uniquePocList.add(ALICE);
        assertTrue(uniquePocList.contains(ALICE));
    }

    @Test
    public void contains_pocWithSameIdentityFieldsInList_returnsTrue() {
        uniquePocList.add(ALICE);
        Poc editedAlice = new PocBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniquePocList.contains(editedAlice));
    }

    @Test
    public void add_nullPoc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePocList.add(null));
    }

    @Test
    public void add_duplicatePoc_throwsDuplicatePocException() {
        uniquePocList.add(ALICE);
        assertThrows(DuplicatePocException.class, () -> uniquePocList.add(ALICE));
    }

    @Test
    public void setPoc_nullTargetPoc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePocList.replacePoc(null, ALICE));
    }

    @Test
    public void setPoc_nullEditedPoc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePocList.replacePoc(ALICE, null));
    }

    @Test
    public void setPoc_targetPocNotInList_throwsPocNotFoundException() {
        assertThrows(PocNotFoundException.class, () -> uniquePocList.replacePoc(ALICE, ALICE));
    }

    @Test
    public void setPoc_editedPocIsSamePoc_success() {
        uniquePocList.add(ALICE);
        uniquePocList.replacePoc(ALICE, ALICE);
        UniquePocList expectedUniquePocList = new UniquePocList();
        expectedUniquePocList.add(ALICE);
        assertEquals(expectedUniquePocList, uniquePocList);
    }

    @Test
    public void setPoc_editedPocHasSameIdentity_success() {
        uniquePocList.add(ALICE);
        Poc editedAlice = new PocBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        uniquePocList.replacePoc(ALICE, editedAlice);
        UniquePocList expectedUniquePocList = new UniquePocList();
        expectedUniquePocList.add(editedAlice);
        assertEquals(expectedUniquePocList, uniquePocList);
    }

    @Test
    public void setPoc_editedPocHasDifferentIdentity_success() {
        uniquePocList.add(ALICE);
        uniquePocList.replacePoc(ALICE, BOB);
        UniquePocList expectedUniquePocList = new UniquePocList();
        expectedUniquePocList.add(BOB);
        assertEquals(expectedUniquePocList, uniquePocList);
    }

    @Test
    public void setPoc_editedPocHasNonUniqueIdentity_throwsDuplicatePocException() {
        uniquePocList.add(ALICE);
        uniquePocList.add(BOB);
        assertThrows(DuplicatePocException.class, () -> uniquePocList.replacePoc(ALICE, BOB));
    }

    @Test
    public void remove_nullPoc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePocList.remove(null));
    }

    @Test
    public void remove_pocDoesNotExist_throwsPocNotFoundException() {
        assertThrows(PocNotFoundException.class, () -> uniquePocList.remove(ALICE));
    }

    @Test
    public void remove_existingPoc_removesPoc() {
        uniquePocList.add(ALICE);
        uniquePocList.remove(ALICE);
        UniquePocList expectedUniquePocList = new UniquePocList();
        assertEquals(expectedUniquePocList, uniquePocList);
    }

    @Test
    public void setPocs_nullUniquePocList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePocList.setPocs((UniquePocList) null));
    }

    @Test
    public void setPocs_uniquePocList_replacesOwnListWithProvidedUniquePocList() {
        uniquePocList.add(ALICE);
        UniquePocList expectedUniquePocList = new UniquePocList();
        expectedUniquePocList.add(BOB);
        uniquePocList.setPocs(expectedUniquePocList);
        assertEquals(expectedUniquePocList, uniquePocList);
    }

    @Test
    public void setPocs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePocList.setPocs((List<Poc>) null));
    }

    @Test
    public void setPocs_list_replacesOwnListWithProvidedList() {
        uniquePocList.add(ALICE);
        List<Poc> pocList = Collections.singletonList(BOB);
        uniquePocList.setPocs(pocList);
        UniquePocList expectedUniquePocList = new UniquePocList();
        expectedUniquePocList.add(BOB);
        assertEquals(expectedUniquePocList, uniquePocList);
    }

    @Test
    public void setPocs_listWithDuplicatePocs_throwsDuplicatePocException() {
        List<Poc> listWithDuplicatePocs = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePocException.class, () -> uniquePocList.setPocs(listWithDuplicatePocs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniquePocList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toString_pocList_returnsCorrectRepresentation() {
        UniquePocList pocs = new UniquePocList();
        pocs.add(ALICE);
        pocs.add(BOB);

        PersonName aliceName = ALICE.getName();
        PersonName bobName = BOB.getName();
        assertEquals(pocs.toString(), "POCS: " + aliceName + ", " + bobName);
    }
}
