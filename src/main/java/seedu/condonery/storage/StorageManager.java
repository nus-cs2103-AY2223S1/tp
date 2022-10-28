package seedu.condonery.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.condonery.commons.core.LogsCenter;
import seedu.condonery.commons.exceptions.DataConversionException;
import seedu.condonery.model.ReadOnlyUserPrefs;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.client.ReadOnlyClientDirectory;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;

/**
 * Manages storage of PropertyDirectory data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ClientDirectoryStorage clientDirectoryStorage;
    private PropertyDirectoryStorage propertyDirectoryStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PropertyDirectoryStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PropertyDirectoryStorage propertyDirectoryStorage,
                          ClientDirectoryStorage clientDirectoryStorage, UserPrefsStorage userPrefsStorage) {
        this.clientDirectoryStorage = clientDirectoryStorage;
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

    // ================ ClientDirectory methods ==============================

    @Override
    public Path getClientDirectoryFilePath() {
        return clientDirectoryStorage.getClientDirectoryFilePath();
    }

    @Override
    public Optional<ReadOnlyClientDirectory> readClientDirectory() throws DataConversionException, IOException {
        return readClientDirectory(clientDirectoryStorage.getClientDirectoryFilePath());
    }

    @Override
    public Optional<ReadOnlyClientDirectory> readClientDirectory(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return clientDirectoryStorage.readClientDirectory(filePath);
    }

    @Override
    public void saveClientDirectory(ReadOnlyClientDirectory clientDirectory) throws IOException {
        saveClientDirectory(clientDirectory, clientDirectoryStorage.getClientDirectoryFilePath());
    }

    @Override
    public void saveClientDirectory(ReadOnlyClientDirectory clientDirectory, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        clientDirectoryStorage.saveClientDirectory(clientDirectory, filePath);
    }
}
