package seedu.intrack.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.intrack.testutil.Assert.assertThrows;
import static seedu.intrack.testutil.TypicalInternships.ALICE;
import static seedu.intrack.testutil.TypicalInternships.HOON;
import static seedu.intrack.testutil.TypicalInternships.IDA;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.intrack.commons.exceptions.DataConversionException;
import seedu.intrack.model.InTrack;
import seedu.intrack.model.ReadOnlyInTrack;

public class JsonInTrackStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonInTrackStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readInTrack_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInTrack(null));
    }

    private java.util.Optional<ReadOnlyInTrack> readInTrack(String filePath) throws Exception {
        return new JsonInTrackStorage(Paths.get(filePath)).readInTrack(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInTrack("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readInTrack("notJsonFormatInTrack.json"));
    }

    @Test
    public void readInTrack_invalidInternshipInTrack_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInTrack("invalidInternshipInTrack.json"));
    }

    @Test
    public void readInTrack_invalidAndValidInternshipInTrack_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInTrack("invalidAndValidInternshipInTrack.json"));
    }

    @Test
    public void readAndSaveInTrack_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempInTrack.json");
        InTrack original = getTypicalInTrack();
        JsonInTrackStorage jsonInTrackStorage = new JsonInTrackStorage(filePath);

        // Save in new file and read back
        jsonInTrackStorage.saveInTrack(original, filePath);
        ReadOnlyInTrack readBack = jsonInTrackStorage.readInTrack(filePath).get();
        assertEquals(original, new InTrack(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addInternship(HOON);
        original.removeInternship(ALICE);
        jsonInTrackStorage.saveInTrack(original, filePath);
        readBack = jsonInTrackStorage.readInTrack(filePath).get();
        assertEquals(original, new InTrack(readBack));

        // Save and read without specifying file path
        original.addInternship(IDA);
        jsonInTrackStorage.saveInTrack(original); // file path not specified
        readBack = jsonInTrackStorage.readInTrack().get(); // file path not specified
        assertEquals(original, new InTrack(readBack));

    }

    @Test
    public void saveInTrack_nullInTrack_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInTrack(null, "SomeFile.json"));
    }

    /**
     * Saves {@code inTrack} at the specified {@code filePath}.
     */
    private void saveInTrack(ReadOnlyInTrack inTrack, String filePath) {
        try {
            new JsonInTrackStorage(Paths.get(filePath))
                    .saveInTrack(inTrack, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveInTrack_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInTrack(new InTrack(), null));
    }
}
