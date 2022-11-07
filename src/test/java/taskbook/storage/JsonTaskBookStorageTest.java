package taskbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import taskbook.commons.exceptions.DataConversionException;
import taskbook.model.ReadOnlyTaskBook;
import taskbook.model.TaskBook;
import taskbook.testutil.Assert;
import taskbook.testutil.TypicalTaskBook;

public class JsonTaskBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readTaskBook(null));
    }

    private java.util.Optional<ReadOnlyTaskBook> readTaskBook(String filePath) throws Exception {
        return new JsonTaskBookStorage(Paths.get(filePath)).readTaskBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, () -> readTaskBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readTaskBook_invalidPersonTaskBook_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, ()
                -> readTaskBook("invalidPersonTaskBook.json"));
    }

    @Test
    public void readTaskBook_invalidAndValidPersonTaskBook_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, ()
                -> readTaskBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveTaskBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        TaskBook original = TypicalTaskBook.getTypicalTaskBook();
        JsonTaskBookStorage jsonTaskBookStorage = new JsonTaskBookStorage(filePath);

        // Save in new file and read back
        jsonTaskBookStorage.saveTaskBook(original, filePath);
        ReadOnlyTaskBook readBack = jsonTaskBookStorage.readTaskBook(filePath).get();
        assertEquals(original, new TaskBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(TypicalTaskBook.HOON);
        original.removePerson(TypicalTaskBook.FIONA); // v1.2: Must not remove person who has a task
        jsonTaskBookStorage.saveTaskBook(original, filePath);
        readBack = jsonTaskBookStorage.readTaskBook(filePath).get();
        assertEquals(original, new TaskBook(readBack));

        // Save and read without specifying file path
        original.addPerson(TypicalTaskBook.IDA);
        jsonTaskBookStorage.saveTaskBook(original); // file path not specified
        readBack = jsonTaskBookStorage.readTaskBook().get(); // file path not specified
        assertEquals(original, new TaskBook(readBack));
    }

    // TODO: Test if person is removed all his/her tasks are removed such that readAndSaveTaskBook all in order

    @Test
    public void saveTaskBook_nullTaskBook_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, ()
                -> saveTaskBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code TaskBook} at the specified {@code filePath}.
     */
    private void saveTaskBook(ReadOnlyTaskBook taskBook, String filePath) {
        try {
            new JsonTaskBookStorage(Paths.get(filePath))
                    .saveTaskBook(taskBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaskBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveTaskBook(new TaskBook(), null));
    }
}
