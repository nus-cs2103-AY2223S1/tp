package seedu.rc4hdb.storage.venue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalVenues.FUNCTION_ROOM;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM_VENUE_NAME;
import static seedu.rc4hdb.testutil.TypicalVenues.getTypicalVenueBook;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.model.ReadOnlyVenueBook;
import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.storage.venuebook.JsonVenueBookStorage;

/**
 * Unit tests for {@link JsonVenueBookStorage}.
 */
public class JsonVenueBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonVenueBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readVenueBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readVenueBook(null));
    }

    private Optional<ReadOnlyVenueBook> readVenueBook(String filePath) throws Exception {
        return new JsonVenueBookStorage().readVenueBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readVenueBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readVenueBook("notJsonFormatVenueBook"));
    }

    @Test
    public void readVenueBook_invalidVenueVenueBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readVenueBook("invalidVenueVenueBook"));
    }

    @Test
    public void readVenueBook_invalidAndValidVenueVenueBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readVenueBook("invalidAndValidVenueVenueBook"));
    }

    @Test
    public void readAndSaveVenueBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempVenueBook.json");
        VenueBook original = getTypicalVenueBook();
        JsonVenueBookStorage jsonVenueBookStorage = new JsonVenueBookStorage();

        // Save in new file and read back
        jsonVenueBookStorage.saveVenueBook(original, filePath);
        ReadOnlyVenueBook readBack = jsonVenueBookStorage.readVenueBook(filePath).get();
        assertEquals(original, new VenueBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addVenue(FUNCTION_ROOM);
        original.removeVenue(MEETING_ROOM_VENUE_NAME);
        jsonVenueBookStorage.saveVenueBook(original, filePath);
        readBack = jsonVenueBookStorage.readVenueBook(filePath).get();
        assertEquals(original, new VenueBook(readBack));

    }

    @Test
    public void saveVenueBook_nullVenueBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveVenueBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code residentBook} at the specified {@code filePath}.
     */
    private void saveVenueBook(ReadOnlyVenueBook venueBook, String filePath) {
        try {
            new JsonVenueBookStorage()
                    .saveVenueBook(venueBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveVenueBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveVenueBook(new VenueBook(), null));
    }

    @Test
    public void deleteVenueBookFile_nullFilePath_throwsNullPointerException() {
        JsonVenueBookStorage jsonVenueBookStorage = new JsonVenueBookStorage();
        assertThrows(NullPointerException.class, () -> jsonVenueBookStorage.deleteVenueBookFile(null));
    }

    @Test
    public void deleteVenueBookFile_existingFile_fileDeleted() throws Exception {
        JsonVenueBookStorage jsonVenueBookStorage = new JsonVenueBookStorage();
        Path folderPath = testFolder.resolve("ToBeDeleted");
        Path toBeDeleted = folderPath.resolve(JsonVenueBookStorage.VENUE_DATA_PATH);
        FileUtil.createIfMissing(toBeDeleted);
        jsonVenueBookStorage.deleteVenueBookFile(folderPath);
        assertFalse(FileUtil.isFileExists(toBeDeleted));
    }

    @Test
    public void deleteVenueBookFile_fileDoesNotExist_throwsNoSuchFileException() {
        JsonVenueBookStorage jsonVenueBookStorage = new JsonVenueBookStorage();
        Path toBeDeleted = testFolder.resolve("ToBeDeleted.json");
        assertThrows(NoSuchFileException.class, () -> jsonVenueBookStorage.deleteVenueBookFile(toBeDeleted));
    }

    @Test
    public void createVenueBookFile_nullFilePath_throwsNullPointerException() {
        JsonVenueBookStorage jsonVenueBookStorage = new JsonVenueBookStorage();
        assertThrows(NullPointerException.class, () -> jsonVenueBookStorage.createVenueBookFile(null));
    }

    @Test
    public void createVenueBookFile_fileDoesNotExist_fileCreated() throws Exception {
        JsonVenueBookStorage jsonVenueBookStorage = new JsonVenueBookStorage();
        Path folderPath = testFolder.resolve("ToBeCreated");
        jsonVenueBookStorage.createVenueBookFile(folderPath);
        assertTrue(FileUtil.isFileExists(folderPath.resolve(JsonVenueBookStorage.VENUE_DATA_PATH)));
    }

    @Test
    public void createVenueBookFile_fileAlreadyExist_throwsFileAlreadyExistException() throws Exception {
        JsonVenueBookStorage jsonVenueBookStorage = new JsonVenueBookStorage();
        Path toBeCreated = testFolder.resolve("ToBeCreated.json");
        jsonVenueBookStorage.createVenueBookFile(toBeCreated);
        assertThrows(FileAlreadyExistsException.class, () ->
                jsonVenueBookStorage.createVenueBookFile(toBeCreated));
    }

}
