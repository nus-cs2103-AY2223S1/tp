package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;

/**
 * A class to access Tasks stored in the hard disk as a json file
 */
public class JsonTaskStorage implements TaskStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonTaskStorage.class);

    private Path filePath;

    public JsonTaskStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getTaskListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException {
        return readTaskList(filePath);
    }

    /**
     * Similar to {@link #readTaskList()}
     * @param tasksFilePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public Optional<ReadOnlyTaskList> readTaskList(Path tasksFilePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            // TODO: JsonSerializableTaskList
            return Optional.of(new TaskList());
        } catch (Exception e) {
            logger.info("Illegal values found in " + filePath + ": " + e.getMessage());
            throw new DataConversionException(e);
        }
    }
}
