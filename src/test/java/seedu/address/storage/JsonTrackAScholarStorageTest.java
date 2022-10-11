package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalApplicants.HOON;
import static seedu.address.testutil.TypicalApplicants.IDA;
import static seedu.address.testutil.TypicalApplicants.getTypicalTrackAScholar;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTrackAScholar;
import seedu.address.model.TrackAScholar;

public class JsonTrackAScholarStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonTrackAScholarStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTrackAScholar_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTrackAScholar(null));
    }

    private java.util.Optional<ReadOnlyTrackAScholar> readTrackAScholar(String filePath) throws Exception {
        return new JsonTrackAScholarStorage(Paths.get(filePath))
                .readTrackAScholar(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTrackAScholar("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTrackAScholar("notJsonFormatTrackAScholar.json"));
    }

    @Test
    public void readTrackAScholar_invalidApplicantTrackAScholar_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readTrackAScholar("invalidApplicantTrackAScholar.json"));
    }

    @Test
    public void readTrackAScholar_invalidAndValidApplicantTrackAScholar_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readTrackAScholar("invalidAndValidApplicantTrackAScholar.json"));
    }

    @Test
    public void readAndSaveTrackAScholar_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTrackAScholar.json");
        TrackAScholar original = getTypicalTrackAScholar();
        JsonTrackAScholarStorage jsonTrackAScholarStorage = new JsonTrackAScholarStorage(filePath);

        // Save in new file and read back
        jsonTrackAScholarStorage.saveTrackAScholar(original, filePath);
        ReadOnlyTrackAScholar readBack = jsonTrackAScholarStorage.readTrackAScholar(filePath).get();
        assertEquals(original, new TrackAScholar(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addApplicant(HOON);
        original.removeApplicant(ALICE);
        jsonTrackAScholarStorage.saveTrackAScholar(original, filePath);
        readBack = jsonTrackAScholarStorage.readTrackAScholar(filePath).get();
        assertEquals(original, new TrackAScholar(readBack));

        // Save and read without specifying file path
        original.addApplicant(IDA);
        jsonTrackAScholarStorage.saveTrackAScholar(original); // file path not specified
        readBack = jsonTrackAScholarStorage.readTrackAScholar().get(); // file path not specified
        assertEquals(original, new TrackAScholar(readBack));

    }

    @Test
    public void saveTrackAScholar_nullTrackAScholar_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrackAScholar(null, "SomeFile.json"));
    }

    /**
     * Saves {@code trackAScholar} at the specified {@code filePath}.
     */
    private void saveTrackAScholar(ReadOnlyTrackAScholar trackAScholar, String filePath) {
        try {
            new JsonTrackAScholarStorage(Paths.get(filePath))
                    .saveTrackAScholar(trackAScholar, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTrackAScholar_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrackAScholar(new TrackAScholar(), null));
    }
}
