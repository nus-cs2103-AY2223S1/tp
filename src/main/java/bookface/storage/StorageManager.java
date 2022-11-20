package bookface.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import bookface.commons.core.LogsCenter;
import bookface.commons.exceptions.DataConversionException;
import bookface.model.ReadOnlyBookFace;
import bookface.model.ReadOnlyUserPrefs;
import bookface.model.UserPrefs;

/**
 * Manages storage of BookFace data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final BookFaceStorage bookFaceStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code BookFaceStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(BookFaceStorage bookFaceStorage, UserPrefsStorage userPrefsStorage) {
        this.bookFaceStorage = bookFaceStorage;
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


    // ================ BookFace methods ==============================

    @Override
    public Path getBookFaceFilePath() {
        return bookFaceStorage.getBookFaceFilePath();
    }

    @Override
    public Optional<ReadOnlyBookFace> readBookFace() throws DataConversionException, IOException {
        return readBookFace(bookFaceStorage.getBookFaceFilePath());
    }

    @Override
    public Optional<ReadOnlyBookFace> readBookFace(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return bookFaceStorage.readBookFace(filePath);
    }

    @Override
    public void saveBookFace(ReadOnlyBookFace bookFace) throws IOException {
        saveBookFace(bookFace, bookFaceStorage.getBookFaceFilePath());
    }

    @Override
    public void saveBookFace(ReadOnlyBookFace bookFace, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        bookFaceStorage.saveBookFace(bookFace, filePath);
    }

}
