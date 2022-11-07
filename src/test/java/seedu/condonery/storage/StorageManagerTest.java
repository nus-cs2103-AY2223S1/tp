package seedu.condonery.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.condonery.commons.core.GuiSettings;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.property.PropertyDirectory;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonPropertyDirectoryStorage propertyDirectoryStorage = new JsonPropertyDirectoryStorage(getTempFilePath("ab"));
        JsonClientDirectoryStorage clientDirectoryStorage = new JsonClientDirectoryStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(propertyDirectoryStorage, clientDirectoryStorage, userPrefsStorage);
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
    public void propertyDirectoryReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPropertyDirectoryStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPropertyDirectoryStorageTest} class.
         */
        PropertyDirectory original = getTypicalPropertyDirectory();
        storageManager.savePropertyDirectory(original);
        ReadOnlyPropertyDirectory retrieved = storageManager.readPropertyDirectory().get();
        assertEquals(original, new PropertyDirectory(retrieved, testFolder.resolve("images")));
    }

    @Test
    public void getPropertyDirectoryFilePath() {
        assertNotNull(storageManager.getPropertyDirectoryFilePath());
    }

}
