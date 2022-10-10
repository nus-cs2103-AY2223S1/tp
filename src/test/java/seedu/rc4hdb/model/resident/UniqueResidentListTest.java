package seedu.rc4hdb.model.resident;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_ROOM_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.resident.exceptions.DuplicateResidentException;
import seedu.rc4hdb.model.resident.exceptions.ResidentNotFoundException;
import seedu.rc4hdb.testutil.ResidentBuilder;

public class UniqueResidentListTest {

    private final UniqueResidentList uniqueResidentList = new UniqueResidentList();

    @Test
    public void contains_nullResident_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidentList.contains(null));
    }

    @Test
    public void contains_residentNotInList_returnsFalse() {
        assertFalse(uniqueResidentList.contains(ALICE));
    }

    @Test
    public void contains_residentInList_returnsTrue() {
        uniqueResidentList.add(ALICE);
        assertTrue(uniqueResidentList.contains(ALICE));
    }

    @Test
    public void contains_residentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueResidentList.add(ALICE);
        Resident editedAlice = new ResidentBuilder(ALICE).withRoom(VALID_ROOM_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueResidentList.contains(editedAlice));
    }

    @Test
    public void add_nullResident_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidentList.add(null));
    }

    @Test
    public void add_duplicateResident_throwsDuplicateResidentException() {
        uniqueResidentList.add(ALICE);
        assertThrows(DuplicateResidentException.class, () -> uniqueResidentList.add(ALICE));
    }

    @Test
    public void setResident_nullTargetResident_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidentList.setResident(null, ALICE));
    }

    @Test
    public void setResident_nullEditedResident_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidentList.setResident(ALICE, null));
    }

    @Test
    public void setResident_targetResidentNotInList_throwsResidentNotFoundException() {
        assertThrows(ResidentNotFoundException.class, () -> uniqueResidentList.setResident(ALICE, ALICE));
    }

    @Test
    public void setResident_editedResidentIsSameResident_success() {
        uniqueResidentList.add(ALICE);
        uniqueResidentList.setResident(ALICE, ALICE);
        UniqueResidentList expectedUniqueResidentList = new UniqueResidentList();
        expectedUniqueResidentList.add(ALICE);
        assertEquals(expectedUniqueResidentList, uniqueResidentList);
    }

    @Test
    public void setResident_editedResidentHasSameIdentity_success() {
        uniqueResidentList.add(ALICE);
        Resident editedAlice = new ResidentBuilder(ALICE).withRoom(VALID_ROOM_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueResidentList.setResident(ALICE, editedAlice);
        UniqueResidentList expectedUniqueResidentList = new UniqueResidentList();
        expectedUniqueResidentList.add(editedAlice);
        assertEquals(expectedUniqueResidentList, uniqueResidentList);
    }

    @Test
    public void setResident_editedResidentHasDifferentIdentity_success() {
        uniqueResidentList.add(ALICE);
        uniqueResidentList.setResident(ALICE, BOB);
        UniqueResidentList expectedUniqueResidentList = new UniqueResidentList();
        expectedUniqueResidentList.add(BOB);
        assertEquals(expectedUniqueResidentList, uniqueResidentList);
    }

    @Test
    public void setResident_editedResidentHasNonUniqueIdentity_throwsDuplicateResidentException() {
        uniqueResidentList.add(ALICE);
        uniqueResidentList.add(BOB);
        assertThrows(DuplicateResidentException.class, () -> uniqueResidentList.setResident(ALICE, BOB));
    }

    @Test
    public void remove_nullResident_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidentList.remove(null));
    }

    @Test
    public void remove_residentDoesNotExist_throwsResidentNotFoundException() {
        assertThrows(ResidentNotFoundException.class, () -> uniqueResidentList.remove(ALICE));
    }

    @Test
    public void remove_existingResident_removesResident() {
        uniqueResidentList.add(ALICE);
        uniqueResidentList.remove(ALICE);
        UniqueResidentList expectedUniqueResidentList = new UniqueResidentList();
        assertEquals(expectedUniqueResidentList, uniqueResidentList);
    }

    @Test
    public void setResidents_nullUniqueResidentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidentList.setResidents((UniqueResidentList) null));
    }

    @Test
    public void setResidents_uniqueResidentList_replacesOwnListWithProvidedUniqueResidentList() {
        uniqueResidentList.add(ALICE);
        UniqueResidentList expectedUniqueResidentList = new UniqueResidentList();
        expectedUniqueResidentList.add(BOB);
        uniqueResidentList.setResidents(expectedUniqueResidentList);
        assertEquals(expectedUniqueResidentList, uniqueResidentList);
    }

    @Test
    public void setResidents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidentList.setResidents((List<Resident>) null));
    }

    @Test
    public void setResidents_list_replacesOwnListWithProvidedList() {
        uniqueResidentList.add(ALICE);
        List<Resident> residentList = Collections.singletonList(BOB);
        uniqueResidentList.setResidents(residentList);
        UniqueResidentList expectedUniqueResidentList = new UniqueResidentList();
        expectedUniqueResidentList.add(BOB);
        assertEquals(expectedUniqueResidentList, uniqueResidentList);
    }

    @Test
    public void setResidents_listWithDuplicateResidents_throwsDuplicateResidentException() {
        List<Resident> listWithDuplicateResidents = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateResidentException.class, () ->
                uniqueResidentList.setResidents(listWithDuplicateResidents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueResidentList.asUnmodifiableObservableList().remove(0));
    }

}
