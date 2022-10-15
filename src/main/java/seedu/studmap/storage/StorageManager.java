package seedu.studmap.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.studmap.commons.core.LogsCenter;
import seedu.studmap.commons.exceptions.DataConversionException;
import seedu.studmap.model.ReadOnlyStudMap;
import seedu.studmap.model.ReadOnlyUserPrefs;
import seedu.studmap.model.UserPrefs;

/**
 * Manages storage of StudMap data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private StudMapStorage studMapStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code StudMapStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(StudMapStorage studMapStorage, UserPrefsStorage userPrefsStorage) {
        this.studMapStorage = studMapStorage;
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


    // ================ StudMap methods ==============================

    @Override
    public Path getStudMapFilePath() {
        return studMapStorage.getStudMapFilePath();
    }

    @Override
    public Optional<ReadOnlyStudMap> readStudMap() throws DataConversionException, IOException {
        return readStudMap(studMapStorage.getStudMapFilePath());
    }

    @Override
    public Optional<ReadOnlyStudMap> readStudMap(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return studMapStorage.readStudMap(filePath);
    }

    @Override
    public void saveStudMap(ReadOnlyStudMap studMap) throws IOException {
        saveStudMap(studMap, studMapStorage.getStudMapFilePath());
    }

    @Override
    public void saveStudMap(ReadOnlyStudMap studMap, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        studMapStorage.saveStudMap(studMap, filePath);
    }

}
