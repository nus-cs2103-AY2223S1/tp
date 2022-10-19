package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTeachersPet;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TeachersPet data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TeachersPetStorage teachersPetStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TeachersPetStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TeachersPetStorage teachersPetStorage, UserPrefsStorage userPrefsStorage) {
        this.teachersPetStorage = teachersPetStorage;
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

    // ================ TeachersPet methods ==============================

    @Override
    public Path getTeachersPetFilePath() {
        return teachersPetStorage.getTeachersPetFilePath();
    }

    @Override
    public Optional<ReadOnlyTeachersPet> readTeachersPet() throws DataConversionException, IOException {
        return readTeachersPet(teachersPetStorage.getTeachersPetFilePath());
    }

    @Override
    public Optional<ReadOnlyTeachersPet> readTeachersPet(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return teachersPetStorage.readTeachersPet(filePath);
    }

    @Override
    public void saveTeachersPet(ReadOnlyTeachersPet teachersPet) throws IOException {
        saveTeachersPet(teachersPet, teachersPetStorage.getTeachersPetFilePath());
    }

    @Override
    public void saveTeachersPet(ReadOnlyTeachersPet teachersPet, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        teachersPetStorage.saveTeachersPet(teachersPet, filePath);
    }

}
