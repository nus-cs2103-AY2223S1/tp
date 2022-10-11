package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntry.GROCERIES;
import static seedu.address.testutil.TypicalEntry.SUPPER;
import static seedu.address.testutil.TypicalEntry.getTypicalPennyWise;
//import static seedu.address.testutil.TypicalPersons.ALICE;
//import static seedu.address.testutil.TypicalPersons.HOON;
//import static seedu.address.testutil.TypicalPersons.IDA;
//import static seedu.address.testutil.TypicalPersons.getTypicalPennyWise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PennyWise;
import seedu.address.model.ReadOnlyPennyWise;

public class JsonPennyWiseStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPennyWiseStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPennyWise_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPennyWise(null));
    }

    private java.util.Optional<ReadOnlyPennyWise> readPennyWise(String filePath) throws Exception {
        return new JsonPennyWiseStorage(Paths.get(filePath)).readPennyWise(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPennyWise("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPennyWise("notJsonFormatPennyWise.json"));
    }

        @Test
        public void readPennyWise_invalidPersonPennyWise_throwDataConversionException() {
            assertThrows(DataConversionException.class, () -> readPennyWise("invalidEntryPennyWise.json"));
        }

        @Test
        public void readPennyWise_invalidAndValidPersonPennyWise_throwDataConversionException() {
            assertThrows(DataConversionException.class, () ->
            readPennyWise("invalidAndValidEntryPennyWise.json"));
        }

    @Test
    public void readAndSavePennyWise_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPennyWise.json");
        PennyWise original = getTypicalPennyWise();
        JsonPennyWiseStorage jsonPennyWiseStorage = new JsonPennyWiseStorage(filePath);

        // Save in new file and read back
        jsonPennyWiseStorage.savePennyWise(original, filePath);
        ReadOnlyPennyWise readBack = jsonPennyWiseStorage.readPennyWise(filePath).get();
        assertEquals(original, new PennyWise(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addExpenditure(GROCERIES);
        //        original.addPerson(HOON);
        //        original.removePerson(ALICE);
        jsonPennyWiseStorage.savePennyWise(original, filePath);
        readBack = jsonPennyWiseStorage.readPennyWise(filePath).get();
        assertEquals(original, new PennyWise(readBack));

        // Save and read without specifying file path
        original.addExpenditure(SUPPER);
        jsonPennyWiseStorage.savePennyWise(original); // file path not specified
        readBack = jsonPennyWiseStorage.readPennyWise().get(); // file path not specified
        assertEquals(original, new PennyWise(readBack));

    }

    @Test
    public void savePennyWise_nullPennyWise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePennyWise(null, "SomeFile.json"));
    }

    /**
     * Saves {@code PennyWise} at the specified {@code filePath}.
     */
    private void savePennyWise(ReadOnlyPennyWise PennyWise, String filePath) {
        try {
            new JsonPennyWiseStorage(Paths.get(filePath))
                    .savePennyWise(PennyWise, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePennyWise_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePennyWise(new PennyWise(), null));
    }
}
