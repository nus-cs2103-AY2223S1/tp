package coydir.storage;

import static coydir.testutil.Assert.assertThrows;
import static coydir.testutil.TypicalPersons.ALICE;
import static coydir.testutil.TypicalPersons.HOON;
import static coydir.testutil.TypicalPersons.IDA;
import static coydir.testutil.TypicalPersons.getTypicalDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import coydir.commons.exceptions.DataConversionException;
import coydir.model.Database;
import coydir.model.ReadOnlyDatabase;
import coydir.testutil.TestUtil;

public class JsonDatabaseStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonDatabaseStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDatabase_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDatabase(null));
    }

    private java.util.Optional<ReadOnlyDatabase> readDatabase(String filePath) throws Exception {
        return new JsonDatabaseStorage(Paths.get(filePath)).readDatabase(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDatabase("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readDatabase("notJsonFormatDatabase.json"));
    }

    @Test
    public void readDatabase_invalidPersonDatabase_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readDatabase("invalidPersonDatabase.json"));
    }

    @Test
    public void readDatabase_invalidAndValidPersonDatabase_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readDatabase("invalidAndValidPersonDatabase.json"));
    }

    @Test
    public void readAndSaveDatabase_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDatabase.json");
        Database original = getTypicalDatabase();
        JsonDatabaseStorage jsonDatabaseStorage = new JsonDatabaseStorage(filePath);

        // Save in new file and read back
        jsonDatabaseStorage.saveDatabase(original, filePath);
        TestUtil.restartEmployeeId(1);
        ReadOnlyDatabase readBack = jsonDatabaseStorage.readDatabase(filePath).get();
        assertEquals(original, new Database(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonDatabaseStorage.saveDatabase(original, filePath);
        TestUtil.restartEmployeeId(1);
        readBack = jsonDatabaseStorage.readDatabase(filePath).get();
        assertEquals(original, new Database(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonDatabaseStorage.saveDatabase(original); // file path not specified
        TestUtil.restartEmployeeId(1);
        readBack = jsonDatabaseStorage.readDatabase().get(); // file path not specified
        assertEquals(original, new Database(readBack));
    }

    @Test
    public void saveDatabase_nullDatabase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDatabase(null, "SomeFile.json"));
    }

    /**
     * Saves {@code database} at the specified {@code filePath}.
     */
    private void saveDatabase(ReadOnlyDatabase database, String filePath) {
        try {
            new JsonDatabaseStorage(Paths.get(filePath))
                    .saveDatabase(database, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveDatabase_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDatabase(new Database(), null));
    }
}
