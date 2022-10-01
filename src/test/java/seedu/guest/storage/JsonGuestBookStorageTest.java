package seedu.guest.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.guest.testutil.Assert.assertThrows;
import static seedu.guest.testutil.TypicalPersons.ALICE;
import static seedu.guest.testutil.TypicalPersons.HOON;
import static seedu.guest.testutil.TypicalPersons.IDA;
import static seedu.guest.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.guest.commons.exceptions.DataConversionException;
import seedu.guest.model.GuestBook;
import seedu.guest.model.ReadOnlyGuestBook;

public class JsonGuestBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonGuestBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyGuestBook> readAddressBook(String filePath) throws Exception {
        return new JsonGuestBookStorage(Paths.get(filePath)).readGuestBook(addToTestDataPathIfNotNull(filePath));
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

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatGuestBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidGuestGuestBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidGuestGuestBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        GuestBook original = getTypicalAddressBook();
        JsonGuestBookStorage jsonAddressBookStorage = new JsonGuestBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveGuestBook(original, filePath);
        ReadOnlyGuestBook readBack = jsonAddressBookStorage.readGuestBook(filePath).get();
        assertEquals(original, new GuestBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addGuest(HOON);
        original.removeGuest(ALICE);
        jsonAddressBookStorage.saveGuestBook(original, filePath);
        readBack = jsonAddressBookStorage.readGuestBook(filePath).get();
        assertEquals(original, new GuestBook(readBack));

        // Save and read without specifying file path
        original.addGuest(IDA);
        jsonAddressBookStorage.saveGuestBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readGuestBook().get(); // file path not specified
        assertEquals(original, new GuestBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyGuestBook addressBook, String filePath) {
        try {
            new JsonGuestBookStorage(Paths.get(filePath))
                    .saveGuestBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new GuestBook(), null));
    }
}
