package seedu.boba.model.customer;

import org.junit.jupiter.api.Test;
import seedu.boba.testutil.CustomerBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_BIRTHDAY_MONTH_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_REWARD_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_GOLD;
import static seedu.boba.testutil.Assert.assertThrows;
import static seedu.boba.testutil.TypicalCustomers.ALICE;
import static seedu.boba.testutil.TypicalCustomers.AMY;
import static seedu.boba.testutil.TypicalCustomers.BOB;

public class CustomerTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Customer customer = new CustomerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> customer.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns false
        Customer editedCustomer = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withReward(VALID_REWARD_BOB).withTags(VALID_TAG_GOLD).build();
        assertFalse(ALICE.isSamePerson(editedCustomer));

        // different name, all other attributes same -> returns true
        editedCustomer = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSamePerson(editedCustomer));

        // same phone number, all other attributes different -> return true
        editedCustomer = new CustomerBuilder(AMY).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_BOB)
                .withReward(VALID_REWARD_BOB).withTags(VALID_TAG_GOLD).build();
        assertTrue(AMY.isSamePerson(editedCustomer));

        // same email address, all other attributes different -> return true
        editedCustomer = new CustomerBuilder(AMY).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY)
                .withReward(VALID_REWARD_BOB).withTags(VALID_TAG_GOLD).build();
        assertTrue(AMY.isSamePerson(editedCustomer));

        // name differs in case, all other attributes same -> returns true
        Customer editedBob = new CustomerBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new CustomerBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));
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

        // different name -> returns true
        Customer editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.equals(editedAlice));

        // different phone, same email -> returns true
        editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(ALICE.equals(editedAlice));

        // different email, same phone -> returns true
        editedAlice = new CustomerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.equals(editedAlice));

        // different email, different phone -> returns false
        editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different birthdayMonth -> returns true
        editedAlice = new CustomerBuilder(ALICE).withBirthdayMonth(VALID_BIRTHDAY_MONTH_BOB).build();
        assertTrue(ALICE.equals(editedAlice));

        // different reward -> returns true
        editedAlice = new CustomerBuilder(ALICE).withReward(VALID_REWARD_BOB).build();
        assertTrue(ALICE.equals(editedAlice));

        // different tags -> returns true
        editedAlice = new CustomerBuilder(ALICE).withTags(VALID_TAG_GOLD).build();
        assertTrue(ALICE.equals(editedAlice));
    }

    @Test
    public void strictlyEquals() {
        // same values -> returns true
        Customer aliceCopy = new CustomerBuilder(ALICE).build();
        assertTrue(ALICE.strictlyEquals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.strictlyEquals(ALICE));

        // null -> returns false
        assertFalse(ALICE.strictlyEquals(null));

        // different type -> returns false
        assertFalse(ALICE.strictlyEquals(5));

        // different customer -> returns false
        assertFalse(ALICE.strictlyEquals(BOB));

        // different name -> returns false
        Customer editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.strictlyEquals(editedAlice));

        // different phone, same email -> returns false
        editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.strictlyEquals(editedAlice));

        // different email, same phone -> returns false
        editedAlice = new CustomerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.strictlyEquals(editedAlice));

        // different email, different phone -> returns false
        editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.strictlyEquals(editedAlice));

        // different birthdayMonth -> returns false
        editedAlice = new CustomerBuilder(ALICE).withBirthdayMonth(VALID_BIRTHDAY_MONTH_BOB).build();
        assertFalse(ALICE.strictlyEquals(editedAlice));

        // different reward -> returns true
        editedAlice = new CustomerBuilder(ALICE).withReward(VALID_REWARD_BOB).build();
        assertFalse(ALICE.strictlyEquals(editedAlice));

        // different tags -> returns false
        editedAlice = new CustomerBuilder(ALICE).withTags(VALID_TAG_GOLD).build();
        assertFalse(ALICE.strictlyEquals(editedAlice));
    }
}
