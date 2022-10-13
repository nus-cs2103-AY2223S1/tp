package seedu.intrack.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.intrack.commons.core.GuiSettings;
import seedu.intrack.model.InTrack;
import seedu.intrack.model.ReadOnlyInTrack;
import seedu.intrack.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonInTrackStorage inTrackStorage = new JsonInTrackStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(inTrackStorage, userPrefsStorage);
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
    public void inTrackReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonInTrackStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonInTrackStorageTest} class.
         */
        InTrack original = getTypicalInTrack();
        storageManager.saveInTrack(original);
        ReadOnlyInTrack retrieved = storageManager.readInTrack().get();
        assertEquals(original, new InTrack(retrieved));
    }

    @Test
    public void getInTrackFilePath() {
        assertNotNull(storageManager.getInTrackFilePath());
    }

}
