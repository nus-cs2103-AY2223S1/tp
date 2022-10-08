package nus.climods.storage.acadyearmodulelist;

import static nus.climods.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.openapitools.client.api.ModulesApi;
import org.openapitools.client.model.ModuleCondensed;

import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.model.module.ReadOnlyAcadYearModuleList;

class JsonAcadYearModuleListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAcadYearModuleListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAcadYearModuleList(null));
    }

    private Optional<ReadOnlyAcadYearModuleList> readAcadYearModuleList(String filePath) throws Exception {
        return new JsonAcadYearModuleListStorage(Paths.get(filePath))
            .readAcadYearModuleList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAcadYearModuleList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAcadYearModuleList(
            "notJsonFormatAcadYearModuleList.json"));
    }

    @Test
    public void readValidJsonFormat_success() throws Exception {
        Optional<ReadOnlyAcadYearModuleList> readOnlyAcadYearModuleList =
            readAcadYearModuleList("validJsonFormatAcadYearModuleList.json");
    }

    /**
     * Calls the API to fetch the module list data and saves in a temporary folder.
     * Then, checks that the data received from API is the same as the data read from storage.
     * @throws Exception any exception that may occur.
     */
    @Test
    public void saveAndReadAcadYearModuleList_success() throws Exception {
        Path filePath = testFolder.resolve("TempAcadYearModuleList.json");
        ModulesApi modulesApi = new ModulesApi();
        List<ModuleCondensed> data = modulesApi.acadYearModuleListJsonGet("2022-2023");
        JsonAcadYearModuleListStorage jsonAcadYearModuleListStorage = new JsonAcadYearModuleListStorage(filePath);

        jsonAcadYearModuleListStorage.saveAcadYearModuleList(data);

        ReadOnlyAcadYearModuleList readBack = jsonAcadYearModuleListStorage.readAcadYearModuleList(filePath).get();
        List<ModuleCondensed> readBackData = readBack.getModuleList();
        assertEquals(data, readBackData);
    }
}
