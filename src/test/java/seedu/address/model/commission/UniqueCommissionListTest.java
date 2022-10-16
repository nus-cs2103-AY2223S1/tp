package seedu.address.model.commission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ANIMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DOG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCommissions.ALICE_CAT;
import static seedu.address.testutil.TypicalCommissions.BENSON_DOG;
import static seedu.address.testutil.TypicalCommissions.CARL_ELEPHANT;
import static seedu.address.testutil.TypicalCustomers.ALICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.commission.exceptions.CommissionNotFoundException;
import seedu.address.model.commission.exceptions.DuplicateCommissionException;
import seedu.address.testutil.CommissionBuilder;

public class UniqueCommissionListTest {
    private final UniqueCommissionList uniqueCommissionList = new UniqueCommissionList();

    @Test
    public void contains_nullCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommissionList.contains(null));
    }

    @Test
    public void contains_commissionNotInList_returnsFalse() {
        assertFalse(uniqueCommissionList.contains(ALICE_CAT));
    }

    @Test
    public void contains_commissionInList_returnsTrue() {
        uniqueCommissionList.add(ALICE_CAT);
        assertTrue(uniqueCommissionList.contains(ALICE_CAT));
    }

    @Test
    public void contains_commissionWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCommissionList.add(ALICE_CAT);
        Commission editedCat = new CommissionBuilder(ALICE_CAT).withDescription(VALID_DESCRIPTION_DOG)
            .withTags(VALID_TAG_FOOD)
            .build(ALICE_CAT.getCustomer());
        assertTrue(uniqueCommissionList.contains(editedCat));
    }

    @Test
    public void add_nullCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommissionList.add(null));
    }

    @Test
    public void add_duplicateCommission_throwsDuplicateCommissionException() {
        uniqueCommissionList.add(ALICE_CAT);
        assertThrows(DuplicateCommissionException.class, () -> uniqueCommissionList.add(ALICE_CAT));
    }

    @Test
    public void setCommission_nullTargetCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommissionList.setCommission(null, ALICE_CAT));
    }

    @Test
    public void setCommission_nullEditedCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommissionList.setCommission(ALICE_CAT, null));
    }

    @Test
    public void setCommission_targetCommissionNotInList_throwsCommissionNotFoundException() {
        assertThrows(CommissionNotFoundException.class, () -> uniqueCommissionList.setCommission(ALICE_CAT, ALICE_CAT));
    }

    @Test
    public void setCommission_editedCommissionIsSameCommission_success() {
        uniqueCommissionList.add(ALICE_CAT);
        uniqueCommissionList.setCommission(ALICE_CAT, ALICE_CAT);
        UniqueCommissionList expectedUniqueCommissionList = new UniqueCommissionList();
        expectedUniqueCommissionList.add(ALICE_CAT);
        assertEquals(expectedUniqueCommissionList, uniqueCommissionList);
    }

    @Test
    public void setCommission_editedCommissionHasSameIdentity_success() {
        uniqueCommissionList.add(ALICE_CAT);
        Commission editedDog = new CommissionBuilder(ALICE_CAT).withTitle(VALID_TITLE_DOG)
                .withTags(VALID_TAG_ANIMAL).build(ALICE);
        uniqueCommissionList.setCommission(ALICE_CAT, editedDog);
        UniqueCommissionList expectedUniqueCommissionList = new UniqueCommissionList();
        expectedUniqueCommissionList.add(editedDog);
        assertEquals(expectedUniqueCommissionList, uniqueCommissionList);
    }

    @Test
    public void setCommission_editedCommissionHasDifferentIdentity_success() {
        uniqueCommissionList.add(ALICE_CAT);
        uniqueCommissionList.setCommission(ALICE_CAT, BENSON_DOG);
        UniqueCommissionList expectedUniqueCommissionList = new UniqueCommissionList();
        expectedUniqueCommissionList.add(BENSON_DOG);
        assertEquals(expectedUniqueCommissionList, uniqueCommissionList);
    }

    @Test
    public void setCommission_editedCommissionHasNonUniqueIdentity_throwsDuplicateCommissionException() {
        uniqueCommissionList.add(ALICE_CAT);
        uniqueCommissionList.add(BENSON_DOG);
        assertThrows(DuplicateCommissionException.class, () ->
                uniqueCommissionList.setCommission(ALICE_CAT, BENSON_DOG));
    }

    @Test
    public void remove_nullCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommissionList.remove(null));
    }

    @Test
    public void remove_customerDoesNotExist_throwsCommissionNotFoundException() {
        assertThrows(CommissionNotFoundException.class, () -> uniqueCommissionList.remove(ALICE_CAT));
    }

    @Test
    public void remove_existingCommission_removesCommission() {
        uniqueCommissionList.add(ALICE_CAT);
        uniqueCommissionList.remove(ALICE_CAT);
        UniqueCommissionList expectedUniqueCommissionList = new UniqueCommissionList();
        assertEquals(expectedUniqueCommissionList, uniqueCommissionList);
    }

    @Test
    public void getSize_check() {
        uniqueCommissionList.add(ALICE_CAT);
        uniqueCommissionList.add(BENSON_DOG);
        uniqueCommissionList.add(CARL_ELEPHANT);
        assertEquals(uniqueCommissionList.getSize(), 3);
    }

    @Test
    public void getActiveSize_check() {
        uniqueCommissionList.add(ALICE_CAT);
        uniqueCommissionList.add(BENSON_DOG);
        uniqueCommissionList.add(CARL_ELEPHANT);
        assertEquals(uniqueCommissionList.getActiveSize(), 1);
    }

    @Test
    public void getLastDate_check() {
        assertEquals(uniqueCommissionList.getLastDate(), null);
        uniqueCommissionList.add(ALICE_CAT);
        uniqueCommissionList.add(BENSON_DOG);
        uniqueCommissionList.add(CARL_ELEPHANT);
        assertEquals(uniqueCommissionList.getLastDate(), BENSON_DOG.getDeadline().deadline);
    }

    @Test
    public void setCommissions_nullUniqueCommissionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueCommissionList.setCommissions((UniqueCommissionList) null));
    }

    @Test
    public void setCommissions_uniqueCommissionList_replacesOwnListWithProvidedUniqueCommissionList() {
        uniqueCommissionList.add(ALICE_CAT);
        UniqueCommissionList expectedUniqueCommissionList = new UniqueCommissionList();
        expectedUniqueCommissionList.add(BENSON_DOG);
        uniqueCommissionList.setCommissions(expectedUniqueCommissionList);
        assertEquals(expectedUniqueCommissionList, uniqueCommissionList);
    }

    @Test
    public void setCommissions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommissionList.setCommissions((List<Commission>) null));
    }

    @Test
    public void setCommissions_list_replacesOwnListWithProvidedList() {
        uniqueCommissionList.add(ALICE_CAT);
        List<Commission> customerList = Collections.singletonList(BENSON_DOG);
        uniqueCommissionList.setCommissions(customerList);
        UniqueCommissionList expectedUniqueCommissionList = new UniqueCommissionList();
        expectedUniqueCommissionList.add(BENSON_DOG);
        assertEquals(expectedUniqueCommissionList, uniqueCommissionList);
    }

    @Test
    public void setCommissions_listWithDuplicateCommissions_throwsDuplicateCommissionException() {
        List<Commission> listWithDuplicateCommissions = Arrays.asList(ALICE_CAT, ALICE_CAT);
        assertThrows(DuplicateCommissionException.class, () -> uniqueCommissionList.setCommissions(
                listWithDuplicateCommissions));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueCommissionList.asUnmodifiableObservableList().remove(0));
    }
}
