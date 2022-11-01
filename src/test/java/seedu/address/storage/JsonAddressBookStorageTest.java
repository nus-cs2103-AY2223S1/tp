package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTruthTable;
import seedu.address.model.TruthTable;

public class JsonTruthTableStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTruthTableStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTruthTable_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTruthTable(null));
    }

    private java.util.Optional<ReadOnlyTruthTable> readTruthTable(String filePath) throws Exception {
        return new JsonTruthTableStorage(Paths.get(filePath)).readTruthTable(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTruthTable("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTruthTable("notJsonFormatTruthTable.json"));
    }

    @Test
    public void readTruthTable_invalidPersonTruthTable_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTruthTable("invalidPersonTruthTable.json"));
    }

    @Test
    public void readTruthTable_invalidAndValidPersonTruthTable_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTruthTable("invalidAndValidPersonTruthTable.json"));
    }

    @Test
    public void readAndSaveTruthTable_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTruthTable.json");
        TruthTable original = getTypicalTruthTable();
        JsonTruthTableStorage jsonTruthTableStorage = new JsonTruthTableStorage(filePath);

        // Save in new file and read back
        jsonTruthTableStorage.saveTruthTable(original, filePath);
        ReadOnlyTruthTable readBack = jsonTruthTableStorage.readTruthTable(filePath).get();
        assertEquals(original, new TruthTable(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonTruthTableStorage.saveTruthTable(original, filePath);
        readBack = jsonTruthTableStorage.readTruthTable(filePath).get();
        assertEquals(original, new TruthTable(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonTruthTableStorage.saveTruthTable(original); // file path not specified
        readBack = jsonTruthTableStorage.readTruthTable().get(); // file path not specified
        assertEquals(original, new TruthTable(readBack));

    }

    @Test
    public void saveTruthTable_nullTruthTable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTruthTable(null, "SomeFile.json"));
    }

    /**
     * Saves {@code truthTable} at the specified {@code filePath}.
     */
    private void saveTruthTable(ReadOnlyTruthTable truthTable, String filePath) {
        try {
            new JsonTruthTableStorage(Paths.get(filePath))
                    .saveTruthTable(truthTable, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTruthTable_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTruthTable(TruthTable.createNewTruthTable(), null));
    }
}
