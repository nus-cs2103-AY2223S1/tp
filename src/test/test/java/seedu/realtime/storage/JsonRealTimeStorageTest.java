package seedu.realtime.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.realtime.testutil.Assert.assertThrows;
import static seedu.realtime.testutil.TypicalPersons.ALICE;
import static seedu.realtime.testutil.TypicalPersons.HOON;
import static seedu.realtime.testutil.TypicalPersons.IDA;
import static seedu.realtime.testutil.TypicalPersons.getTypicalRealTime;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.realtime.commons.exceptions.DataConversionException;
import seedu.realtime.model.realTime;
import seedu.realtime.model.ReadOnlyRealTime;

public class JsonRealTimeStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonRealTimeStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readRealTime_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readRealTime(null));
    }

    private java.util.Optional<ReadOnlyRealTime> readRealTime(String filePath) throws Exception {
        return new JsonRealTimeStorage(Paths.get(filePath)).readRealTime(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readRealTime("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readRealTime("notJsonFormatRealTime.json"));
    }

    @Test
    public void readRealTime_invalidPersonRealTime_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRealTime("invalidPersonRealTime.json"));
    }

    @Test
    public void readRealTime_invalidAndValidPersonRealTime_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRealTime("invalidAndValidPersonRealTime.json"));
    }

    @Test
    public void readAndSaveRealTime_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempRealTime.json");
        RealTime original = getTypicalRealTime();
        JsonRealTimeStorage jsonRealTimeStorage = new JsonRealTimeStorage(filePath);

        // Save in new file and read back
        jsonRealTimeStorage.saveRealTime(original, filePath);
        ReadOnlyRealTime readBack = jsonRealTimeStorage.readRealTime(filePath).get();
        assertEquals(original, new realTime(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonRealTimeStorage.saveRealTime(original, filePath);
        readBack = jsonRealTimeStorage.readRealTime(filePath).get();
        assertEquals(original, new realTime(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonRealTimeStorage.saveRealTime(original); // file path not specified
        readBack = jsonRealTimeStorage.readRealTime().get(); // file path not specified
        assertEquals(original, new realTime(readBack));

    }

    @Test
    public void saveRealTime_nullRealTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRealTime(null, "SomeFile.json"));
    }

    /**
     * Saves {@code RealTime} at the specified {@code filePath}.
     */
    private void saveRealTime(ReadOnlyRealTime realTime, String filePath) {
        try {
            new JsonRealTimeStorage(Paths.get(filePath))
                    .saveRealTime(realTime, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveRealTime_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRealTime(new RealTime(), null));
    }
}
