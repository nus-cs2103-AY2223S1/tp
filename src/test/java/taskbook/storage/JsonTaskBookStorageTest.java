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
import taskbook.testutil.TypicalPersons;

public class JsonTaskBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readtaskBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readtaskBook(null));
    }

    private java.util.Optional<ReadOnlyTaskBook> readtaskBook(String filePath) throws Exception {
        return new JsonTaskBookStorage(Paths.get(filePath)).readTaskBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readtaskBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, () -> readtaskBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readtaskBook_invalidPersontaskBook_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, ()
                -> readtaskBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readtaskBook_invalidAndValidPersontaskBook_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, ()
                -> readtaskBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSavetaskBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        TaskBook original = TypicalPersons.getTypicalTaskBook();
        JsonTaskBookStorage jsontaskBookStorage = new JsonTaskBookStorage(filePath);

        // Save in new file and read back
        jsontaskBookStorage.saveTaskBook(original, filePath);
        ReadOnlyTaskBook readBack = jsontaskBookStorage.readTaskBook(filePath).get();
        assertEquals(original, new TaskBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(TypicalPersons.HOON);
        original.removePerson(TypicalPersons.ALICE);
        jsontaskBookStorage.saveTaskBook(original, filePath);
        readBack = jsontaskBookStorage.readTaskBook(filePath).get();
        assertEquals(original, new TaskBook(readBack));

        // Save and read without specifying file path
        original.addPerson(TypicalPersons.IDA);
        jsontaskBookStorage.saveTaskBook(original); // file path not specified
        readBack = jsontaskBookStorage.readTaskBook().get(); // file path not specified
        assertEquals(original, new TaskBook(readBack));

    }

    @Test
    public void savetaskBook_nulltaskBook_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, ()
                -> savetaskBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code taskBook} at the specified {@code filePath}.
     */
    private void savetaskBook(ReadOnlyTaskBook taskBook, String filePath) {
        try {
            new JsonTaskBookStorage(Paths.get(filePath))
                    .saveTaskBook(taskBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savetaskBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> savetaskBook(new TaskBook(), null));
    }
}
