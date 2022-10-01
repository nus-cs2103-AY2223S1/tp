package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.customer.exceptions.CustomerNotFoundException;
import seedu.address.model.customer.exceptions.DuplicateCustomerException;
import seedu.address.testutil.CustomerBuilder;

public class UniqueCustomerListTest {

    private final UniqueCustomerList uniqueCustomerList = new UniqueCustomerList();

    @Test
    public void contains_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.contains(null));
    }

    @Test
    public void contains_customerNotInList_returnsFalse() {
        assertFalse(uniqueCustomerList.contains(ALICE));
    }

    @Test
    public void contains_customerInList_returnsTrue() {
        uniqueCustomerList.add(ALICE);
        assertTrue(uniqueCustomerList.contains(ALICE));
    }

    @Test
    public void contains_customerWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCustomerList.add(ALICE);
        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(uniqueCustomerList.contains(editedAlice));
    }

    @Test
    public void add_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.add(null));
    }

    @Test
    public void add_duplicateCustomer_throwsDuplicateCustomerException() {
        uniqueCustomerList.add(ALICE);
        assertThrows(DuplicateCustomerException.class, () -> uniqueCustomerList.add(ALICE));
    }

    @Test
    public void setCustomer_nullTargetCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.setCustomer(null, ALICE));
    }

    @Test
    public void setCustomer_nullEditedCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.setCustomer(ALICE, null));
    }

    @Test
    public void setCustomer_targetCustomerNotInList_throwsCustomerNotFoundException() {
        assertThrows(CustomerNotFoundException.class, () -> uniqueCustomerList.setCustomer(ALICE, ALICE));
    }

    @Test
    public void setCustomer_editedCustomerIsSameCustomer_success() {
        uniqueCustomerList.add(ALICE);
        uniqueCustomerList.setCustomer(ALICE, ALICE);
        UniqueCustomerList expectedUniqueCustomerList = new UniqueCustomerList();
        expectedUniqueCustomerList.add(ALICE);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setCustomer_editedCustomerHasSameIdentity_success() {
        uniqueCustomerList.add(ALICE);
        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        uniqueCustomerList.setCustomer(ALICE, editedAlice);
        UniqueCustomerList expectedUniqueCustomerList = new UniqueCustomerList();
        expectedUniqueCustomerList.add(editedAlice);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setCustomer_editedCustomerHasDifferentIdentity_success() {
        uniqueCustomerList.add(ALICE);
        uniqueCustomerList.setCustomer(ALICE, BOB);
        UniqueCustomerList expectedUniqueCustomerList = new UniqueCustomerList();
        expectedUniqueCustomerList.add(BOB);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setCustomer_editedCustomerHasNonUniqueIdentity_throwsDuplicateCustomerException() {
        uniqueCustomerList.add(ALICE);
        uniqueCustomerList.add(BOB);
        assertThrows(DuplicateCustomerException.class, () -> uniqueCustomerList.setCustomer(ALICE, BOB));
    }

    @Test
    public void remove_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.remove(null));
    }

    @Test
    public void remove_customerDoesNotExist_throwsCustomerNotFoundException() {
        assertThrows(CustomerNotFoundException.class, () -> uniqueCustomerList.remove(ALICE));
    }

    @Test
    public void remove_existingCustomer_removesCustomer() {
        uniqueCustomerList.add(ALICE);
        uniqueCustomerList.remove(ALICE);
        UniqueCustomerList expectedUniqueCustomerList = new UniqueCustomerList();
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setCustomers_nullUniqueCustomerList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.setCustomers((UniqueCustomerList) null));
    }

    @Test
    public void setCustomers_uniqueCustomerList_replacesOwnListWithProvidedUniqueCustomerList() {
        uniqueCustomerList.add(ALICE);
        UniqueCustomerList expectedUniqueCustomerList = new UniqueCustomerList();
        expectedUniqueCustomerList.add(BOB);
        uniqueCustomerList.setCustomers(expectedUniqueCustomerList);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setCustomers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.setCustomers((List<Customer>) null));
    }

    @Test
    public void setCustomers_list_replacesOwnListWithProvidedList() {
        uniqueCustomerList.add(ALICE);
        List<Customer> customerList = Collections.singletonList(BOB);
        uniqueCustomerList.setCustomers(customerList);
        UniqueCustomerList expectedUniqueCustomerList = new UniqueCustomerList();
        expectedUniqueCustomerList.add(BOB);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setCustomers_listWithDuplicateCustomers_throwsDuplicateCustomerException() {
        List<Customer> listWithDuplicateCustomers = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateCustomerException.class, () -> uniqueCustomerList.setCustomers(
            listWithDuplicateCustomers));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueCustomerList.asUnmodifiableObservableList().remove(0));
    }
}
