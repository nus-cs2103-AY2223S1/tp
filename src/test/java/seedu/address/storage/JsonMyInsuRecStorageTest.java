package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.HOON;
import static seedu.address.testutil.TypicalClients.IDA;
import static seedu.address.testutil.TypicalClients.getTypicalMyInsuRec;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.MyInsuRec;
import seedu.address.model.ReadOnlyMyInsuRec;

public class JsonMyInsuRecStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMyInsuRecStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMyInsuRec_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMyInsuRec(null));
    }

    private java.util.Optional<ReadOnlyMyInsuRec> readMyInsuRec(String filePath) throws Exception {
        return new JsonMyInsuRecStorage(Paths.get(filePath)).readMyInsuRec(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMyInsuRec("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readMyInsuRec("notJsonFormatMyInsuRec.json"));
    }

    @Test
    public void readMyInsuRec_invalidClientMyInsuRec_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMyInsuRec("invalidClientMyInsuRec.json"));
    }

    @Test
    public void readMyInsuRec_invalidAndValidClientMyInsuRec_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMyInsuRec("invalidAndValidClientMyInsuRec.json"));
    }

    @Test
    public void readAndSaveMyInsuRec_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMyInsuRec.json");
        MyInsuRec original = getTypicalMyInsuRec();
        JsonMyInsuRecStorage jsonMyInsuRecStorage = new JsonMyInsuRecStorage(filePath);

        // Save in new file and read back
        jsonMyInsuRecStorage.saveMyInsuRec(original, filePath);
        ReadOnlyMyInsuRec readBack = jsonMyInsuRecStorage.readMyInsuRec(filePath).get();
        assertEquals(original, new MyInsuRec(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addClient(HOON);
        original.removeClient(ALICE);
        jsonMyInsuRecStorage.saveMyInsuRec(original, filePath);
        readBack = jsonMyInsuRecStorage.readMyInsuRec(filePath).get();
        assertEquals(original, new MyInsuRec(readBack));

        // Save and read without specifying file path
        original.addClient(IDA);
        jsonMyInsuRecStorage.saveMyInsuRec(original); // file path not specified
        readBack = jsonMyInsuRecStorage.readMyInsuRec().get(); // file path not specified
        assertEquals(original, new MyInsuRec(readBack));

    }

    @Test
    public void saveMyInsuRec_nullMyInsuRec_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMyInsuRec(null, "SomeFile.json"));
    }

    /**
     * Saves {@code myInsuRec} at the specified {@code filePath}.
     */
    private void saveMyInsuRec(ReadOnlyMyInsuRec myInsuRec, String filePath) {
        try {
            new JsonMyInsuRecStorage(Paths.get(filePath))
                    .saveMyInsuRec(myInsuRec, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMyInsuRec_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMyInsuRec(new MyInsuRec(), null));
    }
}
