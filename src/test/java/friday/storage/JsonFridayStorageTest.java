package friday.storage;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import friday.commons.exceptions.DataConversionException;
import friday.model.Friday;
import friday.model.ReadOnlyFriday;
import friday.testutil.TypicalStudents;

public class JsonFridayStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFridayStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFriday_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFriday(null));
    }

    private java.util.Optional<ReadOnlyFriday> readFriday(String filePath) throws Exception {
        return new JsonFridayStorage(Paths.get(filePath)).readFriday(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFriday("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () ->
                readFriday("notJsonFormatFriday.json"));
    }

    @Test
    public void readFriday_invalidStudentFriday_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readFriday("invalidStudentFriday.json"));
    }

    @Test
    public void readFriday_invalidAndValidStudentFriday_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readFriday("invalidAndValidStudentFriday.json"));
    }


    @Test
    public void readAndSaveFriday_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFriday.json");
        Friday original = TypicalStudents.getTypicalFriday();
        JsonFridayStorage jsonFridayStorage = new JsonFridayStorage(filePath);

        // Save in new file and read back
        jsonFridayStorage.saveFriday(original, filePath);
        ReadOnlyFriday readBack = jsonFridayStorage.readFriday(filePath).get();
        assertEquals(original, new Friday(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(TypicalStudents.HOON);
        original.removeStudent(TypicalStudents.ALICE);
        jsonFridayStorage.saveFriday(original, filePath);
        readBack = jsonFridayStorage.readFriday(filePath).get();
        assertEquals(original, new Friday(readBack));

        // Save and read without specifying file path
        original.addStudent(TypicalStudents.IDA);
        jsonFridayStorage.saveFriday(original); // file path not specified
        readBack = jsonFridayStorage.readFriday().get(); // file path not specified
        assertEquals(original, new Friday(readBack));

    }

    @Test
    public void saveFriday_nullFriday_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveFriday(null, "SomeFile.json"));
    }

    /**
     * Saves {@code Friday} at the specified {@code filePath}.
     */
    private void saveFriday(ReadOnlyFriday friday, String filePath) {
        try {
            new JsonFridayStorage(Paths.get(filePath))
                    .saveFriday(friday, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFriday_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFriday(new Friday(), null));
    }
}
