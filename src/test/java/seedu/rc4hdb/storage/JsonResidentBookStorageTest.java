package seedu.rc4hdb.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.HOON;
import static seedu.rc4hdb.testutil.TypicalResidents.IDA;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ResidentBook;

public class JsonResidentBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonResidentBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readResidentBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readResidentBook(null));
    }

    private Optional<ReadOnlyResidentBook> readResidentBook(String filePath) throws Exception {
        return new JsonResidentBookStorage(Paths.get(filePath)).readResidentBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readResidentBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readResidentBook("notJsonFormatResidentBook.json"));
    }

    @Test
    public void readResidentBook_invalidResidentResidentBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readResidentBook("invalidResidentResidentBook.json"));
    }

    @Test
    public void readResidentBook_invalidAndValidResidentResidentBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readResidentBook("invalidAndValidResidentResidentBook.json"));
    }

    @Test
    public void readAndSaveResidentBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempResidentBook.json");
        ResidentBook original = getTypicalResidentBook();
        JsonResidentBookStorage jsonResidentBookStorage = new JsonResidentBookStorage(filePath);

        // Save in new file and read back
        jsonResidentBookStorage.saveResidentBook(original, filePath);
        ReadOnlyResidentBook readBack = jsonResidentBookStorage.readResidentBook(filePath).get();
        assertEquals(original, new ResidentBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addResident(HOON);
        original.removeResident(ALICE);
        jsonResidentBookStorage.saveResidentBook(original, filePath);
        readBack = jsonResidentBookStorage.readResidentBook(filePath).get();
        assertEquals(original, new ResidentBook(readBack));

        // Save and read without specifying file path
        original.addResident(IDA);
        jsonResidentBookStorage.saveResidentBook(original); // file path not specified
        readBack = jsonResidentBookStorage.readResidentBook().get(); // file path not specified
        assertEquals(original, new ResidentBook(readBack));

    }

    @Test
    public void saveResidentBook_nullResidentBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveResidentBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code residentBook} at the specified {@code filePath}.
     */
    private void saveResidentBook(ReadOnlyResidentBook residentBook, String filePath) {
        try {
            new JsonResidentBookStorage(Paths.get(filePath))
                    .saveResidentBook(residentBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveResidentBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveResidentBook(new ResidentBook(), null));
    }
}
