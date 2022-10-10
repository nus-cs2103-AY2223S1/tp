package seedu.nutrigoals.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.nutrigoals.commons.core.LogsCenter;
import seedu.nutrigoals.commons.exceptions.DataConversionException;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.ReadOnlyUserPrefs;
import seedu.nutrigoals.model.UserPrefs;

/**
 * Manages storage of NutriGoals data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private NutriGoalsStorage nutriGoalsStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code NutriGoalsStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(NutriGoalsStorage nutriGoalsStorage, UserPrefsStorage userPrefsStorage) {
        this.nutriGoalsStorage = nutriGoalsStorage;
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


    // ================ NutriGoals methods ==============================

    @Override
    public Path getNutriGoalsFilePath() {
        return nutriGoalsStorage.getNutriGoalsFilePath();
    }

    @Override
    public Optional<ReadOnlyNutriGoals> readNutriGoals() throws DataConversionException, IOException {
        return readNutriGoals(nutriGoalsStorage.getNutriGoalsFilePath());
    }

    @Override
    public Optional<ReadOnlyNutriGoals> readNutriGoals(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        Optional<ReadOnlyNutriGoals> output = nutriGoalsStorage.readNutriGoals(filePath);
        return output;
    }

    @Override
    public void saveNutriGoals(ReadOnlyNutriGoals nutriGoals) throws IOException {
        saveNutriGoals(nutriGoals, nutriGoalsStorage.getNutriGoalsFilePath());
    }

    @Override
    public void saveNutriGoals(ReadOnlyNutriGoals nutriGoals, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        nutriGoalsStorage.saveNutriGoals(nutriGoals, filePath);
    }

}
