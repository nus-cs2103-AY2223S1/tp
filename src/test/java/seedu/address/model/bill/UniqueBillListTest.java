package seedu.address.model.bill;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBills.BILL_1;
import static seedu.address.testutil.TypicalBills.BILL_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.bill.exceptions.BillNotFoundException;
import seedu.address.model.bill.exceptions.DuplicateBillException;
import seedu.address.testutil.BillBuilder;

public class UniqueBillListTest {

    private final UniqueBillList uniqueBillList = new UniqueBillList();

    @Test
    public void contains_nullBill_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBillList.contains((Bill) null));
    }

    @Test
    public void containsBill_billNotInList_returnsFalse() {
        assertFalse(uniqueBillList.contains(BILL_1));
    }

    @Test
    public void containsBill_billInList_returnsTrue() {
        uniqueBillList.add(BILL_1);
        assertTrue(uniqueBillList.contains(BILL_1));
    }

    @Test
    public void contains_billWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBillList.add(BILL_1);
        Bill editedBill1 = new BillBuilder(BILL_1)
                .build();
        assertTrue(uniqueBillList.contains(editedBill1));
    }

    @Test
    public void add_nullBill_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBillList.add(null));
    }

    @Test
    public void add_duplicateBill_throwsDuplicateBillException() {
        uniqueBillList.add(BILL_1);
        assertThrows(DuplicateBillException.class, () -> uniqueBillList.add(BILL_1));
    }

    @Test
    public void setBill_nullTargetBill_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBillList.setBill(null, BILL_1));
    }

    @Test
    public void setBill_nullEditedBill_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBillList.setBill(BILL_1, null));
    }

    @Test
    public void setBill_targetBillNotInList_throwsBillNotFoundException() {
        assertThrows(BillNotFoundException.class, () -> uniqueBillList.setBill(BILL_1, BILL_1));
    }

    @Test
    public void setBill_editedBillIsSameBill_success() {
        uniqueBillList.add(BILL_1);
        uniqueBillList.setBill(BILL_1, BILL_1);
        UniqueBillList expectedUniqueBillList = new UniqueBillList();
        expectedUniqueBillList.add(BILL_1);
        assertEquals(expectedUniqueBillList, uniqueBillList);
    }

    @Test
    public void setBill_editedBillHasSameIdentity_success() {
        uniqueBillList.add(BILL_1);
        Bill editedBill1 = new BillBuilder(BILL_1)
                .withAmount(BILL_2.getAmount().toString())
                .withPaymentStatus(BILL_2.getPaymentStatus().toString())
                .withBillDate(BILL_2.getBillDate().toString())
                .build();
        uniqueBillList.setBill(BILL_1, editedBill1);
        UniqueBillList expectedUniqueBillList = new UniqueBillList();
        expectedUniqueBillList.add(editedBill1);
        assertEquals(expectedUniqueBillList, uniqueBillList);
    }

    @Test
    public void setBill_editedBillHasDifferentIdentity_success() {
        uniqueBillList.add(BILL_1);
        uniqueBillList.setBill(BILL_1, BILL_2);
        UniqueBillList expectedUniqueBillList = new UniqueBillList();
        expectedUniqueBillList.add(BILL_2);
        assertEquals(expectedUniqueBillList, uniqueBillList);
    }

    @Test
    public void setBill_editedBillHasNonUniqueIdentity_throwsDuplicateBillException() {
        uniqueBillList.add(BILL_1);
        uniqueBillList.add(BILL_2);
        assertThrows(DuplicateBillException.class, () -> uniqueBillList.setBill(BILL_1, BILL_2));
    }

    @Test
    public void remove_nullBill_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBillList.remove(null));
    }

    @Test
    public void remove_billDoesNotExist_throwsBillNotFoundException() {
        assertThrows(BillNotFoundException.class, () -> uniqueBillList.remove(BILL_1));
    }

    @Test
    public void remove_existingBill_removesBill() {
        uniqueBillList.add(BILL_1);
        uniqueBillList.remove(BILL_1);
        UniqueBillList expectedUniqueBillList = new UniqueBillList();
        assertEquals(expectedUniqueBillList, uniqueBillList);
    }

    @Test
    public void setBills_nullUniqueBillList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBillList.setBills((UniqueBillList) null));
    }

    @Test
    public void setBills_uniqueBillList_replacesOwnListWithProvidedUniqueBillList() {
        uniqueBillList.add(BILL_1);
        UniqueBillList expectedUniqueBillList = new UniqueBillList();
        expectedUniqueBillList.add(BILL_2);
        uniqueBillList.setBills(expectedUniqueBillList);
        assertEquals(expectedUniqueBillList, uniqueBillList);
    }

    @Test
    public void setBills_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBillList.setBills((List<Bill>) null));
    }

    @Test
    public void setBills_list_replacesOwnListWithProvidedList() {
        uniqueBillList.add(BILL_1);
        List<Bill> billList = Collections.singletonList(BILL_2);
        uniqueBillList.setBills(billList);
        UniqueBillList expectedUniqueBillList = new UniqueBillList();
        expectedUniqueBillList.add(BILL_2);
        assertEquals(expectedUniqueBillList, uniqueBillList);
    }

    @Test
    public void setBills_listWithDuplicateBills_throwsDuplicateBillException() {
        List<Bill> listWithDuplicateBills = Arrays.asList(BILL_1, BILL_1);
        assertThrows(DuplicateBillException.class, () -> uniqueBillList.setBills(listWithDuplicateBills));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBillList.asUnmodifiableObservableList().remove(0));
    }
}
