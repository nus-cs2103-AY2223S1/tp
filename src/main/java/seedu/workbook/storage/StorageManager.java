package seedu.workbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.workbook.commons.core.LogsCenter;
import seedu.workbook.commons.exceptions.DataConversionException;
import seedu.workbook.model.ReadOnlyUserPrefs;
import seedu.workbook.model.ReadOnlyWorkBook;
import seedu.workbook.model.UserPrefs;

/**
 * Manages storage of WorkBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private WorkBookStorage workBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code WorkBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(WorkBookStorage workBookStorage, UserPrefsStorage userPrefsStorage) {
        this.workBookStorage = workBookStorage;
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


    // ================ WorkBook methods ==============================

    @Override
    public Path getWorkBookFilePath() {
        return workBookStorage.getWorkBookFilePath();
    }

    @Override
    public Optional<ReadOnlyWorkBook> readWorkBook() throws DataConversionException, IOException {
        return readWorkBook(workBookStorage.getWorkBookFilePath());
    }

    @Override
    public Optional<ReadOnlyWorkBook> readWorkBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return workBookStorage.readWorkBook(filePath);
    }

    @Override
    public void saveWorkBook(ReadOnlyWorkBook workBook) throws IOException {
        saveWorkBook(workBook, workBookStorage.getWorkBookFilePath());
    }

    @Override
    public void saveWorkBook(ReadOnlyWorkBook workBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        workBookStorage.saveWorkBook(workBook, filePath);
    }

}
