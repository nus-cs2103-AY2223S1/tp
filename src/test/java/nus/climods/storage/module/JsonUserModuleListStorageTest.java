package nus.climods.storage.module;

import static nus.climods.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.model.module.UniqueUserModuleList;
import nus.climods.model.module.UserModule;
import nus.climods.storage.module.user.JsonUserModuleListStorage;

class JsonUserModuleListStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUserModuleListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readModuleSummary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUserModuleList(null));
    }

    private Optional<UniqueUserModuleList> readUserModuleList(String filePath) throws Exception {
        return new JsonUserModuleListStorage(Paths.get(filePath))
            .readUserModuleList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readUserModuleList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readUserModuleList(
            "notJsonFormatModuleSummaryList.json"));
    }

    @Test
    public void readValidJsonFormat_success() throws Exception {
        assertTrue(readUserModuleList("validJsonFormatUserModuleList.json").isPresent());
    }

    /**
     * Calls the API to fetch the module list data and saves in a temporary folder. Then, checks that the data received
     * from API is the same as the data read from storage.
     *
     * @throws Exception any exception that may occur.
     */
    @Test
    public void saveAndReadUserModuleList_success() throws Exception {
        Path filePath = testFolder.resolve("TempUserModuleList.json");
        UniqueUserModuleList data = new UniqueUserModuleList();
        data.add(new UserModule("CS2103", "Friday 1400-1500", "Friday 1600-1700", "S1"));
        data.add(new UserModule("CS2030S", "Friday 1400-1500", "Monday 1600-1700", "S2"));
        data.add(new UserModule("CS2040S", "Tuesday 1400-1500", "Monday 1600-1700", "ST2"));

        JsonUserModuleListStorage jsonUserModuleListStorage = new JsonUserModuleListStorage(filePath);

        // Saving from UniqueUserList
        jsonUserModuleListStorage.saveUserModuleList(data);

        Optional<UniqueUserModuleList> optionalReadBack = jsonUserModuleListStorage
            .readUserModuleList(filePath);
        assertTrue(optionalReadBack.isPresent());
        UniqueUserModuleList readBack = optionalReadBack.get();
        assertEquals(data, readBack);
    }
}
