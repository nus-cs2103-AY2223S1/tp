package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTaskList;

/**
 * A class to access TaskList data stored as a json file on the hard disk.
 */
public class JsonArchivedTaskListStorage implements ArchivedTaskListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonArchivedTaskListStorage.class);

    private final Path filePath;

    public JsonArchivedTaskListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getArchivedTaskListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskList> readArchivedTaskList() throws DataConversionException, IOException {
        return readArchivedTaskList(filePath);
    }

    /**
     * Similar to {@link #readArchivedTaskList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyTaskList> readArchivedTaskList(Path filePath)
            throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableArchivedTaskList> jsonTaskList = JsonUtil.readJsonFile(
                filePath, JsonSerializableArchivedTaskList.class);

        if (!jsonTaskList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveArchivedTaskList(ReadOnlyTaskList addressBook) throws IOException {
        saveArchivedTaskList(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveArchivedTaskList(ReadOnlyTaskList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveArchivedTaskList(ReadOnlyTaskList addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableArchivedTaskList(addressBook), filePath);
    }


}
