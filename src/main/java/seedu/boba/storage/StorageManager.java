package seedu.boba.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.boba.commons.core.LogsCenter;
import seedu.boba.commons.exceptions.DataConversionException;
import seedu.boba.model.ReadOnlyBobaBot;
import seedu.boba.model.ReadOnlyUserPrefs;
import seedu.boba.model.UserPrefs;

/**
 * Manages storage of BobaBot data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private BobaBotStorage bobaBotStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code BobaBotStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(BobaBotStorage bobaBotStorage, UserPrefsStorage userPrefsStorage) {
        this.bobaBotStorage = bobaBotStorage;
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


    // ================ BobaBot methods ==============================

    @Override
    public Path getBobaBotFilePath() {
        return bobaBotStorage.getBobaBotFilePath();
    }

    @Override
    public Optional<ReadOnlyBobaBot> readBobaBot() throws DataConversionException, IOException {
        return readBobaBot(bobaBotStorage.getBobaBotFilePath());
    }

    @Override
    public Optional<ReadOnlyBobaBot> readBobaBot(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return bobaBotStorage.readBobaBot(filePath);
    }

    @Override
    public void saveBobaBot(ReadOnlyBobaBot bobaBot) throws IOException {
        saveBobaBot(bobaBot, bobaBotStorage.getBobaBotFilePath());
    }

    @Override
    public void saveBobaBot(ReadOnlyBobaBot bobaBot, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        bobaBotStorage.saveBobaBot(bobaBot, filePath);
    }

}
