package seedu.travelr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.travelr.testutil.TypicalTrips.getTypicalTravelr;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.travelr.commons.core.GuiSettings;
import seedu.travelr.model.ReadOnlyTravelr;
import seedu.travelr.model.Travelr;
import seedu.travelr.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private seedu.travelr.storage.StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTravelrStorage addressBookStorage =
                new JsonTravelrStorage(getTempFilePath("ab"));
        seedu.travelr.storage.JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
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
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        Travelr original = getTypicalTravelr();
        storageManager.saveTravelr(original);
        ReadOnlyTravelr retrieved = storageManager.readTravelr().get();
        assertEquals(original, new Travelr(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getTravelrFilePath());
    }

}
