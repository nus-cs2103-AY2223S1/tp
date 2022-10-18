package nus.climods.storage.module;

import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.model.module.ReadOnlyModuleSummaryList;
import nus.climods.model.module.UniqueUserModuleList;
import nus.climods.storage.module.summary.JsonModuleSummaryListStorage;
import nus.climods.storage.module.user.JsonUserModuleListStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.openapitools.client.api.ModulesApi;
import org.openapitools.client.model.ModuleCondensed;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static nus.climods.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

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

//    @Test
//    public void read_notJsonFormat_exceptionThrown() {
//        assertThrows(DataConversionException.class, () -> readUserModuleList(
//            "notJsonFormatModuleSummaryList.json"));
//    }

//    @Test
//    public void readInvalidJsonFormat_fail() throws Exception {
//        assertTrue(readUserModuleList("validJsonFormatModuleSummaryList.json").isEmpty());
//    }

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
        Path filePath = testFolder.resolve("TempModuleSummaryList.json");
        ModulesApi modulesApi = new ModulesApi();
        List<ModuleCondensed> data = modulesApi.acadYearModuleListJsonGet("2022-2023");
        JsonModuleSummaryListStorage jsonAcadYearModuleListStorage = new JsonModuleSummaryListStorage(filePath);

        jsonAcadYearModuleListStorage.saveModuleSummaryList(data);

        Optional<ReadOnlyModuleSummaryList> optionalReadBack = jsonAcadYearModuleListStorage
            .readModuleSummaryList(filePath);
        assertTrue(optionalReadBack.isPresent());
        ReadOnlyModuleSummaryList readBack = optionalReadBack.get();
        List<ModuleCondensed> readBackData = readBack.getModuleList();
        assertEquals(data, readBackData);
    }
}
