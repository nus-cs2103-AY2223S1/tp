package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.HOON;
import static seedu.address.testutil.TypicalStudents.IDA;
import static seedu.address.testutil.TypicalStudents.getTypicalProfNus;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ProfNus;
import seedu.address.model.ReadOnlyProfNus;

public class JsonProfNusStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonProfNusStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readProfNus_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readProfNus(null));
    }

    private java.util.Optional<ReadOnlyProfNus> readProfNus(String filePath) throws Exception {
        return new JsonProfNusStorage(Paths.get(filePath)).readProfNus(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readProfNus("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readProfNus("notJsonFormatProfNus.json"));
    }

    @Test
    public void readProfNus_invalidPersonProfNus_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readProfNus("invalidPersonProfNus.json"));
    }

    @Test
    public void readProfNus_invalidAndValidPersonProfNus_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readProfNus("invalidAndValidPersonProfNus.json"));
    }

    @Test
    public void readAndSaveProfNus_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempProfNus.json");
        ProfNus original = getTypicalProfNus();
        JsonProfNusStorage jsonProfNusStorage = new JsonProfNusStorage(filePath);

        // Save in new file and read back
        jsonProfNusStorage.saveProfNus(original, filePath);
        ReadOnlyProfNus readBack = jsonProfNusStorage.readProfNus(filePath).get();
        assertEquals(original, new ProfNus(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonProfNusStorage.saveProfNus(original, filePath);
        readBack = jsonProfNusStorage.readProfNus(filePath).get();
        assertEquals(original, new ProfNus(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonProfNusStorage.saveProfNus(original); // file path not specified
        readBack = jsonProfNusStorage.readProfNus().get(); // file path not specified
        assertEquals(original, new ProfNus(readBack));

    }

    @Test
    public void saveProfNus_nullProfNus_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProfNus(null, "SomeFile.json"));
    }

    /**
     * Saves {@code profNus} at the specified {@code filePath}.
     */
    private void saveProfNus(ReadOnlyProfNus profNus, String filePath) {
        try {
            new JsonProfNusStorage(Paths.get(filePath))
                    .saveProfNus(profNus, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveProfNus_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProfNus(new ProfNus(), null));
    }
}
