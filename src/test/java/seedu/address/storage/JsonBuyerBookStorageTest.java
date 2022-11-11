package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.ALICE;
import static seedu.address.testutil.TypicalBuyers.HOON;
import static seedu.address.testutil.TypicalBuyers.IDA;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;

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
    public void readBuyerBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readBuyerBook(null));
    }

    private java.util.Optional<ReadOnlyBuyerBook> readBuyerBook(String filePath) throws Exception {
        return new JsonBuyerBookStorage(Paths.get(filePath)).readBuyerBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readBuyerBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readBuyerBook("notJsonFormatBuyerBook.json"));
    }

    @Test
    public void readBuyerBook_invalidBuyerBuyerBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readBuyerBook("invalidBuyerBuyerBook.json"));
    }

    @Test
    public void readBuyerBook_invalidAndValidBuyerBuyerBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readBuyerBook("invalidAndValidBuyerBuyerBook.json"));
    }

    @Test
    public void readAndSaveBuyerBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempBuyerBook.json");
        BuyerBook original = getTypicalBuyersBook();
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
    public void saveBuyerBook_nullBuyerBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBuyerBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code buyerBook} at the specified {@code filePath}.
     */
    private void saveBuyerBook(ReadOnlyBuyerBook buyerBook, String filePath) {
        try {
            new JsonBuyerBookStorage(Paths.get(filePath))
                    .saveBuyerBook(buyerBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveBuyerBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBuyerBook(new BuyerBook(), null));
    }
}
