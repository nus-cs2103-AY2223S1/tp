package eatwhere.foodguide.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import eatwhere.foodguide.commons.core.LogsCenter;
import eatwhere.foodguide.commons.exceptions.DataConversionException;
import eatwhere.foodguide.model.ReadOnlyAddressBook;
import eatwhere.foodguide.model.ReadOnlyUserPrefs;
import eatwhere.foodguide.model.UserPrefs;

/**
 * Manages storage of FoodGuide data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FoodGuideStorage foodGuideStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FoodGuideStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FoodGuideStorage foodGuideStorage, UserPrefsStorage userPrefsStorage) {
        this.foodGuideStorage = foodGuideStorage;
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


    // ================ FoodGuide methods ==============================

    @Override
    public Path getFoodGuideFilePath() {
        return foodGuideStorage.getFoodGuideFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readFoodGuide() throws DataConversionException, IOException {
        return readFoodGuide(foodGuideStorage.getFoodGuideFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readFoodGuide(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return foodGuideStorage.readFoodGuide(filePath);
    }

    @Override
    public void saveFoodGuide(ReadOnlyAddressBook foodGuide) throws IOException {
        saveFoodGuide(foodGuide, foodGuideStorage.getFoodGuideFilePath());
    }

    @Override
    public void saveFoodGuide(ReadOnlyAddressBook foodGuide, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        foodGuideStorage.saveFoodGuide(foodGuide, filePath);
    }

}
