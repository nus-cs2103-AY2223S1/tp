package seedu.nutrigoals.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.nutrigoals.testutil.Assert.assertThrows;
import static seedu.nutrigoals.testutil.TypicalFoods.APPLE;
import static seedu.nutrigoals.testutil.TypicalFoods.PANCAKE;
import static seedu.nutrigoals.testutil.TypicalFoods.SUSHI;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.nutrigoals.commons.exceptions.DataConversionException;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;

public class JsonNutriGoalsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonNutriGoalsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readNutriGoals_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readNutriGoals(null));
    }

    private java.util.Optional<ReadOnlyNutriGoals> readNutriGoals(String filePath) throws Exception {
        return new JsonNutriGoalsStorage(Paths.get(filePath)).readNutriGoals(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readNutriGoals("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readNutriGoals("notJsonFormatNutriGoals.json"));
    }

    @Test
    public void readNutriGoals_invalidFoodNutriGoals_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readNutriGoals("invalidFoodNutriGoals.json"));
    }

    @Test
    public void readNutriGoals_invalidAndValidFoodNutriGoals_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readNutriGoals("invalidAndValidFoodNutriGoals.json"));
    }

    @Test
    public void readAndSaveNutriGoals_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempNutriGoals.json");
        NutriGoals original = getTypicalNutriGoals();
        JsonNutriGoalsStorage jsonNutriGoalsStorage = new JsonNutriGoalsStorage(filePath);

        // Save in new file and read back
        jsonNutriGoalsStorage.saveNutriGoals(original, filePath);
        ReadOnlyNutriGoals readBack = jsonNutriGoalsStorage.readNutriGoals(filePath).get();
        assertEquals(original, new NutriGoals(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addFood(SUSHI);
        original.removeFood(APPLE);
        jsonNutriGoalsStorage.saveNutriGoals(original, filePath);
        readBack = jsonNutriGoalsStorage.readNutriGoals(filePath).get();
        assertEquals(original, new NutriGoals(readBack));

        // Save and read without specifying file path
        original.addFood(PANCAKE);
        jsonNutriGoalsStorage.saveNutriGoals(original); // file path not specified
        readBack = jsonNutriGoalsStorage.readNutriGoals().get(); // file path not specified
        assertEquals(original, new NutriGoals(readBack));

    }

    @Test
    public void saveNutriGoals_nullNutriGoals_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNutriGoals(null, "SomeFile.json"));
    }

    /**
     * Saves {@code nutriGoals} at the specified {@code filePath}.
     */
    private void saveNutriGoals(ReadOnlyNutriGoals nutriGoals, String filePath) {
        try {
            new JsonNutriGoalsStorage(Paths.get(filePath))
                    .saveNutriGoals(nutriGoals, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveNutriGoals_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNutriGoals(new NutriGoals(), null));
    }
}
