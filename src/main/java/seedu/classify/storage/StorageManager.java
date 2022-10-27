package seedu.classify.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.classify.commons.core.LogsCenter;
import seedu.classify.commons.exceptions.DataConversionException;
import seedu.classify.model.ReadOnlyStudentRecord;
import seedu.classify.model.ReadOnlyUserPrefs;
import seedu.classify.model.UserPrefs;

/**
 * Manages storage of Class-ify data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private StudentRecordStorage studentRecordStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code StudentRecordStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(StudentRecordStorage studentRecordStorage, UserPrefsStorage userPrefsStorage) {
        this.studentRecordStorage = studentRecordStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ StudentRecord methods ==============================

    @Override
    public Path getStudentRecordFilePath() {
        return studentRecordStorage.getStudentRecordFilePath();
    }

    @Override
    public Optional<ReadOnlyStudentRecord> readStudentRecord() throws DataConversionException, IOException {
        return readStudentRecord(studentRecordStorage.getStudentRecordFilePath());
    }

    @Override
    public Optional<ReadOnlyStudentRecord> readStudentRecord(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return studentRecordStorage.readStudentRecord(filePath);
    }

    @Override
    public void saveStudentRecord(ReadOnlyStudentRecord studentRecord) throws IOException {
        saveStudentRecord(studentRecord, studentRecordStorage.getStudentRecordFilePath());
    }

    @Override
    public void saveStudentRecord(ReadOnlyStudentRecord studentRecord, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        studentRecordStorage.saveStudentRecord(studentRecord, filePath);
    }

}
