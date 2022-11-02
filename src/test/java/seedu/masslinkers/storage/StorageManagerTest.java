package seedu.masslinkers.storage;
//@@author
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.masslinkers.testutil.TypicalStudents.getTypicalMassLinkers;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.masslinkers.commons.core.GuiSettings;
import seedu.masslinkers.model.MassLinkers;
import seedu.masslinkers.model.ReadOnlyMassLinkers;
import seedu.masslinkers.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonMassLinkersStorage massLinkersStorage = new JsonMassLinkersStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(massLinkersStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void massLinkersReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonMassLinkersStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonMassLinkersStorageTest} class.
         */
        MassLinkers original = getTypicalMassLinkers();
        storageManager.saveMassLinkers(original);
        ReadOnlyMassLinkers retrieved = storageManager.readMassLinkers().get();
        assertEquals(original, new MassLinkers(retrieved));
    }

    @Test
    public void getMassLinkersFilePath() {
        assertNotNull(storageManager.getMassLinkersFilePath());
    }

}
