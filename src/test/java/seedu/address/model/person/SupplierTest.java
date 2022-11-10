package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalSuppliers.ALICE;
import static seedu.address.testutil.TypicalSuppliers.BOB;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalDeliverers;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalPets;

public class SupplierTest {

    @Test
    public void addPet_success() {
        Supplier supplier = new PersonBuilder().buildSupplier();
        List<UniqueId> uniqueIds = Arrays.asList(TypicalPets.PLUM.getId());
        supplier.addPets(uniqueIds);
        assertEquals(supplier.getPetIds(), uniqueIds);
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Supplier editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .buildSupplier();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).buildSupplier();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Supplier editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).buildSupplier();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).buildSupplier();
        assertFalse(BOB.isSamePerson(editedBob));
    }


    @Test
    public void equals() {
        // same values -> returns true
        Supplier aliceCopy = new PersonBuilder(TypicalPersons.ALICE).buildSupplier();
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
        Supplier editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).buildSupplier();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).buildSupplier();
        assertFalse(TypicalDeliverers.ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).buildSupplier();
        assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).buildSupplier();
        assertFalse(ALICE.equals(editedAlice));
    }


    @Test
    public void toString_withPetsOnSale() {
        Supplier supplier1 = new PersonBuilder().buildSupplier();
        Supplier supplier2 = new PersonBuilder().buildSupplier();
        List<UniqueId> uniqueIds = Arrays.asList(TypicalPets.DOJA.getId(), TypicalPets.PLUM.getId());
        supplier1.addPets(uniqueIds);
        supplier2.addPets(uniqueIds);
        assertEquals(supplier1.toString(), supplier2.toString());
    }

    @Test
    public void hashcode() {
        Supplier supplier1 = new PersonBuilder().withName("Red").withPhone("999").withEmail("red@pokemon.com")
                .withAddress("Celadon City").buildSupplier();
        Supplier supplier2 = new PersonBuilder().buildSupplier();

        List<UniqueId> ids = TypicalPets.getTypicalPets().stream().map(x -> x.getId()).collect(Collectors.toList());
        supplier1.addPets(ids);

        //Same object -> success
        assertEquals(supplier1.hashCode(), supplier1.hashCode());

        //Same copy -> success
        Supplier supplier1Copy = new PersonBuilder().withName("Red").withPhone("999").withEmail("red@pokemon.com")
                .withAddress("Celadon City").buildSupplier();
        supplier1Copy.addPets(ids);
        assertEquals(supplier1Copy.hashCode(), supplier1.hashCode());

        //different supplier -> not same
        assertNotEquals(supplier1, supplier2);
    }
}
