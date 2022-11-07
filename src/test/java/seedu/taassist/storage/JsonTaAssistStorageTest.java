package seedu.taassist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalStudents.ALICE;
import static seedu.taassist.testutil.TypicalStudents.HOON;
import static seedu.taassist.testutil.TypicalStudents.IDA;
import static seedu.taassist.testutil.TypicalStudents.getTypicalTaAssist;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.taassist.commons.exceptions.DataConversionException;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.TaAssist;

public class JsonTaAssistStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaAssistStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaAssist_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTaAssist(null));
    }

    private java.util.Optional<ReadOnlyTaAssist> readTaAssist(String filePath) throws Exception {
        return new JsonTaAssistStorage(Paths.get(filePath)).readTaAssist(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaAssist("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTaAssist("notJsonFormatTaAssist.json"));
    }

    @Test
    public void readTaAssist_invalidStudentTaAssist_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaAssist("invalidStudentTaAssist.json"));
    }

    @Test
    public void readTaAssist_invalidAndValidStudentTaAssist_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaAssist("invalidAndValidStudentTaAssist.json"));
    }

    @Test
    public void readAndSaveTaAssist_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTaAssist.json");
        TaAssist original = getTypicalTaAssist();
        JsonTaAssistStorage jsonTaAssistStorage = new JsonTaAssistStorage(filePath);

        // Save in new file and read back
        jsonTaAssistStorage.saveTaAssist(original, filePath);
        ReadOnlyTaAssist readBack = jsonTaAssistStorage.readTaAssist(filePath).get();
        assertEquals(original, new TaAssist(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonTaAssistStorage.saveTaAssist(original, filePath);
        readBack = jsonTaAssistStorage.readTaAssist(filePath).get();
        assertEquals(original, new TaAssist(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonTaAssistStorage.saveTaAssist(original); // file path not specified
        readBack = jsonTaAssistStorage.readTaAssist().get(); // file path not specified
        assertEquals(original, new TaAssist(readBack));

    }

    @Test
    public void saveTaAssist_nullTaAssist_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaAssist(null, "SomeFile.json"));
    }

    /**
     * Saves {@code taAssist} at the specified {@code filePath}.
     */
    private void saveTaAssist(ReadOnlyTaAssist taAssist, String filePath) {
        try {
            new JsonTaAssistStorage(Paths.get(filePath))
                    .saveTaAssist(taAssist, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaAssist_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaAssist(new TaAssist(), null));
    }
}
