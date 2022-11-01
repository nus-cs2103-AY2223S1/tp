package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.BuyerBook;
import seedu.address.model.ReadOnlyBuyerBook;

public class JsonBuyerBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonBuyerBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPersonBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPersonBook(null));
    }

    private java.util.Optional<ReadOnlyBuyerBook> readPersonBook(String filePath) throws Exception {
        return new JsonBuyerBookStorage(Paths.get(filePath)).readBuyerBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPersonBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPersonBook("notJsonFormatPersonBook.json"));
    }

    @Test
    public void readPersonBook_invalidPersonPersonBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPersonBook("invalidPersonPersonBook.json"));
    }

    @Test
    public void readPersonBook_invalidAndValidPersonPersonBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPersonBook("invalidAndValidPersonPersonBook.json"));
    }

    @Test
    public void readAndSavePersonBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPersonBook.json");
        BuyerBook original = getTypicalPersonsBook();
        JsonBuyerBookStorage jsonBuyerBookStorage = new JsonBuyerBookStorage(filePath);

        // Save in new file and read back
        jsonBuyerBookStorage.saveBuyerBook(original, filePath);
        ReadOnlyBuyerBook readBack = jsonBuyerBookStorage.readBuyerBook(filePath).get();
        assertEquals(original, new BuyerBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addBuyer(HOON);
        original.removeBuyer(ALICE);
        jsonBuyerBookStorage.saveBuyerBook(original, filePath);
        readBack = jsonBuyerBookStorage.readBuyerBook(filePath).get();
        assertEquals(original, new BuyerBook(readBack));

        // Save and read without specifying file path
        original.addBuyer(IDA);
        jsonBuyerBookStorage.saveBuyerBook(original); // file path not specified
        readBack = jsonBuyerBookStorage.readBuyerBook().get(); // file path not specified
        assertEquals(original, new BuyerBook(readBack));

    }

    @Test
    public void savePersonBook_nullPersonBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePersonBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code personBook} at the specified {@code filePath}.
     */
    private void savePersonBook(ReadOnlyBuyerBook personBook, String filePath) {
        try {
            new JsonBuyerBookStorage(Paths.get(filePath))
                    .saveBuyerBook(personBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePersonBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePersonBook(new BuyerBook(), null));
    }
}
