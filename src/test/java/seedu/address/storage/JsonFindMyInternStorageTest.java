package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ALIBABA;
import static seedu.address.testutil.TypicalInternships.HUAWEI;
import static seedu.address.testutil.TypicalInternships.INDEED;
import static seedu.address.testutil.TypicalInternships.getTypicalFindMyIntern;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FindMyIntern;
import seedu.address.model.ReadOnlyFindMyIntern;

public class JsonFindMyInternStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFindMyInternStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFindMyIntern_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFindMyIntern(null));
    }

    private java.util.Optional<ReadOnlyFindMyIntern> readFindMyIntern(String filePath) throws Exception {
        return new JsonFindMyInternStorage(Paths.get(filePath)).readFindMyIntern(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFindMyIntern("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFindMyIntern("notJsonFormatFindMyIntern.json"));
    }

    @Test
    public void readFindMyIntern_invalidFindMyIntern_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFindMyIntern("invalidFindMyIntern.json"));
    }

    @Test
    public void readFindMyIntern_invalidAndValidFindMyIntern_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFindMyIntern("invalidAndValidFindMyIntern.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFindMyIntern.json");
        FindMyIntern original = getTypicalFindMyIntern();
        JsonFindMyInternStorage jsonAddressBookStorage = new JsonFindMyInternStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveFindMyIntern(original, filePath);
        ReadOnlyFindMyIntern readBack = jsonAddressBookStorage.readFindMyIntern(filePath).get();
        assertEquals(original, new FindMyIntern(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addInternship(HUAWEI);
        original.removeInternship(ALIBABA);
        jsonAddressBookStorage.saveFindMyIntern(original, filePath);
        readBack = jsonAddressBookStorage.readFindMyIntern(filePath).get();
        assertEquals(original, new FindMyIntern(readBack));

        // Save and read without specifying file path
        original.addInternship(INDEED);
        jsonAddressBookStorage.saveFindMyIntern(original); // file path not specified
        readBack = jsonAddressBookStorage.readFindMyIntern().get(); // file path not specified
        assertEquals(original, new FindMyIntern(readBack));

    }

    @Test
    public void saveFindMyIntern_nullFindMyIntern_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFindMyIntern(null, "SomeFile.json"));
    }

    /**
     * Saves {@code findMyIntern} at the specified {@code filePath}.
     */
    private void saveFindMyIntern(ReadOnlyFindMyIntern findMyIntern, String filePath) {
        try {
            new JsonFindMyInternStorage(Paths.get(filePath))
                    .saveFindMyIntern(findMyIntern, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFindMyIntern_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFindMyIntern(new FindMyIntern(), null));
    }
}
