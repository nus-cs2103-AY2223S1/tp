package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBuyerBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of BuyerBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private BuyerBookStorage buyerBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private PropertyBookStorage propertyBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code BuyerBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(BuyerBookStorage buyerBookStorage, PropertyBookStorage propertyBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.buyerBookStorage = buyerBookStorage;
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


    // ================ BuyerBook methods ==============================

    @Override
    public Path getBuyerBookFilePath() {
        return buyerBookStorage.getBuyerBookFilePath();
    }

    @Override
    public Optional<ReadOnlyBuyerBook> readBuyerBook() throws DataConversionException, IOException {
        return readBuyerBook(buyerBookStorage.getBuyerBookFilePath());
    }

    @Override
    public Optional<ReadOnlyBuyerBook> readBuyerBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return buyerBookStorage.readBuyerBook(filePath);
    }

    @Override
    public void saveBuyerBook(ReadOnlyBuyerBook buyerBook) throws IOException {
        saveBuyerBook(buyerBook, buyerBookStorage.getBuyerBookFilePath());
    }

    @Override
    public void saveBuyerBook(ReadOnlyBuyerBook buyerBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        buyerBookStorage.saveBuyerBook(buyerBook, filePath);
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
