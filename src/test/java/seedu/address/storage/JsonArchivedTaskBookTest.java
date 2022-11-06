package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.CARLS;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalArchivedTaskBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ArchivedTaskBook;
import seedu.address.model.ReadOnlyAddressBook;

public class JsonArchivedTaskBookTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonArchivedTaskBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readArchivedTaskBook(null));
    }

    private java.util.Optional<ReadOnlyAddressBook> readArchivedTaskBook(String filePath) throws Exception {
        return new JsonArchivedTaskBookStorage(Paths.get(filePath))
                .readArchivedTaskBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readArchivedTaskBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readArchivedTaskBook("notJsonFormatArchivedTaskBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readArchivedTaskBook("invalidTaskArchivedTaskBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidTaskArchivedTaskBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readArchivedTaskBook("invalidAndValidTaskArchivedTaskBook.json"));
    }

    @Test
    public void readAndSaveArchivedTaskBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempArchivedTaskBook.json");
        ArchivedTaskBook original = getTypicalArchivedTaskBook();
        JsonArchivedTaskBookStorage jsonArchivedTaskBookStorage = new JsonArchivedTaskBookStorage(filePath);


        // Save in new file and read back
        jsonArchivedTaskBookStorage.saveArchivedTaskBook(original, filePath);
        ReadOnlyAddressBook readBack = jsonArchivedTaskBookStorage.readArchivedTaskBook(filePath).get();
        assertEquals(original, new ArchivedTaskBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(HOON);
        original.removeTask(CARLS);
        jsonArchivedTaskBookStorage.saveArchivedTaskBook(original, filePath);
        readBack = jsonArchivedTaskBookStorage.readArchivedTaskBook(filePath).get();
        assertEquals(original, new ArchivedTaskBook(readBack));

        // Save and read without specifying file path
        original.addTask(IDA);
        jsonArchivedTaskBookStorage.saveArchivedTaskBook(original); // file path not specified
        readBack = jsonArchivedTaskBookStorage.readArchivedTaskBook().get(); // file path not specified
        assertEquals(original, new ArchivedTaskBook(readBack));
    }

    @Test
    public void saveArchivedTaskBook_nullArchivedTaskBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveArchivedTaskBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveArchivedTaskBook(ReadOnlyAddressBook archivedTaskBook, String filePath) {
        try {
            new JsonArchivedTaskBookStorage(Paths.get(filePath))
                    .saveArchivedTaskBook(archivedTaskBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveArchivedTaskBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveArchivedTaskBook(new ArchivedTaskBook(), null));
    }

}
