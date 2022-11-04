package seedu.taassist.storage;

import static seedu.taassist.commons.core.csv.CsvConfig.CSV_EXTENSION;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.commons.exceptions.DataConversionException;
import seedu.taassist.commons.util.FileUtil;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.ReadOnlyUserPrefs;
import seedu.taassist.model.UserPrefs;

/**
 * Manages storage of TaAssist data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private static final Path USER_DIR = Paths.get(System.getProperty("user.dir")).resolve("data");
    private TaAssistStorage taAssistStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TaAssistStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TaAssistStorage taAssistStorage, UserPrefsStorage userPrefsStorage) {
        this.taAssistStorage = taAssistStorage;
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


    // ================ TaAssist methods ==============================

    @Override
    public Path getTaAssistFilePath() {
        return taAssistStorage.getTaAssistFilePath();
    }

    @Override
    public Optional<ReadOnlyTaAssist> readTaAssist() throws DataConversionException, IOException {
        return readTaAssist(taAssistStorage.getTaAssistFilePath());
    }

    @Override
    public Optional<ReadOnlyTaAssist> readTaAssist(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taAssistStorage.readTaAssist(filePath);
    }

    @Override
    public void saveTaAssist(ReadOnlyTaAssist taAssist) throws IOException {
        saveTaAssist(taAssist, taAssistStorage.getTaAssistFilePath());
    }

    @Override
    public void saveTaAssist(ReadOnlyTaAssist taAssist, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taAssistStorage.saveTaAssist(taAssist, filePath);
    }

    @Override
    public void backupFile(Path filePath) throws IOException {
        if (!FileUtil.isFileExists(filePath)) {
            return;
        }
        Path backupFilePath = filePath.resolveSibling(filePath.getFileName() + ".bak");
        FileUtil.writeToFile(backupFilePath, FileUtil.readFromFile(filePath));
    }

    // ================ Export method ==============================
    @Override
    public void exportAsCsv(String fileName, String fileData) throws IOException {
        Path filePath = USER_DIR.resolve(fileName + CSV_EXTENSION);
        FileUtil.writeToFile(filePath, fileData);
    }
}
