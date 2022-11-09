package seedu.nutrigoals.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.nutrigoals.commons.core.GuiSettings;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonNutriGoalsStorage nutriGoalsStorage = new JsonNutriGoalsStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(nutriGoalsStorage, userPrefsStorage);
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
    public void nutriGoalsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonNutriGoalsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonNutriGoalsStorageTest} class.
         */
        NutriGoals original = getTypicalNutriGoals();
        storageManager.saveNutriGoals(original);
        ReadOnlyNutriGoals retrieved = storageManager.readNutriGoals().get();
        assertEquals(original, new NutriGoals(retrieved));
    }

    @Test
    public void getNutriGoalsFilePath() {
        assertNotNull(storageManager.getNutriGoalsFilePath());
    }

}
