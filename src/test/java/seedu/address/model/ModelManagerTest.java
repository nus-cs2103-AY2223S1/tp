package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
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
    public void equals() {
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
}
