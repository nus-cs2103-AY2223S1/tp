package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.HOON;
import static seedu.address.testutil.TypicalPatients.IDA;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.HealthContact;
import seedu.address.model.ReadOnlyHealthContact;

public class JsonHealthContactStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonHealthContactStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readHealthContact_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readHealthContact(null));
    }

    private java.util.Optional<ReadOnlyHealthContact> readHealthContact(String filePath) throws Exception {
        return new JsonHealthContactStorage(Paths.get(filePath))
                .readHealthContact(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readHealthContact("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readHealthContact("notJsonFormatHealthContact.json"));
    }

    @Test
    public void readHealthContact_invalidPatientHealthContact_throwDataConversionException() {
        assertThrows(
                DataConversionException.class, () -> readHealthContact("invalidPatientHealthContact.json"));
    }

    @Test
    public void readHealthContact_invalidAndValidPatientHealthContact_throwDataConversionException() {
        assertThrows(
                DataConversionException.class, () -> readHealthContact(
                        "invalidAndValidPatientHealthContact.json"));
    }

    @Test
    public void readAndSaveHealthContact_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempHealthContact.json");
        HealthContact original = getTypicalPatientsHealthContact();
        JsonHealthContactStorage jsonHealthContactStorage = new JsonHealthContactStorage(filePath);

        // Save in new file and read back
        jsonHealthContactStorage.saveHealthContact(original, filePath);
        ReadOnlyHealthContact readBack = jsonHealthContactStorage.readHealthContact(filePath).get();
        assertEquals(original, new HealthContact(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPatient(HOON);
        original.removePatient(ALICE);
        jsonHealthContactStorage.saveHealthContact(original, filePath);
        readBack = jsonHealthContactStorage.readHealthContact(filePath).get();
        assertEquals(original, new HealthContact(readBack));

        // Save and read without specifying file path
        original.addPatient(IDA);
        jsonHealthContactStorage.saveHealthContact(original); // file path not specified
        readBack = jsonHealthContactStorage.readHealthContact().get(); // file path not specified
        assertEquals(original, new HealthContact(readBack));

    }

    @Test
    public void saveHealthContact_nullHealthContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHealthContact(null, "SomeFile.json"));
    }

    /**
     * Saves {@code healthContact} at the specified {@code filePath}.
     */
    private void saveHealthContact(ReadOnlyHealthContact healthContact, String filePath) {
        try {
            new JsonHealthContactStorage(Paths.get(filePath))
                    .saveHealthContact(healthContact, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveHealthContact_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHealthContact(new HealthContact(), null));
    }
}
