package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ZERO_MODULE;
import static seedu.address.model.Model.PREDICATE_SHOW_ZERO_PERSON;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2106;
import static seedu.address.testutil.TypicalModules.CS2106_WITH_TYPICAL_TASKS;
import static seedu.address.testutil.TypicalModules.MA2001;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCodeMatchesKeywordPredicate;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.PersonBuilder;

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
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
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
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        assertTrue(modelManager.hasModuleInFilteredList(moduleWithModuleSameCodeButDifferentFields));
    }

    @Test
    public void getModuleUsingModuleCode_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.getModuleUsingModuleCode(null, true));
        assertThrows(NullPointerException.class, () ->
                modelManager.getModuleUsingModuleCode(null, false));
    }

    @Test
    public void getModuleUsingModuleCode_moduleNotInAddressBookAndFilteredModuleList_throwsModuleNotFoundException() {
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
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        assertEquals(CS2106, modelManager.getModuleUsingModuleCode(moduleCodeToSearchFor, true));
        assertEquals(CS2106, modelManager.getModuleUsingModuleCode(moduleCodeToSearchFor, false));
    }

    @Test
    public void getModuleUsingModuleCode_moduleInAddressBookButNotInFilteredListAndSearchInAddressBook_returnsModule() {
        modelManager.addModule(CS2106);
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ZERO_MODULE);
        assertEquals(CS2106, modelManager.getModuleUsingModuleCode(CS2106.getModuleCode(), false));
    }

    @Test
    public void getModuleUsingModuleCode_moduleExistButNotInFilteredListAndSearchInFilteredList_returnsModule() {
        modelManager.addModule(CS2106);
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ZERO_MODULE);
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
    public void hasPersonInFilteredList_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPersonInFilteredList(null));
    }

    @Test
    public void hasPersonInFilteredList_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPersonInFilteredList(AMY));
    }

    @Test
    public void hasPersonInFilteredList_personInAddressBookAndFilteredList_returnsTrue() {
        modelManager.addPerson(AMY);
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        assertTrue(modelManager.hasPersonInFilteredList(AMY));
    }

    @Test
    public void hasPersonInFilteredList_personInAddressBookButNotInFilteredList_returnsFalse() {
        modelManager.addPerson(AMY);
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ZERO_PERSON);
        assertFalse(modelManager.hasPersonInFilteredList(AMY));
    }

    @Test
    public void hasPersonInFilteredList_personWithSameNameButDifferentFieldsInFilteredList_returnsTrue() {
        Person personWithSameNameButDifferentFields =
                new PersonBuilder().withName(VALID_NAME_AMY)
                        .withEmail(VALID_EMAIL_BOB)
                        .withPhone(VALID_PHONE_BOB).build();
        modelManager.addPerson(personWithSameNameButDifferentFields);
        assertTrue(modelManager.hasPersonInFilteredList(personWithSameNameButDifferentFields));
    }

    @Test
    public void getPersonUsingName_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.getPersonUsingName(null, true));
        assertThrows(NullPointerException.class, () ->
                modelManager.getPersonUsingName(null, false));
    }

    @Test
    public void getPersonUsingName_personNotInAddressBookAndFilteredPersonList_throwsPersonNotFoundException() {
        Name nameToSearchFor = AMY.getName();
        // Get from filtered list.
        assertThrows(PersonNotFoundException.class, () ->
                modelManager.getPersonUsingName(nameToSearchFor, true));
        // Get from address book.
        assertThrows(PersonNotFoundException.class, () ->
                modelManager.getPersonUsingName(nameToSearchFor, false));
    }

    @Test
    public void getPersonUsingName_personInAddressBookAndFilteredList_returnsPerson() {
        Name nameToSearchFor = AMY.getName();
        modelManager.addPerson(AMY);
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        assertEquals(AMY, modelManager.getPersonUsingName(nameToSearchFor, true));
        assertEquals(AMY, modelManager.getPersonUsingName(nameToSearchFor, false));
    }

    @Test
    public void getPersonUsingName_personInAddressBookButNotInFilteredListAndSearchInAddressBook_returnsPerson() {
        Name nameToSearchFor = AMY.getName();
        modelManager.addPerson(AMY);
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ZERO_PERSON);
        assertEquals(AMY, modelManager.getPersonUsingName(nameToSearchFor, false));
    }

    @Test
    public void
            getPersonUsingName_personExistButNotInFilteredListAndSearchInFilteredList_throwsPersonNotFoundException() {
        Name nameToSearchFor = AMY.getName();
        modelManager.addPerson(AMY);
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ZERO_PERSON);
        assertThrows(PersonNotFoundException.class, () ->
                modelManager.getPersonUsingName(nameToSearchFor, true));
    }

    @Test
    public void goToHomePage_filteredList_returnsHomePage() {
        // Move out of home page
        modelManager.addPerson(AMY);
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ZERO_PERSON);

        modelManager.addModule(CS2106);
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ZERO_MODULE);

        modelManager.setHomeStatus(false);

        // Validate that person and module is filtered
        assertFalse(modelManager.hasPersonInFilteredList(AMY));
        assertFalse(modelManager.hasModuleInFilteredList(CS2106));
        assertFalse(modelManager.getHomeStatusAsBoolean());

        // Return to home page
        modelManager.goToHomePage();
        assertTrue(modelManager.getHomeStatusAsBoolean());
        assertTrue(modelManager.hasPersonInFilteredList(AMY));
        assertTrue(modelManager.hasModuleInFilteredList(CS2106));
    }

    @Test
    public void setHomeStatus() {
        ObservableList<Boolean> isAtHome = FXCollections.observableArrayList(true);
        ObservableList<Boolean> isNotAtHome = FXCollections.observableArrayList(false);

        // Model default is at home.
        assertEquals(isAtHome, modelManager.getHomeStatus());

        // Leave home.
        modelManager.setHomeStatus(false);
        assertEquals(isNotAtHome, modelManager.getHomeStatus());

        // Return home.
        modelManager.setHomeStatus(true);
        assertEquals(isAtHome, modelManager.getHomeStatus());
    }

    @Test
    public void getHomeStatus() {
        ObservableList<Boolean> isAtHome = FXCollections.observableArrayList(true);
        ObservableList<Boolean> isNotAtHome = FXCollections.observableArrayList(false);

        // Model default is at home.
        assertEquals(isAtHome, modelManager.getHomeStatus());

        // Leave home.
        modelManager.setHomeStatus(false);
        assertEquals(isNotAtHome, modelManager.getHomeStatus());

        // Test that modelManager does not update home status via its methods except goToHomePage.

        // When filtered list is updated.
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ZERO_PERSON);
        assertEquals(isNotAtHome, modelManager.getHomeStatus());

        // After adding person.
        modelManager.addPerson(AMY);
        assertEquals(isNotAtHome, modelManager.getHomeStatus());

        // After removing person via modelManager.
        modelManager.deletePerson(AMY);
        assertEquals(isNotAtHome, modelManager.getHomeStatus());

        // After adding module via modelManger.
        modelManager.addModule(CS2106_WITH_TYPICAL_TASKS);
        assertEquals(isNotAtHome, modelManager.getHomeStatus());

        // After removing module via modelManager.
        modelManager.deleteModule(CS2106_WITH_TYPICAL_TASKS);
        assertEquals(isNotAtHome, modelManager.getHomeStatus());

        // Model returns to home after running goToHomePage.
        modelManager.goToHomePage();
        assertEquals(isAtHome, modelManager.getHomeStatus());
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
