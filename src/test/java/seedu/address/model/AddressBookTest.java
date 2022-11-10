package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.order.Order;
import seedu.address.model.order.Price;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.DateOfBirth;
import seedu.address.model.pet.Height;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.PetName;
import seedu.address.model.pet.Species;
import seedu.address.model.pet.VaccinationStatus;
import seedu.address.model.pet.Weight;
import seedu.address.testutil.OrderBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalDeliverers;
import seedu.address.testutil.TypicalOrders;
import seedu.address.testutil.TypicalPets;
import seedu.address.testutil.TypicalSuppliers;

public class AddressBookTest {
    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getBuyerList());
        assertEquals(Collections.emptyList(), addressBook.getSupplierList());
        assertEquals(Collections.emptyList(), addressBook.getDelivererList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = TypicalBuyers.getTypicalBuyerAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);

        newData = TypicalDeliverers.getTypicalDelivererAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);

        newData = TypicalSuppliers.getTypicalSupplierAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateBuyers_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Buyer editedAlice = new PersonBuilder(TypicalBuyers.ALICE).withAddress(VALID_ADDRESS_BOB).buildBuyer();
        List<Buyer> newPersons = Arrays.asList(TypicalBuyers.ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasBuyer_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasBuyer(null));
    }

    @Test
    public void hasDeliverer_nullDeliverer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasDeliverer(null));
    }

    @Test
    public void hasSupplier_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasSupplier(null));
    }

    @Test
    public void hasPet_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPet(null));
    }

    @Test
    public void hasOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasOrder(null));
    }

    @Test
    public void hasBuyer_buyerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasBuyer(TypicalBuyers.ALICE));
    }

    @Test
    public void hasDeliverer_delivererNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasDeliverer(TypicalDeliverers.ALICE));
    }

    @Test
    public void hasSupplier_supplierNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasSupplier(TypicalSuppliers.ALICE));
    }

    @Test
    public void hasOrder_orderNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasOrder(TypicalOrders.ORDER_1));
    }

    @Test
    public void hasPet_petNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPet(TypicalPets.DOJA));
    }

    @Test
    public void hasBuyer_buyerInAddressBook_returnsTrue() {
        addressBook.addBuyer(TypicalBuyers.ALICE);
        assertTrue(addressBook.hasBuyer(TypicalBuyers.ALICE));
    }

    @Test
    public void hasDeliverer_delivererInAddressBook_returnsTrue() {
        addressBook.addDeliverer(TypicalDeliverers.ALICE);
        assertTrue(addressBook.hasDeliverer(TypicalDeliverers.ALICE));
    }

    @Test
    public void hasSupplier_supplierInAddressBook_returnsTrue() {
        addressBook.addSupplier(TypicalSuppliers.ALICE);
        assertTrue(addressBook.hasSupplier(TypicalSuppliers.ALICE));
    }

    @Test
    public void hasOrder_orderInAddressBook_returnsTrue() {
        addressBook.addOrder(TypicalOrders.ORDER_1);
        assertTrue(addressBook.hasOrder(TypicalOrders.ORDER_1));
    }

    @Test
    public void hasPet_petInAddressBook_returnsTrue() {
        addressBook.addPet(TypicalPets.DOJA);
        assertTrue(addressBook.hasPet(TypicalPets.DOJA));
    }

    @Test
    public void hasBuyer_buyerWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addBuyer(TypicalBuyers.ALICE);
        Buyer editedAlice = new PersonBuilder(TypicalBuyers.ALICE).withAddress(VALID_ADDRESS_BOB).buildBuyer();
        assertTrue(addressBook.hasBuyer(editedAlice));
    }

    @Test
    public void hasDeliverer_delivererWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addDeliverer(TypicalDeliverers.ALICE);
        Deliverer editedAlice = new PersonBuilder(TypicalDeliverers.ALICE).withAddress(VALID_ADDRESS_BOB)
                .buildDeliverer();
        assertTrue(addressBook.hasDeliverer(editedAlice));
    }

    @Test
    public void hasSupplier_supplierWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addSupplier(TypicalSuppliers.ALICE);
        Supplier editedAlice = new PersonBuilder(TypicalSuppliers.ALICE).withAddress(VALID_ADDRESS_BOB)
                .buildSupplier();
        assertTrue(addressBook.hasSupplier(editedAlice));
    }

    @Test
    public void hasOrder_orderWithDifferentIdentityFieldsInAddressBook_returnsFalse() {
        addressBook.addOrder(TypicalOrders.ORDER_1);
        Order editedOrder = new OrderBuilder(TypicalOrders.ORDER_1).withStatus("Delivering").build();
        assertFalse(addressBook.hasOrder(editedOrder));
    }

    @Test
    public void hasPet_petWithSameIdentityFieldsInAddressBook_returnsFalse() {
        addressBook.addPet(TypicalPets.DOJA);
        Pet editedDoja = new Pet(new PetName("Doja"), TypicalSuppliers.ALICE,
                new Color("black"),
                new ColorPattern("black and brown"),
                new DateOfBirth(LocalDate.parse("2022-10-10")), new Species("cat"), new Weight(10.05),
                new Height(100.5), new VaccinationStatus(true), new Price(66.66), new HashSet<>());
        assertFalse(addressBook.hasPet(editedDoja));
    }

    @Test
    public void getBuyerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getBuyerList().remove(0));
    }

    @Test
    public void getDelivererList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getDelivererList().remove(0));
    }

    @Test
    public void getSupplierList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getSupplierList().remove(0));
    }

    @Test
    public void getPetList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPetList().remove(0));
    }

    @Test
    public void getOrderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getOrderList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Buyer> buyers = FXCollections.observableArrayList();
        private final ObservableList<Deliverer> deliverers = FXCollections.observableArrayList();
        private final ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        private final ObservableList<Pet> pets = FXCollections.observableArrayList();
        private final ObservableList<Order> orders = FXCollections.observableArrayList();

        AddressBookStub(Collection<Buyer> buyers) {
            this.buyers.setAll(buyers);
        }

        @Override
        public ObservableList<Buyer> getBuyerList() {
            return buyers;
        }

        @Override
        public ObservableList<Supplier> getSupplierList() {
            return suppliers;
        }

        @Override
        public ObservableList<Deliverer> getDelivererList() {
            return deliverers;
        }

        public ObservableList<Order> getOrderList() {
            return orders;
        }

        public ObservableList<Pet> getPetList() {
            return pets;
        }
    }

}
