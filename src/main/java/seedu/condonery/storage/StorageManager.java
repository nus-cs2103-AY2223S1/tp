package seedu.condonery.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.condonery.commons.core.LogsCenter;
import seedu.condonery.commons.exceptions.DataConversionException;
import seedu.condonery.model.ReadOnlyPropertyDirectory;
import seedu.condonery.model.ReadOnlyUserPrefs;
import seedu.condonery.model.UserPrefs;

/**
 * Manages storage of PropertyDirectory data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PropertyDirectoryStorage propertyDirectoryStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PropertyDirectoryStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PropertyDirectoryStorage propertyDirectoryStorage, UserPrefsStorage userPrefsStorage) {
        this.propertyDirectoryStorage = propertyDirectoryStorage;
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


    // ================ PropertyDirectory methods ==============================

    @Override
    public Path getPropertyDirectoryFilePath() {
        return propertyDirectoryStorage.getPropertyDirectoryFilePath();
    }

    @Override
    public Optional<ReadOnlyPropertyDirectory> readPropertyDirectory() throws DataConversionException, IOException {
        return readPropertyDirectory(propertyDirectoryStorage.getPropertyDirectoryFilePath());
    }

    @Override
    public Optional<ReadOnlyPropertyDirectory> readPropertyDirectory(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return propertyDirectoryStorage.readPropertyDirectory(filePath);
    }

    @Override
    public void savePropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory) throws IOException {
        savePropertyDirectory(propertyDirectory, propertyDirectoryStorage.getPropertyDirectoryFilePath());
    }

    @Override
    public void savePropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        propertyDirectoryStorage.savePropertyDirectory(propertyDirectory, filePath);
    }

}
