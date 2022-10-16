package seedu.uninurse.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalPersons.ALICE;
import static seedu.uninurse.testutil.TypicalPersons.HOON;
import static seedu.uninurse.testutil.TypicalPersons.IDA;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.uninurse.commons.exceptions.DataConversionException;
import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.UninurseBook;

public class JsonUninurseBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUninurseBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readUninurseBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUninurseBook(null));
    }

    private java.util.Optional<ReadOnlyUninurseBook> readUninurseBook(String filePath) throws Exception {
        return new JsonUninurseBookStorage(Paths.get(filePath)).readUninurseBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readUninurseBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readUninurseBook("notJsonFormatUninurseBook.json"));
    }

    @Test
    public void readUninurseBook_invalidPersonUninurseBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readUninurseBook("invalidPersonUninurseBook.json"));
    }

    @Test
    public void readUninurseBook_invalidAndValidPersonUninurseBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readUninurseBook("invalidAndValidPersonUninurseBook.json"));
    }

    @Test
    public void readAndSaveUninurseBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempUninurseBook.json");
        UninurseBook original = getTypicalUninurseBook();
        JsonUninurseBookStorage jsonUninurseBookStorage = new JsonUninurseBookStorage(filePath);

        // Save in new file and read back
        jsonUninurseBookStorage.saveUninurseBook(original, filePath);
        ReadOnlyUninurseBook readBack = jsonUninurseBookStorage.readUninurseBook(filePath).get();
        assertEquals(original, new UninurseBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonUninurseBookStorage.saveUninurseBook(original, filePath);
        readBack = jsonUninurseBookStorage.readUninurseBook(filePath).get();
        assertEquals(original, new UninurseBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonUninurseBookStorage.saveUninurseBook(original); // file path not specified
        readBack = jsonUninurseBookStorage.readUninurseBook().get(); // file path not specified
        assertEquals(original, new UninurseBook(readBack));

    }

    @Test
    public void saveUninurseBook_nullUninurseBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUninurseBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code uninurseBook} at the specified {@code filePath}.
     */
    private void saveUninurseBook(ReadOnlyUninurseBook uninurseBook, String filePath) {
        try {
            new JsonUninurseBookStorage(Paths.get(filePath))
                    .saveUninurseBook(uninurseBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveUninurseBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUninurseBook(new UninurseBook(), null));
    }
}
