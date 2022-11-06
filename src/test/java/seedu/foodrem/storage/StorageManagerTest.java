package seedu.foodrem.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.foodrem.testutil.TypicalFoodRem.getFoodRemWithTypicalItems;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.foodrem.commons.core.GuiSettings;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.UserPrefs;

/**
 * A class to test StorageManager.
 */
public class StorageManagerTest {
    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFoodRemStorage foodRemStorage = new JsonFoodRemStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(foodRemStorage, userPrefsStorage);
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
    public void foodRemReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFoodRemStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonFoodRemStorageTest} class.
         */
        FoodRem original = getFoodRemWithTypicalItems();
        storageManager.saveFoodRem(original);
        ReadOnlyFoodRem retrieved = storageManager.readFoodRem().get();
        assertEquals(original, new FoodRem(retrieved));
    }

    @Test
    public void getFoodRemFilePath() {
        assertNotNull(storageManager.getFoodRemFilePath());
    }
}
