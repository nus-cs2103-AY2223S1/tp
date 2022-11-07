package seedu.hrpro.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.hrpro.testutil.Assert.assertThrows;
import static seedu.hrpro.testutil.TypicalHrPro.getTypicalHrPro;
import static seedu.hrpro.testutil.TypicalProjects.APPLE;
import static seedu.hrpro.testutil.TypicalProjects.HOON;
import static seedu.hrpro.testutil.TypicalProjects.IDA;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.hrpro.commons.exceptions.DataConversionException;
import seedu.hrpro.model.HrPro;
import seedu.hrpro.model.ReadOnlyHrPro;

public class JsonHrProStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonHrProStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readHrPro_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readHrPro(null));
    }

    private java.util.Optional<ReadOnlyHrPro> readHrPro(String filePath) throws Exception {
        return new JsonHrProStorage(Paths.get(filePath)).readHrPro(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readHrPro("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readHrPro("notJsonFormatHrPro.json"));
    }

    @Test
    public void readHrPro_invalidProjectHrPro_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHrPro("invalidProjectHrPro.json"));
    }

    @Test
    public void readHrPro_invalidAndValidProjectHrPro_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHrPro("invalidAndValidProjectHrPro.json"));
    }

    @Test
    public void readAndSaveHrPro_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempHrPro.json");
        HrPro original = getTypicalHrPro();
        JsonHrProStorage jsonHrProStorage = new JsonHrProStorage(filePath);

        // Save in new file and read back
        jsonHrProStorage.saveHrPro(original, filePath);
        ReadOnlyHrPro readBack = jsonHrProStorage.readHrPro(filePath).get();
        assertEquals(original, new HrPro(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addProject(HOON);
        original.removeProject(APPLE);
        jsonHrProStorage.saveHrPro(original, filePath);
        readBack = jsonHrProStorage.readHrPro(filePath).get();
        assertEquals(original, new HrPro(readBack));

        // Save and read without specifying file path
        original.addProject(IDA);
        jsonHrProStorage.saveHrPro(original); // file path not specified
        readBack = jsonHrProStorage.readHrPro().get(); // file path not specified
        assertEquals(original, new HrPro(readBack));

    }

    @Test
    public void saveHrPro_nullHrPro_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHrPro(null, "SomeFile.json"));
    }

    /**
     * Saves {@code hrPro} at the specified {@code filePath}.
     */
    private void saveHrPro(ReadOnlyHrPro hrPro, String filePath) {
        try {
            new JsonHrProStorage(Paths.get(filePath))
                    .saveHrPro(hrPro, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveHrPro_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHrPro(new HrPro(), null));
    }
}
