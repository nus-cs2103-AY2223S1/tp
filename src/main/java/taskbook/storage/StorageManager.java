package taskbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import taskbook.commons.core.LogsCenter;
import taskbook.commons.exceptions.DataConversionException;
import taskbook.model.ReadOnlyTaskBook;
import taskbook.model.ReadOnlyUserPrefs;
import taskbook.model.UserPrefs;

/**
 * Manages storage of TaskBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TaskBookStorage taskBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TaskBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TaskBookStorage taskBookStorage, UserPrefsStorage userPrefsStorage) {
        this.taskBookStorage = taskBookStorage;
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


    // ================ TaskBook methods ==============================

    @Override
    public Path getTaskBookFilePath() {
        return taskBookStorage.getTaskBookFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTaskBook() throws DataConversionException, IOException {
        return readTaskBook(taskBookStorage.getTaskBookFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTaskBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskBookStorage.readTaskBook(filePath);
    }

    @Override
    public void saveTaskBook(ReadOnlyTaskBook taskBook) throws IOException {
        saveTaskBook(taskBook, taskBookStorage.getTaskBookFilePath());
    }

    @Override
    public void saveTaskBook(ReadOnlyTaskBook taskBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskBookStorage.saveTaskBook(taskBook, filePath);
    }

}
