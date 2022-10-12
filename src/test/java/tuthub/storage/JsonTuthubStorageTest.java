package tuthub.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tuthub.testutil.Assert.assertThrows;
import static tuthub.testutil.TypicalTutors.ALICE;
import static tuthub.testutil.TypicalTutors.HOON;
import static tuthub.testutil.TypicalTutors.IDA;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import tuthub.commons.exceptions.DataConversionException;
import tuthub.model.ReadOnlyTuthub;
import tuthub.model.Tuthub;

public class JsonTuthubStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTuthubStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTuthub_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTuthub(null));
    }

    private java.util.Optional<ReadOnlyTuthub> readTuthub(String filePath) throws Exception {
        return new JsonTuthubStorage(Paths.get(filePath)).readTuthub(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTuthub("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTuthub("notJsonFormatTuthub.json"));
    }

    @Test
    public void readTuthub_invalidTutorTuthub_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTuthub("invalidTutorTuthub.json"));
    }

    @Test
    public void readTuthub_invalidAndValidTutorTuthub_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTuthub("invalidAndValidTutorTuthub.json"));
    }

    @Test
    public void readAndSaveTuthub_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTuthub.json");
        Tuthub original = getTypicalTuthub();
        JsonTuthubStorage jsonTuthubStorage = new JsonTuthubStorage(filePath);

        // Save in new file and read back
        jsonTuthubStorage.saveTuthub(original, filePath);
        ReadOnlyTuthub readBack = jsonTuthubStorage.readTuthub(filePath).get();
        assertEquals(original, new Tuthub(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTutor(HOON);
        original.removeTutor(ALICE);
        jsonTuthubStorage.saveTuthub(original, filePath);
        readBack = jsonTuthubStorage.readTuthub(filePath).get();
        assertEquals(original, new Tuthub(readBack));

        // Save and read without specifying file path
        original.addTutor(IDA);
        jsonTuthubStorage.saveTuthub(original); // file path not specified
        readBack = jsonTuthubStorage.readTuthub().get(); // file path not specified
        assertEquals(original, new Tuthub(readBack));

    }

    @Test
    public void saveTuthub_nullTuthub_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTuthub(null, "SomeFile.json"));
    }

    /**
     * Saves {@code tuthub} at the specified {@code filePath}.
     */
    private void saveTuthub(ReadOnlyTuthub tuthub, String filePath) {
        try {
            new JsonTuthubStorage(Paths.get(filePath))
                    .saveTuthub(tuthub, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTuthub_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTuthub(new Tuthub(), null));
    }
}
