package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntry.GROCERIES;
import static seedu.address.testutil.TypicalEntry.SUPPER;
import static seedu.address.testutil.TypicalEntry.getTypicalAddressBook;
//import static seedu.address.testutil.TypicalPersons.ALICE;
//import static seedu.address.testutil.TypicalPersons.HOON;
//import static seedu.address.testutil.TypicalPersons.IDA;
//import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

//import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PennyWise;
import seedu.address.model.ReadOnlyPennyWise;

public class JsonPennyWiseStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPennyWiseStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyPennyWise> readAddressBook(String filePath) throws Exception {
        return new JsonPennyWiseStorage(Paths.get(filePath)).readPennyWise(addToTestDataPathIfNotNull(filePath));
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

    //    @Test
    //    public void read_notJsonFormat_exceptionThrown() {
    //        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    //    }

    //    @Test
    //    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
    //        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
    //    }

    //    @Test
    //    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
    //        assertThrows(DataConversionException.class, () ->
    //        readAddressBook("invalidAndValidPersonAddressBook.json"));
    //    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        PennyWise original = getTypicalAddressBook();
        JsonPennyWiseStorage jsonAddressBookStorage = new JsonPennyWiseStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.savePennyWise(original, filePath);
        ReadOnlyPennyWise readBack = jsonAddressBookStorage.readPennyWise(filePath).get();
        assertEquals(original, new PennyWise(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addExpenditure(GROCERIES);
        //        original.addPerson(HOON);
        //        original.removePerson(ALICE);
        jsonAddressBookStorage.savePennyWise(original, filePath);
        readBack = jsonAddressBookStorage.readPennyWise(filePath).get();
        assertEquals(original, new PennyWise(readBack));

        // Save and read without specifying file path
        original.addExpenditure(SUPPER);
        jsonAddressBookStorage.savePennyWise(original); // file path not specified
        readBack = jsonAddressBookStorage.readPennyWise().get(); // file path not specified
        assertEquals(original, new PennyWise(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyPennyWise addressBook, String filePath) {
        try {
            new JsonPennyWiseStorage(Paths.get(filePath))
                    .savePennyWise(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new PennyWise(), null));
    }
}
