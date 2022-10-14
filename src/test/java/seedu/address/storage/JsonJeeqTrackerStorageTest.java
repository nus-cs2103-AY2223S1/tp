package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.ALICE;
import static seedu.address.testutil.TypicalCompanies.HOON;
import static seedu.address.testutil.TypicalCompanies.IDA;
import static seedu.address.testutil.TypicalCompanies.getTypicalJeeqTracker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.JeeqTracker;
import seedu.address.model.ReadOnlyJeeqTracker;

public class JsonJeeqTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonJeeqTrackerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readJeeqTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readJeeqTracker(null));
    }

    private java.util.Optional<ReadOnlyJeeqTracker> readJeeqTracker(String filePath) throws Exception {
        return new JsonJeeqTrackerStorage(Paths.get(filePath)).readJeeqTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readJeeqTracker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readJeeqTracker("notJsonFormatJeeqTracker.json"));
    }

    @Test
    public void readJeeqTracker_invalidCompanyJeeqTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readJeeqTracker("invalidCompanyJeeqTracker.json"));
    }

    @Test
    public void readJeeqTracker_invalidAndValidCompanyJeeqTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readJeeqTracker("invalidAndValidCompanyJeeqTracker.json"));
    }

    @Test
    public void readAndSaveJeeqTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempJeeqTracker.json");
        JeeqTracker original = getTypicalJeeqTracker();
        JsonJeeqTrackerStorage jsonJeeqTrackerStorage = new JsonJeeqTrackerStorage(filePath);

        // Save in new file and read back
        jsonJeeqTrackerStorage.saveJeeqTracker(original, filePath);
        ReadOnlyJeeqTracker readBack = jsonJeeqTrackerStorage.readJeeqTracker(filePath).get();
        assertEquals(original, new JeeqTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addClient(HOON);
        original.removeClient(ALICE);
        jsonJeeqTrackerStorage.saveJeeqTracker(original, filePath);
        readBack = jsonJeeqTrackerStorage.readJeeqTracker(filePath).get();
        assertEquals(original, new JeeqTracker(readBack));

        // Save and read without specifying file path
        original.addClient(IDA);
        jsonJeeqTrackerStorage.saveJeeqTracker(original); // file path not specified
        readBack = jsonJeeqTrackerStorage.readJeeqTracker().get(); // file path not specified
        assertEquals(original, new JeeqTracker(readBack));

    }

    @Test
    public void saveJeeqTracker_nullJeeqTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveJeeqTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code jeeqTracker} at the specified {@code filePath}.
     */
    private void saveJeeqTracker(ReadOnlyJeeqTracker jeeqTracker, String filePath) {
        try {
            new JsonJeeqTrackerStorage(Paths.get(filePath))
                    .saveJeeqTracker(jeeqTracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveJeeqTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveJeeqTracker(new JeeqTracker(), null));
    }
}
