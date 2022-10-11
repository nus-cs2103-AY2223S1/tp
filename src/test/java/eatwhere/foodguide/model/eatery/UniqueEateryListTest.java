package eatwhere.foodguide.model.eatery;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.model.eatery.exceptions.DuplicateEateryException;
import eatwhere.foodguide.model.eatery.exceptions.EateryNotFoundException;
import eatwhere.foodguide.testutil.Assert;
import eatwhere.foodguide.testutil.EateryBuilder;
import eatwhere.foodguide.testutil.TypicalEateries;

public class UniqueEateryListTest {

    private final UniqueEateryList uniqueEateryList = new UniqueEateryList();

    @Test
    public void contains_nullEatery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueEateryList.contains(null));
    }

    @Test
    public void contains_eateryNotInList_returnsFalse() {
        assertFalse(uniqueEateryList.contains(TypicalEateries.ALICE));
    }

    @Test
    public void contains_eateryInList_returnsTrue() {
        uniqueEateryList.add(TypicalEateries.ALICE);
        assertTrue(uniqueEateryList.contains(TypicalEateries.ALICE));
    }

    @Test
    public void contains_eateryWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEateryList.add(TypicalEateries.ALICE);
        Eatery editedAlice = new EateryBuilder(TypicalEateries.ALICE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueEateryList.contains(editedAlice));
    }

    @Test
    public void add_nullEatery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueEateryList.add(null));
    }

    @Test
    public void add_duplicateEatery_throwsDuplicateEateryException() {
        uniqueEateryList.add(TypicalEateries.ALICE);
        Assert.assertThrows(DuplicateEateryException.class, () -> uniqueEateryList.add(TypicalEateries.ALICE));
    }

    @Test
    public void setEatery_nullTargetEatery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, ()
                -> uniqueEateryList.setEatery(null, TypicalEateries.ALICE));
    }

    @Test
    public void setEatery_nullEditedEatery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, ()
                -> uniqueEateryList.setEatery(TypicalEateries.ALICE, null));
    }

    @Test
    public void setEatery_targetEateryNotInList_throwsEateryNotFoundException() {
        Assert.assertThrows(EateryNotFoundException.class, ()
                -> uniqueEateryList.setEatery(TypicalEateries.ALICE, TypicalEateries.ALICE));
    }

    @Test
    public void setEatery_editedEateryIsSamePerson_success() {
        uniqueEateryList.add(TypicalEateries.ALICE);
        uniqueEateryList.setEatery(TypicalEateries.ALICE, TypicalEateries.ALICE);
        UniqueEateryList expectedUniqueEateryList = new UniqueEateryList();
        expectedUniqueEateryList.add(TypicalEateries.ALICE);
        assertEquals(expectedUniqueEateryList, uniqueEateryList);
    }

    @Test
    public void setEatery_editedEateryHasSameIdentity_success() {
        uniqueEateryList.add(TypicalEateries.ALICE);
        Eatery editedAlice = new EateryBuilder(TypicalEateries.ALICE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueEateryList.setEatery(TypicalEateries.ALICE, editedAlice);
        UniqueEateryList expectedUniqueEateryList = new UniqueEateryList();
        expectedUniqueEateryList.add(editedAlice);
        assertEquals(expectedUniqueEateryList, uniqueEateryList);
    }

    @Test
    public void setEatery_editedEateryHasDifferentIdentity_success() {
        uniqueEateryList.add(TypicalEateries.ALICE);
        uniqueEateryList.setEatery(TypicalEateries.ALICE, TypicalEateries.BOB);
        UniqueEateryList expectedUniqueEateryList = new UniqueEateryList();
        expectedUniqueEateryList.add(TypicalEateries.BOB);
        assertEquals(expectedUniqueEateryList, uniqueEateryList);
    }

    @Test
    public void setEatery_editedEateryHasNonUniqueIdentity_throwsDuplicateEateryException() {
        uniqueEateryList.add(TypicalEateries.ALICE);
        uniqueEateryList.add(TypicalEateries.BOB);
        Assert.assertThrows(DuplicateEateryException.class, ()
                -> uniqueEateryList.setEatery(TypicalEateries.ALICE, TypicalEateries.BOB));
    }

    @Test
    public void remove_nullEatery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueEateryList.remove(null));
    }

    @Test
    public void remove_eateryDoesNotExist_throwsEateryNotFoundException() {
        Assert.assertThrows(EateryNotFoundException.class, () -> uniqueEateryList.remove(TypicalEateries.ALICE));
    }

    @Test
    public void remove_existingEatery_removesEatery() {
        uniqueEateryList.add(TypicalEateries.ALICE);
        uniqueEateryList.remove(TypicalEateries.ALICE);
        UniqueEateryList expectedUniqueEateryList = new UniqueEateryList();
        assertEquals(expectedUniqueEateryList, uniqueEateryList);
    }

    @Test
    public void setEateries_nullUniqueEateryList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueEateryList.setEateries((UniqueEateryList) null));
    }

    @Test
    public void setEateries_uniqueEateryList_replacesOwnListWithProvidedUniqueEateryList() {
        uniqueEateryList.add(TypicalEateries.ALICE);
        UniqueEateryList expectedUniqueEateryList = new UniqueEateryList();
        expectedUniqueEateryList.add(TypicalEateries.BOB);
        uniqueEateryList.setEateries(expectedUniqueEateryList);
        assertEquals(expectedUniqueEateryList, uniqueEateryList);
    }

    @Test
    public void setEateries_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueEateryList.setEateries((List<Eatery>) null));
    }

    @Test
    public void setEateries_list_replacesOwnListWithProvidedList() {
        uniqueEateryList.add(TypicalEateries.ALICE);
        List<Eatery> eateryList = Collections.singletonList(TypicalEateries.BOB);
        uniqueEateryList.setEateries(eateryList);
        UniqueEateryList expectedUniqueEateryList = new UniqueEateryList();
        expectedUniqueEateryList.add(TypicalEateries.BOB);
        assertEquals(expectedUniqueEateryList, uniqueEateryList);
    }

    @Test
    public void setEateries_listWithDuplicateEateries_throwsDuplicateEateryException() {
        List<Eatery> listWithDuplicateEateries = Arrays.asList(TypicalEateries.ALICE, TypicalEateries.ALICE);
        Assert.assertThrows(DuplicateEateryException.class, ()
                -> uniqueEateryList.setEateries(listWithDuplicateEateries));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEateryList.asUnmodifiableObservableList().remove(0));
    }
}
