package gim.storage;

import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.ALICE;
import static gim.testutil.TypicalExercises.HOON;
import static gim.testutil.TypicalExercises.IDA;
import static gim.testutil.TypicalExercises.getTypicalExerciseTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import gim.commons.exceptions.DataConversionException;
import gim.model.ExerciseTracker;
import gim.model.ReadOnlyExerciseTracker;

public class JsonExerciseTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonExerciseTrackerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readExerciseTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readExerciseTracker(null));
    }

    private java.util.Optional<ReadOnlyExerciseTracker> readExerciseTracker(String filePath) throws Exception {
        return new JsonExerciseTrackerStorage(Paths.get(filePath)).readExerciseTracker(
                addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readExerciseTracker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readExerciseTracker("notJsonFormatExerciseTracker.json"));
    }

    @Test
    public void readExerciseTracker_invalidExerciseExerciseTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readExerciseTracker("invalidExerciseExerciseTracker.json"));
    }

    @Test
    public void readExerciseTracker_invalidAndValidExerciseExerciseTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readExerciseTracker(
                "invalidAndValidExerciseExerciseTracker.json"));
    }

    @Test
    public void readAndSaveExerciseTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempExerciseTracker.json");
        ExerciseTracker original = getTypicalExerciseTracker();
        JsonExerciseTrackerStorage jsonExerciseTrackerStorage = new JsonExerciseTrackerStorage(filePath);

        // Save in new file and read back
        jsonExerciseTrackerStorage.saveExerciseTracker(original, filePath);
        ReadOnlyExerciseTracker readBack = jsonExerciseTrackerStorage.readExerciseTracker(filePath).get();
        assertEquals(original, new ExerciseTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addExercise(HOON);
        original.removeExercise(ALICE);
        jsonExerciseTrackerStorage.saveExerciseTracker(original, filePath);
        readBack = jsonExerciseTrackerStorage.readExerciseTracker(filePath).get();
        assertEquals(original, new ExerciseTracker(readBack));

        // Save and read without specifying file path
        original.addExercise(IDA);
        jsonExerciseTrackerStorage.saveExerciseTracker(original); // file path not specified
        readBack = jsonExerciseTrackerStorage.readExerciseTracker().get(); // file path not specified
        assertEquals(original, new ExerciseTracker(readBack));

    }

    @Test
    public void saveExerciseTracker_nullExerciseTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExerciseTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code exerciseTracker} at the specified {@code filePath}.
     */
    private void saveExerciseTracker(ReadOnlyExerciseTracker exerciseTracker, String filePath) {
        try {
            new JsonExerciseTrackerStorage(Paths.get(filePath))
                    .saveExerciseTracker(exerciseTracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveExerciseTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExerciseTracker(new ExerciseTracker(), null));
    }
}
