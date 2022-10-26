package seedu.classify.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.classify.commons.core.LogsCenter;
import seedu.classify.commons.exceptions.DataConversionException;
import seedu.classify.commons.exceptions.IllegalValueException;
import seedu.classify.commons.util.FileUtil;
import seedu.classify.commons.util.JsonUtil;
import seedu.classify.model.ReadOnlyStudentRecord;

/**
 * A class to access StudentRecord data stored as a json file on the hard disk.
 */
public class JsonStudentRecordStorage implements StudentRecordStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonStudentRecordStorage.class);

    private Path filePath;

    public JsonStudentRecordStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getStudentRecordFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStudentRecord> readStudentRecord() throws DataConversionException {
        return readStudentRecord(filePath);
    }

    /**
     * Similar to {@link #readStudentRecord()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStudentRecord> readStudentRecord(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStudentRecord> jsonStudentRecord = JsonUtil.readJsonFile(
                filePath, JsonSerializableStudentRecord.class);
        if (!jsonStudentRecord.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonStudentRecord.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStudentRecord(ReadOnlyStudentRecord studentRecord) throws IOException {
        saveStudentRecord(studentRecord, filePath);
    }

    /**
     * Similar to {@link #saveStudentRecord(ReadOnlyStudentRecord)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStudentRecord(ReadOnlyStudentRecord studentRecord, Path filePath) throws IOException {
        requireNonNull(studentRecord);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableStudentRecord(studentRecord), filePath);
    }

}
