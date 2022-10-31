package seedu.watson.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.watson.testutil.Assert.assertThrows;
import static seedu.watson.testutil.TypicalStudents.ALICE;
import static seedu.watson.testutil.TypicalStudents.HOON;
import static seedu.watson.testutil.TypicalStudents.IDA;
import static seedu.watson.testutil.TypicalStudents.getTypicalDatabase;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.watson.commons.exceptions.DataConversionException;
import seedu.watson.model.Database;
import seedu.watson.model.ReadOnlyDatabase;

public class JsonDatabaseStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonDatabaseStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDatabase(null));
    }

    private java.util.Optional<ReadOnlyDatabase> readDatabase(String filePath) throws Exception {
        return new JsonDatabaseStorage(Paths.get(filePath)).readDatabase(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
               ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
               : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDatabase("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readDatabase("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Database original = getTypicalDatabase();
        JsonDatabaseStorage jsonDatabaseStorage = new JsonDatabaseStorage(filePath);

        // Save in new file and read back
        jsonDatabaseStorage.saveDatabase(original, filePath);
        ReadOnlyDatabase readBack = jsonDatabaseStorage.readDatabase(filePath).get();
        assertEquals(original, new Database(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonDatabaseStorage.saveDatabase(original, filePath);
        readBack = jsonDatabaseStorage.readDatabase(filePath).get();
        assertEquals(original, new Database(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonDatabaseStorage.saveDatabase(original); // file path not specified
        readBack = jsonDatabaseStorage.readDatabase().get(); // file path not specified
        assertEquals(original, new Database(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyDatabase addressBook, String filePath) {
        try {
            new JsonDatabaseStorage(Paths.get(filePath))
                .saveDatabase(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new Database(), null));
    }
}
