package soconnect.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static soconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static soconnect.testutil.Assert.assertThrows;
import static soconnect.testutil.TypicalPersons.ALICE;
import static soconnect.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import soconnect.commons.core.GuiSettings;
import soconnect.model.person.NameContainsKeywordsPredicate;
import soconnect.model.tag.Tag;
import soconnect.testutil.SoConnectBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new SoConnect(), new SoConnect(modelManager.getSoConnect()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setSoConnectFilePath(Paths.get("soconnect/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setSoConnectFilePath(Paths.get("new/soconnect/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE");
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setSoConnectFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSoConnectFilePath(null));
    }

    @Test
    public void setSoConnectFilePath_validPath_setsSoConnectFilePath() {
        Path path = Paths.get("soconnect/file/path");
        modelManager.setSoConnectFilePath(path);
        assertEquals(path, modelManager.getSoConnectFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInSoConnect_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInSoConnect_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        SoConnect soConnect = new SoConnectBuilder().withPerson(ALICE).withPerson(BENSON).build();
        SoConnect differentSoConnect = new SoConnect();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(soConnect, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(soConnect, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different soConnect -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentSoConnect, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(soConnect, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setSoConnectFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(soConnect, differentUserPrefs)));
    }

    @Test
    void sortByName() {
        ModelManager sampleA = new ModelManager();
        ModelManager sampleB = new ModelManager();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(BENSON);
        sampleB.addPerson(BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByName(true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByName(false);
        assertNotEquals(sampleA, sampleB);
    }

    @Test
    void sortByPhone() {
        ModelManager sampleA = new ModelManager();
        ModelManager sampleB = new ModelManager();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(BENSON);
        sampleB.addPerson(BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByPhone(true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByPhone(false);
        assertNotEquals(sampleA, sampleB);
    }

    @Test
    void sortByEmail() {
        ModelManager sampleA = new ModelManager();
        ModelManager sampleB = new ModelManager();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(BENSON);
        sampleB.addPerson(BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByEmail(true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByEmail(false);
        assertNotEquals(sampleA, sampleB);
    }

    @Test
    void sortByAddress() {
        ModelManager sampleA = new ModelManager();
        ModelManager sampleB = new ModelManager();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(BENSON);
        sampleB.addPerson(BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByAddress(true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByAddress(false);
        assertNotEquals(sampleA, sampleB);
    }

    @Test
    void sortByTag() {
        ModelManager sampleA = new ModelManager();
        ModelManager sampleB = new ModelManager();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(BENSON);
        sampleB.addPerson(BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByTag(new Tag("owesMoney"), false);
        assertEquals(sampleA, sampleB);
        sampleA.sortByTag(new Tag("owesMoney"), true);
        assertNotEquals(sampleA, sampleB);
    }
}
