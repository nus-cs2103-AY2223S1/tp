package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.CARLS;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalArchivedTaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ArchivedTaskList;
import seedu.address.model.ReadOnlyTaskList;

public class JsonArchivedTaskListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonArchivedTaskListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readArchivedTaskBook(null));
    }

    private java.util.Optional<ReadOnlyTaskList> readArchivedTaskBook(String filePath) throws Exception {
        return new JsonArchivedTaskListStorage(Paths.get(filePath))
                .readArchivedTaskList(addToTestDataPathIfNotNull(filePath));
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
        assertThrows(DataConversionException.class, () -> readArchivedTaskBook("notJsonFormatArchivedTaskList.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readArchivedTaskBook("invalidTaskArchivedTaskList.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidTaskArchivedTaskBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readArchivedTaskBook("invalidAndValidTaskArchivedTaskList.json"));
    }

    @Test
    public void readAndSaveArchivedTaskBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempArchivedTaskList.json");
        ArchivedTaskList original = getTypicalArchivedTaskList();
        JsonArchivedTaskListStorage jsonArchivedTaskBookStorage = new JsonArchivedTaskListStorage(filePath);


        // Save in new file and read back
        jsonArchivedTaskBookStorage.saveArchivedTaskList(original, filePath);
        ReadOnlyTaskList readBack = jsonArchivedTaskBookStorage.readArchivedTaskList(filePath).get();
        assertEquals(original, new ArchivedTaskList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(HOON);
        original.removeTask(CARLS);
        jsonArchivedTaskBookStorage.saveArchivedTaskList(original, filePath);
        readBack = jsonArchivedTaskBookStorage.readArchivedTaskList(filePath).get();
        assertEquals(original, new ArchivedTaskList(readBack));

        // Save and read without specifying file path
        original.addTask(IDA);
        jsonArchivedTaskBookStorage.saveArchivedTaskList(original); // file path not specified
        readBack = jsonArchivedTaskBookStorage.readArchivedTaskList().get(); // file path not specified
        assertEquals(original, new ArchivedTaskList(readBack));
    }

    @Test
    public void saveArchivedTaskBook_nullArchivedTaskBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveArchivedTaskBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveArchivedTaskBook(ReadOnlyTaskList archivedTaskBook, String filePath) {
        try {
            new JsonArchivedTaskListStorage(Paths.get(filePath))
                    .saveArchivedTaskList(archivedTaskBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveArchivedTaskBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveArchivedTaskBook(new ArchivedTaskList(), null));
    }

}
