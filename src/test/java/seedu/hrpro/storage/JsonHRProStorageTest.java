package seedu.hrpro.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.hrpro.testutil.Assert.assertThrows;
import static seedu.hrpro.testutil.TypicalHRPro.getTypicalHRPro;
import static seedu.hrpro.testutil.TypicalProjects.APPLE;
import static seedu.hrpro.testutil.TypicalProjects.HOON;
import static seedu.hrpro.testutil.TypicalProjects.IDA;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.hrpro.commons.exceptions.DataConversionException;
import seedu.hrpro.model.HRPro;
import seedu.hrpro.model.ReadOnlyHRPro;

public class JsonHRProStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonHRProStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readHRPro_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readHRPro(null));
    }

    private java.util.Optional<ReadOnlyHRPro> readHRPro(String filePath) throws Exception {
        return new JsonHRProStorage(Paths.get(filePath)).readHRPro(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readHRPro("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readHRPro("notJsonFormatHRPro.json"));
    }

    @Test
    public void readHRPro_invalidProjectHRPro_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHRPro("invalidProjectHRPro.json"));
    }

    @Test
    public void readHRPro_invalidAndValidProjectHRPro_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHRPro("invalidAndValidProjectHRPro.json"));
    }

    @Test
    public void readAndSaveHRPro_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempHRPro.json");
        HRPro original = getTypicalHRPro();
        JsonHRProStorage jsonHRProStorage = new JsonHRProStorage(filePath);

        // Save in new file and read back
        jsonHRProStorage.saveHRPro(original, filePath);
        ReadOnlyHRPro readBack = jsonHRProStorage.readHRPro(filePath).get();
        assertEquals(original, new HRPro(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addProject(HOON);
        original.removeProject(APPLE);
        jsonHRProStorage.saveHRPro(original, filePath);
        readBack = jsonHRProStorage.readHRPro(filePath).get();
        assertEquals(original, new HRPro(readBack));

        // Save and read without specifying file path
        original.addProject(IDA);
        jsonHRProStorage.saveHRPro(original); // file path not specified
        readBack = jsonHRProStorage.readHRPro().get(); // file path not specified
        assertEquals(original, new HRPro(readBack));

    }

    @Test
    public void saveHRPro_nullHRPro_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHRPro(null, "SomeFile.json"));
    }

    /**
     * Saves {@code hrPro} at the specified {@code filePath}.
     */
    private void saveHRPro(ReadOnlyHRPro hrPro, String filePath) {
        try {
            new JsonHRProStorage(Paths.get(filePath))
                    .saveHRPro(hrPro, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveHRPro_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHRPro(new HRPro(), null));
    }
}
