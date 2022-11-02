package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyHealthContact;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of HealthContact data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HealthContactStorage healthContactStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code HealthContactStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HealthContactStorage healthContactStorage, UserPrefsStorage userPrefsStorage) {
        this.healthContactStorage = healthContactStorage;
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


    // ================ HealthContact methods ==============================

    @Override
    public Path getHealthContactFilePath() {
        return healthContactStorage.getHealthContactFilePath();
    }

    @Override
    public Optional<ReadOnlyHealthContact> readHealthContact() throws DataConversionException, IOException {
        return readHealthContact(healthContactStorage.getHealthContactFilePath());
    }

    @Override
    public Optional<ReadOnlyHealthContact> readHealthContact(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return healthContactStorage.readHealthContact(filePath);
    }

    @Override
    public void saveHealthContact(ReadOnlyHealthContact healthContact) throws IOException {
        saveHealthContact(healthContact, healthContactStorage.getHealthContactFilePath());
    }

    @Override
    public void saveHealthContact(ReadOnlyHealthContact healthContact, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        healthContactStorage.saveHealthContact(healthContact, filePath);
    }

}
