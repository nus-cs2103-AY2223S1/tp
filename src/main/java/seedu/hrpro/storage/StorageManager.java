package seedu.hrpro.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.hrpro.commons.core.LogsCenter;
import seedu.hrpro.commons.exceptions.DataConversionException;
import seedu.hrpro.model.ReadOnlyHrPro;
import seedu.hrpro.model.ReadOnlyUserPrefs;
import seedu.hrpro.model.UserPrefs;

/**
 * Manages storage of HrPro data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HrProStorage hrProStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code HrProStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HrProStorage hrProStorage, UserPrefsStorage userPrefsStorage) {
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


    // ================ HrPro methods ==============================

    @Override
    public Path getHrProFilePath() {
        return hrProStorage.getHrProFilePath();
    }

    @Override
    public Optional<ReadOnlyHrPro> readHrPro() throws DataConversionException, IOException {
        return readHrPro(hrProStorage.getHrProFilePath());
    }

    @Override
    public Optional<ReadOnlyHrPro> readHrPro(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return hrProStorage.readHrPro(filePath);
    }

    @Override
    public void saveHrPro(ReadOnlyHrPro hrPro) throws IOException {
        saveHrPro(hrPro, hrProStorage.getHrProFilePath());
    }

    @Override
    public void saveHrPro(ReadOnlyHrPro hrPro, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        hrProStorage.saveHrPro(hrPro, filePath);
    }

}
