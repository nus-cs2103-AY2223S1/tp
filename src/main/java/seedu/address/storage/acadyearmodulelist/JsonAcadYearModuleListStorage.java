package seedu.address.storage.acadyearmodulelist;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.openapitools.client.model.ModuleCondensed;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAcadYearModuleList;

/**
 * A class to access AcadYearModuleList data stored as a json file on the hard disk.
 */
public class JsonAcadYearModuleListStorage implements AcadYearModuleListStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonAcadYearModuleListStorage.class);

    private final Path filePath;

    public JsonAcadYearModuleListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAcadYearModuleListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAcadYearModuleList> readAcadYearModuleList() throws DataConversionException {
        return readAcadYearModuleList(filePath);
    }

    /**
     * Similar to {@link #readAcadYearModuleList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAcadYearModuleList> readAcadYearModuleList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAcadYearModuleList> jsonAcadYearModuleList = JsonUtil.readJsonFile(
                filePath, JsonSerializableAcadYearModuleList.class);
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
        JsonUtil.saveJsonFile(new JsonSerializableAcadYearModuleList(moduleCondensedList), filePath);
    }

}
