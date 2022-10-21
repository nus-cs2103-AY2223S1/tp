package seedu.trackascholar.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.trackascholar.testutil.TypicalApplicants.getTypicalTrackAScholar;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.trackascholar.commons.core.GuiSettings;
import seedu.trackascholar.model.ReadOnlyTrackAScholar;
import seedu.trackascholar.model.TrackAScholar;
import seedu.trackascholar.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTrackAScholarStorage trackAScholarStorage = new JsonTrackAScholarStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(trackAScholarStorage, userPrefsStorage);
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
    public void trackAScholarReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTrackAScholarStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTrackAScholarStorageTest} class.
         */
        TrackAScholar original = getTypicalTrackAScholar();
        storageManager.saveTrackAScholar(original);
        ReadOnlyTrackAScholar retrieved = storageManager.readTrackAScholar().get();
        assertEquals(original, new TrackAScholar(retrieved));
    }

    @Test
    public void getTrackAScholarFilePath() {
        assertNotNull(storageManager.getTrackAScholarFilePath());
    }

}
