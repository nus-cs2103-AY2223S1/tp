package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.group.testutil.TypicalGroups.INDIVIDUAL_PROJECT;
import static seedu.address.model.group.testutil.TypicalGroups.TEAM_PROJECT;
import static seedu.address.model.person.testutil.Assert.assertThrows;
import static seedu.address.model.person.testutil.TypicalPersons.ALICE;
import static seedu.address.model.person.testutil.TypicalPersons.BENSON;
import static seedu.address.model.person.testutil.TypicalPersons.BOB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.testutil.AddressBookBuilder;

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
    public void getPersonWithName_nameOfAlice_returnAlice() {
        modelManager.addPerson(ALICE);
        modelManager.addPerson(BOB);
        Person alice = modelManager.getPersonWithName(ALICE.getName()).get(0);

        assertEquals(ALICE, alice);
    }

    @Test
    public void getPersonWithName_emptyName_returnEmptyList() {
        ObservableList<Person> personList = modelManager.getPersonWithName(ALICE.getName());

        assertThrows(IndexOutOfBoundsException.class, () -> personList.get(0));
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
    public void addPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addPerson(null));
    }

    @Test
    public void addPerson_personAlreadyInAddressBook_throwsDuplicatePersonException() {
        modelManager.addPerson(ALICE);
        assertThrows(DuplicatePersonException.class, () -> modelManager.addPerson(ALICE));
    }

    @Test
    public void deletePerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deletePerson(null));
    }

    @Test
    public void deletePerson_personNotInAddressBook_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> modelManager.deletePerson(ALICE));
    }

    @Test
    public void deletePerson_personInAddressBook_personSuccessfullyRemoved() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
        modelManager.deletePerson(ALICE);
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void setPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_personNotInAddressBook_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> modelManager.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_personInAddressBook_personSuccessfullySet() {
        modelManager.addPerson(ALICE);
        modelManager.setPerson(ALICE, BOB);
        assertFalse(modelManager.hasPerson(ALICE));
        assertTrue(modelManager.hasPerson(BOB));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getGroupWithName_nameOfTp_returnTp() {
        modelManager.addGroup(TEAM_PROJECT);
        modelManager.addGroup(INDIVIDUAL_PROJECT);
        Group tP = modelManager.getGroupWithName(TEAM_PROJECT.getName()).get(0);

        assertEquals(TEAM_PROJECT, tP);
    }

    @Test
    public void getGroupWithName_emptyName_returnEmptyList() {
        ObservableList<Group> groupList = modelManager.getGroupWithName(TEAM_PROJECT.getName());

        assertThrows(IndexOutOfBoundsException.class, () -> groupList.get(0));
    }

    @Test
    public void hasGroup_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasGroup(null));
    }

    @Test
    public void hasGroup_groupNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasGroup(TEAM_PROJECT));
    }

    @Test
    public void hasGroup_groupInAddressBook_returnsTrue() {
        modelManager.addGroup(TEAM_PROJECT);
        assertTrue(modelManager.hasGroup(TEAM_PROJECT));
    }

    @Test
    public void addGroup_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addGroup(null));
    }

    @Test
    public void addGroup_groupAlreadyInAddressBook_throwsDuplicateGroupException() {
        modelManager.addGroup(TEAM_PROJECT);
        assertThrows(DuplicateGroupException.class, () -> modelManager.addGroup(TEAM_PROJECT));
    }

    @Test
    public void deleteGroup_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteGroup(null));
    }

    @Test
    public void deleteGroup_groupNotInAddressBook_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> modelManager.deleteGroup(TEAM_PROJECT));
    }

    @Test
    public void deleteGroup_groupInAddressBook_groupSuccessfullyRemoved() {
        modelManager.addGroup(TEAM_PROJECT);
        assertTrue(modelManager.hasGroup(TEAM_PROJECT));
        modelManager.deleteGroup(TEAM_PROJECT);
        assertFalse(modelManager.hasGroup(TEAM_PROJECT));
    }

    @Test
    public void setGroup_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGroup(TEAM_PROJECT, null));
    }

    @Test
    public void setGroup_groupNotInAddressBook_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> modelManager.setGroup(TEAM_PROJECT, TEAM_PROJECT));
    }

    @Test
    public void setGroup_groupInAddressBook_groupSuccessfullySet() {
        modelManager.addGroup(TEAM_PROJECT);
        modelManager.setGroup(TEAM_PROJECT, INDIVIDUAL_PROJECT);
        assertFalse(modelManager.hasGroup(TEAM_PROJECT));
        assertTrue(modelManager.hasGroup(INDIVIDUAL_PROJECT));
    }

    @Test
    public void getFilteredGroupList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredGroupList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
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
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
