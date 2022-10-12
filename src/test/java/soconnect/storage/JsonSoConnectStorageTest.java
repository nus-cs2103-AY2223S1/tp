package soconnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static soconnect.testutil.Assert.assertThrows;
import static soconnect.testutil.TypicalPersons.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import soconnect.commons.exceptions.DataConversionException;
import soconnect.model.SoConnect;
import soconnect.model.ReadOnlySoConnect;

public class JsonSoConnectStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSoConnectStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSoConnect_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSoConnect(null));
    }

    private java.util.Optional<ReadOnlySoConnect> readSoConnect(String filePath) throws Exception {
        return new JsonSoConnectStorage(Paths.get(filePath)).readSoConnect(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSoConnect("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSoConnect("notJsonFormatSoConnect.json"));
    }

    @Test
    public void readSoConnect_invalidPersonSoConnect_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSoConnect("invalidPersonSoConnect.json"));
    }

    @Test
    public void readSoConnect_invalidAndValidPersonSoConnect_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSoConnect("invalidAndValidPersonSoConnect.json"));
    }

    @Test
    public void readAndsaveSoConnect_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSoConnect.json");
        SoConnect original = getTypicalSoConnect();
        JsonSoConnectStorage jsonSoConnectStorage = new JsonSoConnectStorage(filePath);

        // Save in new file and read back
        jsonSoConnectStorage.saveSoConnect(original, filePath);
        ReadOnlySoConnect readBack = jsonSoConnectStorage.readSoConnect(filePath).get();
        assertEquals(original, new SoConnect(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonSoConnectStorage.saveSoConnect(original, filePath);
        readBack = jsonSoConnectStorage.readSoConnect(filePath).get();
        assertEquals(original, new SoConnect(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonSoConnectStorage.saveSoConnect(original); // file path not specified
        readBack = jsonSoConnectStorage.readSoConnect().get(); // file path not specified
        assertEquals(original, new SoConnect(readBack));

    }

    @Test
    public void saveSoConnect_nullSoConnect_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSoConnect(null, "SomeFile.json"));
    }

    /**
     * Saves {@code soConnect} at the specified {@code filePath}.
     */
    private void saveSoConnect(ReadOnlySoConnect soConnect, String filePath) {
        try {
            new JsonSoConnectStorage(Paths.get(filePath))
                    .saveSoConnect(soConnect, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSoConnect_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSoConnect(new SoConnect(), null));
    }
}
