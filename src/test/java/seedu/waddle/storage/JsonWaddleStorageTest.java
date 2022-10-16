package seedu.waddle.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalItineraries.*;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.waddle.commons.exceptions.DataConversionException;
import seedu.waddle.model.ReadOnlyWaddle;
import seedu.waddle.model.Waddle;

public class JsonWaddleStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonWaddleStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readWaddle_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readWaddle(null));
    }

    private java.util.Optional<ReadOnlyWaddle> readWaddle(String filePath) throws Exception {
        return new JsonWaddleStorage(Paths.get(filePath)).readWaddle(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readWaddle("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readWaddle("notJsonFormatWaddle.json"));
    }

    @Test
    public void readWaddle_invalidItineraryWaddle_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readWaddle("invalidItineraryWaddle.json"));
    }

    @Test
    public void readWaddle_invalidAndValidItineraryWaddle_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readWaddle("invalidAndValidItineraryWaddle.json"));
    }

    @Test
    public void readAndSaveWaddle_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempWaddle.json");
        Waddle original = getTypicalWaddle();
        JsonWaddleStorage jsonWaddleStorage = new JsonWaddleStorage(filePath);

        // Save in new file and read back
        jsonWaddleStorage.saveWaddle(original, filePath);
        ReadOnlyWaddle readBack = jsonWaddleStorage.readWaddle(filePath).get();
        assertEquals(original, new Waddle(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addItinerary(GRADUATION);
        original.removeItinerary(SUMMER);
        jsonWaddleStorage.saveWaddle(original, filePath);
        readBack = jsonWaddleStorage.readWaddle(filePath).get();
        assertEquals(original, new Waddle(readBack));

        // Save and read without specifying file path
        original.addItinerary(AUTUMN);
        jsonWaddleStorage.saveWaddle(original); // file path not specified
        readBack = jsonWaddleStorage.readWaddle().get(); // file path not specified
        assertEquals(original, new Waddle(readBack));

    }

    @Test
    public void saveWaddle_nullWaddle_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWaddle(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveWaddle(ReadOnlyWaddle waddle, String filePath) {
        try {
            new JsonWaddleStorage(Paths.get(filePath))
                    .saveWaddle(waddle, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveWaddle_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWaddle(new Waddle(), null));
    }
}
