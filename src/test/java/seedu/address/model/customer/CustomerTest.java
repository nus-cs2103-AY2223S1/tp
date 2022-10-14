package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCommissions.ALICE_CAT;
import static seedu.address.testutil.TypicalCommissions.BENSON_DOG;
import static seedu.address.testutil.TypicalCommissions.CAT_PRODUCER;
import static seedu.address.testutil.TypicalCommissions.DOG_PRODUCER;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.commission.Commission;
import seedu.address.model.commission.exceptions.CommissionNotFoundException;
import seedu.address.model.commission.exceptions.DuplicateCommissionException;
import seedu.address.testutil.CustomerBuilder;

public class CustomerTest {

    // Reinitialize a customer each time to not deal with the mess of shared customer objects
    private Customer tom = new CustomerBuilder().withName("Tom").build();
    private Commission tomCatCommission = CAT_PRODUCER.apply(tom);

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Customer customer = new CustomerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> customer.getTags().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> customer.getCommissionList().remove(0));
    }

    @Test
    public void isSameCustomer() {
        // same object -> returns true
        assertTrue(ALICE.isSameCustomer(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameCustomer(null));

        // same name, all other attributes different -> returns true
        Customer editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameCustomer(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Customer editedBob = new CustomerBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameCustomer(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new CustomerBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameCustomer(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Customer aliceCopy = new CustomerBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different customer -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Customer editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new CustomerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new CustomerBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void addCommission_validCommission_success() {
        assertDoesNotThrow(() -> tom.addCommission(tomCatCommission));
    }

    @Test
    public void addCommission_duplicateCommission_throwsDuplicateCommissionException() {
        assertDoesNotThrow(() -> tom.addCommission(tomCatCommission));
        assertThrows(DuplicateCommissionException.class, () -> tom.addCommission(tomCatCommission));
    }

    @Test
    public void hasCommission_noCommissions_returnsFalse() {
        assertFalse(tom.hasCommission(BENSON_DOG));
    }

    @Test
    public void hasCommission_commissionNotInNonEmptyList_returnsFalse() {
        assertDoesNotThrow(() -> tom.addCommission(tomCatCommission));
        assertFalse(tom.hasCommission(BENSON_DOG));
    }

    @Test
    public void hasCommission_commissionInList_returnsTrue() {
        assertDoesNotThrow(() -> tom.addCommission(tomCatCommission));
        assertTrue(tom.hasCommission(tomCatCommission));
    }

    @Test
    public void setCommission_commissionNotInList_throwsCommissionNotFoundException() {
        assertThrows(CommissionNotFoundException.class, () -> tom.setCommission(ALICE_CAT, BENSON_DOG));
    }

    @Test
    public void setCommission_commissionInList_success() {
        Commission tomDogCommission = DOG_PRODUCER.apply(tom);
        assertDoesNotThrow(() -> tom.addCommission(tomCatCommission));
        assertDoesNotThrow(() -> tom.setCommission(tomCatCommission, tomDogCommission));
        assertFalse(tom.hasCommission(tomCatCommission));
        assertTrue(tom.hasCommission(tomDogCommission));
    }

    @Test
    public void removeCommission_commissionNotInList_throwsCommissionNotFoundException() {
        assertThrows(CommissionNotFoundException.class, () -> tom.removeCommission(ALICE_CAT));
    }

    @Test
    public void removeCommission_commissionInList_success() {
        assertDoesNotThrow(() -> tom.addCommission(tomCatCommission));
        assertDoesNotThrow(() -> tom.removeCommission(tomCatCommission));
        assertFalse(tom.hasCommission(tomCatCommission));
    }

}
