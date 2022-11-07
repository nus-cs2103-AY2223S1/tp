package seedu.modquik.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.modquik.commons.core.LogsCenter;
import seedu.modquik.commons.exceptions.DataConversionException;
import seedu.modquik.model.ReadOnlyModQuik;
import seedu.modquik.model.ReadOnlyUserPrefs;
import seedu.modquik.model.UserPrefs;

/**
 * Manages storage of ModQuik data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ModQuikStorage modQuikStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ModQuikStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ModQuikStorage modQuikStorage, UserPrefsStorage userPrefsStorage) {
        this.modQuikStorage = modQuikStorage;
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


    // ================ ModQuik methods ==============================

    @Override
    public Path getModQuikFilePath() {
        return modQuikStorage.getModQuikFilePath();
    }

    @Override
    public Optional<ReadOnlyModQuik> readModQuik() throws DataConversionException, IOException {
        return readModQuik(modQuikStorage.getModQuikFilePath());
    }

    @Override
    public Optional<ReadOnlyModQuik> readModQuik(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return modQuikStorage.readModQuik(filePath);
    }

    @Override
    public void saveModQuik(ReadOnlyModQuik modQuik) throws IOException {
        saveModQuik(modQuik, modQuikStorage.getModQuikFilePath());
    }

    @Override
    public void saveModQuik(ReadOnlyModQuik modQuik, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        modQuikStorage.saveModQuik(modQuik, filePath);
    }

}
