package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2106;
import static seedu.address.testutil.TypicalModules.MA2001;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCodeMatchesKeywordPredicate;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.ModuleBuilder;

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
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasModule(CS2106));
    }

    @Test
    public void hasModule_moduleInAddressBook_returnsTrue() {
        modelManager.addModule(CS2106);
        assertTrue(modelManager.hasModule(CS2106));
    }

    @Test
    public void hasModule_moduleInAddressBookButNotInFilteredList_returnsTrue() {
        modelManager.addModule(CS2106);
        String keywordToFilterBy = MA2001.getModuleCodeAsUpperCaseString();
        modelManager.updateFilteredModuleList(new ModuleCodeMatchesKeywordPredicate(keywordToFilterBy));
        assertTrue(modelManager.hasModule(CS2106));
    }

    @Test
    public void hasModule_moduleWithSameModuleCodeButDifferentFieldsInAddressBook_returnsTrue() {
        String moduleCodeToCheckFor = CS2106.getModuleCode().value;
        Module moduleWithModuleSameCodeButDifferentFields =
                new ModuleBuilder().withModuleCode(moduleCodeToCheckFor).build();
        modelManager.addModule(CS2106);
        assertTrue(modelManager.hasModule(moduleWithModuleSameCodeButDifferentFields));
    }

    @Test
    public void hasModuleInFilteredList_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModuleInFilteredList(null));
    }

    @Test
    public void hasModuleInFilteredList_moduleNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasModuleInFilteredList(CS2106));
    }

    @Test
    public void hasModuleInFilteredList_moduleInAddressBookAndFilteredList_returnsTrue() {
        modelManager.addModule(CS2106);
        assertTrue(modelManager.hasModuleInFilteredList(CS2106));
    }

    @Test
    public void hasModuleInFilteredList_moduleInAddressBookButNotInFilteredList_returnsFalse() {
        modelManager.addModule(CS2106);
        String keywordToFilterBy = MA2001.getModuleCodeAsUpperCaseString();
        modelManager.updateFilteredModuleList(new ModuleCodeMatchesKeywordPredicate(keywordToFilterBy));
        assertFalse(modelManager.hasModuleInFilteredList(CS2106));
    }

    @Test
    public void hasModuleInFilteredList_moduleWithSameModuleCodeButDifferentFieldsInFilteredList_returnsTrue() {
        String moduleCodeToCheckFor = CS2106.getModuleCode().value;
        Module moduleWithModuleSameCodeButDifferentFields =
                new ModuleBuilder().withModuleCode(moduleCodeToCheckFor).build();
        modelManager.addModule(CS2106);
        assertTrue(modelManager.hasModule(moduleWithModuleSameCodeButDifferentFields));
    }

    @Test
    public void getModuleUsingModuleCode_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.getModuleUsingModuleCode(null, true));
    }

    @Test
    public void getModuleUsingModuleCode_moduleNotInAddressBookOrFilteredModuleList_throwsModuleNotFoundException() {
        ModuleCode moduleCodeToSearchFor = CS2106.getModuleCode();
        // Get from filtered list.
        assertThrows(ModuleNotFoundException.class, () ->
                modelManager.getModuleUsingModuleCode(moduleCodeToSearchFor, true));
        // Get from address book.
        assertThrows(ModuleNotFoundException.class, () ->
                modelManager.getModuleUsingModuleCode(moduleCodeToSearchFor, false));
    }

    @Test
    public void getModuleUsingModuleCode_moduleInAddressBookAndFilteredList_returnsModule() {
        ModuleCode moduleCodeToSearchFor = CS2106.getModuleCode();
        modelManager.addModule(CS2106);
        assertEquals(CS2106, modelManager.getModuleUsingModuleCode(moduleCodeToSearchFor, true));
        assertEquals(CS2106, modelManager.getModuleUsingModuleCode(moduleCodeToSearchFor, false));
    }

    @Test
    public void
            getModuleUsingModuleCode_moduleInAddressBookButNotInFilteredListAndSearchInAddressBook_returnsModule() {
        modelManager.addModule(CS2106);
        String keywordToFilterBy = MA2001.getModuleCodeAsUpperCaseString();
        modelManager.updateFilteredModuleList(new ModuleCodeMatchesKeywordPredicate(keywordToFilterBy));
        assertEquals(CS2106, modelManager.getModuleUsingModuleCode(CS2106.getModuleCode(), false));
    }

    @Test
    public void
            getModuleUsingModuleCode_moduleInAddressBookButNotInFilteredListAndSearchInFilteredList_returnsModule() {
        modelManager.addModule(CS2106);
        String keywordToFilterBy = MA2001.getModuleCodeAsUpperCaseString();
        modelManager.updateFilteredModuleList(new ModuleCodeMatchesKeywordPredicate(keywordToFilterBy));
        assertThrows(ModuleNotFoundException.class, () ->
                modelManager.getModuleUsingModuleCode(CS2106.getModuleCode(), true));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }


    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON)
                                                          .withModule(CS2106).build();
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

        // different filteredPersonList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different filteredModuleList -> returns false
        String keywordToFilterBy = MA2001.getModuleCodeAsUpperCaseString();
        modelManager.updateFilteredModuleList(new ModuleCodeMatchesKeywordPredicate(keywordToFilterBy));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
