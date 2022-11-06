package seedu.address.model.teammate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeammates.ALICE;
import static seedu.address.testutil.TypicalTeammates.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.teammate.exceptions.DuplicateTeammateException;
import seedu.address.model.teammate.exceptions.TeammateNotFoundException;
import seedu.address.testutil.TeammateBuilder;

public class UniqueTeammateListTest {

    private final UniqueTeammateList uniqueTeammateList = new UniqueTeammateList();

    @Test
    public void contains_nullTeammate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeammateList.contains(null));
    }

    @Test
    public void contains_teammateNotInList_returnsFalse() {
        assertFalse(uniqueTeammateList.contains(ALICE));
    }

    @Test
    public void contains_teammateInList_returnsTrue() {
        uniqueTeammateList.add(ALICE);
        assertTrue(uniqueTeammateList.contains(ALICE));
    }

    @Test
    public void contains_teammateWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTeammateList.add(ALICE);
        Teammate editedAlice = new TeammateBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueTeammateList.contains(editedAlice));
    }

    @Test
    public void add_nullTeammate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeammateList.add(null));
    }

    @Test
    public void add_duplicateTeammate_throwsDuplicateTeammateException() {
        uniqueTeammateList.add(ALICE);
        assertThrows(DuplicateTeammateException.class, () -> uniqueTeammateList.add(ALICE));
    }

    @Test
    public void setTeammate_nullTargetTeammate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeammateList.setTeammate(null, ALICE));
    }

    @Test
    public void setTeammate_nullEditedTeammate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeammateList.setTeammate(ALICE, null));
    }

    @Test
    public void setTeammate_targetTeammateNotInList_throwsTeammateNotFoundException() {
        assertThrows(TeammateNotFoundException.class, () -> uniqueTeammateList.setTeammate(ALICE, ALICE));
    }

    @Test
    public void setTeammate_editedTeammateIsSameTeammate_success() {
        uniqueTeammateList.add(ALICE);
        uniqueTeammateList.setTeammate(ALICE, ALICE);
        UniqueTeammateList expectedUniqueTeammateList = new UniqueTeammateList();
        expectedUniqueTeammateList.add(ALICE);
        assertEquals(expectedUniqueTeammateList, uniqueTeammateList);
    }

    @Test
    public void setTeammate_editedTeammateHasSameIdentity_success() {
        uniqueTeammateList.add(ALICE);
        Teammate editedAlice = new TeammateBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueTeammateList.setTeammate(ALICE, editedAlice);
        UniqueTeammateList expectedUniqueTeammateList = new UniqueTeammateList();
        expectedUniqueTeammateList.add(editedAlice);
        assertEquals(expectedUniqueTeammateList, uniqueTeammateList);
    }

    @Test
    public void setTeammate_editedTeammateHasDifferentIdentity_success() {
        uniqueTeammateList.add(ALICE);
        uniqueTeammateList.setTeammate(ALICE, BOB);
        UniqueTeammateList expectedUniqueTeammateList = new UniqueTeammateList();
        expectedUniqueTeammateList.add(BOB);
        assertEquals(expectedUniqueTeammateList, uniqueTeammateList);
    }

    @Test
    public void setTeammate_editedTeammateHasNonUniqueIdentity_throwsDuplicateTeammateException() {
        uniqueTeammateList.add(ALICE);
        uniqueTeammateList.add(BOB);
        assertThrows(DuplicateTeammateException.class, () -> uniqueTeammateList.setTeammate(ALICE, BOB));
    }

    @Test
    public void remove_nullTeammate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeammateList.remove(null));
    }

    @Test
    public void remove_teammateDoesNotExist_throwsTeammateNotFoundException() {
        assertThrows(TeammateNotFoundException.class, () -> uniqueTeammateList.remove(ALICE));
    }

    @Test
    public void remove_existingTeammate_removesTeammate() {
        uniqueTeammateList.add(ALICE);
        uniqueTeammateList.remove(ALICE);
        UniqueTeammateList expectedUniqueTeammateList = new UniqueTeammateList();
        assertEquals(expectedUniqueTeammateList, uniqueTeammateList);
    }

    @Test
    public void setTeammates_nullUniqueTeammateList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeammateList.setTeammates((UniqueTeammateList) null));
    }

    @Test
    public void setTeammates_uniqueTeammateList_replacesOwnListWithProvidedUniqueTeammateList() {
        uniqueTeammateList.add(ALICE);
        UniqueTeammateList expectedUniqueTeammateList = new UniqueTeammateList();
        expectedUniqueTeammateList.add(BOB);
        uniqueTeammateList.setTeammates(expectedUniqueTeammateList);
        assertEquals(expectedUniqueTeammateList, uniqueTeammateList);
    }

    @Test
    public void setTeammates_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeammateList.setTeammates((List<Teammate>) null));
    }

    @Test
    public void setTeammates_list_replacesOwnListWithProvidedList() {
        uniqueTeammateList.add(ALICE);
        List<Teammate> teammateList = Collections.singletonList(BOB);
        uniqueTeammateList.setTeammates(teammateList);
        UniqueTeammateList expectedUniqueTeammateList = new UniqueTeammateList();
        expectedUniqueTeammateList.add(BOB);
        assertEquals(expectedUniqueTeammateList, uniqueTeammateList);
    }

    @Test
    public void setTeammates_listWithDuplicateTeammates_throwsDuplicateTeammateException() {
        List<Teammate> listWithDuplicateTeammates = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateTeammateException.class, () ->
                uniqueTeammateList.setTeammates(listWithDuplicateTeammates));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTeammateList.asUnmodifiableObservableList().remove(0));
    }
}
