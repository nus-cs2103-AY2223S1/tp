package seedu.foodrem.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.foodrem.testutil.Assert.assertThrows;
import static seedu.foodrem.testutil.TypicalFoodRem.getFoodRemWithTypicalItems;
import static seedu.foodrem.testutil.TypicalItems.CARROTS;
import static seedu.foodrem.testutil.TypicalItems.POTATOES;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.foodrem.commons.exceptions.DataConversionException;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.testutil.TypicalTags;

/**
 * A class to test JsonFoodRemStorage.
 */
public class JsonFoodRemStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFoodRemStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFoodRem_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFoodRem(null));
    }

    private java.util.Optional<ReadOnlyFoodRem> readFoodRem(String filePath) throws Exception {
        return new JsonFoodRemStorage(Paths.get(filePath)).readFoodRem(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFoodRem("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFoodRem("notJsonFormatFoodRem.json"));
    }

    @Test
    public void readFoodRem_invalidItemFoodRem_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFoodRem("invalidItemFoodRem.json"));
    }

    @Test
    public void readFoodRem_invalidAndValidItemFoodRem_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFoodRem("invalidAndValidItemFoodRem.json"));
    }

    @Test
    public void readAndSaveFoodRem_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFoodRem.json");
        FoodRem original = getFoodRemWithTypicalItems();
        JsonFoodRemStorage jsonFoodRemStorage = new JsonFoodRemStorage(filePath);

        // Save in new file and read back
        jsonFoodRemStorage.saveFoodRem(original, filePath);
        ReadOnlyFoodRem readBack = jsonFoodRemStorage.readFoodRem(filePath).get();
        assertEquals(original, new FoodRem(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addItem(CARROTS);
        original.removeItem(POTATOES);
        jsonFoodRemStorage.saveFoodRem(original, filePath);
        readBack = jsonFoodRemStorage.readFoodRem(filePath).get();
        assertEquals(original, new FoodRem(readBack));

        // Save and read without specifying file path
        original.addItem(POTATOES);
        jsonFoodRemStorage.saveFoodRem(original); // file path not specified
        readBack = jsonFoodRemStorage.readFoodRem().get(); // file path not specified
        assertEquals(original, new FoodRem(readBack));

        // Modify tags, overwrite exiting file, and read back
        original.addTag(TypicalTags.NUMBERS);
        jsonFoodRemStorage.saveFoodRem(original);
        readBack = jsonFoodRemStorage.readFoodRem(filePath).get();
        assertEquals(original, new FoodRem(readBack));

        // Save and read without specifying file path
        original.removeTag(TypicalTags.NUMBERS);
        original.addTag(TypicalTags.FRUITS);
        jsonFoodRemStorage.saveFoodRem(original); // file path not specified
        readBack = jsonFoodRemStorage.readFoodRem().get(); // file path not specified
        assertEquals(original, new FoodRem(readBack));
    }

    @Test
    public void saveFoodRem_nullFoodRem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFoodRem(null, "SomeFile.json"));
    }

    /**
     * Saves {@code foodRem} at the specified {@code filePath}.
     */
    private void saveFoodRem(ReadOnlyFoodRem foodRem, String filePath) {
        try {
            new JsonFoodRemStorage(Paths.get(filePath))
                    .saveFoodRem(foodRem, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFoodRem_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFoodRem(new FoodRem(), null));
    }
}
