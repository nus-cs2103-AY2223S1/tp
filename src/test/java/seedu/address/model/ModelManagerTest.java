package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalPersons.ALICE;
//import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalEntry.DINNER;
import static seedu.address.testutil.TypicalEntry.LUNCH;

import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
//import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new PennyWise(), new PennyWise(modelManager.getPennyWise()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPennyWiseFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPennyWiseFilePath(Paths.get("new/address/book/file/path"));
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
        assertThrows(NullPointerException.class, () -> modelManager.setPennyWiseFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setPennyWiseFilePath(path);
        assertEquals(path, modelManager.getPennyWiseFilePath());
    }

    @Test
    public void hasExpenditure_nullExpenditure_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasExpenditure(null));
    }

    @Test
    public void hasIncome_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasIncome(null));
    }

    @Test
    public void hasExpenditure_incomeNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasExpenditure(LUNCH));
    }

    @Test
    public void hasExpenditure_expenditurenInAddressBook_returnsTrue() {
        modelManager.addExpenditure(LUNCH);
        assertTrue(modelManager.hasExpenditure(LUNCH));
    }

    @Test
    public void getFilteredExpenditureList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredExpenditureList().remove(0));
    }

    @Test
    public void equals() {
        PennyWise pennyWise = new AddressBookBuilder().withExpenditure(LUNCH).withExpenditure(DINNER).build();
        PennyWise differentPennyWise = new PennyWise();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(pennyWise, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(pennyWise, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different pennyWise -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPennyWise, userPrefs)));

        // different filteredList -> returns false
        // String[] keywords = LUNCH.getName().fullName.split("\\s+");
        // modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        // assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEntryList(Model.PREDICATE_SHOW_ALL_ENTRIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPennyWiseFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(pennyWise, differentUserPrefs)));
    }
}
