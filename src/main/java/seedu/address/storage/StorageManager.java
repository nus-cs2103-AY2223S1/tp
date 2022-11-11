package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTruthTable;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TruthTable data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TruthTableStorage truthTableStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TruthTableStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TruthTableStorage truthTableStorage, UserPrefsStorage userPrefsStorage) {
        this.truthTableStorage = truthTableStorage;
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


    // ================ TruthTable methods ==============================

    @Override
    public Path getTruthTableFilePath() {
        return truthTableStorage.getTruthTableFilePath();
    }

    @Override
    public Optional<ReadOnlyTruthTable> readTruthTable() throws DataConversionException, IOException {
        return readTruthTable(truthTableStorage.getTruthTableFilePath());
    }

    @Override
    public Optional<ReadOnlyTruthTable> readTruthTable(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return truthTableStorage.readTruthTable(filePath);
    }

    @Override
    public void saveTruthTable(ReadOnlyTruthTable truthTable) throws IOException {
        saveTruthTable(truthTable, truthTableStorage.getTruthTableFilePath());
    }

    @Override
    public void saveTruthTable(ReadOnlyTruthTable truthTable, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        truthTableStorage.saveTruthTable(truthTable, filePath);
    }

}
