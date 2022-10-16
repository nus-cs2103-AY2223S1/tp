package seedu.uninurse.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.uninurse.commons.core.LogsCenter;
import seedu.uninurse.commons.exceptions.DataConversionException;
import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.ReadOnlyUserPrefs;
import seedu.uninurse.model.UserPrefs;

/**
 * Manages storage of UninurseBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private UninurseBookStorage uninurseBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code UninurseBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(UninurseBookStorage uninurseBookStorage, UserPrefsStorage userPrefsStorage) {
        this.uninurseBookStorage = uninurseBookStorage;
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


    // ================ UninurseBook methods ==============================

    @Override
    public Path getUninurseBookFilePath() {
        return uninurseBookStorage.getUninurseBookFilePath();
    }

    @Override
    public Optional<ReadOnlyUninurseBook> readUninurseBook() throws DataConversionException, IOException {
        return readUninurseBook(uninurseBookStorage.getUninurseBookFilePath());
    }

    @Override
    public Optional<ReadOnlyUninurseBook> readUninurseBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return uninurseBookStorage.readUninurseBook(filePath);
    }

    @Override
    public void saveUninurseBook(ReadOnlyUninurseBook uninurseBook) throws IOException {
        saveUninurseBook(uninurseBook, uninurseBookStorage.getUninurseBookFilePath());
    }

    @Override
    public void saveUninurseBook(ReadOnlyUninurseBook uninurseBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        uninurseBookStorage.saveUninurseBook(uninurseBook, filePath);
    }

}
