package seedu.foodrem.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.foodrem.commons.core.LogsCenter;
import seedu.foodrem.commons.exceptions.DataConversionException;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.ReadOnlyUserPrefs;
import seedu.foodrem.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final FoodRemStorage foodRemStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FoodRemStorage foodRemStorage, UserPrefsStorage userPrefsStorage) {
        this.foodRemStorage = foodRemStorage;
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getFoodRemFilePath() {
        return foodRemStorage.getFoodRemFilePath();
    }

    @Override
    public Optional<ReadOnlyFoodRem> readFoodRem() throws DataConversionException, IOException {
        return readFoodRem(foodRemStorage.getFoodRemFilePath());
    }

    @Override
    public Optional<ReadOnlyFoodRem> readFoodRem(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return foodRemStorage.readFoodRem(filePath);
    }

    @Override
    public void saveFoodRem(ReadOnlyFoodRem addressBook) throws IOException {
        saveFoodRem(addressBook, foodRemStorage.getFoodRemFilePath());
    }

    @Override
    public void saveFoodRem(ReadOnlyFoodRem addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        foodRemStorage.saveFoodRem(addressBook, filePath);
    }

}
