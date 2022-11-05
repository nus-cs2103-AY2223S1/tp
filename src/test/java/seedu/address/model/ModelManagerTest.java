package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalTasks.COOK;
import static seedu.address.testutil.TypicalTasks.STUDY;
import static seedu.address.testutil.TypicalTeams.BACKEND;
import static seedu.address.testutil.TypicalTeams.FRONTEND;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.task.Name;
import seedu.address.model.team.Team;
import seedu.address.model.team.exceptions.TeamNotFoundException;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private static final String DATE_FORMAT = "dd-MM-uuuu";
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);

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
    public void hasTeam_teamInAddressBook_returnsTrue() {
        modelManager.addTeam(FRONTEND);
        assertTrue(modelManager.hasTeam(FRONTEND));
    }

    @Test
    public void setTeamName_validTeamName_setsTeamName() {
        modelManager.addTeam(FRONTEND);
        Team teamToEdit = modelManager.getTeam(FRONTEND.getName());
        modelManager.setTeamName(INDEX_FIRST_PERSON, BACKEND.getName());
        assertEquals(teamToEdit.getName(), BACKEND.getName());
    }

    @Test
    public void deleteTeam_teamNotInModelManager_throwsTeamNotFoundException() {
        assertThrows(TeamNotFoundException.class, () -> modelManager.deleteTeam(FRONTEND));
    }

    @Test
    public void deleteTeam_teamInModeManager_teamIsRemoved() {
        modelManager.addTeam(FRONTEND);
        Team teamToDelete = modelManager.getTeam(FRONTEND.getName());
        modelManager.deleteTeam(teamToDelete);
        assertFalse(modelManager.hasTeam(teamToDelete));
    }


    @Test
    public void teamHasTask_nullIndex_throwsNullPointerException() {
        modelManager.addTeam(FRONTEND);
        assertThrows(NullPointerException.class, () -> modelManager.teamHasTask(null, STUDY));
    }

    @Test
    public void teamHasTask_nullTask_throwsNullPointerException() {
        modelManager.addTeam(FRONTEND);
        assertThrows(NullPointerException.class, () -> modelManager.teamHasTask(INDEX_FIRST_TEAM, null));
    }

    @Test
    public void teamHasTask_teamHasTasks_returnsTrue() {
        modelManager.addTeam(FRONTEND);
        assertTrue(modelManager.teamHasTask(INDEX_FIRST_TEAM, STUDY));
    }

    @Test
    public void teamHasTask_teamHasNoTasks_returnsFalse() {
        modelManager.addTeam(FRONTEND);
        assertFalse(modelManager.teamHasTask(INDEX_FIRST_TEAM, COOK));
    }


    @Test
    public void deleteTask_nullTeamIndex_throwsNullPointerException() {
        modelManager.addTeam(FRONTEND);
        assertThrows(NullPointerException.class, () -> modelManager.deleteTask(null,INDEX_FIRST_TASK));
    }

    @Test
    public void deleteTask_nullTask_throwsNullPointerException() {
        modelManager.addTeam(FRONTEND);
        assertThrows(NullPointerException.class, () -> modelManager.deleteTask(INDEX_FIRST_TEAM, null));
    }


    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
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
