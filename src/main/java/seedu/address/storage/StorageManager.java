package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyPersonBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of PersonBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PersonBookStorage personBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private PropertyBookStorage propertyBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PersonBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PersonBookStorage personBookStorage, PropertyBookStorage propertyBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.personBookStorage = personBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.propertyBookStorage = propertyBookStorage;
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


    // ================ PersonBook methods ==============================

    @Override
    public Path getPersonBookFilePath() {
        return personBookStorage.getPersonBookFilePath();
    }

    @Override
    public Optional<ReadOnlyPersonBook> readPersonBook() throws DataConversionException, IOException {
        return readPersonBook(personBookStorage.getPersonBookFilePath());
    }

    @Override
    public Optional<ReadOnlyPersonBook> readPersonBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return personBookStorage.readPersonBook(filePath);
    }

    @Override
    public void savePersonBook(ReadOnlyPersonBook personBook) throws IOException {
        savePersonBook(personBook, personBookStorage.getPersonBookFilePath());
    }

    @Override
    public void savePersonBook(ReadOnlyPersonBook personBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        personBookStorage.savePersonBook(personBook, filePath);
    }

    // ================ PropertyBook methods ==============================

    @Override
    public Path getPropertyBookFilePath() {
        return propertyBookStorage.getPropertyBookFilePath();
    }

    @Override
    public Optional<ReadOnlyPropertyBook> readPropertyBook() throws DataConversionException, IOException {
        return readPropertyBook(propertyBookStorage.getPropertyBookFilePath());
    }

    @Override
    public Optional<ReadOnlyPropertyBook> readPropertyBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return propertyBookStorage.readPropertyBook(filePath);
    }

    @Override
    public void savePropertyBook(ReadOnlyPropertyBook propertyBook) throws IOException {
        savePropertyBook(propertyBook, propertyBookStorage.getPropertyBookFilePath());
    }

    @Override
    public void savePropertyBook(ReadOnlyPropertyBook propertyBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        propertyBookStorage.savePropertyBook(propertyBook, filePath);
    }

}
