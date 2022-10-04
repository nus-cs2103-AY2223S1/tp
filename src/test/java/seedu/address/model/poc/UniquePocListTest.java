package seedu.address.model.poc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
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
    public void contains_companyNotInList_returnsFalse() {
        assertFalse(uniquePocList.contains(ALICE));
    }

    @Test
    public void contains_companyInList_returnsTrue() {
        uniquePocList.add(ALICE);
        assertTrue(uniquePocList.contains(ALICE));
    }

    @Test
    public void contains_companyWithSameIdentityFieldsInList_returnsTrue() {
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
        assertThrows(NullPointerException.class, () -> uniquePocList.setPoc(null, ALICE));
    }

    @Test
    public void setPoc_nullEditedPoc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePocList.setPoc(ALICE, null));
    }

    @Test
    public void setPoc_targetPocNotInList_throwsPocNotFoundException() {
        assertThrows(PocNotFoundException.class, () -> uniquePocList.setPoc(ALICE, ALICE));
    }

    @Test
    public void setPoc_editedPocIsSamePoc_success() {
        uniquePocList.add(ALICE);
        uniquePocList.setPoc(ALICE, ALICE);
        UniquePocList expectedUniquePocList = new UniquePocList();
        expectedUniquePocList.add(ALICE);
        assertEquals(expectedUniquePocList, uniquePocList);
    }

    @Test
    public void setPoc_editedPocHasSameIdentity_success() {
        uniquePocList.add(ALICE);
        Poc editedAlice = new PocBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        uniquePocList.setPoc(ALICE, editedAlice);
        UniquePocList expectedUniquePocList = new UniquePocList();
        expectedUniquePocList.add(editedAlice);
        assertEquals(expectedUniquePocList, uniquePocList);
    }

    @Test
    public void setPoc_editedPocHasDifferentIdentity_success() {
        uniquePocList.add(ALICE);
        uniquePocList.setPoc(ALICE, BOB);
        UniquePocList expectedUniquePocList = new UniquePocList();
        expectedUniquePocList.add(BOB);
        assertEquals(expectedUniquePocList, uniquePocList);
    }

    @Test
    public void setPoc_editedPocHasNonUniqueIdentity_throwsDuplicatePocException() {
        uniquePocList.add(ALICE);
        uniquePocList.add(BOB);
        assertThrows(DuplicatePocException.class, () -> uniquePocList.setPoc(ALICE, BOB));
    }

    @Test
    public void remove_nullPoc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePocList.remove(null));
    }

    @Test
    public void remove_companyDoesNotExist_throwsPocNotFoundException() {
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
    public void setCompanies_nullUniquePocList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePocList.setPocs((UniquePocList) null));
    }

    @Test
    public void setCompanies_uniquePocList_replacesOwnListWithProvidedUniquePocList() {
        uniquePocList.add(ALICE);
        UniquePocList expectedUniquePocList = new UniquePocList();
        expectedUniquePocList.add(BOB);
        uniquePocList.setPocs(expectedUniquePocList);
        assertEquals(expectedUniquePocList, uniquePocList);
    }

    @Test
    public void setCompanies_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePocList.setPocs((List<Poc>) null));
    }

    @Test
    public void setCompanies_list_replacesOwnListWithProvidedList() {
        uniquePocList.add(ALICE);
        List<Poc> companyList = Collections.singletonList(BOB);
        uniquePocList.setPocs(companyList);
        UniquePocList expectedUniquePocList = new UniquePocList();
        expectedUniquePocList.add(BOB);
        assertEquals(expectedUniquePocList, uniquePocList);
    }

    @Test
    public void setCompanies_listWithDuplicateCompanies_throwsDuplicatePocException() {
        List<Poc> listWithDuplicateCompanies = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePocException.class, () -> uniquePocList.setPocs(listWithDuplicateCompanies));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniquePocList.asUnmodifiableObservableList().remove(0));
    }
}
