package seedu.address.model.commission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ANIMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DOG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCommissions.CAT;
import static seedu.address.testutil.TypicalCommissions.DOG;

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
        assertFalse(uniqueCommissionList.contains(CAT));
    }

    @Test
    public void contains_commissionInList_returnsTrue() {
        uniqueCommissionList.add(CAT);
        assertTrue(uniqueCommissionList.contains(CAT));
    }

    @Test
    public void contains_commissionWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCommissionList.add(CAT);
        Commission editedCat = new CommissionBuilder(CAT).withDescription(VALID_DESCRIPTION_DOG)
                .withTags(VALID_TAG_FOOD)
                .build();
        assertTrue(uniqueCommissionList.contains(editedCat));
    }

    @Test
    public void add_nullCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommissionList.add(null));
    }

    @Test
    public void add_duplicateCommission_throwsDuplicateCommissionException() {
        uniqueCommissionList.add(CAT);
        assertThrows(DuplicateCommissionException.class, () -> uniqueCommissionList.add(CAT));
    }

    @Test
    public void setCommission_nullTargetCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommissionList.setCommission(null, CAT));
    }

    @Test
    public void setCommission_nullEditedCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommissionList.setCommission(CAT, null));
    }

    @Test
    public void setCommission_targetCommissionNotInList_throwsCommissionNotFoundException() {
        assertThrows(CommissionNotFoundException.class, () -> uniqueCommissionList.setCommission(CAT, CAT));
    }

    @Test
    public void setCommission_editedCommissionIsSameCommission_success() {
        uniqueCommissionList.add(CAT);
        uniqueCommissionList.setCommission(CAT, CAT);
        UniqueCommissionList expectedUniqueCommissionList = new UniqueCommissionList();
        expectedUniqueCommissionList.add(CAT);
        assertEquals(expectedUniqueCommissionList, uniqueCommissionList);
    }

    @Test
    public void setCommission_editedCommissionHasSameIdentity_success() {
        uniqueCommissionList.add(CAT);
        Commission editedDog = new CommissionBuilder(CAT).withTitle(VALID_TITLE_DOG)
                .withTags(VALID_TAG_ANIMAL).build();
        uniqueCommissionList.setCommission(CAT, editedDog);
        UniqueCommissionList expectedUniqueCommissionList = new UniqueCommissionList();
        expectedUniqueCommissionList.add(editedDog);
        assertEquals(expectedUniqueCommissionList, uniqueCommissionList);
    }

    @Test
    public void setCommission_editedCommissionHasDifferentIdentity_success() {
        uniqueCommissionList.add(CAT);
        uniqueCommissionList.setCommission(CAT, DOG);
        UniqueCommissionList expectedUniqueCommissionList = new UniqueCommissionList();
        expectedUniqueCommissionList.add(DOG);
        assertEquals(expectedUniqueCommissionList, uniqueCommissionList);
    }

    @Test
    public void setCommission_editedCommissionHasNonUniqueIdentity_throwsDuplicateCommissionException() {
        uniqueCommissionList.add(CAT);
        uniqueCommissionList.add(DOG);
        assertThrows(DuplicateCommissionException.class, () -> uniqueCommissionList.setCommission(CAT, DOG));
    }

    @Test
    public void remove_nullCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommissionList.remove(null));
    }

    @Test
    public void remove_customerDoesNotExist_throwsCommissionNotFoundException() {
        assertThrows(CommissionNotFoundException.class, () -> uniqueCommissionList.remove(CAT));
    }

    @Test
    public void remove_existingCommission_removesCommission() {
        uniqueCommissionList.add(CAT);
        uniqueCommissionList.remove(CAT);
        UniqueCommissionList expectedUniqueCommissionList = new UniqueCommissionList();
        assertEquals(expectedUniqueCommissionList, uniqueCommissionList);
    }

    @Test
    public void setCommissions_nullUniqueCommissionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueCommissionList.setCommissions((UniqueCommissionList) null));
    }

    @Test
    public void setCommissions_uniqueCommissionList_replacesOwnListWithProvidedUniqueCommissionList() {
        uniqueCommissionList.add(CAT);
        UniqueCommissionList expectedUniqueCommissionList = new UniqueCommissionList();
        expectedUniqueCommissionList.add(DOG);
        uniqueCommissionList.setCommissions(expectedUniqueCommissionList);
        assertEquals(expectedUniqueCommissionList, uniqueCommissionList);
    }

    @Test
    public void setCommissions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommissionList.setCommissions((List<Commission>) null));
    }

    @Test
    public void setCommissions_list_replacesOwnListWithProvidedList() {
        uniqueCommissionList.add(CAT);
        List<Commission> customerList = Collections.singletonList(DOG);
        uniqueCommissionList.setCommissions(customerList);
        UniqueCommissionList expectedUniqueCommissionList = new UniqueCommissionList();
        expectedUniqueCommissionList.add(DOG);
        assertEquals(expectedUniqueCommissionList, uniqueCommissionList);
    }

    @Test
    public void setCommissions_listWithDuplicateCommissions_throwsDuplicateCommissionException() {
        List<Commission> listWithDuplicateCommissions = Arrays.asList(CAT, CAT);
        assertThrows(DuplicateCommissionException.class, () -> uniqueCommissionList.setCommissions(
                listWithDuplicateCommissions));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueCommissionList.asUnmodifiableObservableList().remove(0));
    }
}
