package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.predicates.OrderStatusPredicate;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.predicates.PetNameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.OrderBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PetBuilder;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalDeliverers;
import seedu.address.testutil.TypicalOrders;
import seedu.address.testutil.TypicalPets;
import seedu.address.testutil.TypicalSuppliers;


public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasBuyer_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasBuyer(null));
    }

    @Test
    public void hasBuyer_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasSupplier(null));
    }

    @Test
    public void hasBuyer_nullDeliverer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasDeliverer(null));
    }

    @Test
    public void hasPet_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPet(null));
    }

    @Test
    public void hasOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasOrder(null));
    }

    @Test
    public void hasBuyer_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasBuyer(TypicalBuyers.ALICE));
    }

    @Test
    public void hasSupplier_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasSupplier(TypicalSuppliers.ALICE));
    }

    @Test
    public void hasDeliverer_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasDeliverer(TypicalDeliverers.ALICE));
    }

    @Test
    public void hasPet_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPet(TypicalPets.DOJA));
    }

    @Test
    public void hasOrder_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasOrder(TypicalOrders.ORDER_1));
    }

    @Test
    public void hasBuyer_buyerInAddressBook_returnsTrue() {
        modelManager.addBuyer(TypicalBuyers.ALICE);
        assertTrue(modelManager.hasBuyer(TypicalBuyers.ALICE));
    }

    @Test
    public void hasSupplier_buyerInAddressBook_returnsTrue() {
        modelManager.addSupplier(TypicalSuppliers.ALICE);
        assertTrue(modelManager.hasSupplier(TypicalSuppliers.ALICE));
    }

    @Test
    public void hasDeliverer_delivererInAddressBook_returnsTrue() {
        modelManager.addDeliverer(TypicalDeliverers.ALICE);
        assertTrue(modelManager.hasDeliverer(TypicalDeliverers.ALICE));
    }

    @Test
    public void hasPet_petInAddressBook_returnsTrue() {
        modelManager.addPet(TypicalPets.DOJA);
        assertTrue(modelManager.hasPet(TypicalPets.DOJA));
    }

    @Test
    public void hasOrder_orderInAddressBook_returnsTrue() {
        modelManager.addOrder(TypicalOrders.ORDER_1);
        assertTrue(modelManager.hasOrder(TypicalOrders.ORDER_1));
    }

    @Test
    public void getFilteredBuyerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredBuyerList().remove(0));
    }

    @Test
    public void getFilteredSupplierList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredSupplierList().remove(0));
    }

    @Test
    public void getFilteredDelivererList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredDelivererList().remove(0));
    }

    @Test
    public void getFilteredPetList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPetList().remove(0));
    }

    @Test
    public void getFilteredOrderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredOrderList().remove(0));
    }

    @Test
    public void getOrdersFromBuyer() {
        Buyer buyer = new PersonBuilder().withName("Caroline").buildBuyer();
        buyer.addOrders(Arrays.asList(TypicalOrders.ORDER_1.getId(), TypicalOrders.ORDER_2.getId()));
        AddressBook addressBook = new AddressBookBuilder().withBuyer(buyer).withOrder(TypicalOrders.ORDER_1)
                .withOrder(TypicalOrders.ORDER_2).build();

        modelManager = new ModelManager(addressBook, new UserPrefs());

        List<Order> orders = modelManager.getOrdersFromBuyer(buyer);

        List<Order> expected = Arrays.asList(TypicalOrders.ORDER_1, TypicalOrders.ORDER_2);
        assertEquals(orders, expected);
    }

    @Test
    public void getPetsFromSupplier() {
        Supplier supplier = new PersonBuilder().withName("Caroline").buildSupplier();
        supplier.addPets(Arrays.asList(TypicalPets.DOJA.getId(), TypicalPets.PLUM.getId()));
        AddressBook addressBook = new AddressBookBuilder().withSupplier(supplier).withPet(TypicalPets.DOJA)
                .withPet(TypicalPets.PLUM).build();

        modelManager = new ModelManager(addressBook, new UserPrefs());

        List<Pet> orders = modelManager.getPetsFromSupplier(supplier);

        List<Pet> expected = Arrays.asList(TypicalPets.DOJA, TypicalPets.PLUM);
        assertEquals(orders, expected);
    }

    @Test
    public void setBuyer_null_throwsNullPointerException() {
        Buyer buyer = new PersonBuilder().withName("Caroline").buildBuyer();
        AddressBook addressBook = new AddressBookBuilder().withBuyer(buyer).build();
        modelManager = new ModelManager(addressBook, new UserPrefs());

        assertThrows(NullPointerException.class, () -> modelManager.setBuyer(buyer, null));
    }

    @Test
    public void setSupplier_null_throwsNullPointerException() {
        Supplier supplier = new PersonBuilder().withName("Caroline").buildSupplier();
        AddressBook addressBook = new AddressBookBuilder().withSupplier(supplier).build();
        modelManager = new ModelManager(addressBook, new UserPrefs());

        assertThrows(NullPointerException.class, () -> modelManager.setSupplier(supplier, null));
    }

    @Test
    public void setDeliverer_null_throwsNullPointerException() {
        Deliverer deliverer = new PersonBuilder().withName("Caroline").buildDeliverer();
        AddressBook addressBook = new AddressBookBuilder().withDeliverer(deliverer).build();
        modelManager = new ModelManager(addressBook, new UserPrefs());

        assertThrows(NullPointerException.class, () -> modelManager.setDeliverer(deliverer, null));
    }

    @Test
    public void setPet_null_throwsNullPointerException() {
        Pet pet = new PetBuilder().withName("Vinnie").build();
        AddressBook addressBook = new AddressBookBuilder().withPet(pet).build();
        modelManager = new ModelManager(addressBook, new UserPrefs());

        assertThrows(NullPointerException.class, () -> modelManager.setPet(pet, null));
    }

    @Test
    public void setOrder_null_throwsNullPointerException() {
        Order order = new OrderBuilder().build();
        AddressBook addressBook = new AddressBookBuilder().withOrder(order).build();
        modelManager = new ModelManager(addressBook, new UserPrefs());

        assertThrows(NullPointerException.class, () -> modelManager.setOrder(order, null));
    }

    @Test
    public void setBuyer_validBuyer_success() {
        Buyer buyer = new PersonBuilder().withName("Caroline").buildBuyer();
        AddressBook addressBook = new AddressBookBuilder().withBuyer(buyer).build();
        modelManager = new ModelManager(addressBook, new UserPrefs());

        Buyer newBuyer = new PersonBuilder().withName("Coraline").buildBuyer();
        modelManager.setBuyer(buyer, newBuyer);

        assertTrue(modelManager.hasBuyer(newBuyer));
        assertFalse(modelManager.hasBuyer(buyer));
    }

    @Test
    public void setBuyer_validSupplier_success() {
        Supplier supplier = new PersonBuilder().withName("Caroline").buildSupplier();
        AddressBook addressBook = new AddressBookBuilder().withSupplier(supplier).build();
        modelManager = new ModelManager(addressBook, new UserPrefs());

        Supplier newSupplier = new PersonBuilder().withName("Coraline").buildSupplier();
        modelManager.setSupplier(supplier, newSupplier);

        assertTrue(modelManager.hasSupplier(newSupplier));
        assertFalse(modelManager.hasSupplier(supplier));
    }

    @Test
    public void setDeliverer_validDeliverer_success() {
        Deliverer deliverer = new PersonBuilder().withName("Caroline").buildDeliverer();
        AddressBook addressBook = new AddressBookBuilder().withDeliverer(deliverer).build();
        modelManager = new ModelManager(addressBook, new UserPrefs());

        Deliverer newDeliverer = new PersonBuilder().withName("Coraline").buildDeliverer();
        modelManager.setDeliverer(deliverer, newDeliverer);

        assertTrue(modelManager.hasDeliverer(newDeliverer));
        assertFalse(modelManager.hasDeliverer(deliverer));
    }

    @Test
    public void setPet_validPet_success() {
        Pet pet = new PetBuilder().withName("HelloKitty").build();
        AddressBook addressBook = new AddressBookBuilder().withPet(pet).build();
        modelManager = new ModelManager(addressBook, new UserPrefs());

        Pet newPet = new PetBuilder().withName("Pompompurin").build();
        modelManager.setPet(pet, newPet);

        assertTrue(modelManager.hasPet(newPet));
        assertFalse(modelManager.hasPet(pet));
    }

    @Test
    public void setOrder_validOrder_success() {
        Order order = new OrderBuilder().withStatus(OrderStatus.DELIVERING.toString()).build();
        AddressBook addressBook = new AddressBookBuilder().withOrder(order).build();
        modelManager = new ModelManager(addressBook, new UserPrefs());

        Order newOrder = new OrderBuilder().withStatus(OrderStatus.PENDING.toString()).build();
        modelManager.setOrder(order, newOrder);

        assertTrue(modelManager.hasOrder(newOrder));
        assertFalse(modelManager.hasOrder(order));
    }

    @Test
    public void equals_buyers_success() {
        AddressBook addressBook = new AddressBookBuilder().withBuyer(TypicalBuyers.ALICE)
                .withBuyer(TypicalBuyers.BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = TypicalBuyers.ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredBuyerList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredBuyerList(Model.PREDICATE_SHOW_ALL_BUYERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }

    @Test
    public void equals_deliverers_success() {
        AddressBook addressBook = new AddressBookBuilder().withDeliverer(TypicalDeliverers.ALICE)
                .withDeliverer(TypicalDeliverers.BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = TypicalDeliverers.ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredDelivererList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredDelivererList(Model.PREDICATE_SHOW_ALL_DELIVERERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }

    @Test
    public void equals_suppliers_success() {
        AddressBook addressBook = new AddressBookBuilder().withSupplier(TypicalSuppliers.ALICE)
                .withSupplier(TypicalSuppliers.BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = TypicalSuppliers.ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredSupplierList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredSupplierList(Model.PREDICATE_SHOW_ALL_SUPPLIERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }

    @Test
    public void equals_pets_success() {
        AddressBook addressBook = new AddressBookBuilder().withPet(TypicalPets.DOJA)
                .withPet(TypicalPets.PLUM).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = TypicalPets.DOJA.getName().fullName.split("\\s+");
        modelManager.updateFilteredPetList(new PetNameContainsKeywordsPredicate<>(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPetList(Model.PREDICATE_SHOW_ALL_PETS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }

    @Test
    public void equals_orders_success() {
        AddressBook addressBook = new AddressBookBuilder().withOrder(TypicalOrders.ORDER_1)
                .withOrder(TypicalOrders.ORDER_2).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        OrderStatus orderStatus = TypicalOrders.ORDER_1.getOrderStatus();
        modelManager.updateFilteredOrderList(new OrderStatusPredicate<>(orderStatus));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredOrderList(Model.PREDICATE_SHOW_ALL_ORDERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
