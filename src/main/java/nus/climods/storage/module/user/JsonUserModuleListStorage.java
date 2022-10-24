package nus.climods.storage.module.user;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import nus.climods.commons.core.LogsCenter;
import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.commons.util.FileUtil;
import nus.climods.commons.util.JsonUtil;
import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.module.UniqueUserModuleList;
import nus.climods.storage.exceptions.StorageException;

/**
 * A class to access UserModuleList data stored as a json file on the hard disk.
 */
public class JsonUserModuleListStorage implements UserModuleListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUserModuleListStorage.class);

    private final Path filePath;

    /**
     * Creates JsonUserModuleListStorage with
     * @param filePath of the stored json
     */
    public JsonUserModuleListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getUserModuleListFilePath() {
        return filePath;
    }

    @Override
    public Optional<UniqueUserModuleList> readUserModuleList() throws DataConversionException {
        return readUserModuleList(filePath);
    }

    /**
     * Similar to {@link #readUserModuleList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<UniqueUserModuleList> readUserModuleList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableUserModuleList> jsonUserModuleList = JsonUtil.readJsonFile(
            filePath, JsonSerializableUserModuleList.class);
        if (jsonUserModuleList.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUserModuleList.get().toModelType());
        } catch (StorageException | CommandException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveUserModuleList(UniqueUserModuleList userModuleList) throws StorageException {
        try {
            saveUserModuleList(userModuleList, filePath);
        } catch (IOException e) {
            throw new StorageException(e.getMessage(), e);
        }
    }

    /**
     * Similar to {@link #saveUserModuleList(UniqueUserModuleList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveUserModuleList(UniqueUserModuleList userModuleList, Path filePath) throws IOException {
        requireNonNull(userModuleList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUserModuleList(convertToList(userModuleList)), filePath);
    }

    private List<JsonAdaptedUserModule> convertToList(UniqueUserModuleList modules) {
        return modules.asUnmodifiableObservableList().stream().map(JsonAdaptedUserModule::new)
                .collect(Collectors.toList());
    }
}
