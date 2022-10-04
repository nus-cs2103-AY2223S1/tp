package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.HOON;
import static seedu.address.testutil.TypicalClients.IDA;
import static seedu.address.testutil.TypicalClients.getTypicalClientBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ClientBook;
import seedu.address.model.ReadOnlyClientBook;

public class JsonClientBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonClientBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readClientBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readClientBook(null));
    }

    private java.util.Optional<ReadOnlyClientBook> readClientBook(String filePath) throws Exception {
        return new JsonClientBookStorage(Paths.get(filePath)).readClientBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readClientBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readClientBook("notJsonFormatClientBook.json"));
    }

    @Test
    public void readClientBook_invalidClientClientBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readClientBook("invalidPersonClientBook.json"));
    }

    @Test
    public void readClientBook_invalidAndValidClientClientBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readClientBook("invalidAndValidClientClientBook.json"));
    }

    @Test
    public void readAndSaveClientBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempClientBook.json");
        ClientBook original = getTypicalClientBook();
        JsonClientBookStorage jsonClientBookStorage = new JsonClientBookStorage(filePath);

        // Save in new file and read back
        jsonClientBookStorage.saveClientBook(original, filePath);
        ReadOnlyClientBook readBack = jsonClientBookStorage.readClientBook(filePath).get();
        assertEquals(original, new ClientBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addClient(HOON);
        original.removeClient(ALICE);
        jsonClientBookStorage.saveClientBook(original, filePath);
        readBack = jsonClientBookStorage.readClientBook(filePath).get();
        assertEquals(original, new ClientBook(readBack));

        // Save and read without specifying file path
        original.addClient(IDA);
        jsonClientBookStorage.saveClientBook(original); // file path not specified
        readBack = jsonClientBookStorage.readClientBook().get(); // file path not specified
        assertEquals(original, new ClientBook(readBack));

    }

    @Test
    public void saveClientBook_nullClientBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClientBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code clientBook} at the specified {@code filePath}.
     */
    private void saveClientBook(ReadOnlyClientBook clientBook, String filePath) {
        try {
            new JsonClientBookStorage(Paths.get(filePath))
                    .saveClientBook(clientBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveClientBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClientBook(new ClientBook(), null));
    }
}
