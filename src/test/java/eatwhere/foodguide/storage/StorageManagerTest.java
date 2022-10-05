package eatwhere.foodguide.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import eatwhere.foodguide.testutil.TypicalPersons;
import eatwhere.foodguide.commons.core.GuiSettings;
import eatwhere.foodguide.model.AddressBook;
import eatwhere.foodguide.model.ReadOnlyAddressBook;
import eatwhere.foodguide.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFoodGuideStorage foodGuideStorage = new JsonFoodGuideStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(foodGuideStorage, userPrefsStorage);
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
    public void foodGuideReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFoodGuideStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonFoodGuideStorageTest} class.
         */
        AddressBook original = TypicalPersons.getTypicalAddressBook();
        storageManager.saveFoodGuide(original);
        ReadOnlyAddressBook retrieved = storageManager.readFoodGuide().get();
        assertEquals(original, new AddressBook(retrieved));
    }

    @Test
    public void getFoodGuideFilePath() {
        assertNotNull(storageManager.getFoodGuideFilePath());
    }

}
