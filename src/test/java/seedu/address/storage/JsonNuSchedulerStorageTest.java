package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.PRACTICE;
import static seedu.address.testutil.TypicalEvents.TUTORIAL;
import static seedu.address.testutil.TypicalNuScheduler.getTypicalNuScheduler;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.HOON;
import static seedu.address.testutil.TypicalProfiles.IDA;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.NuScheduler;
import seedu.address.model.ReadOnlyNuScheduler;

public class JsonNuSchedulerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonNuSchedulerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readNuScheduler_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readNuScheduler(null));
    }

    private java.util.Optional<ReadOnlyNuScheduler> readNuScheduler(String filePath) throws Exception {
        return new JsonNuSchedulerStorage(Paths.get(filePath)).readNuScheduler(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readNuScheduler("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readNuScheduler("notJsonFormatNuScheduler.json"));
    }

    @Test
    public void readNuScheduler_invalidProfileNuScheduler_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readNuScheduler("invalidNameNuScheduler.json"));
    }

    @Test
    public void readNuScheduler_invalidAndValidProfileNuScheduler_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readNuScheduler("invalidPhoneAndValidProfileNuScheduler.json"));
    }

    @Test
    public void readAddressBook_invalidEventAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readNuScheduler("invalidTitleAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidEventAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readNuScheduler("invalidStartAndValidEventAddressBook.json"));
    }

    @Test
    public void readAndSaveNuScheduler_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempNuScheduler.json");
        NuScheduler original = getTypicalNuScheduler();
        JsonNuSchedulerStorage jsonNuSchedulerStorage = new JsonNuSchedulerStorage(filePath);

        // Save in new file and read back
        jsonNuSchedulerStorage.saveNuScheduler(original, filePath);
        ReadOnlyNuScheduler readBack = jsonNuSchedulerStorage.readNuScheduler(filePath).get();
        assertEquals(original, new NuScheduler(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addProfile(HOON);
        original.addEvent(TUTORIAL);
        original.removeProfile(ALICE);
        original.removeEvent(PRACTICE);
        jsonNuSchedulerStorage.saveNuScheduler(original, filePath);
        readBack = jsonNuSchedulerStorage.readNuScheduler(filePath).get();
        assertEquals(original, new NuScheduler(readBack));

        // Save and read without specifying file path
        original.addProfile(IDA);
        jsonNuSchedulerStorage.saveNuScheduler(original); // file path not specified
        readBack = jsonNuSchedulerStorage.readNuScheduler().get(); // file path not specified
        assertEquals(original, new NuScheduler(readBack));

    }

    @Test
    public void saveNuScheduler_nullNuScheduler_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNuScheduler(null, "SomeFile.json"));
    }

    /**
     * Saves {@code nuScheduler} at the specified {@code filePath}.
     */
    private void saveNuScheduler(ReadOnlyNuScheduler nuScheduler, String filePath) {
        try {
            new JsonNuSchedulerStorage(Paths.get(filePath))
                    .saveNuScheduler(nuScheduler, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveNuScheduler_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNuScheduler(new NuScheduler(), null));
    }
}
