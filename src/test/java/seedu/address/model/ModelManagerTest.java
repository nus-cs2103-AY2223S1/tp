package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.transformation.FilteredList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.CommissionBuilder;
import seedu.address.testutil.CustomerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
        // Sets selected customer to ObservableObject of null if AddressBook is empty
        assertNull(modelManager.getSelectedCustomer().getValue());
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
    public void hasCustomer_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCustomer(null));
    }

    @Test
    public void hasCustomer_customerNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasCustomer(ALICE));
    }

    @Test
    public void hasCustomer_customerInAddressBook_returnsTrue() {
        modelManager.addCustomer(ALICE);
        assertTrue(modelManager.hasCustomer(ALICE));
    }

    @Test
    public void getSelectedCustomer_addressBookNonEmpty_returnsFirstCustomer() {
        modelManager.addCustomer(ALICE);
        modelManager.selectCustomer(ALICE);
        assertEquals(ALICE, modelManager.getSelectedCustomer().getValue());
    }

    @Test
    public void selectCustomer() {
        modelManager.addCustomer(ALICE);
        modelManager.selectCustomer(ALICE);
        modelManager.addCustomer(BENSON);
        assertEquals(ALICE, modelManager.getSelectedCustomer().getValue());
        modelManager.selectCustomer(BENSON);
        assertEquals(BENSON, modelManager.getSelectedCustomer().getValue());

    }

    @Test
    public void getFilteredCustomerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCustomerList().remove(0));
    }

    @Test
    public void selectCustomer_newCustomer_selectsNewCustomer() {
        Customer testCustomer = new CustomerBuilder(ALICE).build();
        modelManager.addCustomer(testCustomer);
        Commission testCommission = new CommissionBuilder().build(testCustomer);
        testCustomer.addCommission(testCommission);
        modelManager.selectCustomer(testCustomer);
        assertEquals(testCustomer, modelManager.getSelectedCustomer().getValue());
        modelManager.addCustomer(BENSON);
        modelManager.selectCustomer(BENSON);
        assertEquals(BENSON, modelManager.getSelectedCustomer().getValue());
        modelManager.selectCustomer(null);
        assertNull(modelManager.getSelectedCommission().getValue());
    }

    @Test
    public void addCommission_emptyList_updatesObservableList() {
        Customer aliceCopy = new CustomerBuilder(ALICE).build();
        modelManager.selectCustomer(null);
        Pair<Customer, FilteredList<Commission>> originalList =
                modelManager.getObservableFilteredCommissionList().getValue();
        modelManager.addCustomer(aliceCopy);
        Commission testCommission = new CommissionBuilder().build(aliceCopy);
        modelManager.selectCustomer(aliceCopy);
        aliceCopy.addCommission(testCommission);
        assertNotEquals(originalList, modelManager.getObservableFilteredCommissionList().getValue());
    }


    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withCustomer(ALICE).withCustomer(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(null, modelManager);

        // different types -> returns false
        assertNotEquals(5, modelManager);

        // different addressBook -> returns false
        assertNotEquals(modelManager, new ModelManager(differentAddressBook, userPrefs));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredCustomerList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertNotEquals(modelManager, new ModelManager(addressBook, userPrefs));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(addressBook, differentUserPrefs));
    }
}
