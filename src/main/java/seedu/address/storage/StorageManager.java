package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyProfNus;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ProfNus data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ProfNusStorage profNusStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ProfNusStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ProfNusStorage profNusStorage, UserPrefsStorage userPrefsStorage) {
        this.profNusStorage = profNusStorage;
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


    // ================ ProfNus methods ==============================

    @Override
    public Path getProfNusFilePath() {
        return profNusStorage.getProfNusFilePath();
    }

    @Override
    public Optional<ReadOnlyProfNus> readProfNus() throws DataConversionException, IOException {
        return readProfNus(profNusStorage.getProfNusFilePath());
    }

    @Override
    public Optional<ReadOnlyProfNus> readProfNus(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return profNusStorage.readProfNus(filePath);
    }

    @Override
    public void saveProfNus(ReadOnlyProfNus profNus) throws IOException {
        saveProfNus(profNus, profNusStorage.getProfNusFilePath());
    }

    @Override
    public void saveProfNus(ReadOnlyProfNus profNus, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        profNusStorage.saveProfNus(profNus, filePath);
    }

}
