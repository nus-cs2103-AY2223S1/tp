package seedu.hrpro.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.hrpro.commons.core.LogsCenter;
import seedu.hrpro.commons.exceptions.DataConversionException;
import seedu.hrpro.model.ReadOnlyHRPro;
import seedu.hrpro.model.ReadOnlyUserPrefs;
import seedu.hrpro.model.UserPrefs;

/**
 * Manages storage of HRPro data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HRProStorage hrProStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code HRProStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HRProStorage hrProStorage, UserPrefsStorage userPrefsStorage) {
        this.hrProStorage = hrProStorage;
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


    // ================ HRPro methods ==============================

    @Override
    public Path getHRProFilePath() {
        return hrProStorage.getHRProFilePath();
    }

    @Override
    public Optional<ReadOnlyHRPro> readHRPro() throws DataConversionException, IOException {
        return readHRPro(hrProStorage.getHRProFilePath());
    }

    @Override
    public Optional<ReadOnlyHRPro> readHRPro(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return hrProStorage.readHRPro(filePath);
    }

    @Override
    public void saveHRPro(ReadOnlyHRPro hrPro) throws IOException {
        saveHRPro(hrPro, hrProStorage.getHRProFilePath());
    }

    @Override
    public void saveHRPro(ReadOnlyHRPro hrPro, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        hrProStorage.saveHRPro(hrPro, filePath);
    }

}
