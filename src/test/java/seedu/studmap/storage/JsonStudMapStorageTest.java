package seedu.studmap.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.studmap.testutil.Assert.assertThrows;
import static seedu.studmap.testutil.TypicalStudents.ALICE;
import static seedu.studmap.testutil.TypicalStudents.HOON;
import static seedu.studmap.testutil.TypicalStudents.IDA;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.studmap.commons.exceptions.DataConversionException;
import seedu.studmap.model.ReadOnlyStudMap;
import seedu.studmap.model.StudMap;

public class JsonStudMapStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonStudMapStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readStudMap_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readStudMap(null));
    }

    private java.util.Optional<ReadOnlyStudMap> readStudMap(String filePath) throws Exception {
        return new JsonStudMapStorage(Paths.get(filePath)).readStudMap(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readStudMap("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readStudMap("notJsonFormatStudMap.json"));
    }

    @Test
    public void readStudMap_invalidstudentStudMap_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readStudMap("invalidStudentStudMap.json"));
    }

    @Test
    public void readStudMap_invalidAndValidstudentStudMap_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readStudMap("invalidAndValidStudentStudMap.json"));
    }

    @Test
    public void readAndSaveStudMap_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempStudMap.json");
        StudMap original = getTypicalStudMap();
        JsonStudMapStorage jsonStudMapStorage = new JsonStudMapStorage(filePath);

        // Save in new file and read back
        jsonStudMapStorage.saveStudMap(original, filePath);
        ReadOnlyStudMap readBack = jsonStudMapStorage.readStudMap(filePath).get();
        assertEquals(original, new StudMap(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonStudMapStorage.saveStudMap(original, filePath);
        readBack = jsonStudMapStorage.readStudMap(filePath).get();
        assertEquals(original, new StudMap(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonStudMapStorage.saveStudMap(original); // file path not specified
        readBack = jsonStudMapStorage.readStudMap().get(); // file path not specified
        assertEquals(original, new StudMap(readBack));

    }

    @Test
    public void saveStudMap_nullStudMap_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStudMap(null, "SomeFile.json"));
    }

    /**
     * Saves {@code studMap} at the specified {@code filePath}.
     */
    private void saveStudMap(ReadOnlyStudMap studMap, String filePath) {
        try {
            new JsonStudMapStorage(Paths.get(filePath))
                    .saveStudMap(studMap, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveStudMap_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStudMap(new StudMap(), null));
    }
}
