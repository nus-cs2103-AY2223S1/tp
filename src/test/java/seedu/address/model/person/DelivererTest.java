package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalDeliverers.ALICE;
import static seedu.address.testutil.TypicalDeliverers.BOB;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class DelivererTest {

    @Test
    public void getOrders_noParam() {
        Deliverer deliverer = new PersonBuilder().buildDeliverer();
        assertTrue(deliverer.getOrders().size() == 0);
        assertEquals(deliverer.getOrders(), new ArrayList<>());
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .buildDeliverer();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).buildDeliverer();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).buildDeliverer();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).buildDeliverer();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Deliverer aliceCopy = new PersonBuilder(TypicalPersons.ALICE).buildDeliverer();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Deliverer editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).buildDeliverer();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).buildDeliverer();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).buildDeliverer();
        assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).buildDeliverer();
        assertFalse(ALICE.equals(editedAlice));

    }

    @Test
    public void hashCode_sameObject() {
        Deliverer alice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).buildDeliverer();
        assertEquals(alice.hashCode(), alice.hashCode());
    }

    @Test
    public void hashCode_diffObject() {
        Deliverer deliverer1 = new PersonBuilder().buildDeliverer();
        Deliverer deliverer2 = new PersonBuilder().buildDeliverer();
        assertEquals(deliverer1.hashCode(), deliverer2.hashCode());
    }
}
