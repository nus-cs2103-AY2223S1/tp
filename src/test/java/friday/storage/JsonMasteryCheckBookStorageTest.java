package friday.storage;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

// import friday.commons.exceptions.DataConversionException;
import friday.model.Friday;
import friday.model.ReadOnlyFriday;
import friday.testutil.TypicalStudents;

public class JsonMasteryCheckBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMasteryCheckBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyFriday> readAddressBook(String filePath) throws Exception {
        return new JsonFridayStorage(Paths.get(filePath)).readFriday(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    /*
    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonAddressBook.json"));
    }
    */

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Friday original = TypicalStudents.getTypicalAddressBook();
        JsonFridayStorage jsonFridayStorage = new JsonFridayStorage(filePath);

        // Save in new file and read back
        jsonFridayStorage.saveFriday(original, filePath);
        ReadOnlyFriday readBack = jsonFridayStorage.readFriday(filePath).get();
        assertEquals(original, new Friday(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(TypicalStudents.HOON);
        original.removePerson(TypicalStudents.ALICE);
        jsonFridayStorage.saveFriday(original, filePath);
        readBack = jsonFridayStorage.readFriday(filePath).get();
        assertEquals(original, new Friday(readBack));

        // Save and read without specifying file path
        original.addPerson(TypicalStudents.IDA);
        jsonFridayStorage.saveFriday(original); // file path not specified
        readBack = jsonFridayStorage.readFriday().get(); // file path not specified
        assertEquals(original, new Friday(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyFriday addressBook, String filePath) {
        try {
            new JsonFridayStorage(Paths.get(filePath))
                    .saveFriday(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new Friday(), null));
    }
}
