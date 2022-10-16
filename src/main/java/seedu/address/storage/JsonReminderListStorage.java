package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.reminder.ReadOnlyReminderList;

import static java.util.Objects.requireNonNull;

/**
 * A class to access ReminderList data stored as a json file on the hard disk.
 */
public class JsonReminderListStorage implements ReminderListStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonReminderListStorage.class);

    private Path filePath;

    public JsonReminderListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getReminderListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyReminderList> readReminderList() throws DataConversionException {
        return readReminderList(filePath);
    }

    /**
     * Similar to {@link #readReminderList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyReminderList> readReminderList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableReminderList> jsonReminderList = JsonUtil.readJsonFile(
                filePath, JsonSerializableReminderList.class);
        if (!jsonReminderList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonReminderList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveReminderList(ReadOnlyReminderList reminderList) throws IOException {
        saveReminderList(reminderList, filePath);
    }

    /**
     * Similar to {@link #saveReminderList(ReadOnlyReminderList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveReminderList(ReadOnlyReminderList reminderList, Path filePath) throws IOException {
        requireNonNull(reminderList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableReminderList(reminderList), filePath);
    }

}
