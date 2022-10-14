package seedu.application.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalApplications.FACEBOOK;
import static seedu.application.testutil.TypicalApplications.GOOGLE;
import static seedu.application.testutil.TypicalApplications.SHOPEE;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.application.commons.exceptions.DataConversionException;
import seedu.application.model.ApplicationBook;
import seedu.application.model.ReadOnlyApplicationBook;

public class JsonApplicationBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonApplicationBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readApplicationBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readApplicationBook(null));
    }

    private java.util.Optional<ReadOnlyApplicationBook> readApplicationBook(String filePath) throws Exception {
        return new JsonApplicationBookStorage(Paths.get(filePath))
                .readApplicationBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readApplicationBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readApplicationBook(
                "notJsonFormatApplicationBook.json"));
    }

    @Test
    public void readApplicationBook_invalidApplicationApplicationBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readApplicationBook(
                "invalidApplicationApplicationBook.json"));
    }

    @Test
    public void readApplicationBook_invalidAndValidApplicationApplicationBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readApplicationBook(
                "invalidAndValidApplicationApplicationBook.json"));
    }

    @Test
    public void readAndSaveApplicationBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempApplicationBook.json");
        ApplicationBook original = getTypicalApplicationBook();
        JsonApplicationBookStorage jsonApplicationBookStorage = new JsonApplicationBookStorage(filePath);

        // Save in new file and read back
        jsonApplicationBookStorage.saveApplicationBook(original, filePath);
        ReadOnlyApplicationBook readBack = jsonApplicationBookStorage.readApplicationBook(filePath).get();
        assertEquals(original, new ApplicationBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addApplication(FACEBOOK);
        original.removeApplication(SHOPEE);
        jsonApplicationBookStorage.saveApplicationBook(original, filePath);
        readBack = jsonApplicationBookStorage.readApplicationBook(filePath).get();
        assertEquals(original, new ApplicationBook(readBack));

        // Save and read without specifying file path
        original.addApplication(GOOGLE);
        jsonApplicationBookStorage.saveApplicationBook(original); // file path not specified
        readBack = jsonApplicationBookStorage.readApplicationBook().get(); // file path not specified
        assertEquals(original, new ApplicationBook(readBack));

    }

    @Test
    public void saveApplicationBook_nullApplicationBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveApplicationBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code applicationBook} at the specified {@code filePath}.
     */
    private void saveApplicationBook(ReadOnlyApplicationBook applicationBook, String filePath) {
        try {
            new JsonApplicationBookStorage(Paths.get(filePath))
                    .saveApplicationBook(applicationBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveApplicationBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveApplicationBook(new ApplicationBook(), null));
    }
}
