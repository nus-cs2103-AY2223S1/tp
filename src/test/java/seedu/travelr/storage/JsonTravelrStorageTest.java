package seedu.travelr.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.travelr.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.travelr.commons.exceptions.DataConversionException;
import seedu.travelr.model.ReadOnlyTravelr;
import seedu.travelr.model.Travelr;

public class JsonTravelrStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyTravelr> readAddressBook(String filePath) throws Exception {
        return new JsonTravelrStorage(Paths.get(filePath)).readTravelr(
                addToTestDataPathIfNotNull(filePath));
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
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    //Not working at the moment
    /*
    @Test
    public void readAddressBook_invalidTripAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTravelr("invalidTripAddressBook.json"));
    }
     */

    //Not working at the moment
    /*
    @Test
    public void readAddressBook_invalidAndValidTripAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTravelr("invalidAndValidTripAddressBook.json"));
    }
     */

    //Not working at the moment
    /*
    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        AddressBook original = getTypicalTravelr();
        seedu.travelr.storage.JsonAddressBookStorage jsonAddressBookStorage =
                new seedu.travelr.storage.JsonAddressBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveTravelr(original, filePath);
        ReadOnlyAddressBook readBack = jsonAddressBookStorage.readTravelr(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTrip(MOON);
        original.removeTrip(EUROPE);
        jsonAddressBookStorage.saveTravelr(original, filePath);
        readBack = jsonAddressBookStorage.readTravelr(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Save and read without specifying file path
        original.addTrip(MARS);
        jsonAddressBookStorage.saveTravelr(original); // file path not specified
        readBack = jsonAddressBookStorage.readTravelr().get(); // file path not specified
        assertEquals(original, new AddressBook(readBack));

    }
     */

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyTravelr addressBook, String filePath) {
        try {
            new JsonTravelrStorage(Paths.get(filePath))
                    .saveTravelr(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new Travelr(), null));
    }
}
