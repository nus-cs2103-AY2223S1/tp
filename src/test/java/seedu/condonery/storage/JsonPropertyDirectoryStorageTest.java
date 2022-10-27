package seedu.condonery.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.condonery.testutil.Assert.assertThrows;
import static seedu.condonery.testutil.TypicalProperties.HOON;
import static seedu.condonery.testutil.TypicalProperties.IDA;
import static seedu.condonery.testutil.TypicalProperties.PINNACLE;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.condonery.commons.exceptions.DataConversionException;
import seedu.condonery.model.property.PropertyDirectory;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;

public class JsonPropertyDirectoryStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPropertyDirectoryStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPropertyDirectory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPropertyDirectory(null));
    }

    private java.util.Optional<ReadOnlyPropertyDirectory> readPropertyDirectory(String filePath) throws Exception {
        return new JsonPropertyDirectoryStorage(Paths.get(filePath)).readPropertyDirectory(
                addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPropertyDirectory("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(
                DataConversionException.class,
                //CHECKSTYLE.OFF: SeparatorWrap
                () -> readPropertyDirectory("notJsonFormatPropertyDirectory.json"));
        //CHECKSTYLE.ON
    }

    @Test
    public void readPropertyDirectory_invalidPropertyDirectory_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPropertyDirectory("invalidPropertyDirectory.json"));
    }

    @Test
    public void readPropertyDirectory_invalidAndValidPropertyDirectory_throwDataConversionException() {
        assertThrows(
                DataConversionException.class,
                //CHECKSTYLE.OFF: SeparatorWrap
                () -> readPropertyDirectory("invalidAndValidPropertyDirectory.json")
        //CHECKSTYLE.ON: SeparatorWrap
        );
    }

    @Test
    public void readAndSavePropertyDirectory_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPropertyDirectory.json");
        PropertyDirectory original = getTypicalPropertyDirectory();
        JsonPropertyDirectoryStorage jsonPropertyDirectoryStorage = new JsonPropertyDirectoryStorage(filePath);

        // Save in new file and read back
        jsonPropertyDirectoryStorage.savePropertyDirectory(original, filePath);
        ReadOnlyPropertyDirectory readBack = jsonPropertyDirectoryStorage.readPropertyDirectory(filePath).get();
        assertEquals(original, new PropertyDirectory(readBack, testFolder.resolve("images")));

        // Modify data, overwrite exiting file, and read back
        original.addProperty(HOON);
        original.removeProperty(PINNACLE);
        jsonPropertyDirectoryStorage.savePropertyDirectory(original, filePath);
        readBack = jsonPropertyDirectoryStorage.readPropertyDirectory(filePath).get();
        assertEquals(original, new PropertyDirectory(readBack, testFolder.resolve("images")));

        // Save and read without specifying file path
        original.addProperty(IDA);
        jsonPropertyDirectoryStorage.savePropertyDirectory(original); // file path not specified
        readBack = jsonPropertyDirectoryStorage.readPropertyDirectory().get(); // file path not specified
        assertEquals(original, new PropertyDirectory(readBack, testFolder.resolve("images")));

    }

    @Test
    public void savePropertyDirectory_nullPropertyDirectory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePropertyDirectory(null, "SomeFile.json"));
    }

    /**
     * Saves {@code propertyDirectory} at the specified {@code filePath}.
     */
    private void savePropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory, String filePath) {
        try {
            new JsonPropertyDirectoryStorage(Paths.get(filePath))
                    .savePropertyDirectory(propertyDirectory, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePropertyDirectory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePropertyDirectory(new PropertyDirectory(), null));
    }
}
