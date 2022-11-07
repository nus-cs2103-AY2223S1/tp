package nus.climods.storage.module;

import static nus.climods.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.openapitools.client.api.ModulesApi;
import org.openapitools.client.model.ModuleCondensed;

import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.model.module.ReadOnlyModuleSummaryList;
import nus.climods.storage.module.summary.JsonModuleSummaryListStorage;

class JsonModuleSummaryListStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonModuleSummaryListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readModuleSummary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readModuleSummaryList(null));
    }

    private Optional<ReadOnlyModuleSummaryList> readModuleSummaryList(String filePath) throws Exception {
        return new JsonModuleSummaryListStorage(Paths.get(filePath))
            .readModuleSummaryList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readModuleSummaryList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readModuleSummaryList(
            "notJsonFormatModuleSummaryList.json"));
    }

    @Test
    public void readValidJsonFormat_success() throws Exception {
        assertTrue(readModuleSummaryList("validJsonFormatModuleSummaryList.json").isPresent());
    }

    /**
     * Calls the API to fetch the module list data and saves in a temporary folder. Then, checks that the data received
     * from API is the same as the data read from storage.
     *
     * @throws Exception any exception that may occur.
     */
    @Test
    public void saveAndReadModuleSummaryList_success() throws Exception {
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
