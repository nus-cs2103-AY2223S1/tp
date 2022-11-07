package jeryl.fyp.storage;

import static jeryl.fyp.testutil.Assert.assertThrows;
import static jeryl.fyp.testutil.TypicalStudents.ALICE;
import static jeryl.fyp.testutil.TypicalStudents.HOON;
import static jeryl.fyp.testutil.TypicalStudents.IDA;
import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import jeryl.fyp.commons.exceptions.DataConversionException;
import jeryl.fyp.model.FypManager;
import jeryl.fyp.model.ReadOnlyFypManager;

public class JsonFypManagerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFypManagerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFypManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFypManager(null));
    }

    private java.util.Optional<ReadOnlyFypManager> readFypManager(String filePath) throws Exception {
        return new JsonFypManagerStorage(Paths.get(filePath)).readFypManager(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFypManager("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFypManager("notJsonFormatFypManager.json"));
    }

    @Test
    public void readFypManager_invalidStudentFypManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFypManager("invalidStudentFypManager.json"));
    }

    @Test
    public void readFypManager_invalidAndValidStudentFypManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFypManager("invalidAndValidStudentFypManager.json"));
    }

    @Test
    public void readAndSaveFypManager_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFypManager.json");
        FypManager original = getTypicalFypManager();
        JsonFypManagerStorage jsonFypManagerStorage = new JsonFypManagerStorage(filePath);

        // Save in new file and read back
        jsonFypManagerStorage.saveFypManager(original, filePath);
        ReadOnlyFypManager readBack = jsonFypManagerStorage.readFypManager(filePath).get();
        assertEquals(original, new FypManager(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonFypManagerStorage.saveFypManager(original, filePath);
        readBack = jsonFypManagerStorage.readFypManager(filePath).get();
        assertEquals(original, new FypManager(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonFypManagerStorage.saveFypManager(original); // file path not specified
        readBack = jsonFypManagerStorage.readFypManager().get(); // file path not specified
        assertEquals(original, new FypManager(readBack));

    }

    @Test
    public void saveFypManager_nullFypManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFypManager(null, "SomeFile.json"));
    }

    /**
     * Saves {@code fypManager} at the specified {@code filePath}.
     */
    private void saveFypManager(ReadOnlyFypManager fypManager, String filePath) {
        try {
            new JsonFypManagerStorage(Paths.get(filePath))
                    .saveFypManager(fypManager, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFypManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFypManager(new FypManager(), null));
    }
}
