package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalSurvin;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Survin;
import seedu.address.model.ReadOnlySurvin;

public class JsonSurvinStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSurvinStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSurvin_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSurvin(null));
    }

    private java.util.Optional<ReadOnlySurvin> readSurvin(String filePath) throws Exception {
        return new JsonSurvinStorage(Paths.get(filePath)).readSurvin(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSurvin("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSurvin("notJsonFormatSurvin.json"));
    }

    @Test
    public void readSurvin_invalidPersonSurvin_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSurvin("invalidPersonSurvin.json"));
    }

    @Test
    public void readSurvin_invalidAndValidPersonSurvin_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSurvin("invalidAndValidPersonSurvin.json"));
    }

    @Test
    public void readAndSaveSurvin_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSurvin.json");
        Survin original = getTypicalSurvin();
        JsonSurvinStorage jsonSurvinStorage = new JsonSurvinStorage(filePath);

        // Save in new file and read back
        jsonSurvinStorage.saveSurvin(original, filePath);
        ReadOnlySurvin readBack = jsonSurvinStorage.readSurvin(filePath).get();
        assertEquals(original, new Survin(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonSurvinStorage.saveSurvin(original, filePath);
        readBack = jsonSurvinStorage.readSurvin(filePath).get();
        assertEquals(original, new Survin(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonSurvinStorage.saveSurvin(original); // file path not specified
        readBack = jsonSurvinStorage.readSurvin().get(); // file path not specified
        assertEquals(original, new Survin(readBack));

    }

    @Test
    public void saveSurvin_nullSurvin_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSurvin(null, "SomeFile.json"));
    }

    /**
     * Saves {@code survin} at the specified {@code filePath}.
     */
    private void saveSurvin(ReadOnlySurvin survin, String filePath) {
        try {
            new JsonSurvinStorage(Paths.get(filePath))
                    .saveSurvin(survin, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSurvin_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSurvin(new Survin(), null));
    }
}
