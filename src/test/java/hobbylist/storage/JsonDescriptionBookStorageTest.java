package hobbylist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import hobbylist.commons.exceptions.DataConversionException;
import hobbylist.model.HobbyList;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.testutil.Assert;
import hobbylist.testutil.TypicalActivities;

public class JsonDescriptionBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonHobbyListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readHobbyList_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readHobbyList(null));
    }

    private java.util.Optional<ReadOnlyHobbyList> readHobbyList(String filePath) throws Exception {
        return new JsonHobbyListStorage(Paths.get(filePath)).readHobbyList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readHobbyList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, () -> readHobbyList("notJsonFormatHobbyList.json"));
    }

    @Test
    public void readHobbyList_invalidActivityHobbyList_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readHobbyList("invalidActivityHobbyList.json"));
    }

    @Test
    public void readHobbyList_invalidAndValidActivityHobbyList_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, ()
                -> readHobbyList("invalidAndValidActivityHobbyList.json"));
    }

    @Test
    public void readAndSaveHobbyList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempHobbyList.json");
        HobbyList original = TypicalActivities.getTypicalHobbyList();
        JsonHobbyListStorage jsonHobbyListStorage = new JsonHobbyListStorage(filePath);

        // Save in new file and read back
        jsonHobbyListStorage.saveHobbyList(original, filePath);
        ReadOnlyHobbyList readBack = jsonHobbyListStorage.readHobbyList(filePath).get();
        assertEquals(original, new HobbyList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addActivity(TypicalActivities.ACTIVITY_H);
        original.removeActivity(TypicalActivities.ACTIVITY_A);
        jsonHobbyListStorage.saveHobbyList(original, filePath);
        readBack = jsonHobbyListStorage.readHobbyList(filePath).get();
        assertEquals(original, new HobbyList(readBack));

        // Save and read without specifying file path
        original.addActivity(TypicalActivities.ACTIVITY_I);
        jsonHobbyListStorage.saveHobbyList(original); // file path not specified
        readBack = jsonHobbyListStorage.readHobbyList().get(); // file path not specified
        assertEquals(original, new HobbyList(readBack));

    }

    @Test
    public void saveHobbyList_nullHobbyList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveHobbyList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code hobbyList} at the specified {@code filePath}.
     */
    private void saveHobbyList(ReadOnlyHobbyList hobbyList, String filePath) {
        try {
            new JsonHobbyListStorage(Paths.get(filePath))
                    .saveHobbyList(hobbyList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveHobbyList_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveHobbyList(new HobbyList(), null));
    }
}
