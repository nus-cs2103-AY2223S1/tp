package seedu.waddle.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.waddle.commons.core.LogsCenter;
import seedu.waddle.commons.exceptions.DataConversionException;
import seedu.waddle.model.ReadOnlyUserPrefs;
import seedu.waddle.model.ReadOnlyWaddle;
import seedu.waddle.model.UserPrefs;

/**
 * Manages storage of Waddle data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final WaddleStorage waddleStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code WaddleStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(WaddleStorage waddleStorage, UserPrefsStorage userPrefsStorage) {
        this.waddleStorage = waddleStorage;
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


    // ================ Waddle methods ==============================

    @Override
    public Path getWaddleFilePath() {
        return waddleStorage.getWaddleFilePath();
    }

    @Override
    public Optional<ReadOnlyWaddle> readWaddle() throws DataConversionException, IOException {
        return readWaddle(waddleStorage.getWaddleFilePath());
    }

    @Override
    public Optional<ReadOnlyWaddle> readWaddle(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return waddleStorage.readWaddle(filePath);
    }

    @Override
    public void saveWaddle(ReadOnlyWaddle waddle) throws IOException {
        saveWaddle(waddle, waddleStorage.getWaddleFilePath());
    }

    @Override
    public void saveWaddle(ReadOnlyWaddle waddle, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        waddleStorage.saveWaddle(waddle, filePath);
    }

}
