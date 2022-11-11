package seedu.modquik.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.modquik.testutil.Assert.assertThrows;
import static seedu.modquik.testutil.TypicalStudents.ALICE;
import static seedu.modquik.testutil.TypicalStudents.HOON;
import static seedu.modquik.testutil.TypicalStudents.IDA;
import static seedu.modquik.testutil.TypicalStudents.getTypicalModQuik;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.modquik.commons.exceptions.DataConversionException;
import seedu.modquik.model.ModQuik;
import seedu.modquik.model.ReadOnlyModQuik;

public class JsonModQuikStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readModQuik_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readModQuik(null));
    }

    private java.util.Optional<ReadOnlyModQuik> readModQuik(String filePath) throws Exception {
        return new JsonModQuikStorage(Paths.get(filePath)).readModQuik(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readModQuik("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readModQuik("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readModQuik("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readModQuik("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        ModQuik original = getTypicalModQuik();
        JsonModQuikStorage jsonModQuikStorage = new JsonModQuikStorage(filePath);

        // Save in new file and read back
        jsonModQuikStorage.saveModQuik(original, filePath);
        ReadOnlyModQuik readBack = jsonModQuikStorage.readModQuik(filePath).get();
        assertEquals(original, new ModQuik(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonModQuikStorage.saveModQuik(original, filePath);
        readBack = jsonModQuikStorage.readModQuik(filePath).get();
        assertEquals(original, new ModQuik(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonModQuikStorage.saveModQuik(original); // file path not specified
        readBack = jsonModQuikStorage.readModQuik().get(); // file path not specified
        assertEquals(original, new ModQuik(readBack));

    }

    @Test
    public void saveModQuik_nullModQuik_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveModQuik(null, "SomeFile.json"));
    }

    /**
     * Saves {@code modQuik} at the specified {@code filePath}.
     */
    private void saveModQuik(ReadOnlyModQuik modQuik, String filePath) {
        try {
            new JsonModQuikStorage(Paths.get(filePath))
                    .saveModQuik(modQuik, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveModQuik_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveModQuik(new ModQuik(), null));
    }
}
