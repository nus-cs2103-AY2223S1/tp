package nus.climods.storage.module;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.openapitools.client.model.ModuleCondensed;

import nus.climods.commons.core.LogsCenter;
import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.commons.exceptions.IllegalValueException;
import nus.climods.commons.util.FileUtil;
import nus.climods.commons.util.JsonUtil;
import nus.climods.model.module.ReadOnlyModuleSummaryList;

/**
 * A class to access ModuleSummaryList data stored as a json file on the hard disk.
 */
public class JsonModuleSummaryListStorage implements ModuleSummaryListStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonModuleSummaryListStorage.class);

    private final Path filePath;

    public JsonModuleSummaryListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAcadYearModuleListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyModuleSummaryList> readAcadYearModuleList() throws DataConversionException {
        return readAcadYearModuleList(filePath);
    }

    /**
     * Similar to {@link #readAcadYearModuleList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyModuleSummaryList> readAcadYearModuleList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableModuleSummaryList> jsonAcadYearModuleList = JsonUtil.readJsonFile(
                filePath, JsonSerializableModuleSummaryList.class);
        if (jsonAcadYearModuleList.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAcadYearModuleList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    public void saveAcadYearModuleList(List<ModuleCondensed> moduleCondensedList) throws IOException {
        saveAcadYearModuleList(moduleCondensedList, filePath);
    }

    /**
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAcadYearModuleList(List<ModuleCondensed> moduleCondensedList, Path filePath) throws IOException {
        requireNonNull(moduleCondensedList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableModuleSummaryList(moduleCondensedList), filePath);
    }

}
