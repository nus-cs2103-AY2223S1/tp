package seedu.workbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.workbook.testutil.Assert.assertThrows;
import static seedu.workbook.testutil.TypicalInternships.ALICE;
import static seedu.workbook.testutil.TypicalInternships.HOON;
import static seedu.workbook.testutil.TypicalInternships.IDA;
import static seedu.workbook.testutil.TypicalInternships.getTypicalWorkBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.workbook.commons.exceptions.DataConversionException;
import seedu.workbook.model.ReadOnlyWorkBook;
import seedu.workbook.model.WorkBook;

public class JsonWorkBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonWorkBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readWorkBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readWorkBook(null));
    }

    private java.util.Optional<ReadOnlyWorkBook> readWorkBook(String filePath) throws Exception {
        return new JsonWorkBookStorage(Paths.get(filePath)).readWorkBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readWorkBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readWorkBook("notJsonFormatWorkBook.json"));
    }

    @Test
    public void readWorkBook_invalidInternshipWorkBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readWorkBook("invalidInternshipWorkBook.json"));
    }

    @Test
    public void readWorkBook_invalidWorkBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readWorkBook("invalidAndValidInternshipWorkBook.json"));
    }

    @Test
    public void readAndSaveWorkBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempWorkBook.json");
        WorkBook original = getTypicalWorkBook();
        JsonWorkBookStorage jsonWorkBookStorage = new JsonWorkBookStorage(filePath);

        // Save in new file and read back
        jsonWorkBookStorage.saveWorkBook(original, filePath);
        ReadOnlyWorkBook readBack = jsonWorkBookStorage.readWorkBook(filePath).get();
        assertEquals(original, new WorkBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addInternship(HOON);
        original.removeInternship(ALICE);
        jsonWorkBookStorage.saveWorkBook(original, filePath);
        readBack = jsonWorkBookStorage.readWorkBook(filePath).get();
        assertEquals(original, new WorkBook(readBack));

        // Save and read without specifying file path
        original.addInternship(IDA);
        jsonWorkBookStorage.saveWorkBook(original); // file path not specified
        readBack = jsonWorkBookStorage.readWorkBook().get(); // file path not specified
        assertEquals(original, new WorkBook(readBack));

    }

    @Test
    public void saveWorkBook_nullWorkBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWorkBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code workBook} at the specified {@code filePath}.
     */
    private void saveWorkBook(ReadOnlyWorkBook workBook, String filePath) {
        try {
            new JsonWorkBookStorage(Paths.get(filePath))
                    .saveWorkBook(workBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveWorkBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWorkBook(new WorkBook(), null));
    }
}
