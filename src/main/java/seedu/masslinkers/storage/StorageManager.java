package seedu.masslinkers.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.masslinkers.commons.core.LogsCenter;
import seedu.masslinkers.commons.exceptions.DataConversionException;
import seedu.masslinkers.model.ReadOnlyMassLinkers;
import seedu.masslinkers.model.ReadOnlyUserPrefs;
import seedu.masslinkers.model.UserPrefs;
//@@author
/**
 * Manages storage of MassLinkers data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private MassLinkersStorage massLinkersStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code MassLinkersStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(MassLinkersStorage massLinkersStorage, UserPrefsStorage userPrefsStorage) {
        this.massLinkersStorage = massLinkersStorage;
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


    // ================ MassLinkers methods ==============================

    @Override
    public Path getMassLinkersFilePath() {
        return massLinkersStorage.getMassLinkersFilePath();
    }

    @Override
    public Optional<ReadOnlyMassLinkers> readMassLinkers() throws DataConversionException, IOException {
        return readMassLinkers(massLinkersStorage.getMassLinkersFilePath());
    }

    @Override
    public Optional<ReadOnlyMassLinkers> readMassLinkers(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return massLinkersStorage.readMassLinkers(filePath);
    }

    @Override
    public void saveMassLinkers(ReadOnlyMassLinkers massLinkers) throws IOException {
        saveMassLinkers(massLinkers, massLinkersStorage.getMassLinkersFilePath());
    }

    @Override
    public void saveMassLinkers(ReadOnlyMassLinkers massLinkers, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        massLinkersStorage.saveMassLinkers(massLinkers, filePath);
    }

}
