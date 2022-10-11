package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyPennyWise;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of PennyWise data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PennyWiseStorage pennyWiseStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PennyWiseStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PennyWiseStorage pennyWiseStorage, UserPrefsStorage userPrefsStorage) {
        this.pennyWiseStorage = pennyWiseStorage;
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


    // ================ PennyWise methods ==============================

    @Override
    public Path getPennyWiseFilePath() {
        return pennyWiseStorage.getPennyWiseFilePath();
    }

    @Override
    public Optional<ReadOnlyPennyWise> readPennyWise() throws DataConversionException, IOException {
        return readPennyWise(pennyWiseStorage.getPennyWiseFilePath());
    }

    @Override
    public Optional<ReadOnlyPennyWise> readPennyWise(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return pennyWiseStorage.readPennyWise(filePath);
    }

    @Override
    public void savePennyWise(ReadOnlyPennyWise pennyWise) throws IOException {
        savePennyWise(pennyWise, pennyWiseStorage.getPennyWiseFilePath());
    }

    @Override
    public void savePennyWise(ReadOnlyPennyWise pennyWise, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        pennyWiseStorage.savePennyWise(pennyWise, filePath);
    }

}
