package foodwhere.model.stall;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import foodwhere.logic.commands.CommandTestUtil;
import foodwhere.model.stall.exceptions.DuplicateStallException;
import foodwhere.model.stall.exceptions.StallNotFoundException;
import foodwhere.testutil.StallBuilder;
import foodwhere.testutil.TypicalStalls;

public class UniqueStallListTest {

    private final UniqueStallList uniqueStallList = new UniqueStallList();

    @Test
    public void contains_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.contains(null));
    }

    @Test
    public void contains_stallNotInList_returnsFalse() {
        assertFalse(uniqueStallList.contains(TypicalStalls.ALICE));
    }

    @Test
    public void contains_stallInList_returnsTrue() {
        uniqueStallList.add(TypicalStalls.ALICE);
        assertTrue(uniqueStallList.contains(TypicalStalls.ALICE));
    }

    @Test
    public void contains_stallWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStallList.add(TypicalStalls.ALICE);
        Stall editedAlice =
                new StallBuilder(TypicalStalls.ALICE)
                        .withAddress(CommandTestUtil.VALID_ADDRESS_BOB)
                        .withTags(CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueStallList.contains(editedAlice));
    }

    @Test
    public void add_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.add(null));
    }

    @Test
    public void add_duplicateStall_throwsDuplicateStallException() {
        uniqueStallList.add(TypicalStalls.ALICE);
        assertThrows(DuplicateStallException.class, () -> uniqueStallList.add(TypicalStalls.ALICE));
    }

    @Test
    public void setStall_nullTargetStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStall(null, TypicalStalls.ALICE));
    }

    @Test
    public void setStall_nullEditedStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueStallList.setStall(TypicalStalls.ALICE, null));
    }

    @Test
    public void setStall_targetStallNotInList_throwsStallNotFoundException() {
        assertThrows(StallNotFoundException.class, () ->
                uniqueStallList.setStall(TypicalStalls.ALICE, TypicalStalls.ALICE));
    }

    @Test
    public void setStall_editedStallIsSameStall_success() {
        uniqueStallList.add(TypicalStalls.ALICE);
        uniqueStallList.setStall(TypicalStalls.ALICE, TypicalStalls.ALICE);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(TypicalStalls.ALICE);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStall_editedStallHasSameIdentity_success() {
        uniqueStallList.add(TypicalStalls.ALICE);
        Stall editedAlice = new StallBuilder(TypicalStalls.ALICE)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        uniqueStallList.setStall(TypicalStalls.ALICE, editedAlice);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(editedAlice);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStall_editedStallHasDifferentIdentity_success() {
        uniqueStallList.add(TypicalStalls.ALICE);
        uniqueStallList.setStall(TypicalStalls.ALICE, TypicalStalls.BOB);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(TypicalStalls.BOB);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStall_editedStallHasNonUniqueIdentity_throwsDuplicateStallException() {
        uniqueStallList.add(TypicalStalls.ALICE);
        uniqueStallList.add(TypicalStalls.BOB);
        assertThrows(DuplicateStallException.class, () ->
                uniqueStallList.setStall(TypicalStalls.ALICE, TypicalStalls.BOB));
    }

    @Test
    public void remove_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.remove(null));
    }

    @Test
    public void remove_stallDoesNotExist_throwsStallNotFoundException() {
        assertThrows(StallNotFoundException.class, () -> uniqueStallList.remove(TypicalStalls.ALICE));
    }

    @Test
    public void remove_existingStall_removesStall() {
        uniqueStallList.add(TypicalStalls.ALICE);
        uniqueStallList.remove(TypicalStalls.ALICE);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStalls_nullUniqueStallList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStalls((UniqueStallList) null));
    }

    @Test
    public void setStalls_uniqueStallList_replacesOwnListWithProvidedUniqueStallList() {
        uniqueStallList.add(TypicalStalls.ALICE);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(TypicalStalls.BOB);
        uniqueStallList.setStalls(expectedUniqueStallList);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStalls_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStalls((List<Stall>) null));
    }

    @Test
    public void setStalls_list_replacesOwnListWithProvidedList() {
        uniqueStallList.add(TypicalStalls.ALICE);
        List<Stall> stallList = Collections.singletonList(TypicalStalls.BOB);
        uniqueStallList.setStalls(stallList);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(TypicalStalls.BOB);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStalls_listWithDuplicateStalls_throwsDuplicateStallException() {
        List<Stall> listWithDuplicateStalls = Arrays.asList(TypicalStalls.ALICE, TypicalStalls.ALICE);
        assertThrows(DuplicateStallException.class, () -> uniqueStallList.setStalls(listWithDuplicateStalls));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueStallList.asUnmodifiableObservableList().remove(0));
    }
}
