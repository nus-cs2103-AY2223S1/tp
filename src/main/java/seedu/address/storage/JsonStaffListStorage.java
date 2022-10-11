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
import seedu.address.model.ReadOnlyStaffList;

/**
 * A class to access StaffList data stored as a json file on the hard disk.
 */
public class JsonStaffListStorage implements StaffListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonStaffListStorage.class);

    private Path filePath;

    public JsonStaffListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getStaffListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStaffList> readStaffList() throws DataConversionException {
        return readStaffList(filePath);
    }

    /**
     * Similar to {@link #readStaffList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStaffList> readStaffList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStaffList> jsonStaffList = JsonUtil.readJsonFile(
                filePath, JsonSerializableStaffList.class);
        if (!jsonStaffList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonStaffList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStaffList(ReadOnlyStaffList staffList) throws IOException {
        saveStaffList(staffList, filePath);
    }

    /**
     * Similar to {@link #saveStaffList(ReadOnlyStaffList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStaffList(ReadOnlyStaffList staffList, Path filePath) throws IOException {
        requireNonNull(staffList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableStaffList(staffList), filePath);
    }

}
